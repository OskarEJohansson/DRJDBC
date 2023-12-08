package com.OskarJohansson.DungeonRun.Model.Items.Weapon.WeaponClassThree;

import com.OskarJohansson.DungeonRun.Model.Items.Weapon.WeaponParentModel;

public class AtlanteanSword extends WeaponParentModel {

    public AtlanteanSword(){
        this.setWeaponID(31);
        this.setName("Atlantean - Sword of Conan The Barbarian");
        this.setWeaponClass("Sword");
        this.setDamageMin(7);
        this.setDamageMax(10);
        this.setTurningPoints(3);
        this.setMinimumLevel(5);
        this.setCost(50);
        this.setSoundOfAttack("TASTE THE POWER OF CONAN!");
    }
}
