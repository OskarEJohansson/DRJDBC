package com.OskarJohansson.DungeonRun.Model.Items.Weapon.WeaponClassThree;

import com.OskarJohansson.DungeonRun.Model.Items.Weapon.WeaponParentModel;

public class PredatorDrone extends WeaponParentModel {

    public PredatorDrone(){
        this.setName("Predator Drone delivering Hell Fire");
        this.setWeaponClass("Drone");
        this.setDamageMin(7);
        this.setDamageMax(10);
        this.setTurnPoints(3);
        this.setMinimumLevel(5);
        this.setCost(50);
        this.setSoundOfAttack("Hell Fire rockets launching");
    }
}
