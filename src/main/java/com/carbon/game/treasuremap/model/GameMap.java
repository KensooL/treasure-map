package com.carbon.game.treasuremap.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Getter
@RequiredArgsConstructor
@Slf4j
public class GameMap {

    private final int length; // Nombre de case en largeur
    private final int height; // Nombre de case en hauteur

    private int actionNumber = 0;

    private final Map<Integer, Map<Integer, EntityCase>> entityCases = new HashMap<>(); // Position des trésors et montagnes sur la carte.
    private final Map<Integer, Map<Integer, EntityCase>> adventurerCases = new HashMap<>(); // Position des aventuriers sur la carte.
    private final List<Adventurer> adventurers = new ArrayList<>(); // Liste pour l'ordre d'apparition.

    /**
     * Récupérer une entité de la carte
     * @param x
     * @param y
     * @return
     */
    public EntityCase getEntity(int x, int y) {
        return this.getEntity(this.entityCases, x, y);
    }

    /**
     * Récupérer un aventurier de la carte.
     * @param x
     * @param y
     * @return
     */
    public EntityCase getAdventurer(int x, int y) {
        return this.getEntity(this.adventurerCases, x, y);
    }

    public List<Adventurer> getAdventurers() {
        return Collections.unmodifiableList(this.adventurers);
    }

    /**
     * Récupérer l'entité de la map spécifiée.
     * @param map
     * @param x
     * @param y
     * @return
     */
    private EntityCase getEntity(Map<Integer, Map<Integer, EntityCase>> map, int x, int y) {

        EntityCase entityCase = null;

        // On récuprère la colonne
        Map<Integer, EntityCase> colCases = map.get(x);

        if (Objects.nonNull(colCases)) {
            entityCase = colCases.get(y);
        }
        return entityCase;
    }

    /**
     * Ajout des entités à la carte.
     *
     * @param entityCase
     */
    public void addEntityCase(EntityCase entityCase) {
        if (Objects.nonNull(entityCase)) {
            switch (entityCase.getType()) {
                case A:
                    this.insertInMap(this.adventurerCases, entityCase.getX(), entityCase.getY(), entityCase);
                    Adventurer adventurer = (Adventurer) entityCase;
                    this.adventurers.add(adventurer);
                    if (this.actionNumber < adventurer.getMovements().length) {
                        this.actionNumber = adventurer.getMovements().length;
                    }
                    break;

                case M:
                case T:
                    this.insertInMap(this.entityCases, entityCase.getX(), entityCase.getY(), entityCase);
                    break;

                default:
                    throw new IllegalArgumentException("Le type de l'entité n'est pas pris en charge : " + entityCase.getType());
            }
        }
    }

    /**
     * Insérer un entité dans la map spécifiée.
     * @param map
     * @param x
     * @param y
     * @param entityCase
     */
    private void insertInMap(Map<Integer, Map<Integer, EntityCase>> map, int x, int y, EntityCase entityCase) {
        if (x >= this.length) {
            throw new IllegalArgumentException("La position x de l'entité est en dehors des limites");
        }

        if (y >= this.height) {
            throw new IllegalArgumentException("La position y de l'entité est en dehors des limites");
        }

        if (Objects.isNull(map.get(x))) {
            map.put(x, new HashMap<>());
        } else if (Objects.nonNull(map.get(x).get(y))) {
            throw new IllegalArgumentException("Une entité existe aux coordonnées : x=" + x + " y=" + y);
        }
        map.get(x).put(y, entityCase);
    }

    /**
     * Déplacer l'aventurier sur la carte.
     *
     * @param adventurer
     * @param x
     * @param y
     */
    public void moveAdventurer(EntityCase adventurer, int x, int y) {
        if (!EntityType.A.equals(adventurer.getType())) {
            throw new IllegalArgumentException("Ce n'est pas un aventurier. EntityType=" + adventurer.getType());
        }

        if (this.canMoveOnCase(x, y)) {
            this.moveAdventurerOnMap(x, y, adventurer);
        } else {
            log.debug("L'aventurier ne peut pas se déplacer sur la case x={}, y={}, entity={}, aventurier={}", x,y, this.getEntity(x,y), this.getAdventurer(x,y));
        }
    }

    /**
     * Déplacer l'aventurier et enregistrer son déplacement.
     * @param x
     * @param y
     * @param adventurer
     */
    private void moveAdventurerOnMap(int x, int y, EntityCase adventurer) {

        if (this.isTreasureCase(x, y)) {
            EntityCase treasure = this.getEntity(x, y);
            if (treasure.getTreasureNumber() > 0) {
                log.debug("L'aventurier récupère un tresor : {}", treasure);
                treasure.setTreasureNumber(treasure.getTreasureNumber() - 1);
                adventurer.setTreasureNumber(adventurer.getTreasureNumber() + 1);
            }

        }

        this.removeAdventurer(adventurer.getX(), adventurer.getY());
        adventurer.setX(x);
        adventurer.setY(y);

        this.insertInMap(this.adventurerCases, x, y, adventurer);
    }

    /**
     * Supprimer la référence d'un aventurier à une position
     * @param x
     * @param y
     */
    private void removeAdventurer(int x, int y) {
        if (Objects.nonNull(this.adventurerCases.get(x))) {
            this.adventurerCases.get(x).remove(y);
        }
    }

    /**
     * On peut se déplacer sur la case seulement si c'est un trésor ou une plaine et que ses coordonnées sont dans les limites de la carte.
     *
     * @param x
     * @param y
     * @return
     */
    private boolean canMoveOnCase(int x, int y) {
        boolean canMove = true;
        EntityCase entity = this.getEntity(x, y);
        EntityCase adventurer = this.getAdventurer(x, y);
        return x >= 0 && x < this.length && y >= 0 && y < this.height && (Objects.isNull(entity) || this.isTreasureCase(x, y)) && Objects.isNull(adventurer);
    }

    private boolean isTreasureCase(int x, int y) {
        EntityCase entity = this.getEntity(x, y);
        return Objects.nonNull(entity) && EntityType.T.equals(entity.getType());
    }

}
