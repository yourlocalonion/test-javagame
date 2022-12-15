package net.onionlocal.entity;

import net.onionlocal.main.GamePanel;
import net.onionlocal.main.KeyHandler;
import net.onionlocal.main.LogUtil;

import java.awt.*;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyH;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyH = keyHandler;
        setDefValues();
        LogUtil.logUtil(LogUtil.LogPriority.INFO, String.format(" [%s]: Created Player Object!", this.getClass().getSimpleName()));
    }

    public void setDefValues() {
        x = 100;
        y = 100;
        speed = 4;
    }

    public void update() {
        if (keyH.upPressed) {
            y -= speed * gamePanel.deltaTime;
        } else if (keyH.downPressed) {
            y += speed * gamePanel.deltaTime;
        } else if (keyH.leftPressed) {
            x -= speed * gamePanel.deltaTime;
        } else if (keyH.rightPressed) {
            x += speed * gamePanel.deltaTime;
        }
    }
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.white);
        graphics2D.fillRect(x, y, gamePanel.TILESIZE, gamePanel.TILESIZE);
    }
}
