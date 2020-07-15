package com.carbon.game.treasuremap.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mountain extends EntityCase {
    public Mountain(int x, int y){
        super(EntityType.M, x, y, 0);
    }
}
