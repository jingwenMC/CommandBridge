package cn.southplex.commandbridge.commands;

import cn.southplex.commandbridge.CommandBridgeSpigot;
import cn.southplex.commandbridge.ServerStatus;
import cn.southplex.commandbridge.enums.RunningMode;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SendCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player)
            if(!(CommandBridgeSpigot.getPluginConfig().getBoolean("enable-player-command") && sender.hasPermission("commandbridge.spigot.send")))
                return true;
        if(args[0]==null)sender.sendMessage(ChatColor.RED+"Error: Null Command");
        CommandBridgeSpigot.checkRunningMode();
        if(ServerStatus.getRunningMode() == RunningMode.PLUGIN_MESSAGE) {
            CommandBridgeSpigot.getRunningModeItem().onCmd(args);
        }
        if(ServerStatus.getRunningMode() == RunningMode.MESSAGE_QUEUE) {
            CommandBridgeSpigot.getRunningModeItem().onCmd(args);
        }
        return true;
    }
}
