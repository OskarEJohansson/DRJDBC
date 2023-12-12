package com.OskarJohansson.DungeonRun.Model.Characters;

import com.OskarJohansson.DungeonRun.Model.Items.Weapon.WeaponClassOne.Sword;

public class Barbarian extends Hero {

    public Barbarian() {
        this.setHeroClass("Barbarian");
        this.setStrength(10);
        this.setIntelligence(5);
        this.setAgility(5);
        this.setHealthPoints(100);
        this.setHealthPointsBase(5);
        this.setTurningPoints(100);
        this.setTurningPointsBase(100);
        this.setWeapon(new Sword());
    }
}