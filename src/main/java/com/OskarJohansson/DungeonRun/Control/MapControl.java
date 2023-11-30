package com.OskarJohansson.DungeonRun.Control;

import com.OskarJohansson.DungeonRun.Model.Map.*;

public class MapControl {

    Level currentLevel;
    ICA ica;
    Sats sats;
    KjellCO kjellCO;
    TeacherLounge teacherLounge;

    MapControl() {
        this.currentLevel = new Level();
        this.ica = new ICA();
        this.sats = new Sats();
        this.kjellCO = new KjellCO();
        this.teacherLounge = new TeacherLounge();
    }

    public void setMap(int choice) {
        switch (choice) {
            case 1 -> {
                this.currentLevel = this.ica;
                this.currentLevel.setLevelName(this.ica.getLevelName());
                this.currentLevel.setLevelNumber(this.ica.getLevelNumber());
                this.currentLevel.setFinalBoss(this.ica.getFinalBoss());
                this.currentLevel.setMonsterList(this.ica.getMonsterList());

            }
            case 2 -> {
                this.currentLevel = this.sats;
                this.currentLevel.setLevelName(this.sats.getLevelName());
                this.currentLevel.setLevelNumber(this.sats.getLevelNumber());
                this.currentLevel.setFinalBoss(this.sats.getFinalBoss());
                this.currentLevel.setMonsterList(this.sats.getMonsterList());
            }
            case 3 -> {
                this.currentLevel = this.kjellCO;
                this.currentLevel.setLevelName(this.kjellCO.getLevelName());
                this.currentLevel.setLevelNumber(this.kjellCO.getLevelNumber());
                this.currentLevel.setFinalBoss(this.kjellCO.getFinalBoss());
                this.currentLevel.setMonsterList(this.kjellCO.getMonsterList());
            }
            case 4 -> {
                this.currentLevel = this.teacherLounge;
                this.currentLevel.setLevelName(this.teacherLounge.getLevelName());
                this.currentLevel.setLevelNumber(this.teacherLounge.getLevelNumber());
                this.currentLevel.setFinalBoss(this.teacherLounge.getFinalBoss());
                this.currentLevel.setMonsterList(this.teacherLounge.getMonsterList());
            }
        }
    }

    public void resetLevel(MapControl mapControl) {
        if (mapControl.currentLevel == this.ica) {
            this.ica = new ICA();
        }
        if (mapControl.currentLevel == this.sats) {
            this.sats = new Sats();
        }
        if (mapControl.currentLevel == this.kjellCO) {
            this.kjellCO = new KjellCO();
        }
    }
}