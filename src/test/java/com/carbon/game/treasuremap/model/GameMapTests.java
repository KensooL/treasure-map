package com.carbon.game.treasuremap.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameMapTests {

    @Test
    void test_addEntityAdventurer() {
        Adventurer entity = new Adventurer(1,1,"Indiana", Orientation.S, Movement.getMovements("AADADA"));
        GameMap gameMap = new GameMap(3,4);
        gameMap.addEntityCase(entity);
        assertThat(gameMap.getAdventurer(1,1)).isEqualTo(entity);
        assertThat(gameMap.getAdventurerCases()).isNotEmpty();
        assertThat(gameMap.getAdventurerCases().get(0)).isEqualTo(entity);
    }

    @Test
    void test_addEntityMountain() {
        Mountain entity = new Mountain(1,1);
        GameMap gameMap = new GameMap(3,4);
        gameMap.addEntityCase(entity);
        assertThat(gameMap.getEntity(1,1)).isEqualTo(entity);
        assertThat(gameMap.getAdventurerCases()).isEmpty();
    }

    @Test
    void test_addEntityTreasure() {
        Treasure entity = new Treasure(1,1, 1);
        GameMap gameMap = new GameMap(3,4);
        gameMap.addEntityCase(entity);
        assertThat(gameMap.getEntity(1,1)).isEqualTo(entity);
        assertThat(gameMap.getAdventurerCases()).isEmpty();
    }

    @Test
    void test_getEntityCase_Throw_Exception() {
        Mountain entity = new Mountain(3,1);
        GameMap gameMap = new GameMap(3,4);
        assertThrows(IllegalArgumentException.class, () -> gameMap.addEntityCase(entity));
    }

    @Test
    void  test_moveAdventurer(){
        GameMap gameMap = new GameMap(3, 4);
        gameMap.addEntityCase(new Mountain(1,0));
        gameMap.addEntityCase(new Mountain(2,1));
        gameMap.addEntityCase(new Treasure(0,3,2));
        gameMap.addEntityCase(new Treasure(1,3,2));
        gameMap.addEntityCase(new Adventurer(0,3, "Lara", Orientation.S, Movement.getMovements("AADADAGGA")));

    }
}
