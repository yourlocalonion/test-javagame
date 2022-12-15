package net.onionlocal.main;


import static net.onionlocal.main.JFrameWindowHandler.*;
import static net.onionlocal.main.LogUtil.*;

public class Main {
    public static void main(String[] args) {

        logUtil(LogPriority.INFO, " Starting testJVGame!");

        logUtilTest();
        createJFrameWindow();

        GamePanel gPanel = new GamePanel();
        addPanel(gPanel);

        gPanel.startGameThread();
    }
}
