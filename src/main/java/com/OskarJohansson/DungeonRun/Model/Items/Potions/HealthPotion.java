package com.OskarJohansson.DungeonRun.Model.Items.Potions;

import java.io.Serializable;

public class HealthPotion{

    private int hP = 10;
    private boolean isUsed = false;

    public int gethP() {
        return hP;
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
