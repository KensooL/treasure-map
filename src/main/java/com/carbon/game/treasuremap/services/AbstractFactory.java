package com.carbon.game.treasuremap.services;

public abstract class AbstractFactory {

    protected final String[] splitLine(String line) {
        return line.split(" - ");
    }

}
