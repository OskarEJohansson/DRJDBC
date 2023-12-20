

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
    itemValue INT NOT NULL
    uniqueItemID BIGINT NOT NULL
    );
   
CREATE TABLE UniqueStash (
	stashID BIGINT NOT NULL,
	itemID BIGINT NOT NULL,
	FOREIGN KEY (stashID) REFERENCES Stash(stashID),
	FOREIGN KEY (itemID) REFERENCES Item(itemID)
	);

CREATE TABLE MonsterKillList (
    monsterKillListID BIGINT PRIMARY KEY NOT NULL,
    monsterName VARCHAR(30) NOT NULL,
    timeOfDeath TIMESTAMP NOT NULL,
    FOREIGN KEY (monsterKillListID) REFERENCES MonsterKillListBridgeTable(monsterKillListID)
);

CREATE TABLE KillListBridgeTable (
    monsterKillListID BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    heroID BIGINT NOT NULL
);

   
 