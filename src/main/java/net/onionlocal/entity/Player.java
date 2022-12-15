package net.onionlocal.entity;

import net.onionlocal.main.GamePanel;
import net.onionlocal.main.KeyHandler;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {

        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;


    }
}
