package com.carbon.game.treasuremap.utils;

import com.carbon.game.treasuremap.model.Adventurer;
import com.carbon.game.treasuremap.model.EntityCase;
import com.carbon.game.treasuremap.model.GameMap;

import java.util.*;
import java.util.stream.Collectors;

public class OutputFormatter {

    public static final String SEPARATOR = " - ";
    public static final String TABULATION = "\u0009";

    public static String formatState(GameMap gameMap) {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < gameMap.getHeight(); y++) {

            for (int x = 0; x < gameMap.getLength(); x++) {
                Adventurer adventurer = (Adventurer) gameMap.getAdventurer(x, y);
                EntityCase entityCase = gameMap.getEntity(x, y);

                if (Objects.isNull(adventurer) && Objects.isNull(entityCase)) {
                    sb.append(".");
                } else {
                    sb.append(Objects.nonNull(entityCase) ? stateFormatEntity(entityCase) : "");
                    sb.append(Objects.nonNull(adventurer) ? stateFormatEntity(adventurer) : "");
                }

                if (x < gameMap.getLength() - 1) {
                    sb.append(TABULATION); // tabulation
                }
            }

            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    private static String stateFormatEntity(EntityCase entityCase) {
        Objects.requireNonNull(entityCase);
        switch (entityCase.getType()) {
            case A:
                Adventurer adventurer = (Adventurer) entityCase;
                return adventurer.getType() + "(" + adventurer.getName() + ")";
            case T:
                return entityCase.getType() + "(" + entityCase.getTreasureNumber() + ")";
            case M:
                return entityCase.getType().toString();
            default:
                return ".";
        }
    }

    /**
     * Formater la sortie
     *
     * @param gameMap
     * @return
     */
    public static String formatOutput(GameMap gameMap) {
        Objects.requireNonNull(gameMap);
        List<EntityCase> entities = new ArrayList<>();

        List<EntityCase> list = gameMap.getEntityCases().values().stream().flatMap(col -> col.values().stream()).collect(Collectors.toList());
        entities.addAll(list);

        Comparator<EntityCase> entityCaseComparator = Comparator.comparing(EntityCase::getType).thenComparingInt(EntityCase::getX).thenComparingInt(EntityCase::getY);
        Collections.sort(entities, entityCaseComparator);

        entities.addAll(gameMap.getAdventurers());

        StringBuilder sb = new StringBuilder();
        sb.append(outputFormatMap(gameMap)).append(System.lineSeparator());

        boolean isTreasure = false;
        boolean isAdventurer = false;
        boolean shouldAppend = true;
        for (int i = 0; i < entities.size(); i++) {
            EntityCase entityCase = entities.get(i);
            switch (entityCase.getType()) {
                case M:
                    shouldAppend = true;
                    sb.append(outputFormatEntity(entityCase));
                    break;
                case T:

                    if (!isTreasure) {
                        isTreasure = true;
                        sb.append("# {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors restants}").append(System.lineSeparator());
                        shouldAppend = false;
                    }

                    if (entityCase.getTreasureNumber() > 0) {
                        shouldAppend = true;
                        sb.append(outputFormatEntity(entityCase));
                    }
                    break;
                case A:
                    if (!isAdventurer) {
                        isAdventurer = true;
                        sb.append("# {A comme Aventurier} - {Nom de l’aventurier} - {Axe horizontal} - {Axe vertical} - {Orientation} - {Nb. trésors ramassés}").append(System.lineSeparator());
                        shouldAppend = false;
                    }
                    shouldAppend = true;
                    sb.append(outputFormatEntity(entityCase));
                    break;
                default:
                    throw new IllegalArgumentException("Le type " + entityCase.getType() + " n'est pas implémenté.");
            }

            if (i < entities.size() - 1 && shouldAppend) {
                sb.append(System.lineSeparator());
            }
        }

        return sb.toString();
    }

    private static String outputFormatEntity(EntityCase entityCase) {
        Objects.requireNonNull(entityCase);
        switch (entityCase.getType()) {
            case A:
                Adventurer adventurer = (Adventurer) entityCase;
                return adventurer.getType() + SEPARATOR + adventurer.getName() + SEPARATOR + adventurer.getX() + SEPARATOR + adventurer.getY() + SEPARATOR + adventurer.getOrientation().toString() + SEPARATOR + adventurer.getTreasureNumber();
            case T:
                return entityCase.getType() + SEPARATOR + entityCase.getX() + SEPARATOR + entityCase.getY() + SEPARATOR + entityCase.getTreasureNumber();
            case M:
                return entityCase.getType().toString() + SEPARATOR + entityCase.getX() + SEPARATOR + entityCase.getY();
            default:
                return "#";
        }
    }

    private static String outputFormatMap(GameMap gameMap) {
        return "C - " + gameMap.getLength() + SEPARATOR + gameMap.getHeight();
    }

}
