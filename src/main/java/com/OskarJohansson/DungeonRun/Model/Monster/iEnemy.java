package com.OskarJohansson.DungeonRun.Model.Monster;


import com.OskarJohansson.DungeonRun.Control.MapControl;
import com.OskarJohansson.DungeonRun.Control.MenuControl;
import com.OskarJohansson.DungeonRun.Control.PlayerControl;

import java.util.Random;

public interface iEnemy {

    int enemyAttack(PlayerControl player, MapControl mapControl, MenuControl menuControl);

    boolean block();

    void displayEnemyStatus();

    void takeDamage(Boolean takeDamage, int damage);

    boolean isBossNerdWizard();
}
