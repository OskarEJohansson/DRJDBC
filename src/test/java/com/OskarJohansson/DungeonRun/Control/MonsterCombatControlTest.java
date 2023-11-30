package com.OskarJohansson.DungeonRun.Control;


import com.OskarJohansson.DungeonRun.Model.Characters.Barbarian;
import com.OskarJohansson.DungeonRun.Model.Monster.EnemyParentModel;
import com.OskarJohansson.DungeonRun.Model.Monster.Monster.Minion;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class MonsterCombatControlTest {

    PlayerControl player;
    MenuControl menuControl;
    MapControl mapControl;
    MonsterCombatControl monsterCombatControl;

    @BeforeEach
    void setUp(){
        player = new PlayerControl();
        menuControl = new MenuControl();
        mapControl = new MapControl();
        mapControl.setMap(1);
        monsterCombatControl = new MonsterCombatControl();
        player.setHero(new Barbarian());
    }

    @Test
    void hasPlayerKilledAllTheEnemies() {
        List<EnemyParentModel> monsterList = mapControl.currentLevel.getMonsterList();
        monsterList.remove(0);
        monsterList.remove(0);

        Assert.assertFalse(monsterCombatControl.hasPlayerKilledAllTheEnemies(mapControl, player));
        monsterList.remove(0);
        mapControl.currentLevel.setMonsterList(monsterList);
        Assert.assertTrue(monsterCombatControl.hasPlayerKilledAllTheEnemies(mapControl, player));


    }

    @Test
    void displayHowManyMonstersInCombat() {
        Assert.assertEquals(3,monsterCombatControl.displayHowManyMonstersInCombat(mapControl) );

        List<EnemyParentModel> monsterList = mapControl.currentLevel.getMonsterList();
        monsterList.remove(0);
        mapControl.currentLevel.setMonsterList(monsterList);
        Assert.assertEquals(2,monsterCombatControl.displayHowManyMonstersInCombat(mapControl) );

        monsterList.add(new Minion());
        monsterList.add(new Minion());
        Assert.assertEquals(4,monsterCombatControl.displayHowManyMonstersInCombat(mapControl) );

    }

    @Test
    void monsterBattle() {
    }

    @Test
    void monsterAttackPlayer() {
    }

    @Test
    void playerAttackCurrantMonster() {
        player.attack(player);
    }

    @Test
    void checkIfPlayerHasKilledCurrantMonster() {
        EnemyParentModel minion = new Minion();
        Assert.assertFalse(monsterCombatControl.checkIfPlayerHasKilledCurrantMonster(minion, player));
        minion.setHealthPoints(-5);
        Assert.assertTrue(monsterCombatControl.checkIfPlayerHasKilledCurrantMonster(minion, player));

    }

    @Test
    void checkIfPlayerIsOutOfTurningPoints() {
    }

    @Test
    void resetMonsterAndBossHealthAndTurningPointsWhenPlayerIsKilled() {
        mapControl.currentLevel.getFinalBoss().setHealthPoints(9);
        Assert.assertEquals(9, mapControl.currentLevel.getFinalBoss().getHealthPoints());
        monsterCombatControl.resetMonsterAndBossHealthAndTurningPointsWhenPlayerIsKilled(mapControl);
        Assert.assertEquals(10, mapControl.currentLevel.getFinalBoss().getHealthPoints());

    }

    @Test
    void ifPlayerHasKilledTheCurrantEnemy() {
    }

    @Test
    void testHasPlayerKilledAllTheEnemies() {
        Assert.assertFalse(monsterCombatControl.hasPlayerKilledAllTheEnemies(mapControl, player));
        mapControl.currentLevel.setMonsterList(new ArrayList<>());
        Assert.assertTrue(monsterCombatControl.hasPlayerKilledAllTheEnemies(mapControl, player));

    }
}