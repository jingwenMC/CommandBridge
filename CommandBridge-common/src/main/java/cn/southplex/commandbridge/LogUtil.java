package cn.southplex.commandbridge;

import lombok.Setter;
import net.md_5.bungee.api.ChatColor;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogUtil {
    @Setter
    private static Logger logger;

    public static void log(Level level, String message) {
        logger.log(level,process(message));
    }
    public static void log(Level level,String message,Boolean raw) {
        if(raw)logger.log(level,message);
        else log(level,message);
    }

    public static String process(String in) {
        in = ChatColor.GREEN+"[CommandBridge]"+in;
        in = ChatColor.translateAlternateColorCodes('&',in);
        return in;
    }
}
