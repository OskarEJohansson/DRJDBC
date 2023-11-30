package com.OskarJohansson.DungeonRun.Model.Items.Weapon;

import java.io.Serializable;

public class WeaponParentModel implements Serializable  {

    private String name;
    private String weaponClass;
    private int damageMin;
    private int damageMax;
    private int turnPoints;
    private int minimumLevel;
    private int cost;
    private String soundOfAttack;

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getWeaponClass() {
        return weaponClass;
    }
    public void setWeaponClass(String weaponClass) {
        this.weaponClass = weaponClass;
    }

    public int getDamageMin() {
        return damageMin;
    }

    public void setDamage(int damage) {
        this.damageMin = damage;
    }


    public void setDamageMin(int damageMin) {
        this.damageMin = damageMin;
    }

    public int getDamageMax() {
        return damageMax;
    }

    public void setDamageMax(int damageMax) {
        this.damageMax = damageMax;
    }

    public int getTurnPoints() {
        return turnPoints;
    }

    public void setTurnPoints(int turnPoints) {
        this.turnPoints = turnPoints;
    }

    public int getMinimumLevel() {return minimumLevel;}

    public void setMinimumLevel(int minimumLevel) {this.minimumLevel = minimumLevel;}

    public int getCost() {return cost;}

    public void setCost(int cost) {this.cost = cost;}

    public String getSoundOfAttack() {
        return soundOfAttack;
    }

    public void setSoundOfAttack(String soundOfAttack) {
        this.soundOfAttack = "\033[1;95m" + soundOfAttack + "\033[0m";
    }
}
