package com.carbon.game.treasuremap.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class EntityCase extends MapCase {

    private final EntityType type;
    private int treasureNumber;

    public EntityCase(EntityType type , int x, int y, int treasureNumber){
        super(x,y);
        this.type = type;
        this.treasureNumber = treasureNumber;
    }
}
