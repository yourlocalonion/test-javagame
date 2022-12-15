package net.onionlocal.main;


import static java.lang.System.exit;
import static net.onionlocal.main.JFrameWindowHandler.*;
import static net.onionlocal.main.LogUtil.*;

public class Main {
    public static void main(String[] args) {

        logUtilTest();
        logUtil(LogPriority.INFO, " Starting testJVGame!");

        createJFrameWindow();

        GamePanel gPanel = new GamePanel();
        addPanel(gPanel);

        boolean gPanelResult = gPanel.startGameThread();
        if (!gPanelResult) {
            exit(-1);
        }
    }
}
