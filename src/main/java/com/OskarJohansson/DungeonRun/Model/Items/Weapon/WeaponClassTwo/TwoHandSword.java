package com.OskarJohansson.DungeonRun.Model.Items.Weapon.WeaponClassTwo;

import com.OskarJohansson.DungeonRun.Model.Items.Weapon.WeaponParentModel;

public class TwoHandSword extends WeaponParentModel {

    public TwoHandSword(){
        this.setName("Two Handed Sword");
        this.setWeaponClass("Sword");
        this.setDamageMin(3);
        this.setDamageMax(5);
        this.setTurnPoints(2);
        this.setMinimumLevel(3);
        this.setCost(20);
        this.setSoundOfAttack("Slash slash hack hack!");
    }
}
