package cn.southplex.commandbridge;

import cn.southplex.commandbridge.enums.ServerType;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CommandBridgeSpigot extends JavaPlugin {

    @Override
    public void onEnable() {
        new LogUtil(Bukkit.getLogger());
        new ServerStatus(ServerType.SPIGOT);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
