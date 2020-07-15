package com.carbon.game.treasuremap.services;

import com.carbon.game.treasuremap.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class EntityLoaderFactoryTests {

    @Autowired
    private EntityLoaderFactory entityCaseFactory;

    @Test
    void test_getEntityCase_Throw_Exception() {
        assertThrows(IllegalArgumentException.class, () -> this.entityCaseFactory.getEntityCase("AA - Indiana - 1 - 1 - S - AADADA"));
    }

    @Test
    void test_getEntityCaseAdventurer() {
        EntityCase entity = this.entityCaseFactory.getEntityCase("A - Indiana - 1 - 1 - S - AADADA");
        assertThat(entity.getType()).isEqualTo(EntityType.A);
        assertThat(entity.getX()).isEqualTo(1);
        assertThat(entity.getY()).isEqualTo(1);
        assertThat(entity).isInstanceOf(Adventurer.class);

        Adventurer adventurer = (Adventurer) entity;

        assertThat(adventurer.getOrientation()).isEqualTo(Orientation.S);
        assertThat(adventurer.getName()).isEqualTo("Indiana");
        assertThat(adventurer.getMovements()).containsExactly(Movement.A, Movement.A, Movement.D, Movement.A, Movement.D, Movement.A);
    }

    @Test
    void test_getEntityCaseMountain() {
        EntityCase entity = this.entityCaseFactory.getEntityCase("M - 3 - 4");
        assertThat(entity.getType()).isEqualTo(EntityType.M);
        assertThat(entity.getX()).isEqualTo(3);
        assertThat(entity.getY()).isEqualTo(4);
        assertThat(entity).isInstanceOf(Mountain.class);
    }

    @Test
    void test_getEntityCaseTreasure() {
        EntityCase entity = this.entityCaseFactory.getEntityCase("T - 0 - 3 - 2");
        assertThat(entity.getType()).isEqualTo(EntityType.T);
        assertThat(entity.getX()).isZero();
        assertThat(entity.getY()).isEqualTo(3);
        assertThat(entity).isInstanceOf(Treasure.class);

        Treasure treasure = (Treasure) entity;

        assertThat(treasure.getTreasureNumber()).isEqualTo(2);
    }



    @Test
    void test_lineIsMountain_Pass() {
        assertThat(this.entityCaseFactory.isMountain("M - 3 - 4")).isTrue();
    }

    @Test
    void test_lineMountain_Fail() {
        assertThat(this.entityCaseFactory.isMountain("A - 3 - 4")).isFalse();
    }

    @Test
    void test_lineIsTreasure_Pass() {
        assertThat(this.entityCaseFactory.isTreasure("T - 0 - 3 - 2")).isTrue();
    }

    @Test
    void test_lineIsTreasure_Fail() {
        assertThat(this.entityCaseFactory.isTreasure("M - 3 - 4")).isFalse();
    }

    @Test
    void test_lineIsAdventurer_Pass() {
        assertThat(this.entityCaseFactory.isAdventurer("A - Indiana - 1 - 1 - S - AADADA")).isTrue();
    }

    @Test
    void test_lineIsAdventurer_Fail() {
        assertThat(this.entityCaseFactory.isAdventurer("T - 3 - 4")).isFalse();
    }


}
