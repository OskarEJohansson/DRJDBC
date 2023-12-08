package com.OskarJohansson.DungeonRun.Model.Items.Weapon.WeaponClassOne;

import com.OskarJohansson.DungeonRun.Model.Items.Weapon.WeaponParentModel;

public class Sword extends WeaponParentModel {

    public Sword(){
        this.setWeaponID(11);
        this.setName("Sword");
        this.setWeaponClass("Drone");
        this.setDamageMin(1);
        this.setDamageMax(3);
        this.setTurningPoints(1);
        this.setMinimumLevel(1);
        this.setSoundOfAttack("Kling kling");
    }
}
