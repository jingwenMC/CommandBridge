package cn.southplex.commandbridge.commands;

import cn.southplex.commandbridge.CommandBridgeSpigot;
import cn.southplex.commandbridge.ServerStatus;
import cn.southplex.commandbridge.enums.RunningMode;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SendCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (needRejectNoPerm(sender)) return true;
        if(args[0]==null)sender.sendMessage(ChatColor.RED + "Error: Wrong Command Usage.");
        if(args[1]==null)sender.sendMessage(ChatColor.RED+"Error: Wrong Command Usage.");
        String dest = args[0];
        String[] out = new String[args.length-1];
        System.arraycopy(args, 1, out, 0, args.length - 1);
        CommandBridgeSpigot.checkRunningMode();
        if(ServerStatus.getRunningMode() == RunningMode.MESSAGE_QUEUE) {
            CommandBridgeSpigot.getRunningModeItem().onCmd(dest,out);
        }
        return true;
    }

    public static boolean needRejectNoPerm(CommandSender sender) {
        if(sender instanceof Player) {
            if (CommandBridgeSpigot.getPluginConfig().getBoolean("enable-player-command")) {
                if (!sender.hasPermission("commandbridge.spigot.send")) {
                    sender.sendMessage(ChatColor.RED + "Error: No Permission or player command not enabled.");
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Error: No Permission or player command not enabled.");
                return true;
            }
        }
        return false;
    }
}
