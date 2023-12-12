package com.OskarJohansson.DungeonRun.Model.Items.Potions;


public class PotionParentModel {

    private String potionName;
    private int potionID = 11;
    private int potionValue = 10;
    private boolean isUsed = false;
    private Long uniqueItemID;

    public Long getUniqueItemID() {
        return uniqueItemID;
    }

    public void setUniqueItemID(Long uniqueItemID) {
        this.uniqueItemID = uniqueItemID;
    }

    public String getPotionName() {
        return potionName;
    }

    public void setPotionName(String potionName) {
        this.potionName = potionName;
    }

    public int getPotionID() {
        return potionID;
    }

    public void setPotionID(int potionID) {
        this.potionID = potionID;
    }

    public int getPotionValue() {
        return potionValue;
    }

    public void setPotionValue(int potionValue) {
        this.potionValue = potionValue;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public int useHealthPotion() {
        this.isUsed = true;
        return this.potionValue;
    }
}
