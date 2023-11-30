package com.OskarJohansson.DungeonRun.Model.Items.Potions;

import java.io.Serializable;

public class HealthPotion implements Serializable {

    private int hP = 10;
    private boolean isUsed = false;

    public int gethP() {
        return hP;
    }

    public void sethP(int hP) {
        this.hP = hP;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public int useHealthPotion() {
        this.isUsed = true;
        return this.hP;
    }
}
