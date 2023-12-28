package com.OskarJohansson.DungeonRun.Model.Monster.Bosses;

import com.OskarJohansson.DungeonRun.Control.MapControl;
import com.OskarJohansson.DungeonRun.Control.MenuControl;
import com.OskarJohansson.DungeonRun.Control.PlayerControl;
import com.OskarJohansson.DungeonRun.Model.Monster.EnemyParentModel;
import com.OskarJohansson.DungeonRun.Model.Monster.iEnemy;

import java.util.Random;

public class IcaBoss extends EnemyParentModel implements iEnemy {

    public IcaBoss() {
        super();
        this.setName("The Meat Cleaver Maniac");
        this.setHealthPoints(10);
        this.setHealthPointsBase(10);
        this.setDamageMin(2);
        this.setDamageMax(3);
        this.setAttackCost(2);
        this.setTurningPoints(5);
        this.setTurningPointsBase(5);
        this.setExperiencePoints(5);
        this.setGold(10);
        this.setLevel(2);
        this.setKilled(false);
        this.setBlockLevel(2);
    }

    @Override
    public int enemyAttack(PlayerControl player, MapControl mapControl, MenuControl menuControl) {
        this.setTurningPoints(this.getTurningPoints() - this.getAttackCost());
        return new Random().nextInt(getDamageMin(), getDamageMax());
    }

    @Override
    public boolean isBossNerdWizard() {
        return false;
    }
}
