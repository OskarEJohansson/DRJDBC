package com.OskarJohansson.DungeonRun.Model.Characters;

import com.OskarJohansson.DungeonRun.Model.Items.Potions.HealthPotion;
import com.OskarJohansson.DungeonRun.Model.Items.Potions.PotionParentModel;
import com.OskarJohansson.DungeonRun.Model.Items.Weapon.WeaponParentModel;
import com.OskarJohansson.DungeonRun.Model.Monster.EnemyParentModel;

import java.util.ArrayList;
import java.util.List;

public class Hero{

    private Long id;
    private String name;
    private WeaponParentModel weapon;
    private String heroClass;
    private int strength;
    private int intelligence;
    private int agility;
    private int healthPoints;
    private int healthPointsBase;
    private int turningPoints;
    private int turningPointsBase;
    private int experiencePoints;
    private int gold;
    private int goldBase;
    private int level;
    private int killList;
    private int deathCount;
    private int codeBreaker;
    private List<PotionParentModel> potionStash;
    private List<EnemyParentModel> killStats;
    private Long stashID;
    private Long killListID;

    public Hero() {
        this.healthPoints = 5;
        this.healthPointsBase = 5;
        this.turningPoints = 1;
        this.turningPointsBase = 1;
        this.experiencePoints = 0;
        this.gold = 100;
        this.goldBase = 0;
        this.level = 10;
        this.killList = 0;
        this.deathCount = 0;
        this.codeBreaker = 0;
        this.potionStash = new ArrayList<>();
        this.killStats = new ArrayList<>();

    }

    public Long getStashID() {
        return this.stashID ;
    }

    public void setStashID(Long stashID) {
        this.stashID = stashID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WeaponParentModel getWeapon() {
        return weapon;
    }

    public void setWeapon(WeaponParentModel weapon) {
        this.weapon = weapon;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void addStrength(int strength) {
        this.strength += strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void addIntelligence(int intelligence) {
        this.intelligence += intelligence;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void addAgility(int agility) {
        this.agility += agility;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void addHealthPoints(int healthPoints) {
        this.healthPoints += healthPoints;
    }

    public int getHealthPointsBase() {
        return healthPointsBase;
    }

    public void setHealthPointsBase(int healthPointsBase) {
        this.healthPointsBase = healthPointsBase;
    }

    public void addHealthPointsBase(int healthPointsBase) {
        this.healthPointsBase += healthPointsBase;
    }

    public void resetHealthPoints() {
        this.healthPoints = this.healthPointsBase;
    }

    public int getTurningPoints() {
        return turningPoints;
    }

    public void setTurningPoints(int turningPoints) {
        this.turningPoints = turningPoints;
    }

    public void addTurningPoints(int turningPoints) {
        this.turningPoints += turningPoints;
    }

    public int getTurningPointsBase() {
        return turningPointsBase;
    }

    public void setTurningPointsBase(int turningPointsBase) {
        this.turningPointsBase = turningPointsBase;
    }

    public void addTurningPointsBase(int turningPointsBase) {
        this.turningPointsBase += turningPointsBase;
    }

    public void resetTurningPoints() {
        this.turningPoints = turningPointsBase;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public void addExperiencePoints(int experiencePoints) {
        this.experiencePoints += experiencePoints;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void addGold(int gold) {
        this.gold += gold;
    }

    public void resetGold() {
        this.gold = this.goldBase;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void addLevel(int level) {
        this.level += level;
    }

    public int getKillList() {
        return killList;
    }

    public void setKillList(int killList) {
        this.killList = killList;
    }

    public void addKillList(int killList) {
        this.killList += killList;
    }

    public int getDeathCount() {
        return deathCount;
    }

    public void setDeathCount(int deathCount) {
        this.deathCount = deathCount;
    }

    public void addDeathCount(int deathCount) {
        this.deathCount += deathCount;
    }

    public int getCodeBreaker() {
        return codeBreaker;
    }

    public void setCodeBreaker(int codeBreaker) {
        this.codeBreaker = codeBreaker;
    }

    public List<PotionParentModel> getPotionStash() {
        return potionStash;
    }

    public void addPotionStash(HealthPotion potion) {
        this.potionStash.add(potion);
    }

    public List<EnemyParentModel> getKillStats() {
        return killStats;
    }

    public void setKillStats(List<EnemyParentModel> killStats) {
        this.killStats = killStats;
    }

    public void addToKillStats(EnemyParentModel killstats){
        this.killStats.add(killstats);
    }

    public Long getKillListID() {
        return killListID;
    }

    public void setKillListID(Long killListID) {
        this.killListID = killListID;
    }
}
