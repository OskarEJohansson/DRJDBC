package com.OskarJohansson.DungeonRun.Control;

import com.OskarJohansson.DungeonRun.Model.Characters.Barbarian;
import com.OskarJohansson.DungeonRun.Model.Characters.CodeMonkey;
import com.OskarJohansson.DungeonRun.Model.Characters.Hero;
import com.OskarJohansson.DungeonRun.Model.Monster.EnemyParentModel;
import com.OskarJohansson.DungeonRun.Repository.HeroDao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class MenuControl {


    PlayerControl player;
    MapControl map;
    MenuControl menuControl;
    ShopControl shopControl;
    HeroDao heroDao;
    CombatControl combatControl;


    public MenuControl() {

        this.player = new PlayerControl();
        this.map = new MapControl();
        this.shopControl = new ShopControl();
        this.heroDao = new HeroDao();
        this.combatControl = new CombatControl();
    }

    public void showStartGameTerminalDisplay() {
        System.out.printf("""
                               
                ++++|      \033[42mWelcome to STI Dungeon Run!\033[0m      |++++
                                
                __________________________________________________________________________________                     
                #1 - Create a New Player    |   #2 - Load Game      |   #3 - Show all saved games      |
                                
                                
                """);
    }

    public void startGame(MenuControl menuControl) {

        this.menuControl = menuControl;

        showStartGameTerminalDisplay();

        boolean on = true;
        while (on)
            switch (UserInputControl.inputInt()) {
                case 1 -> {
                    menuControl.createNewPlayer(player);
                    on = false;
                }
                case 2 -> {
                    menuControl.loadPlayer(player);
                    on = false;
                }
                case 3 -> {
                    menuControl.showAllSavedPlayers();
                }
                default -> System.out.println("Input must be 1 - 3!");
            }
        menuControl.mainMenu(player, menuControl, map, shopControl, combatControl);
    }

    public void setCharacter(PlayerControl player) {

        boolean on = true;
        do {
            System.out.printf("""
                              
                    ++++|         \033[0;92mPick a Hero\033[0m        |++++
                    __________________________________________________              
                       #1 - Barbarian      |      #2 - Code Monkey    |
                                    
                    """);

            switch (UserInputControl.inputInt()) {
                case 1 -> {
                    player.setHero(new Barbarian());
                    on = false;
                }
                case 2 -> {
                    player.setHero(new CodeMonkey());
                    on = false;
                }

                default -> System.out.println("Input must be 1 - 2!");
            }
        } while (on);
    }


    public void mainMenu(PlayerControl player, MenuControl menuControl, MapControl mapControl, ShopControl shopControl, CombatControl combatControl) {
        boolean on = true;

        do {
            System.out.printf("""
                                         
                    +++++|                                          \033[42m   Main Menu   \033[0m                                                       |+++++
                    ___________________________________________________________________________________________________________________________________________                         
                    #1 - Show %s the %s's Level  |   #2 - Show Stats  |   #3 - Show Kill List  |   #4 - Show MAP   |   #5 - Enter SHOP   |   #6 - Save Game   |
                                         
                     """, player.getHero().getName(), player.getHero().getHeroClass());

            switch (UserInputControl.inputInt()) {
                case 1 -> getPlayerStats(player);
                case 2 -> getStatus(player);
                case 3 -> getKillList(player);
                case 4 -> mapMenu(mapControl, player, menuControl, combatControl);
                case 5 -> shopControl.shop(player);
                case 6 -> savePlayer(player);
                default -> System.out.println("Input must be 1 - 6!");
            }
        } while (on);
    }

    public void mapMenu(MapControl mapControl, PlayerControl player, MenuControl menuControl, CombatControl combatControl) {

        boolean on = true;
        do {
            System.out.println("""
                    +++++|                           \033[42m   Explore the world of STI!   \033[0m                            +++++
                    ________________________________________________________________________________________________________________             
                    #1 - Battle the Minions of ICA  |   #2 - Defeat the PT's of Sats    |   #3 - Challenge the Wizards of Kjell&Co  |
                    #4 - Conquer the Tower of the Teachers Lounge  |   #5 - Drink potion  |   #6 - Return to main menu  |
                                    
                    """);

            switch (UserInputControl.inputInt()) {
                case 1 -> {
                    mapControl.setMap(1);
                    System.out.println("Entering the Dungeons of Ica");
                    mapStructure(mapControl, player, menuControl, combatControl);
                }
                case 2 -> {
                    if (player.getHero().getLevel() < 3) {
                        System.out.println("You must be level 3 to enter Sats!");
                        return;
                    }
                    mapControl.setMap(2);
                    System.out.println("Entering the Dungeons of Sats");
                    mapStructure(mapControl, player, menuControl, combatControl);
                }
                case 3 -> {
                    if (player.getHero().getLevel() < 5) {
                        System.out.println("You must be level 5 to enter Kjell & Co !");
                        return;
                    }
                    mapControl.setMap(3);
                    System.out.println("Entering the Dungeons of Kjell & Co");
                    mapStructure(mapControl, player, menuControl, combatControl);

                }
                case 4 -> {
                    if (player.getHero().getLevel() < 7) {
                        System.out.println("You must be level 7 to enter Teacher Lounge!");
                    }
                    if (player.getHero().getCodeBreaker() == 0) {
                        System.out.println("You can't crack the security code to break in to the Tower! Defeat the Nerd Wizard of Kjell & Co to attain the Code Breaker!");
                        return;
                    }
                    mapControl.setMap(4);
                    System.out.println("Entering the Tower of The Teachers Lounge");
                    mapStructure(mapControl, player, menuControl, combatControl);
                }
                case 5 -> {
                    player.drinkHealthPotionOptions(player);
                    on = false;
                }
                case 6 -> {
                    System.out.println("Returning to Main Menu");
                    on = false;
                }
                default -> System.out.println("Input must be 1 - 3!");
            }
        } while (on);
    }

    public void mapStructure(MapControl mapControl, PlayerControl player, MenuControl menuControl, CombatControl combatControl) {


        boolean on = true;

        do {
            System.out.printf("""
                    +++++|                          \033[42m   Level %s   \033[0m                           |+++++
                    +++++|                      \033[42m    %s  \033[0m                       |+++++
                    _____________________________________________________________________________
                    #1 - Enter Kill Zone!    |   #2 - Enter Boss Zone!    |    #3 - Reset Level!    |    #4 - Leave Level! |
                    """, mapControl.currentLevel.getLevelNumber(), mapControl.currentLevel.getLevelName());

            switch (UserInputControl.inputInt()) {
                case 1 -> {
                    System.out.println("You are entering the kill zone!");
                    combatControl.minionBattleControl(player, mapControl, menuControl, combatControl);
                }
                case 2 -> {
                    System.out.println("You are about to challenging the stage Boss!");
                    combatControl.bossBattleControl(player, mapControl, menuControl, combatControl);
                    on = false;
                }
                case 3 -> {
                    System.out.println("You are resetting the Level!");
                    mapControl.resetLevel(mapControl);
                    on = false;
                }
                case 4 -> {
                    System.out.println("You are leaving the Level!");
                    on = false;
                }
                default -> System.out.println("Input must be 1 - 4!");

            }
        } while (on);
    }

    public boolean playerBattleOptions(PlayerControl player, MapControl mapControl, CombatControl combatControl, boolean isBossBattle) {
        boolean on = true;
        while (on) {
            displayPlayerOptionsInBattleOptions(player);

            switch (UserInputControl.inputInt()) {
                case 1 -> {
                    if (isBossBattle) {
                        combatControl.playerAttackCurrantMonster(mapControl.currentLevel.getFinalBoss(), player);
                    } else {
                        combatControl.iterateMinionMonsterListAndAttackMonster(player, mapControl);
                    }
                    on = false;
                }
                case 2 -> player.drinkHealthPotionOptions(player);
                case 3 -> {
                    player.flee(player);
                    return false;
                }
                default -> System.out.println("Input must be 1-3!");
            }
        }
        return true;
    }

    public void setNameAndCharacter(PlayerControl player) {
        setCharacter(player);
        System.out.printf("You have chosen to play as a %s\n", player.getHero().getHeroClass());
        System.out.println("Chose a name for your Hero: ");
        player.getHero().setName(new Scanner(System.in).nextLine());
        System.out.printf("Welcome %s the %s ", player.getHero().getName(), player.getHero().getHeroClass());
    }

    public void getPlayerStats(PlayerControl player) {
        player.levelUp(player);
        System.out.printf("""
                ++++|                                                                       \033[0;35m    Stats   \033[0m                                                      |++++
                ___________________________________________________________________________________________________________________________________________________            
                Level  \033[1;33m%d\033[0m   |   Experience Points   \033[1;33m%d/%d\033[0m  |   Strength    \033[1;35m%d\033[0m  |   Intelligence   \033[1;32m%d\033[0m  |   Agility   \033[1;31m%d\033[0m  |   Weapon   \033[4;31m%s\033[0m    |   HealthPotions   \033[0;34m%d\033[0m   |
                                
                """, player.getHero().getLevel(), player.getHero().getExperiencePoints(), player.getHero().getLevel() * 10, player.getHero().getStrength(), player.getHero().getIntelligence(), player.getHero().getAgility(), player.getHero().getWeapon().getName(), player.getHero().getPotionStash().size());
    }

    public void getStatus(PlayerControl player) {
        player.levelUp(player);
        System.out.printf("""
                ++++|                                                                  \033[0;35m  Stats   \033[0m                                                |++++
                ______________________________________________________________________________________________________________________________________
                %s the %s   |   Death count   \033[0;31m%d\033[0m    |   Kill count   \033[0;31m%d\033[0m  |   Health Points   \033[0;34m%d/%d\033[0m  |   Turning Points  \033[1;31m%d/%d\033[0m   |   Gold    \033[1;33m%d\033[0m |
                                
                """, player.getHero().getName(), player.getHero().getHeroClass(), player.getHero().getDeathCount(), player.getHero().getKillList(), player.getHero().getHealthPoints(), player.getHero().getHealthPointsBase(), player.getHero().getTurningPoints(), player.getHero().getTurningPointsBase(), player.getHero().getGold());
    }

    public void getKillList(PlayerControl player) {

        for (EnemyParentModel monster : player.getHero().getKillStats()) {

            Timestamp time = monster.getTimeOfDeath();
            LocalDateTime localDateTime = time.toLocalDateTime();
            String formattedDateTime = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            System.out.printf("""
                    ++++|       \033[0;35m  Monster Kill List  \033[0m        |++++
                    _______________________________________________________________
                    Monster: %s     |    Time of Death:    \033[0;31m%s\033[0m   |

                    """, monster.getName(), formattedDateTime);
        }
    }


    public void displayPlayerOptionsInBattleOptions(PlayerControl player) {
        System.out.printf("""
                   
                   +++++|                  Actions                 |+++++
                   ______________________________________________________\s
                   #1 - Attack!  |   #2 - Drink Potion |   #3 - Flee!   |
                                
                >>>>    %s's turn! <<<<
                >>>>  Turning points: %d  <<<<
                                
                Choose Action:\s
                                
                """, player.getHero().getName(), player.getHero().getTurningPoints());
    }

    public void savePlayer(PlayerControl player) {
        heroDao.saveHero(player.getHero());
        System.out.println("Game saved!");
    }

    public void loadPlayer(PlayerControl player) {
        System.out.println("Input ID to choose player to load!");
        long input = (long) UserInputControl.inputInt();
        player.setHero(heroDao.getHeroById(input));
    }

    public void showAllSavedPlayers() {
        List<Hero> heroList = heroDao.getAllHeroes();
        for (Hero hero : heroList)
            if (hero != null) {
                System.out.printf("""
                        ++++|                                              \033[0;35m    Player   \033[0m                                           |++++
                        _________________________________________________________________________________________________________________________________         
                        ID  \033[1;33m%d\033[0m   |   Name   \033[1;33m%s\033[0m  |   Class    \033[1;35m%s\033[0m  |   Level   \033[1;32m%d\033[0m  |
                                        
                        """, hero.getId(), hero.getName(), hero.getHeroClass(), hero.getLevel());
            }
        showStartGameTerminalDisplay();
    }


    public void createNewPlayer(PlayerControl player) {
        setNameAndCharacter(player);
    }
}

