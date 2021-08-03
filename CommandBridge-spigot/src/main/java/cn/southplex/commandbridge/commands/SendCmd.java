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
            if(CommandBridgeSpigot.getPluginConfig().getBoolean("enable-player-command"))
                return true;
        if(args[0]==null)sender.sendMessage(ChatColor.RED+"Error: Null Command");
        CommandBridgeSpigot.checkRunningMode();
        if(ServerStatus.getRunningMode() == RunningMode.PLUGIN_MESSAGE) {
            String[] out = new String[args.length-1];
            if(args.length>1)System.arraycopy(args, 1, out, 0, args.length - 1);
            CommandBridgeSpigot.getRunningModeItem().onCmd(args[0],out);
        }
        return true;
    }
}
