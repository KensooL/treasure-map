package com.carbon.game.treasuremap.services;

import com.carbon.game.treasuremap.model.*;
import com.carbon.game.treasuremap.utils.OutputFormatter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MovementSimulatorTests {

    @Autowired
    private MovementSimulator movementSimulator;

    @Test
    void test_simulate() {
        GameMap gameMap = new GameMap(3, 4);
        gameMap.addEntityCase(new Mountain(1, 0));
        gameMap.addEntityCase(new Mountain(2, 1));

        Treasure treasure1 = new Treasure(0, 3, 2);
        gameMap.addEntityCase(treasure1);
        Treasure treasure2 = new Treasure(1, 3, 3);
        gameMap.addEntityCase(treasure2);

        Adventurer adventurer = new Adventurer(1, 1, "Lara", Orientation.S, Movement.getMovements("AADADAGGA"));
        gameMap.addEntityCase(adventurer);

        this.movementSimulator.simulate(gameMap);

        assertThat(adventurer.getOrientation()).isEqualTo(Orientation.S);
        assertThat(adventurer.getX()).isZero();
        assertThat(adventurer.getY()).isEqualTo(3);
        assertThat(adventurer.getTreasureNumber()).isEqualTo(3);

        assertThat(treasure1.getTreasureNumber()).isZero();
        assertThat(treasure2.getTreasureNumber()).isEqualTo(2);

        OutputFormatter.formatState(gameMap);
    }
}
