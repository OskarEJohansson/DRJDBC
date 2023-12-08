package com.OskarJohansson.DungeonRun.Model.Items.Potions;


public class HealthPotion extends PotionParentModel{

    private String potionName ="Health Potion";
    private int potionID = 11;
    private int potionValue = 10;

    public HealthPotion() {
        this.setPotionID(this.potionID);
        this.setPotionName(this.potionName);
        this.setPotionValue(this.potionValue);
    }

}
