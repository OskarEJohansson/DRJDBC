package com.OskarJohansson.DungeonRun.Control;

import com.OskarJohansson.DungeonRun.Model.Items.Potions.HealthPotion;
import com.OskarJohansson.DungeonRun.Model.Items.Potions.PotionParentModel;
import com.OskarJohansson.DungeonRun.Model.Items.Weapon.WeaponClassThree.AtlanteanSword;
import com.OskarJohansson.DungeonRun.Model.Items.Weapon.WeaponClassThree.PredatorDrone;
import com.OskarJohansson.DungeonRun.Model.Items.Weapon.WeaponClassTwo.Spot;
import com.OskarJohansson.DungeonRun.Model.Items.Weapon.WeaponClassTwo.TwoHandSword;
import com.OskarJohansson.DungeonRun.Model.Items.Weapon.WeaponParentModel;

public class ShopControl {


    public void shop(PlayerControl player) {

        boolean on = true;

        System.out.println("Welcome to the Shop!");

        do {
            System.out.printf("""
                                    
                    +++++|                                \033[0;32m    Shop Menu   \033[0m                                 |+++++
                    ____________________________________________________________________________________________________
                    #1 - Buy Swords  |   #2 - Buy Drones  |   #3 - Buy Health Potion  |   #4 - Exit SHOP   |
                                    
                    You have \033[1;33m%d\033[0m gold
                    """, player.getHero().getGold());

            switch (UserInputControl.inputInt()) {
                case 1 -> buySwords(player);
                case 2 -> buyDrone(player);
                case 3 -> buyPotion(player);
                case 4 -> {
                    System.out.println("You are leaving the Shop");
                    on = false;
                }
                case 5 -> System.out.println("Input must be 1-4!");
            }
        } while (on);
    }

    public void buyPotion(PlayerControl player) {
        if (player.getHero().getGold() < 5) {
            System.out.println("You can't afford the potion. Kill more minions and collect more gold!");
        } else {
            System.out.println("You purchased a 10 hp Potion for 5 gold.");
            player.getHero().addGold(-5);
            player.addHealthPotionToStash(player, new HealthPotion());
        }
    }

    public void buyWeapon(PlayerControl player, WeaponParentModel weapon){
        if(weapon.getWeaponClass()=="Barbarian" && player.getHero().getHeroClass() != weapon.getWeaponClass()){
            if(player.getHero().getHeroClass() == "Code Monkey"){System.out.println("You must be a Barbarian to be able to lift this sword!");
                return;}
            if(weapon.getWeaponClass()=="Code Monkey" && player.getHero().getHeroClass() == "Barbarian"){
                System.out.println("You must be a Code Monkey to be able to control this Drone!");
                return;}
        }
        if(player.getHero().getLevel() < weapon.getMinimumLevel()){
            System.out.println("You need to level up to equip this weapon!");
            return;
        }
        if(player.getHero().getGold() < weapon.getCost()){
            System.out.println("You need more gold to purchase this item!");
            return;
        }
        System.out.printf("You bought %s for %d", weapon.getName(), weapon.getCost());
        player.getHero().setWeapon(weapon);
        player.getHero().addGold(-weapon.getCost());
    }

    public void buyDrone(PlayerControl player){

        boolean on = true;

        System.out.printf("""
                                    
                    +++++|                                \033[0;32m    Drone Menu   \033[0m                                 |+++++
                    ____________________________________________________________________________________________________
                    #1 - Buy Spot - The Killer Robot Dog    -   20 Gold     |     #2 - Buy Predator Drone  -  50 gold  |    #3 - Leave Drone menu  |
                    """);

        do {
            switch (UserInputControl.inputInt()) {
                case 1 -> {
                    buyWeapon(player, new Spot());
                    on = false;
                }
                case 2 -> {
                    buyWeapon(player, new PredatorDrone());
                    on = false;
                }
                case 3 -> {
                    on = false;
                }
                default -> System.out.println("Input must be 1 - 3!");
            }
        }while (on);
    }

    public void buySwords(PlayerControl player){

        System.out.printf("""
                                    
                    +++++|                                \033[0;32m    Sword Menu   \033[0m                                 |+++++
                    ____________________________________________________________________________________________________
                    #1 - Buy Two-handed Sword    -   20 Gold     |     #2 - Buy Atlantean Sword  -  50 gold  |    #3 - Leave Drone menu  | 
                    """);

        boolean on = true;
        do {
            switch (UserInputControl.inputInt()) {
                case 1 -> {
                    System.out.println("Buy Two-handed Sword for 20 gold");
                    buyWeapon(player, new TwoHandSword());
                    on = false;
                }
                case 2 -> {
                    System.out.println("Buy Atlantean Sword for 50 gold");
                    buyWeapon(player, new AtlanteanSword());
                    on = false;
                }
                case 3 -> {
                    System.out.println("Leave Sword Menu");
                    on = false;
                }
                default -> System.out.println("Input must be 1 - 3!");
            }
        }while (on);
    }
}


