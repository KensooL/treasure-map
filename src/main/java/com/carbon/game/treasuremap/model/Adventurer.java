package com.carbon.game.treasuremap.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Adventurer extends EntityCase {

    private final String name;
    private Orientation orientation;
    private final Movement[] movements;

    public Adventurer(int x, int y, String name, Orientation orientation, Movement[] movements ){
        super(EntityType.A, x, y, 0);
        this.name = name;
        this.orientation =  orientation;
        this.movements = movements;
    }

    public Adventurer(int x, int y, String name, Orientation orientation, Movement[] movements, int treasureNumber){
        this(x, y, name,orientation,movements);
        this.setTreasureNumber(treasureNumber);
    }

}
