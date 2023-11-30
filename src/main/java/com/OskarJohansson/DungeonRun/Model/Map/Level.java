package com.OskarJohansson.DungeonRun.Model.Map;


import com.OskarJohansson.DungeonRun.Model.Monster.EnemyParentModel;

import java.util.List;

public class Level {

    private String levelName;
    private String levelNumber;
    private List<EnemyParentModel> monsterList;
    private EnemyParentModel finalBoss;

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(String levelNumber) {
        this.levelNumber = levelNumber;
    }

    public List<EnemyParentModel> getMonsterList() {
        return monsterList;
    }

    public void setMonsterList(List<EnemyParentModel> monsterList) {
        this.monsterList = monsterList;
    }

    public EnemyParentModel getFinalBoss() {
        return finalBoss;
    }

    public void setFinalBoss(EnemyParentModel finalBoss) {
        this.finalBoss = finalBoss;
    }

}

