package com.OskarJohansson.DungeonRun.Control;

import com.OskarJohansson.DungeonRun.Model.Monster.EnemyParentModel;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.OskarJohansson.DungeonRun.Control.UserInputControl.PRESS_ENTER_TO_CONTINUE;

public class MonsterCombatControl {

    public void monsterBattleControl(PlayerControl player, MapControl mapControl, MenuControl menuControl, MonsterCombatControl newCombatControl) {

        displayHowManyMonstersInCombat(mapControl);

        boolean on = true;

        do {

            if (hasPlayerKilledAllTheEnemies(mapControl, player)) {
                return;
            }

            player.getHero().resetTurningPoints();
            monsterResetTurningPoints(mapControl);
            monsterBattle(player, mapControl, menuControl);

            if (player.isPlayerKilledInCombat(player)) {
                resetMonsterAndBossHealthAndTurningPointsWhenPlayerIsKilled(mapControl);
                return;
            }
            if (player.getHero().getTurningPoints() > 0)
                on = menuControl.playerMinionBattleOptions(player, mapControl, newCombatControl);

            PRESS_ENTER_TO_CONTINUE();

        } while (on);
    }

    public boolean hasPlayerKilledAllTheEnemies(MapControl mapControl, PlayerControl player) {
        if (removeKilledEnemiesFromMonsterList(mapControl) == 0) {
            System.out.println("////    \033[0;31m  You have killed all the monsters!   \033[0m   ////\n");
            player.levelUp(player);
            return true;
        }
        return false;
    }

    public int displayHowManyMonstersInCombat(MapControl mapControl) {
        System.out.printf("""
                You are being attacked by %d monsters!
                                
                """, mapControl.currentLevel.getMonsterList().size());

        return mapControl.currentLevel.getMonsterList().size();
    }

    public void monsterBattle(PlayerControl player, MapControl mapControl, MenuControl menuControl) {
        for (EnemyParentModel monster : mapControl.currentLevel.getMonsterList()) {
            while (monster.getTurningPoints() > 0) {
                monsterAttackPlayer(player, monster, mapControl, menuControl);
                if (player.getHero().getHealthPoints() <= 0) {
                    return;
                }
            }
        }
    }

    public void monsterAttackPlayer(PlayerControl player, EnemyParentModel monster, MapControl mapControl, MenuControl menuControl) {
        System.out.printf(">>>>     \033[4;31m%s %d attacks!\033[0m     <<<<\n", monster.getName(), mapControl.currentLevel.getMonsterList().indexOf(monster) + 1);
        player.takeDamage(player, player.block(player), monster.attack());
        menuControl.getStatus(player);
    }

    private int removeKilledEnemiesFromMonsterList(MapControl mapControl) {
        mapControl.currentLevel.getMonsterList().removeIf(c -> c.getHealthPoints() <= 0);
        return mapControl.currentLevel.getMonsterList().size();
    }

    private void monsterResetTurningPoints(MapControl mapControl) {
        for (EnemyParentModel monster : mapControl.currentLevel.getMonsterList()) {
            monster.resetTurningPoints();
        }
    }

    public void playerAttackCurrantMonster(EnemyParentModel currantMonster, PlayerControl player) {
        System.out.printf("\033[4;32m%s attacks for %d turningpoints and with %d maximum damage points\033[0m\n", player.getHero().getName(), player.getHero().getWeapon().getTurningPoints(), player.getHero().getWeapon().getDamageMax());
        System.out.println(player.getHero().getWeapon().getSoundOfAttack());
        if (currantMonster.getHealthPoints() > 0 && !currantMonster.isKilled()) {
            currantMonster.takeDamage(currantMonster.block(), player.attack(player));
        }
    }

    public boolean checkIfPlayerHasKilledCurrantMonster(EnemyParentModel currantMonster, PlayerControl player) {
        if (currantMonster.getHealthPoints() <= 0 && !currantMonster.isKilled()) {
            ifPlayerHasKilledTheCurrantEnemy(player, currantMonster);
            return true;
        }
        return false;
    }

    public boolean checkIfPlayerIsOutOfTurningPoints(PlayerControl player) {
        if (player.getHero().getTurningPoints() <= 0) {
            System.out.println("You are out of Turning Points!");
            return true;
        }
        return false;
    }

    public void iterateMinionMonsterListAndAttackMonster(PlayerControl player, MapControl mapControl) {

        for (EnemyParentModel currantMonster : mapControl.currentLevel.getMonsterList()) {
            if (checkIfPlayerIsOutOfTurningPoints(player)) {
                return;
            }
            while (player.getHero().getTurningPoints() >= player.getHero().getWeapon().getTurningPoints()) {
                playerAttackCurrantMonster(currantMonster, player);
                if (checkIfPlayerHasKilledCurrantMonster(currantMonster, player)) {
                    break;
                }
            }
        }
    }

    public void resetMonsterAndBossHealthAndTurningPointsWhenPlayerIsKilled(MapControl mapControl) {
        mapControl.currentLevel.getMonsterList().forEach(c -> {
            c.resetTurningPoints();
            c.resetHealthPoints();
        });
        mapControl.currentLevel.getFinalBoss().resetHealthPoints();
        mapControl.currentLevel.getFinalBoss().resetTurningPoints();
    }

    public boolean ifPlayerHasKilledTheCurrantEnemy(PlayerControl player, EnemyParentModel monster) {
        System.out.printf("////     You killed the monster and gained %d experience points!    //// \n", monster.getExperiencePoints());
        player.getHero().addKillList(1);
        monster.setTimeOfDeath(Timestamp.valueOf(LocalDateTime.now()));
        player.getHero().addToKillStats(monster);
        player.getHero().addExperiencePoints(monster.getExperiencePoints());
        player.getHero().addGold(monster.getGold());
        monster.setKilled(true);
        return true;
    }
}
