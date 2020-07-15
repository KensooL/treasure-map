package com.carbon.game.treasuremap.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Treasure extends EntityCase {

    public Treasure(int x, int y, int treasureNumber) {
        super(EntityType.T, x, y, treasureNumber);
    }
}
