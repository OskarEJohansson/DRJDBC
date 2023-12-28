package com.OskarJohansson.DungeonRun.Model.Monster;

import com.OskarJohansson.DungeonRun.Control.MapControl;
import com.OskarJohansson.DungeonRun.Control.MenuControl;
import com.OskarJohansson.DungeonRun.Control.PlayerControl;

import java.sql.Timestamp;
import java.util.Random;

public class EnemyParentModel implements iEnemy {

    private String name;
    private int healthPoints;
    private int healthPointsBase;
    private int damageMin;
    private int damageMax;
    private int turningPoints;
    private int turningPointsBase;
    private int attackCost;
    private int experiencePoints;
    private int gold;
    private int level;
    private boolean killed;
    private int blockLevel;
    private Timestamp timeOfDeath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getHealthPointsBase() {
        return healthPointsBase;
    }

    public void setHealthPointsBase(int healthPointsBase) {
        this.healthPointsBase = healthPointsBase;
    }

    public int getDamageMin() {
        return damageMin;
    }

    public void setDamageMin(int damageMin) {
        this.damageMin = damageMin;
    }

    public int getDamageMax() {
        return damageMax;
    }

    public void setDamageMax(int damageMax) {
        this.damageMax = damageMax;
    }

    public int getTurningPoints() {
        return turningPoints;
    }

    public void setTurningPoints(int turningPoints) {
        this.turningPoints = turningPoints;
    }

    public int getTurningPointsBase() {
        return turningPointsBase;
    }

    public void setTurningPointsBase(int turningPointsBase) {
        this.turningPointsBase = turningPointsBase;
    }

    public int getAttackCost() {
        return attackCost;
    }

    public void setAttackCost(int attackCost) {
        this.attackCost = attackCost;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isKilled() {
        return killed;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    public int getBlockLevel() {
        return blockLevel;
    }

    public void setBlockLevel(int blockLevel) {
        this.blockLevel = blockLevel;
    }

    public void resetTurningPoints() {
        setTurningPoints(getTurningPointsBase());
    }

    public void resetHealthPoints() {
        this.healthPoints = this.healthPointsBase;
    }

    public Timestamp getTimeOfDeath() {
        return timeOfDeath;
    }

    public void setTimeOfDeath(Timestamp timeOfDeath) {
        this.timeOfDeath = timeOfDeath;
    }

    @Override
    public boolean isBossNerdWizard() {
        return false;
    }

    @Override
    public int enemyAttack(PlayerControl player, MapControl mapControl, MenuControl menuControl) {
        this.setTurningPoints(this.getTurningPoints() - this.getAttackCost());
        return new Random().nextInt(getDamageMin(), getDamageMax());
    }

    @Override
    public boolean block() {
        if (new Random().nextInt(1, 10) < this.blockLevel) {
            System.out.printf(">>>>    \033[0;32m %s blocked the attack successfully!\033[0m    <<<<\n", this.getName());
            return true;
        }
        System.out.printf(">>>>    \033[0;31m%s fails to block the attack!\033[0m    <<<<\n", this.getName());
        return false;
    }

    @Override
    public void displayEnemyStatus() {
        System.out.printf("""
                ++++                    Enemy Stats                     ++++
                ____________________________________________________________                
                Enemy   %s  |   Health Points   %d/%d  |   Turning Points  %d  |
                                
                """, this.getName(), this.getHealthPoints(), getHealthPointsBase(), this.getTurningPoints());
    }

    @Override
    public void takeDamage(Boolean takeDamage, int damage) {
        if (!takeDamage) {
            this.setHealthPoints(this.getHealthPoints() - damage);
            System.out.printf(">>>>    \033[0;91m%s takes %d in damage!\033[0m    <<<<\n", this.getName(), damage);
            displayEnemyStatus();
        }
    }
}