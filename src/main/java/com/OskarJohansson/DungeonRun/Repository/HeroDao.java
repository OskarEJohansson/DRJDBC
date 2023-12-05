package com.OskarJohansson.DungeonRun.Repository;

import com.OskarJohansson.DungeonRun.Model.Characters.Hero;
import com.OskarJohansson.DungeonRun.Model.Items.Weapon.WeaponParentModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HeroDao {

    private static final String INSERT_HERO_SQL = "INSERT INTO heroes (name, heroClass, strength, intelligence, agility, healthPoints, healthPointsBase, turningPoints, turningPointsBase, experiencePoints, gold, level, killList, deathCount, codeBreaker, weaponName, weaponClass, damageMin, damageMax, turnpoints, minimumLevel, cost, soundOfAttack) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_HERO_SQL = "UPDATE heroes SET name = ?, heroClass = ?, strength = ?, intelligence = ?, agility = ?, healthPoints = ?, healthPointsBase = ?, turningPoints = ?, turningPointsBase = ?, experiencePoints = ?, gold = ?, level = ?, killList = ?, deathCount = ?, codeBreaker = ?, weaponName = ?, weaponClass = ?, damageMin = ?, damageMax = ?, turnpoints = ?, minimumLevel = ?, cost = ?, soundOfAttack = ? WHERE id = ?";


    public boolean saveHero(Hero hero) {                                       
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Create PreparedStatement
            if (hero.getId() == null) {
                PreparedStatement statement = conn.prepareStatement(INSERT_HERO_SQL);
                statement.setString(1, hero.getName());
                statement.setString(2, hero.getHeroClass());
                statement.setInt(3, hero.getStrength());
                statement.setInt(4, hero.getIntelligence());
                statement.setInt(5, hero.getAgility());
                statement.setInt(6, hero.getHealthPoints());
                statement.setInt(7, hero.getHealthPointsBase());
                statement.setInt(8, hero.getTurningPoints());
                statement.setInt(9, hero.getTurningPointsBase());
                statement.setInt(10, hero.getExperiencePoints());
                statement.setInt(11, hero.getGold());
                statement.setInt(12, hero.getLevel());
                statement.setInt(13, hero.getKillList());
                statement.setInt(14, hero.getDeathCount());
                statement.setInt(15, hero.getCodeBreaker());
                statement.setString(16, hero.getWeapon().getName());
                statement.setString(17, hero.getWeapon().getWeaponClass());
                statement.setInt(18, hero.getWeapon().getDamageMin());
                statement.setInt(19, hero.getWeapon().getDamageMax());
                statement.setInt(20, hero.getWeapon().getTurnPoints());
                statement.setInt(21, hero.getWeapon().getMinimumLevel());
                statement.setInt(22, hero.getWeapon().getCost());
                statement.setString(23, hero.getWeapon().getSoundOfAttack());

                int rowsInserted = statement.executeUpdate();

                return rowsInserted > 0;

            } else {
                PreparedStatement statement = conn.prepareStatement(UPDATE_HERO_SQL);
                statement.setString(1, hero.getName());
                statement.setString(2, hero.getHeroClass());
                statement.setInt(3, hero.getStrength());
                statement.setInt(4, hero.getIntelligence());
                statement.setInt(5, hero.getAgility());
                statement.setInt(6, hero.getHealthPoints());
                statement.setInt(7, hero.getHealthPointsBase());
                statement.setInt(8, hero.getTurningPoints());
                statement.setInt(9, hero.getTurningPointsBase());
                statement.setInt(10, hero.getExperiencePoints());
                statement.setInt(11, hero.getGold());
                statement.setInt(12, hero.getLevel());
                statement.setInt(13, hero.getKillList());
                statement.setInt(14, hero.getDeathCount());
                statement.setInt(15, hero.getCodeBreaker());
                statement.setString(16, hero.getWeapon().getName());
                statement.setString(17, hero.getWeapon().getWeaponClass());
                statement.setInt(18, hero.getWeapon().getDamageMin());
                statement.setInt(19, hero.getWeapon().getDamageMax());
                statement.setInt(20, hero.getWeapon().getTurnPoints());
                statement.setInt(21, hero.getWeapon().getMinimumLevel());
                statement.setInt(22, hero.getWeapon().getCost());
                statement.setString(23, hero.getWeapon().getSoundOfAttack());
                statement.setLong(24, hero.getId());

                int rowsInserted = statement.executeUpdate();

                return rowsInserted > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public List<Hero> getAllHeroes() {
        List<Hero> heroes = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM heroes");
            ResultSet resultSet = statement.executeQuery();
            {
                while (resultSet.next()) {
                    Hero hero = new Hero();
                    hero.setId(resultSet.getLong("id"));
                    hero.setName(resultSet.getString("name"));
                    hero.setHeroClass(resultSet.getString("heroClass"));
                    hero.setStrength(resultSet.getInt("strength"));
                    hero.setIntelligence(resultSet.getInt("intelligence"));
                    hero.setAgility(resultSet.getInt("agility"));
                    hero.setHealthPoints(resultSet.getInt("healthPoints"));
                    hero.setHealthPointsBase(resultSet.getInt("healthPointsBase"));
                    hero.setTurningPoints(resultSet.getInt("turningPoints"));
                    hero.setTurningPointsBase(resultSet.getInt("turningPointsBase"));
                    hero.setExperiencePoints(resultSet.getInt("experiencePoints"));
                    hero.setGold(resultSet.getInt("gold"));
                    hero.setLevel(resultSet.getInt("level"));
                    hero.setKillList(resultSet.getInt("killList"));
                    hero.setDeathCount(resultSet.getInt("deathCount"));
                    hero.setCodeBreaker(resultSet.getInt("codeBreaker"));

                    WeaponParentModel weapon = new WeaponParentModel();
                    weapon.setName(resultSet.getString("weaponName"));
                    weapon.setWeaponClass(resultSet.getString("weaponClass"));
                    weapon.setDamageMin(resultSet.getInt("DamageMin"));
                    weapon.setDamageMax(resultSet.getInt("DamageMax"));
                    weapon.setTurnPoints(resultSet.getInt("TurnPoints"));
                    weapon.setMinimumLevel(resultSet.getInt("MinimumLevel"));
                    weapon.setCost(resultSet.getInt("Cost"));
                    weapon.setSoundOfAttack(resultSet.getString("SoundOfAttack"));

                    hero.setWeapon(weapon);
                    heroes.add(hero);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return heroes;
    }

    public Hero getHeroById(Long id) {
        Hero hero = null;

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM heroes WHERE id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                hero = new Hero();
                hero.setId(resultSet.getLong("id"));
                hero.setName(resultSet.getString("name"));
                hero.setHeroClass(resultSet.getString("heroClass"));
                hero.setStrength(resultSet.getInt("strength"));
                hero.setIntelligence(resultSet.getInt("intelligence"));
                hero.setAgility(resultSet.getInt("agility"));
                hero.setHealthPoints(resultSet.getInt("healthPoints"));
                hero.setHealthPointsBase(resultSet.getInt("healthPointsBase"));
                hero.setTurningPoints(resultSet.getInt("turningPoints"));
                hero.setTurningPointsBase(resultSet.getInt("turningPointsBase"));
                hero.setExperiencePoints(resultSet.getInt("experiencePoints"));
                hero.setGold(resultSet.getInt("gold"));
                hero.setLevel(resultSet.getInt("level"));
                hero.setKillList(resultSet.getInt("killList"));
                hero.setDeathCount(resultSet.getInt("deathCount"));
                hero.setCodeBreaker(resultSet.getInt("codeBreaker"));

                WeaponParentModel weapon = new WeaponParentModel();
                weapon.setName(resultSet.getString("weaponName"));
                weapon.setWeaponClass(resultSet.getString("weaponClass"));
                weapon.setDamageMin(resultSet.getInt("DamageMin"));
                weapon.setDamageMax(resultSet.getInt("DamageMax"));
                weapon.setTurnPoints(resultSet.getInt("TurnPoints"));
                weapon.setMinimumLevel(resultSet.getInt("MinimumLevel"));
                weapon.setCost(resultSet.getInt("Cost"));
                weapon.setSoundOfAttack(resultSet.getString("SoundOfAttack"));

                hero.setWeapon(weapon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hero;
    }
}
