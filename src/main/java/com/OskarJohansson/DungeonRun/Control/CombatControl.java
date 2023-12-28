package com.OskarJohansson.DungeonRun.Control;

import com.OskarJohansson.DungeonRun.Model.Monster.EnemyParentModel;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static com.OskarJohansson.DungeonRun.Control.UserInputControl.PRESS_ENTER_TO_CONTINUE;

public class CombatControl {

    public void minionBattleControl(PlayerControl player, MapControl mapControl, MenuControl menuControl, CombatControl combatControl) {
        displayHowManyMonstersInCombat(mapControl);

        boolean on = true;

        do {
            if (hasPlayerKilledAllTheEnemies(mapControl, player)) {
                return;
            }

            player.getHero().resetTurningPoints();
            monsterResetTurningPoints(mapControl);
            enemyBattle(player, mapControl, menuControl);

            if (player.isPlayerKilledInCombat(player)) {
                resetMinionHealthAndTurningPoints(mapControl);
                return;
            }

            if (player.getHero().getTurningPoints() > 0)
                on = menuControl.playerBattleOptions(player, mapControl, combatControl, false);

            PRESS_ENTER_TO_CONTINUE();

        } while (on);
    }

    public void bossBattleControl(PlayerControl player, MapControl mapControl, MenuControl menuControl, CombatControl combatControl) {
        System.out.println(displayBossNameInBattle(mapControl));

        boolean on = true;

        do {
            if (checkIfPlayerHasKilledCurrantEnemy(mapControl.currentLevel.getFinalBoss(), player)) {
                return;
            }
            player.getHero().resetTurningPoints();
            mapControl.currentLevel.getFinalBoss().resetTurningPoints();

            if (!mapControl.currentLevel.getFinalBoss().isKilled()) {
                enemyAttackPlayer(player, mapControl.currentLevel.getFinalBoss(), mapControl, menuControl);
            }
            if (mapControl.currentLevel.getFinalBoss().isKilled()) {
                player.levelUp(player);
                return;
            }
            if (player.isPlayerKilledInCombat(player)) {
                resetBossHealthAndTurningPoints(mapControl);
                return;
            }

            if (player.getHero().getTurningPoints() > 0)
                on = menuControl.playerBattleOptions(player, mapControl, combatControl, true);

            PRESS_ENTER_TO_CONTINUE();

        } while (on);
    }


    public void enemyBattle(PlayerControl player, MapControl mapControl, MenuControl menuControl) {
        if (mapControl.currentLevel.getMonsterList() != null) {
            handleEnemyBattle(player, mapControl.currentLevel.getMonsterList(), mapControl, menuControl);
        } else {
            handleEnemyBattle(player, List.of(mapControl.currentLevel.getFinalBoss()), mapControl, menuControl);
        }
    }

    private void handleEnemyBattle(PlayerControl player, List<EnemyParentModel> enemies, MapControl mapControl, MenuControl menuControl) {
        for (EnemyParentModel enemy : enemies) {
            while (enemy.getTurningPoints() > 0) {
                enemyAttackPlayer(player, enemy, mapControl, menuControl);
                if (player.getHero().getHealthPoints() <= 0) {
                    return;
                }
            }
        }
    }

    public void enemyAttackPlayer(PlayerControl player, EnemyParentModel monster, MapControl
            mapControl, MenuControl menuControl) {
        System.out.printf(">>>>     \033[4;31m%s attacks!\033[0m     <<<<\n", monster.getName());
        player.takeDamage(player, player.block(player), monster.enemyAttack(player, mapControl, menuControl));
        menuControl.getStatus(player);
    }

    public void playerAttackCurrantMonster(EnemyParentModel currantMonster, PlayerControl player) {
        System.out.printf("\033[4;32m%s attacks for %d turningpoints and with %d maximum damage points\033[0m\n", player.getHero().getName(), player.getHero().getWeapon().getTurningPoints(), player.getHero().getWeapon().getDamageMax());
        System.out.println(player.getHero().getWeapon().getSoundOfAttack());
        if (currantMonster.getHealthPoints() > 0 && !currantMonster.isKilled()) {
            currantMonster.takeDamage(currantMonster.block(), player.attack(player));
        }
    }

    public void iterateMinionMonsterListAndAttackMonster(PlayerControl player, MapControl mapControl) {

        for (EnemyParentModel currantMonster : mapControl.currentLevel.getMonsterList()) {
            if (player.checkIfPlayerIsOutOfTurningPoints()) {
                return;
            }
            while (player.getHero().getTurningPoints() >= player.getHero().getWeapon().getTurningPoints()) {
                playerAttackCurrantMonster(currantMonster, player);
                if (checkIfPlayerHasKilledCurrantEnemy(currantMonster, player)) {
                    break;
                }
            }
        }
    }

    public boolean checkIfPlayerHasKilledCurrantEnemy(EnemyParentModel currantMonster, PlayerControl player) {
        if (currantMonster.getHealthPoints() <= 0 && !currantMonster.isKilled()) {
            ifPlayerHasKilledTheCurrantEnemy(player, currantMonster);
            return true;
        }
        return false;
    }

    public boolean ifPlayerHasKilledTheCurrantEnemy(PlayerControl player, EnemyParentModel monster) {
        System.out.printf("////     You killed %s and gained %d experience points!    //// \n", monster.getName(), monster.getExperiencePoints());
        player.getHero().addKillList(1);
        monster.setTimeOfDeath(Timestamp.valueOf(LocalDateTime.now()));
        player.getHero().addToKillStats(monster);
        player.getHero().addExperiencePoints(monster.getExperiencePoints());
        player.getHero().addGold(monster.getGold());
        monster.setKilled(true);
        return true;
    }

    private int removeKilledEnemiesFromMonsterList(MapControl mapControl) {
        mapControl.currentLevel.getMonsterList().removeIf(c -> c.getHealthPoints() <= 0);
        return mapControl.currentLevel.getMonsterList().size();
    }

    public boolean hasPlayerKilledAllTheEnemies(MapControl mapControl, PlayerControl player) {
        if (removeKilledEnemiesFromMonsterList(mapControl) == 0) {
            System.out.println("////    \033[0;31m  You have killed all the monsters!   \033[0m   ////\n");
            player.levelUp(player);
            return true;
        }
        return false;
    }

    public void resetMinionHealthAndTurningPoints(MapControl mapControl) {
        for (EnemyParentModel monster : mapControl.currentLevel.getMonsterList()) {
            monster.resetTurningPoints();
            monster.resetHealthPoints();
        }
    }

    public void resetBossHealthAndTurningPoints(MapControl mapControl) {
        mapControl.currentLevel.getFinalBoss().resetHealthPoints();
        mapControl.currentLevel.getFinalBoss().resetTurningPoints();
    }

    private void monsterResetTurningPoints(MapControl mapControl) {
        for (EnemyParentModel monster : mapControl.currentLevel.getMonsterList()) {
            monster.resetTurningPoints();
        }
    }

    public String displayBossNameInBattle(MapControl mapControl) {
        return System.out.printf("""
                You are being attacked by %s!
                                
                """, mapControl.currentLevel.getFinalBoss().getName()).toString();
    }

    public int displayHowManyMonstersInCombat(MapControl mapControl) {
        System.out.printf("""
                You are being attacked by %d monsters!
                                
                """, mapControl.currentLevel.getMonsterList().size());

        return mapControl.currentLevel.getMonsterList().size();
    }

}


