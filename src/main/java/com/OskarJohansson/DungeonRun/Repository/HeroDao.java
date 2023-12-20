package com.OskarJohansson.DungeonRun.Repository;

import com.OskarJohansson.DungeonRun.Model.Characters.Hero;
import com.OskarJohansson.DungeonRun.Model.Items.Potions.HealthPotion;
import com.OskarJohansson.DungeonRun.Model.Items.Potions.PotionParentModel;
import com.OskarJohansson.DungeonRun.Model.Items.Weapon.WeaponParentModel;
import com.OskarJohansson.DungeonRun.Model.Monster.EnemyParentModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HeroDao {

    private static final String INSERT_HERO_SQL = "INSERT INTO hero (" +
                                                  "name, " +
                                                  "heroClass, " +
                                                  "strength, " +
                                                  "intelligence, " +
                                                  "agility, " +
                                                  "healthPoints, " +
                                                  "healthPointsBase, " +
                                                  "turningPoints, " +
                                                  "turningPointsBase, " +
                                                  "experiencePoints, " +
                                                  "gold, " +
                                                  "level, " +
                                                  "killList, " +
                                                  "deathCount, " +
                                                  "codeBreaker, " +
                                                  "weaponID) " +
                                                  "VALUES (" +
                                                  "?, " +
                                                  "?, " +
                                                  "?, " +
                                                  "?, " +
                                                  "?, " +
                                                  "?, " +
                                                  "?, " +
                                                  "?, " +
                                                  "?, " +
                                                  "?, " +
                                                  "?, " +
                                                  "?, " +
                                                  "?, " +
                                                  "?, " +
                                                  "?, " +
                                                  "?)";
    private static final String INSERT_SAVEHERO_SQL = "INSERT INTO HeroSave (heroID) Value(?)";

    private static final String UPDATE_HERO_SQL = "UPDATE hero SET " +
                                                  "strength = ?, " +
                                                  "intelligence = ?, " +
                                                  "agility = ?, " +
                                                  "healthPoints = ?, " +
                                                  "healthPointsBase = ?, " +
                                                  "turningPoints = ?, " +
                                                  "turningPointsBase = ?, " +
                                                  "experiencePoints = ?, " +
                                                  "gold = ?, " +
                                                  "level = ?, " +
                                                  "killList = ?, " +
                                                  "deathCount = ?, " +
                                                  "codeBreaker = ?, " +
                                                  "weaponID = ? " +
                                                  "WHERE heroID = ? ";


    public boolean saveHero(Hero hero) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (hero.getId() == null) {
                PreparedStatement statement = conn.prepareStatement(INSERT_HERO_SQL, Statement.RETURN_GENERATED_KEYS);

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
                statement.setInt(16, hero.getWeapon().getWeaponID());


                int rowsInserted = statement.executeUpdate();

                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    hero.setId(generatedKeys.getLong(1));
                }

                PreparedStatement saveHeroStmt = conn.prepareStatement(INSERT_SAVEHERO_SQL);

                saveHeroStmt.setLong(1, hero.getId());

                rowsInserted += saveHeroStmt.executeUpdate();

                PreparedStatement saveStashStatement = conn.prepareStatement("INSERT INTO Stash (heroID) Values(?)", Statement.RETURN_GENERATED_KEYS);
                saveStashStatement.setLong(1, hero.getId());
                rowsInserted += saveStashStatement.executeUpdate();

                ResultSet generatedStashID = saveStashStatement.getGeneratedKeys();

                rowsInserted += saveStashStatement.executeUpdate();

                if (generatedStashID.next()) {
                    hero.setStashID(generatedKeys.getLong(1));
                }
                PreparedStatement saveStashIDInHero = conn.prepareStatement("UPDATE Hero SET stashID = ? WHERE (heroID) = ? ");
                saveStashIDInHero.setLong(1,hero.getStashID());
                saveStashIDInHero.setLong(2,hero.getStashID());

                rowsInserted += saveStashIDInHero.executeUpdate();

                PreparedStatement saveItemInStash = conn.prepareStatement("INSERT INTO uniqueStash(stashID, itemID) VALUES (?, ?)");

                for (PotionParentModel potion : hero.getPotionStash()) {
                    saveItemInStash.setLong(1, hero.getStashID());
                    saveItemInStash.setLong(2, potion.getPotionID());

                    rowsInserted += saveItemInStash.executeUpdate();
                }

                PreparedStatement saveKillList = conn.prepareStatement("INSERT INTO KillListBridgeTable (heroID) Values(?)", Statement.RETURN_GENERATED_KEYS);
                saveKillList.setLong(1, hero.getId());
                rowsInserted += saveKillList.executeUpdate();

                ResultSet generatedMonsterKillListID = saveKillList.getGeneratedKeys();

                if(generatedMonsterKillListID.next()){
                    hero.setKillListID(generatedMonsterKillListID.getLong(1));
                }

                PreparedStatement saveUniqueKillList = conn.prepareStatement("INSERT INTO MonsterKillList (monsterKillListID, monsterName, timeOfDeath) Values(?,?,?)");

                for(EnemyParentModel monster : hero.getKillStats()){
                    saveUniqueKillList.setLong(1, hero.getKillListID());
                    saveUniqueKillList.setString(2, monster.getName());
                    saveUniqueKillList.setTimestamp(3, monster.getTimeOfDeath());

                    System.out.println(hero.getKillListID() + monster.getName() +  monster.getTimeOfDeath());
                    rowsInserted += saveUniqueKillList.executeUpdate();
                }

                return rowsInserted > 0;

            } else {
                System.out.println(hero.getWeapon().getWeaponID());
                PreparedStatement statement = conn.prepareStatement(UPDATE_HERO_SQL);
                statement.setInt(1, hero.getStrength());
                statement.setInt(2, hero.getIntelligence());
                statement.setInt(3, hero.getAgility());
                statement.setInt(4, hero.getHealthPoints());
                statement.setInt(5, hero.getHealthPointsBase());
                statement.setInt(6, hero.getTurningPoints());
                statement.setInt(7, hero.getTurningPointsBase());
                statement.setInt(8, hero.getExperiencePoints());
                statement.setInt(9, hero.getGold());
                statement.setInt(10, hero.getLevel());
                statement.setInt(11, hero.getKillList());
                statement.setInt(12, hero.getDeathCount());
                statement.setInt(13, hero.getCodeBreaker());
                statement.setInt(14, hero.getWeapon().getWeaponID());
                statement.setLong(15, hero.getId());

                int rowsInserted = statement.executeUpdate();

                PreparedStatement deleteStash = conn.prepareStatement("TRUNCATE TABLE  uniqueStash  WHERE stashID = ? ");
                deleteStash.setLong(1,hero.getStashID());
                deleteStash.executeQuery();

                PreparedStatement saveItemInStash = conn.prepareStatement("INSERT INTO uniqueStash(stashID, itemID) VALUES (?, ?)");
                saveItemInStash.setLong(3, hero.getStashID());


                for (PotionParentModel potion : hero.getPotionStash()) {
                    saveItemInStash.setLong(1, hero.getStashID());
                    saveItemInStash.setLong(2, potion.getPotionID());


                    rowsInserted += saveItemInStash.executeUpdate();
                }

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
            PreparedStatement statement = conn.prepareStatement("SELECT h.*, w.name, w.weaponClass, w.DamageMin, w.DamageMax, w.TurningPoints, w.MinimumLevel, w.Cost, w.SoundOfAttack\n" +
                                                                "FROM hero h\n" +
                                                                "INNER JOIN weapon w ON h.weaponID = w.weaponID;");
            ResultSet resultSet = statement.executeQuery();
            {
                while (resultSet.next()) {
                    Hero hero = new Hero();
                    hero.setId(resultSet.getLong("heroID"));
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
                    weapon.setName(resultSet.getString("name"));
                    weapon.setWeaponClass(resultSet.getString("weaponClass"));
                    weapon.setDamageMin(resultSet.getInt("DamageMin"));
                    weapon.setDamageMax(resultSet.getInt("DamageMax"));
                    weapon.setTurningPoints(resultSet.getInt("TurningPoints"));
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
            PreparedStatement statement = conn.prepareStatement("SELECT h.*, w.name, w.weaponClass, w.DamageMin, w.DamageMax, w.turningPoints, w.MinimumLevel, w.Cost, w.SoundOfAttack " +
                                                                "FROM hero h " +
                                                                "INNER JOIN weapon w ON h.weaponID = w.weaponID " +
                                                                "WHERE heroID = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                hero = new Hero();
                hero.setId(resultSet.getLong("heroID"));
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
                hero.setStashID(resultSet.getLong("stashID"));
                hero.setId(resultSet.getLong("heroID"));

                WeaponParentModel weapon = new WeaponParentModel();
                weapon.setName(resultSet.getString("w.name"));
                weapon.setWeaponID(resultSet.getInt("weaponID"));
                weapon.setWeaponClass(resultSet.getString("weaponClass"));
                weapon.setDamageMin(resultSet.getInt("DamageMin"));
                weapon.setDamageMax(resultSet.getInt("DamageMax"));
                weapon.setTurningPoints(resultSet.getInt("TurningPoints"));
                weapon.setMinimumLevel(resultSet.getInt("MinimumLevel"));
                weapon.setCost(resultSet.getInt("Cost"));
                weapon.setSoundOfAttack(resultSet.getString("SoundOfAttack"));

                hero.setWeapon(weapon);

                PreparedStatement loadItems = conn.prepareStatement(
                        "SELECT u.*, i.itemID, i.itemName, i.itemValue " +
                        "FROM uniqueStash u " +
                        "INNER JOIN Item i ON i.itemID = u.itemID " +
                        "WHERE stashID = ?"
                );

                loadItems.setLong(1, hero.getStashID());
                ResultSet loadedItems = loadItems.executeQuery();

                while (loadedItems.next()) {
                    HealthPotion item = new HealthPotion();
                    item.setPotionID(loadedItems.getInt("itemID"));
                    item.setPotionName(loadedItems.getString("itemName"));
                    item.setPotionValue(loadedItems.getInt("itemValue"));
                    hero.addPotionStash(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hero;
    }
}
