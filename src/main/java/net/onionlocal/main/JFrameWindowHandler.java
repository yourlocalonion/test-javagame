package net.onionlocal.main;

import javax.swing.*;

import static net.onionlocal.main.LogUtil.logUtil;


public class JFrameWindowHandler {
    static JFrame window = new JFrame();
    /**
     * Create a JFrame Window With the name of "A Test Java game" "
     */
    public static void createJFrameWindow() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("A Test Java game");
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);


         //Checks if window exists (completely useless for now :p)

        if (window == null)
            logUtil(LogUtil.LogPriority.ERROR,  String.format(" [%s] Failed to create window!", JFrameWindowHandler.class.getSimpleName()));
        else {
            logUtil(LogUtil.LogPriority.INFO,  String.format(" [%s] Window has been created!", JFrameWindowHandler.class.getSimpleName()));
        }
    }


    /**
     * Adds a game panel to the window
     */
    public static void addPanel(GamePanel gamePanel) {
        window.add(gamePanel);
        window.pack();
    }
}
