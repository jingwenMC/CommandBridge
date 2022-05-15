package cn.southplex.commandbridge;

import cn.southplex.commandbridge.command.SendCmdToServer;
import cn.southplex.commandbridge.enums.RunningMode;
import cn.southplex.commandbridge.enums.ServerType;
import cn.southplex.commandbridge.mode.MessageQueueMode;
import cn.southplex.commandbridge.mode.NotsetMode;
import cn.southplex.commandbridge.structure.RunningModeItem;
import cn.southplex.commandbridge.util.ConfigUtil;
import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

import java.util.Objects;
import java.util.logging.Level;

public final class CommandBridgeBungee extends Plugin {

    @Setter
    @Getter
    private static RunningModeItem runningModeItem = new NotsetMode();

    @Getter
    ConfigUtil configUtil = null;

    @Getter
    private static CommandBridgeBungee instance;

    @Override
    public void onEnable() {
        instance=this;
        LogUtil.setLogger(getLogger());
        ServerStatus.setServerStatus(ServerType.BUNGEE);
        configUtil = new ConfigUtil(this);
        LogUtil.log(Level.INFO,"Setting Up...");
        getProxy().getPluginManager().registerCommand(this,new SendCmdToServer());
        checkRunningMode();
        LogUtil.log(Level.INFO,"Done! Plugin By: jingwenMC");
    }

    @Override
    public void onDisable() {
        CommandBridgeBungee.getRunningModeItem().onDisable();
        LogUtil.log(Level.INFO,"Shutting Down...");
        LogUtil.log(Level.INFO,"Goodbye!");
    }

    public Configuration getConfig() {
        return configUtil.getConfig();
    }

    public static void checkRunningMode() {
        String runningMode = Objects.requireNonNull(getInstance().getConfig().getString("running-mode"));

        if(runningMode.equalsIgnoreCase("MessageQueue")) {
            if (CommandBridgeBungee.getRunningModeItem() instanceof MessageQueueMode) return;
            CommandBridgeBungee.getRunningModeItem().onDisable();
            RunningModeItem runningModeItem2 = new MessageQueueMode();
            runningModeItem2.onEnable();
            CommandBridgeBungee.setRunningModeItem(runningModeItem2);
            ServerStatus.setRunningMode(RunningMode.MESSAGE_QUEUE);
        }
                /*Not supported right now
            case 78837083:
                if(CommandBridgeSpigot.getRunningModeItem() instanceof RedisMode)break;
                CommandBridgeSpigot.getRunningModeItem().onDisable();
                RunningModeItem runningModeItem3 = new RedisMode();
                runningModeItem3.onEnable();
                CommandBridgeSpigot.setRunningModeItem(runningModeItem3);
                ServerStatus.setRunningMode(RunningMode.REDIS);
                 */
        else {
            LogUtil.log(Level.WARNING, "Did not found a valid running mode, please check your config.");
            LogUtil.log(Level.WARNING, "The plugin won't work right now.");
        }
    }
}
