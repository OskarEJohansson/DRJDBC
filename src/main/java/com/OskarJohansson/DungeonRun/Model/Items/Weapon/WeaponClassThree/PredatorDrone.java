package com.OskarJohansson.DungeonRun.Model.Items.Weapon.WeaponClassThree;

import com.OskarJohansson.DungeonRun.Model.Items.Weapon.WeaponParentModel;

public class PredatorDrone extends WeaponParentModel {

    public PredatorDrone(){
        this.setWeaponID(32);
        this.setName("Predator Drone delivering Hell Fire");
        this.setWeaponClass("Drone");
        this.setDamageMin(7);
        this.setDamageMax(10);
        this.setTurningPoints(3);
        this.setMinimumLevel(5);
        this.setCost(50);
        this.setSoundOfAttack("Hell Fire rockets launching");
    }
}
