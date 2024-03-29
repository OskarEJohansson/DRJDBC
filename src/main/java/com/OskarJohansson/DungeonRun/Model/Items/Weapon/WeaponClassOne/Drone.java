package com.OskarJohansson.DungeonRun.Model.Items.Weapon.WeaponClassOne;

import com.OskarJohansson.DungeonRun.Model.Items.Weapon.WeaponParentModel;

public class Drone extends WeaponParentModel {

    public Drone(){
        this.setWeaponID(12);
        this.setName("Drone");
        this.setWeaponClass("Drone");
        this.setDamageMin(1);
        this.setDamageMax(3);
        this.setTurningPoints(1);
        this.setMinimumLevel(1);
        this.setSoundOfAttack("Pew pew");
    }
}
