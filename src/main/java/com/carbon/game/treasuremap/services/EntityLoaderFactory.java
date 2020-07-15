package com.carbon.game.treasuremap.services;

import com.carbon.game.treasuremap.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.regex.Pattern;

@Component
@Slf4j
public class EntityLoaderFactory extends AbstractFactory {

    private final Pattern adventurerPattern = Pattern.compile("^A - [A-Z][a-z]* - [0-9] - [0-9] - [ENSO] - [ADG]*$");
    private final Pattern mountainPattern = Pattern.compile("^M - [0-9] - [0-9]$");
    private final Pattern treasurePattern = Pattern.compile("^T - [0-9] - [0-9] - [0-9]$");


    /**
     * Charger une entité à partir d'une ligne.
     * @param line
     * @return
     */
    public EntityCase getEntityCase(String line) {

        if (this.isAdventurer(line)) {
            log.debug("La ligne est un aventurier : {}", line);
            return this.getAdventurer(line);
        } else if (this.isMountain(line)) {
            log.debug("La ligne est une montagne : {}", line);
            return this.getMountain(line);
        } else if (this.isTreasure(line)) {
            log.debug("La ligne est un trésor : {}", line);
            return this.getTreasure(line);
        } else {
            throw new IllegalArgumentException("Le format de la ligne n'est pas autorisé : " + line);
        }
    }

    private Treasure getTreasure(String line) {
        String[] data = this.splitLine(line);
        return new Treasure(Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]));
    }

    private Mountain getMountain(String line) {
        String[] data = this.splitLine(line);
        return new Mountain(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
    }

    public boolean isAdventurer(String line) {
        Objects.requireNonNull(line);
        return this.adventurerPattern.matcher(line).matches();
        
    }

    public boolean isMountain(String line) {
        Objects.requireNonNull(line);
        return this.mountainPattern.matcher(line).matches();
    }

    public boolean isTreasure(String line) {
        Objects.requireNonNull(line);
        return this.treasurePattern.matcher(line).matches();
    }

    private Adventurer getAdventurer(String line) {
        String[] data = this.splitLine(line);
        return new Adventurer(Integer.parseInt(data[2]), Integer.parseInt(data[3]), data[1], Orientation.valueOf(data[4]), Movement.getMovements(data[5]));
    }
}
