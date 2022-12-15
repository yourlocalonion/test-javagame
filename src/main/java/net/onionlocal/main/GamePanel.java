package net.onionlocal.main;

import net.onionlocal.entity.Player;

import javax.swing.*;
import java.awt.*;

import static net.onionlocal.main.LogUtil.*;

public class GamePanel extends JPanel implements Runnable {

    // window variables
    static final int ORIGTILESIZE = 16;
    static final int SCALE = 3;
    public static final int TILESIZE = ORIGTILESIZE * SCALE; // 48x48 tile
    static final int MAXSCREENCOL = 16;
    static final int MAXSCREENROW = 12;
    static final int SCREENWIDTH = TILESIZE * MAXSCREENCOL;
    static final int SCREENHEIGHT = TILESIZE * MAXSCREENROW;

    //normal (player, delta etc) variables
    int fps = 60;
    double drawInterval = 1000000000/fps;
    public double deltaTime = 0;
    long last = System.nanoTime();
    long current;

    //objects
    KeyHandler keyH = new KeyHandler();
    Player player = new Player(this, keyH);
    Thread gameThread;


    /**
     * initializes the window
     */
    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    /**
     * starts the game thread (loop basically idk)
     */
    public boolean startGameThread() {
        gameThread = new Thread(this);

        if (gameThread != null) {
            logUtil(LogUtil.LogPriority.INFO, String.format(" [%s]: Created Game Thread!", this.getClass().getSimpleName()));
        } else {
            logUtil(LogUtil.LogPriority.ERROR, String.format(" [%s]: Cannot create thread!", this.getClass().getSimpleName()));
            return false;
        }

        gameThread.start();

        if (gameThread.isAlive()) {
            logUtil(LogUtil.LogPriority.INFO, String.format(" [%s]: Game Thread Started!", this.getClass().getSimpleName()));
        } else {
            logUtil(LogUtil.LogPriority.ERROR, String.format(" [%s]: Game thread cannot start!", this.getClass().getSimpleName()));
            return false;
        }
        return true;
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
        player.update();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D)graphics;
        player.draw(graphics2D);

        graphics2D.dispose();
    }
}
