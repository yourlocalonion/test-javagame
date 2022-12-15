package net.onionlocal.main;

import javax.swing.*;
import java.awt.*;

import static net.onionlocal.main.LogUtil.logUtil;

public class GamePanel extends JPanel implements Runnable {
    static final int ORIGTILESIZE = 16;
    static final int SCALE = 3;
    static final int TILESIZE = ORIGTILESIZE * SCALE; // 48x48 tile
    static final int MAXSCREENCOL = 16;
    static final int MAXSCREENROW = 12;
    static final int SCREENWIDTH = TILESIZE * MAXSCREENCOL;
    static final int SCREENHEIGHT = TILESIZE * MAXSCREENROW;

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    int fps = 60;

    double drawInterval = 1000000000/fps;
    double deltaTime = 0;
    long last = System.nanoTime();
    long current;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public void startGameThread() {
        gameThread = new Thread(this);

        if (gameThread != null) {
            logUtil(LogUtil.LogPriority.INFO, String.format(" [%s]: Created Game Thread!", System.getProperty("java.class.path")));
        }

        gameThread.start();

        if (gameThread.isAlive()) {
            logUtil(LogUtil.LogPriority.INFO, String.format(" [%s]: Game Thread Started!", System.getProperty("java.class.path")));
        }
    }


    @Override
    public void run() {
        while (gameThread != null) {
            current = System.nanoTime();

            deltaTime += (current - last) / drawInterval;

            last = current;

            if (deltaTime >= 1) {
                update();
                repaint();
                deltaTime--;
            }
        }
    }
    public void update() {
        if (keyH.upPressed) {
            playerY -= playerSpeed * deltaTime;
        } else if (keyH.downPressed) {
            playerY += playerSpeed * deltaTime;
        } else if (keyH.leftPressed) {
            playerX -= playerSpeed * deltaTime;
        } else if (keyH.rightPressed) {
            playerX += playerSpeed * deltaTime;
        }
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D)graphics;

        graphics2D.setColor(Color.white);
        graphics2D.fillRect(playerX, playerY, TILESIZE, TILESIZE);
        graphics2D.dispose();
    }
}
