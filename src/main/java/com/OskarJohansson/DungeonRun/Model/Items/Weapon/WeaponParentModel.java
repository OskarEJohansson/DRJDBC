package com.OskarJohansson.DungeonRun.Model.Items.Weapon;

public class WeaponParentModel  {

    private int weaponID;
    private String name;
    private String weaponClass;
    private int damageMin;
    private int damageMax;
    private int turningPoints;
    private int minimumLevel;
    private int cost; // SHOULD THIS VARIABLE BE HOLD BY THE STORE CLASS?
    private String soundOfAttack;


    public int getWeaponID() {
        return weaponID;
    }

    public void setWeaponID(int weaponID) {
        this.weaponID = weaponID;
    }

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

    public void setDamageMin(int damageMin) {
        this.damageMin = damageMin;
    }

    public int getDamageMax() {
        return damageMax;
    }

    public void setDamageMax(int damageMax) {
        this.damageMax = damageMax;
    }

    public int getTurningPoints() {
        return turningPoints;
    }

    public void setTurningPoints(int turningPoints) {
        this.turningPoints = turningPoints;
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
