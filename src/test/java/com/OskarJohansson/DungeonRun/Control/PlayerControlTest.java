package com.OskarJohansson.DungeonRun.Control;

import com.OskarJohansson.DungeonRun.Model.Characters.Barbarian;
import com.OskarJohansson.DungeonRun.Model.Characters.CodeMonkey;
import com.OskarJohansson.DungeonRun.Model.Items.Potions.HealthPotion;
import com.OskarJohansson.DungeonRun.Model.Items.Weapon.WeaponClassTwo.Spot;
import com.OskarJohansson.DungeonRun.Model.Items.Weapon.WeaponClassTwo.TwoHandSword;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;


class PlayerControlTest {

    private  PlayerControl player;
    private  PlayerControl player2;

    @BeforeEach
    void setUp() {
        player = new PlayerControl();
        player.setHero(new Barbarian());
        player.getHero().setWeapon(new TwoHandSword());

        player2 = new PlayerControl();
        player2.setHero(new CodeMonkey());
        player2.getHero().setWeapon(new Spot());

    }

    @Test
    void attack() {

        int playerAttack = player.attack(player);
        System.out.println(playerAttack);

        int player2Attack = player2.attack(player2);
        System.out.println(player2Attack);

        assertThat(playerAttack, greaterThanOrEqualTo(player.getHero().getWeapon().getDamageMin()));
        assertThat(playerAttack, lessThanOrEqualTo(player.getHero().getWeapon().getDamageMax()));

        assertThat(player2Attack, greaterThanOrEqualTo(player2.getHero().getWeapon().getDamageMin()));
        assertThat(player2Attack, lessThanOrEqualTo(player2.getHero().getWeapon().getDamageMax()));
    }


    @Test
    void block() {

    }

    @Test
    void takeDamage() {
        // Player is fails to block
        System.out.println("Player health: " + player.getHero().getHealthPoints());
        player.takeDamage(player, false, 10);

        // PLayer is killed
        Assert.assertEquals(0, player.getHero().getHealthPoints());
        Assert.assertTrue(player.isPlayerKilledInCombat(player));


        // Player 2 blocks successfully
        System.out.println("Player health: " + player2.getHero().getHealthPoints());
        player.takeDamage(player2, true, 10);

        // Player 2 is not killed
        Assert.assertEquals(10, player2.getHero().getHealthPoints());
        Assert.assertFalse(player2.isPlayerKilledInCombat(player2));
    }

    @Test
    void flee() {
        Assert.assertTrue(player.flee(player));
        player.getHero().setTurningPoints(-4);
        Assert.assertFalse(player.flee(player));
    }

    @Test
    void drinkHealthPotion() {
        player.getHero().addPotionStash(new HealthPotion());
        Assert.assertFalse(player.drinkHealthPotionOptions(player));
        player.getHero().setHealthPoints(-1);
        Assert.assertTrue(player.drinkHealthPotionOptions(player));
        Assert.assertFalse(player.drinkHealthPotionOptions(player));
    }

    @Test
    void levelUp() {
        player.getHero().setExperiencePoints(9);
        Assert.assertFalse(player.levelUp(player));
        player.getHero().setExperiencePoints(2);
        Assert.assertTrue(player.levelUp(player));
    }

    @Test
    void drinkHealthPotionOptions() {

        Assertions.assertTrue(player.playerHasNoHealthPotions(player));

        player.getHero().addPotionStash(new HealthPotion());
        Assert.assertEquals(player.getHero().getPotionStash().size(), 1);

        for (HealthPotion potion : player.getHero().getPotionStash()) {
            Assert.assertTrue(player.playerHasMaxHealthPoints(player));
            player.getHero().setHealthPoints(-1);
            Assert.assertTrue(player.drinkHealtPotion(player, potion));
        }

        player.removeUsedPotionsFromPotionStash(player);
        Assert.assertEquals(player.getHero().getPotionStash().size(), 0);

    }

}