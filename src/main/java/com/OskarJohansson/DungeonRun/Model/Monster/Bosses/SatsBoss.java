package com.OskarJohansson.DungeonRun.Model.Monster.Bosses;

import com.OskarJohansson.DungeonRun.Model.Monster.EnemyParentModel;

import java.util.Random;

public class SatsBoss extends EnemyParentModel {

    public SatsBoss() {
        super();
        this.setName("PT Super Grunt");
        this.setHealthPoints(15);
        this.setHealthPointsBase(15);
        this.setDamageMin(3);
        this.setDamageMax(5);
        this.setAttackCost(3);
        this.setTurningPoints(12);
        this.setTurningPointsBase(12);
        this.setExperiencePoints(10);
        this.setGold(10);
        this.setLevel(5);
        this.setKilled(false);
        this.setBlockLevel(3);
    }
}
