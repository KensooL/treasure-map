package com.carbon.game.treasuremap.utils;

import com.carbon.game.treasuremap.model.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OutputFormatterTests {

    @Test
    void test_printState(){
        GameMap gameMap = new GameMap(3, 4);
        gameMap.addEntityCase(new Mountain(1,0));
        gameMap.addEntityCase(new Mountain(2,1));
        gameMap.addEntityCase(new Treasure(0,3,2));
        gameMap.addEntityCase(new Treasure(1,3,3));
        gameMap.addEntityCase(new Adventurer(1,1, "Lara", Orientation.S, Movement.getMovements("AADADAGGA")));

        StringBuilder sb = new StringBuilder();
        sb.append(".").append(OutputFormatter.TABULATION).append("M").append(OutputFormatter.TABULATION).append(".").append(System.lineSeparator());
        sb.append(".").append(OutputFormatter.TABULATION).append("A(Lara)").append(OutputFormatter.TABULATION).append("M").append(System.lineSeparator());
        sb.append(".").append(OutputFormatter.TABULATION).append(".").append(OutputFormatter.TABULATION).append(".").append(System.lineSeparator());
        sb.append("T(2)").append(OutputFormatter.TABULATION).append("T(3)").append(OutputFormatter.TABULATION).append(".").append(System.lineSeparator());

        assertThat(OutputFormatter.formatState(gameMap)).isEqualTo(sb.toString());
    }

    @Test
    void test_printStateMultiEntity(){
        GameMap gameMap = new GameMap(3, 4);
        gameMap.addEntityCase(new Mountain(1,0));
        gameMap.addEntityCase(new Mountain(2,1));
        gameMap.addEntityCase(new Treasure(0,3,2));
        gameMap.addEntityCase(new Treasure(1,3,3));
        gameMap.addEntityCase(new Adventurer(0,3, "Lara", Orientation.S, Movement.getMovements("AADADAGGA")));

        StringBuilder sb = new StringBuilder();
        sb.append(".").append(OutputFormatter.TABULATION).append("M").append(OutputFormatter.TABULATION).append(".").append(System.lineSeparator());
        sb.append(".").append(OutputFormatter.TABULATION).append(".").append(OutputFormatter.TABULATION).append("M").append(System.lineSeparator());
        sb.append(".").append(OutputFormatter.TABULATION).append(".").append(OutputFormatter.TABULATION).append(".").append(System.lineSeparator());
        sb.append("T(2)A(Lara)").append(OutputFormatter.TABULATION).append("T(3)").append(OutputFormatter.TABULATION).append(".").append(System.lineSeparator());

        assertThat(OutputFormatter.formatState(gameMap)).isEqualTo(sb.toString());
    }

    @Test
    void test_formatOutput(){
        GameMap gameMap = new GameMap(3, 4);
        gameMap.addEntityCase(new Mountain(1,0));
        gameMap.addEntityCase(new Mountain(2,1));
        gameMap.addEntityCase(new Treasure(0,3,0));
        gameMap.addEntityCase(new Treasure(1,3,2));
        gameMap.addEntityCase(new Adventurer(0,3, "Lara", Orientation.S, Movement.getMovements("AADADAGGA"),3));


        StringBuilder sb = new StringBuilder();
        sb.append("C - 3 - 4").append(System.lineSeparator());
        sb.append("M - 1 - 0").append(System.lineSeparator());
        sb.append("M - 2 - 1").append(System.lineSeparator());
        sb.append("# {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors restants}").append(System.lineSeparator());
        sb.append("T - 1 - 3 - 2").append(System.lineSeparator());
        sb.append("# {A comme Aventurier} - {Nom de l’aventurier} - {Axe horizontal} - {Axe vertical} - {Orientation} - {Nb. trésors ramassés}").append(System.lineSeparator());
        sb.append("A - Lara - 0 - 3 - S - 3");

        assertThat(OutputFormatter.formatOutput(gameMap)).isEqualTo(sb.toString());
    }

    @Test
    void test_formatOutputWithTreasure(){
        GameMap gameMap = new GameMap(3, 4);
        gameMap.addEntityCase(new Mountain(1,0));
        gameMap.addEntityCase(new Mountain(2,1));
        gameMap.addEntityCase(new Treasure(0,3,1));
        gameMap.addEntityCase(new Treasure(1,3,2));
        gameMap.addEntityCase(new Adventurer(0,3, "Lara", Orientation.S, Movement.getMovements("AADADAGGA"),3));


        StringBuilder sb = new StringBuilder();
        sb.append("C - 3 - 4").append(System.lineSeparator());
        sb.append("M - 1 - 0").append(System.lineSeparator());
        sb.append("M - 2 - 1").append(System.lineSeparator());
        sb.append("# {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors restants}").append(System.lineSeparator());
        sb.append("T - 0 - 3 - 1").append(System.lineSeparator());
        sb.append("T - 1 - 3 - 2").append(System.lineSeparator());
        sb.append("# {A comme Aventurier} - {Nom de l’aventurier} - {Axe horizontal} - {Axe vertical} - {Orientation} - {Nb. trésors ramassés}").append(System.lineSeparator());
        sb.append("A - Lara - 0 - 3 - S - 3");

        assertThat(OutputFormatter.formatOutput(gameMap)).isEqualTo(sb.toString());
    }
}
