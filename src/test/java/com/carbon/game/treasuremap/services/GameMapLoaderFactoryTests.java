package com.carbon.game.treasuremap.services;

import com.carbon.game.treasuremap.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class GameMapLoaderFactoryTests {

    @Autowired
    private GameMapLoaderFactory gameMapLoaderFactory;

    @Test
    void test_getEntityCase_Throw_Exception() {
        assertThrows(IllegalArgumentException.class, () -> this.gameMapLoaderFactory.getGameMap("AA - Indiana - 1 - 1 - S - AADADA"));
    }

    @Test
    void test_getGameMap() {
        GameMap entity = this.gameMapLoaderFactory.getGameMap("C - 3 - 4");
        assertThat(entity.getLength()).isEqualTo(3);
        assertThat(entity.getHeight()).isEqualTo(4);
        assertThat(entity.getAdventurerCases()).isNotNull();
        assertThat(entity.getEntityCases()).isNotNull();
    }


    @Test
    void test_lineIsGameMap_Pass() {
        assertThat(this.gameMapLoaderFactory.isGameMap("C - 3 - 4")).isTrue();
    }

    @Test
    void test_lineIsGameMap_Fail() {
        assertThat(this.gameMapLoaderFactory.isGameMap("C - 3 - 4 - A")).isFalse();
    }


}
