package com.carbon.game.treasuremap.utils;

import com.carbon.game.treasuremap.model.Movement;
import com.carbon.game.treasuremap.model.Orientation;

public class MovementToOrientation {

    public static Orientation getNextOrientation(Orientation actual, Movement movement){
        switch (actual){
            case N:
                return fromNorth(movement);
            case S:
                return fromSouth(movement);
            case E:
                return fromEast(movement);
            case O:
                return fromWest(movement);
            default:
                throw new IllegalArgumentException("Erreur orientation : " + actual);
        }
    }

    private static Orientation fromSouth(Movement movement) {
        switch (movement){
            case A:
                return Orientation.S;
            case D:
                return Orientation.O;
            case G:
                return Orientation.E;
            default:
                throw new IllegalArgumentException("Erreur mouvement : " + movement);
        }
    }

    private static Orientation fromNorth(Movement movement) {
        switch (movement){
            case A:
                return Orientation.N;
            case D:
                return Orientation.E;
            case G:
                return Orientation.O;
            default:
                throw new IllegalArgumentException("Erreur mouvement : " + movement);
        }
    }

    private static Orientation fromEast(Movement movement) {
        switch (movement){
            case A:
                return Orientation.E;
            case D:
                return Orientation.S;
            case G:
                return Orientation.N;
            default:
                throw new IllegalArgumentException("Erreur mouvement : " + movement);
        }
    }

    private static Orientation fromWest(Movement movement) {
        switch (movement){
            case A:
                return Orientation.O;
            case D:
                return Orientation.N;
            case G:
                return Orientation.S;
            default:
                throw new IllegalArgumentException("Erreur mouvement : " + movement);
        }
    }
}
