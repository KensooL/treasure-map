package com.carbon.game.treasuremap.model;

import java.util.Objects;

public enum Movement {
    A('A'),  // Avancer
    G('G'),  // Gauche
    D('D');  // Droite

    private final char c;

    Movement(char c){
        this.c = c;
    }

    public char getC(){
        return this.c;
    }

    public static Movement[] getMovements(String data){
        Objects.requireNonNull(data);
        char[] array = data.toCharArray();
        Movement[] output = new Movement[array.length];
        for(int i =0 ;i < array.length ; i++){
            output[i] = getMovement(array[i]);
        }
        return output;
    }

    public static Movement getMovement(char c){
        Movement m = null;
        Movement[] array = Movement.values();
        for(int i = 0 ; i < array.length ; i++){
            if(array[i].getC() == c  ){
                m =  array[i];
                break;
            }
        }
        return m;
    }
}
