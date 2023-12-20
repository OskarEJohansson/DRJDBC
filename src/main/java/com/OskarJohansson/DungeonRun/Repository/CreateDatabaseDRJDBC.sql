#DROP DATABASE IF EXISTS DRJDBC

#CREATE DATABASE IF NOT EXISTS DRJDBC

DELETE FROM MonsterKillList WHERE monsterKillListID = 1

CREATE TABLE Weapon (
	weaponID INT PRIMARY KEY NOT NULL,
    name VARCHAR(50) NOT NULL,
    weaponClass VARCHAR(50) NOT NULL,
    damageMin INT NOT NULL,
    damageMax INT NOT NULL,
    turningPoints INT NOT NULL,
    minimumLevel INT NOT NULL,
    cost INT NOT NULL,
    soundOfAttack VARCHAR(50) NOT NULL
    );
   

CREATE TABLE Stash (
    stashID BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    heroID BIGINT NOT NULL
	);


CREATE TABLE Hero (
    heroID BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(50),
    heroClass VARCHAR(50) NOT NULL,
    strength INT NOT NULL,
    intelligence INT NOT NULL,
    agility INT NOT NULL,
    healthPoints INT NOT NULL,
    healthPointsBase INT NOT NULL,
    turningPoints INT NOT NULL,
    turningPointsBase INT NOT NULL,
    experiencePoints INT NOT NULL,
    gold INT NOT NULL,
    level INT NOT NULL,
    killList INT NOT NULL,
    deathCount INT NOT NULL,
    codeBreaker INT NOT NULL,
	weaponID INT NOT NULL,
	stashID BIGINT NULL,
	killListID BIGINT NULL,
    FOREIGN KEY (weaponID) REFERENCES Weapon(weaponID),
    FOREIGN KEY (stashID) REFERENCES Stash(stashID)
    );

CREATE TABLE HeroSave (
	heroSave BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	heroID BIGINT NOT NULL,
    FOREIGN KEY (heroID) REFERENCES Hero(heroID)
    );


CREATE TABLE Item (
	itemID BIGINT PRIMARY KEY NOT NULL,
    itemName VARCHAR(50) NOT NULL,
    itemValue INT NOT NULL,
    uniqueItemID BIGINT NOT NULL
    );
   
CREATE TABLE UniqueStash (
	stashID BIGINT NOT NULL,
	itemID BIGINT NOT NULL,
	FOREIGN KEY (stashID) REFERENCES Stash(stashID),
	FOREIGN KEY (itemID) REFERENCES Item(itemID)
	);

CREATE TABLE KillListBridgeTable (
    monsterKillListID BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    heroID BIGINT NOT NULL
);

CREATE TABLE MonsterKillList (
	monsterID BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    monsterKillListID BIGINT NOT NULL,
    monsterName VARCHAR(30) NOT NULL,
    timeOfDeath TIMESTAMP NOT NULL,
    FOREIGN KEY (monsterKillListID) REFERENCES KillListBridgeTable(monsterKillListID)
);

INSERT INTO Weapon (
	weaponID, 
	name,
	weaponClass,
	damageMin, 
	damageMax, 
	turningPoints, 
	minimumLevel,
	cost, 
	soundOfAttack)
Values(
	11,
	"Wooden Sword",
	"Sword",
	1,
	3, 
	1, 
	1, 
	0,
	"Kling Kling"  
	);
	
INSERT INTO Weapon (
	weaponID, 
	name,
	weaponClass,
	damageMin, 
	damageMax, 
	turningPoints, 
	minimumLevel,
	cost, 
	soundOfAttack)
Values(
	12,
	"Toy store Drone",
	"Drone",
	1,
	3, 
	1, 
	1, 
	0,
	"Pew Pew"  
	);
	
INSERT INTO Weapon (
	weaponID, 
	name,
	weaponClass,
	damageMin, 
	damageMax, 
	turningPoints, 
	minimumLevel,
	cost, 
	soundOfAttack)
Values(
	22,
	"Spot The Killer Robot Dog",
	"Drone",
	3,
	5, 
	2, 
	3, 
	20,
	"Sniff sniff, growl growl, skweek skweek"
	);
	
INSERT INTO Weapon (
	weaponID, 
	name,
	weaponClass,
	damageMin, 
	damageMax, 
	turningPoints, 
	minimumLevel,
	cost, 
	soundOfAttack)
Values(
	21,
	"Two Handed Sword",
	"Sword",
	3,
	5, 
	2, 
	3, 
	20,
	"Slash slash hack hack!"
	);
	
INSERT INTO Weapon (
	weaponID, 
	name,
	weaponClass,
	damageMin, 
	damageMax, 
	turningPoints, 
	minimumLevel,
	cost, 
	soundOfAttack)
Values(
	31,
	"Atlantean - Sword of Conan The Barbarian",
	"Sword",
	7,
	10, 
	3, 
	5, 
	50,
	"TASTE THE POWER OF CONAN!"
	);
	
INSERT INTO Weapon (
	weaponID, 
	name,
	weaponClass,
	damageMin, 
	damageMax, 
	turningPoints, 
	minimumLevel,
	cost, 
	soundOfAttack)
Values(
	32,
	"Predator Drone delivering Hell Fire",
	"Drone",
	7,
	10, 
	3, 
	5, 
	50,
	"Hell Fire rockets launching"
	);


   
 