package net.onionlocal.main;

import java.util.Arrays;

public class LogUtil {
    private static final String DEBUG_PREFIX = "{Debug}:";
    private static final String INFO_PREFIX = "{Info}:";
    private static final String WARNING_PREFIX = "{Warning}:";
    private static final String ERROR_PREFIX = "{Error}:";
    public enum LogPriority {
        ERROR,
        WARNING,
        INFO,
        DEBUG
    }
    public static void logUtil(LogPriority logPriority, String msg) {
        if (logPriority == LogPriority.DEBUG) {
            System.out.println("\u001B[32m" + DEBUG_PREFIX + msg + "\u001B[0m");
        } else if (logPriority == LogPriority.INFO) {
            System.out.println(INFO_PREFIX + msg);
        } else if (logPriority == LogPriority.WARNING) {
            System.out.println("\u001B[33m" + WARNING_PREFIX + msg + "\u001B[0m");
        } else if (logPriority == LogPriority.ERROR) {
            System.err.println(ERROR_PREFIX + msg);
        }
    }
    public static void logUtilTest() {
        for (LogPriority logPriority : Arrays.asList(LogPriority.DEBUG, LogPriority.INFO, LogPriority.WARNING, LogPriority.ERROR)) {
            logUtil(logPriority, " test DEBUG");
        }
    }
}
