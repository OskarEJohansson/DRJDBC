package com.OskarJohansson.DungeonRun.Model.Characters;

import com.OskarJohansson.DungeonRun.Model.Items.Weapon.WeaponClassOne.Sword;

public class Barbarian extends Hero {

    public Barbarian() {
        this.setHeroClass("Barbarian");
        this.setStrength(5);
        this.setIntelligence(5);
        this.setAgility(5);
        this.setHealthPoints(5);
        this.setHealthPointsBase(5);
        this.setTurningPoints(3);
        this.setTurningPointsBase(3);
        this.setWeapon(new Sword());
    }
}