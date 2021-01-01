package cn.southplex.commandbridge;

import cn.southplex.commandbridge.commands.SendCmd;
import cn.southplex.commandbridge.enums.ServerType;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class CommandBridgeSpigot extends JavaPlugin {

    @Override
    public void onEnable() {
        new LogUtil(Bukkit.getLogger());
        new ServerStatus(ServerType.SPIGOT);
        LogUtil.log(Level.INFO,"Setting Up...");
        LogUtil.log(Level.INFO,"Registering Outgoing Channel...");
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        LogUtil.log(Level.INFO,"Registering Command...");
        getCommand("sendcmd").setExecutor(new SendCmd());
        LogUtil.log(Level.INFO,"Done! Plugin By: jingwenMC");
    }

    @Override
    public void onDisable() {
        LogUtil.log(Level.INFO,"Shutting Down...");
        LogUtil.log(Level.INFO,"Goodbye!");
    }

    public static Plugin getInstance() {
        return Bukkit.getPluginManager().getPlugin("CommandBridge");
    }
}
