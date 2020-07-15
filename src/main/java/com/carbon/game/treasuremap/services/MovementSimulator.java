package com.carbon.game.treasuremap.services;

import com.carbon.game.treasuremap.model.GameMap;
import com.carbon.game.treasuremap.model.Movement;
import com.carbon.game.treasuremap.model.Orientation;
import com.carbon.game.treasuremap.utils.MovementToOrientation;
import org.springframework.stereotype.Component;

@Component
public class MovementSimulator {

    public void simulate(GameMap gameMap) {

        for (int i = 0; i < gameMap.getActionNumber(); i++) {
            int index = i;
            gameMap.getAdventurers().forEach(
                    adventurer -> {
                        int x = adventurer.getX();
                        int y = adventurer.getY();
                        Movement[] movements = adventurer.getMovements();
                        Orientation orientation = adventurer.getOrientation();

                        Movement movement = movements[index];

                        Orientation nextOrientation;
                        int nextX = x;
                        int nextY = y;

                        nextOrientation = MovementToOrientation.getNextOrientation(orientation, movement);

                        adventurer.setOrientation(nextOrientation);

                        if(Movement.A.equals(movement)) {
                            switch (nextOrientation.getAxe()) {
                                case X:
                                    nextX += nextOrientation.getIncrement();
                                    break;
                                case Y:
                                    nextY += nextOrientation.getIncrement();
                                    break;
                                default:
                                    throw new IllegalArgumentException("L'axe n'est pas implémenté");
                            }

                            gameMap.moveAdventurer(adventurer, nextX, nextY);
                        }
                    }
            );
        }
    }

}
