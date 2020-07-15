package com.carbon.game.treasuremap.services;

import com.carbon.game.treasuremap.model.GameMap;
import com.carbon.game.treasuremap.model.Mountain;
import com.carbon.game.treasuremap.model.Treasure;
import com.carbon.game.treasuremap.utils.OutputFormatter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FileLoaderTests {
    @Autowired
    private FileLoader fileLoader;

    @Test
    public void test_loadFile(){
        List<String> lines = Arrays.asList("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "T - 0 - 3 - 2", "T - 1 - 3 - 1");

        GameMap gameMap = this.fileLoader.loadFile(lines);

        assertThat(gameMap.getLength()).isEqualTo(3);
        assertThat(gameMap.getHeight()).isEqualTo(4);
        assertThat(gameMap.getAdventurerCases()).isEmpty();
        assertThat(gameMap.getEntity(0,0)).isNull();
        assertThat(gameMap.getEntity(0,1)).isNull();
        assertThat(gameMap.getEntity(0,2)).isNull();
        assertThat(gameMap.getEntity(0,3)).isNotNull();
        assertThat(gameMap.getEntity(0,3)).isInstanceOf(Treasure.class);

        assertThat(gameMap.getEntity(1,0)).isNull();
        assertThat(gameMap.getEntity(1,1)).isNotNull();
        assertThat(gameMap.getEntity(1,1)).isInstanceOf(Mountain.class);
        assertThat(gameMap.getEntity(1,2)).isNull();
        assertThat(gameMap.getEntity(1,3)).isNotNull();
        assertThat(gameMap.getEntity(1,3)).isInstanceOf(Treasure.class);

        assertThat(gameMap.getEntity(2,0)).isNull();
        assertThat(gameMap.getEntity(2,1)).isNull();
        assertThat(gameMap.getEntity(2,2)).isNotNull();
        assertThat(gameMap.getEntity(2,2)).isInstanceOf(Mountain.class);
        assertThat(gameMap.getEntity(2,3)).isNull();

        OutputFormatter.formatState(gameMap);
    }
}
