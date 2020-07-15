package com.carbon.game.treasuremap.model;

public enum Orientation {
    N(-1, Axe.Y),  // Nord
    S(1, Axe.Y),  // Sud
    E(1, Axe.X),  // Est
    O(-1, Axe.X);  // Ouest

    private final int increment;
    private final Axe axe;

    Orientation(int increment, Axe axe){
        this.increment = increment;
        this.axe = axe;
    }

    public Axe getAxe(){
        return this.axe;
    }

    public int getIncrement(){
        return this.increment;
    }
}

