package com.OskarJohansson.DungeonRun.Model.Monster.Monster;

import com.OskarJohansson.DungeonRun.Control.MapControl;
import com.OskarJohansson.DungeonRun.Control.MenuControl;
import com.OskarJohansson.DungeonRun.Control.PlayerControl;
import com.OskarJohansson.DungeonRun.Model.Monster.EnemyParentModel;
import com.OskarJohansson.DungeonRun.Model.Monster.iEnemy;

import java.util.Random;

public class Grunt extends EnemyParentModel implements iEnemy {

    public Grunt() {
        super();
        this.setName("Grunt");
        this.setHealthPoints(5);
        this.setHealthPointsBase(5);
        this.setDamageMin(1);
        this.setDamageMax(3);
        this.setAttackCost(1);
        this.setTurningPoints(2);
        this.setTurningPointsBase(2);
        this.setExperiencePoints(5);
        this.setGold(3);
        this.setLevel(2);
        this.setBlockLevel(2);
    }
}
