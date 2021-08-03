package cn.southplex.commandbridge;

import cn.southplex.commandbridge.commands.SendCmd;
import cn.southplex.commandbridge.enums.RunningMode;
import cn.southplex.commandbridge.enums.ServerType;
import cn.southplex.commandbridge.mode.MessageQueueMode;
import cn.southplex.commandbridge.mode.NotsetMode;
import cn.southplex.commandbridge.mode.PluginMessageMode;
import cn.southplex.commandbridge.mode.RedisMode;
import cn.southplex.commandbridge.structure.RunningModeItem;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Level;

public final class CommandBridgeSpigot extends JavaPlugin {

    @Setter
    @Getter
    private static RunningModeItem runningModeItem = new NotsetMode();

    @Override
    public void onEnable() {
        LogUtil.setLogger(Bukkit.getLogger());
        ServerStatus.setServerStatus(ServerType.SPIGOT);
        LogUtil.log(Level.INFO,"Setting Up...");
        saveDefaultConfig();
        checkRunningMode();
        LogUtil.log(Level.INFO,"Registering Command...");
        getCommand("sendcmd").setExecutor(new SendCmd());
        LogUtil.log(Level.INFO,"Done! Plugin By: jingwenMC");
    }

    public static void checkRunningMode() {
        switch (Objects.requireNonNull(getPluginConfig().getString("running-mode"))) {
            case "PluginMessage":
                if(CommandBridgeSpigot.getRunningModeItem() instanceof PluginMessageMode)break;
                CommandBridgeSpigot.getRunningModeItem().onDisable();
                RunningModeItem runningModeItem = new PluginMessageMode();
                runningModeItem.onEnable();
                CommandBridgeSpigot.setRunningModeItem(runningModeItem);
                ServerStatus.setRunningMode(RunningMode.PLUGIN_MESSAGE);
            case "MessageQueue":
                if(CommandBridgeSpigot.getRunningModeItem() instanceof MessageQueueMode)break;
                CommandBridgeSpigot.getRunningModeItem().onDisable();
                RunningModeItem runningModeItem2 = new MessageQueueMode();
                runningModeItem2.onEnable();
                CommandBridgeSpigot.setRunningModeItem(runningModeItem2);
                ServerStatus.setRunningMode(RunningMode.MESSAGE_QUEUE);
            case "Redis":
                if(CommandBridgeSpigot.getRunningModeItem() instanceof RedisMode)break;
                CommandBridgeSpigot.getRunningModeItem().onDisable();
                RunningModeItem runningModeItem3 = new RedisMode();
                runningModeItem3.onEnable();
                CommandBridgeSpigot.setRunningModeItem(runningModeItem3);
                ServerStatus.setRunningMode(RunningMode.REDIS);
            default:
                LogUtil.log(Level.WARNING,"Did not found a valid running mode, please check your config.");
                LogUtil.log(Level.WARNING,"Using PluginMessage Mode instead.");
                if(CommandBridgeSpigot.getRunningModeItem() instanceof MessageQueueMode)break;
                CommandBridgeSpigot.getRunningModeItem().onDisable();
                RunningModeItem runningModeItem4 = new MessageQueueMode();
                runningModeItem4.onEnable();
                CommandBridgeSpigot.setRunningModeItem(runningModeItem4);
                ServerStatus.setRunningMode(RunningMode.MESSAGE_QUEUE);
        }
    }

    @Override
    public void onDisable() {
        LogUtil.log(Level.INFO,"Shutting Down...");
        LogUtil.log(Level.INFO,"Goodbye!");
    }

    public static Plugin getInstance() {
        return Bukkit.getPluginManager().getPlugin("CommandBridge");
    }

    public static FileConfiguration getPluginConfig() {
        getInstance().reloadConfig();
        return getInstance().getConfig();
    }
}
