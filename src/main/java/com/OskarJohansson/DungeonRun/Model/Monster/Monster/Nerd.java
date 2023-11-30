package com.OskarJohansson.DungeonRun.Model.Monster.Monster;

import com.OskarJohansson.DungeonRun.Model.Monster.EnemyParentModel;

import java.util.Random;

public class Nerd extends EnemyParentModel {

    public Nerd() {
        super();
        this.setName("Nerd");
        this.setHealthPoints(7);
        this.setHealthPointsBase(7);
        this.setDamageMin(2);
        this.setDamageMax(4);
        this.setAttackCost(1);
        this.setTurningPoints(3);
        this.setTurningPointsBase(3);
        this.setExperiencePoints(5);
        this.setGold(5);
        this.setLevel(3);
        this.setBlockLevel(3);
    }
}
