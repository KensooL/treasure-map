package com.carbon.game.treasuremap.model;

import lombok.Data;

@Data
public class MapCase {
    private int x;
    private int y;

    public MapCase(){
        super();
    }

    public MapCase(int x, int y){
        this();
        this.x = x;
        this.y = y;
    }


}
