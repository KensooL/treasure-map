package com.carbon.game.treasuremap.services;

import com.carbon.game.treasuremap.model.GameMap;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.regex.Pattern;

@Component
public class GameMapLoaderFactory extends AbstractFactory{

    private final Pattern mapPattern = Pattern.compile("^C - [0-9] - [0-9]$");

    /**
     * Charger une map à partir d'une ligne.
     * @param line
     * @return
     */
    public GameMap getGameMap(String line) {
        if(!this.isGameMap(line))  throw new IllegalArgumentException("Le format de la ligne n'est pas autorisé : " + line);
        String[] data = this.splitLine(line);
        return  new GameMap(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
    }

    public boolean isGameMap(String line){
        Objects.requireNonNull(line);
        return this.mapPattern.matcher(line).matches();
    }
}
