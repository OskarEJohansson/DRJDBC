package com.OskarJohansson.DungeonRun.Model.Monster.Bosses;

import com.OskarJohansson.DungeonRun.Model.Monster.EnemyParentModel;

import java.util.Random;

public class TechWizOfDoom extends EnemyParentModel {

    public TechWizOfDoom() {
        super();
        this.setName("Kris The TechWiz of Doom");
        this.setHealthPoints(30);
        this.setHealthPointsBase(30);
        this.setDamageMin(5);
        this.setDamageMax(10);
        this.setAttackCost(3);
        this.setTurningPoints(12);
        this.setTurningPointsBase(12);
        this.setExperiencePoints(100);
        this.setGold(100);
        this.setLevel(5);
        this.setKilled(false);
        this.setBlockLevel(5);
    }
}
