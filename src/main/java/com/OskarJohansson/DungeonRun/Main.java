package com.OskarJohansson.DungeonRun;


import com.OskarJohansson.DungeonRun.Control.MenuControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        final Logger logger = LoggerFactory.getLogger(MenuControl.class);

        MenuControl menuControl = new MenuControl();

        menuControl.startGame(menuControl);
    }
}
