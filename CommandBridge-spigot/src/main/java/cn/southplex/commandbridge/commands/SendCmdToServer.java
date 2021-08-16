package cn.southplex.commandbridge.commands;

import cn.southplex.commandbridge.CommandBridgeSpigot;
import cn.southplex.commandbridge.ServerStatus;
import cn.southplex.commandbridge.enums.RunningMode;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SendCmdToServer implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player)
            if(CommandBridgeSpigot.getInstance().getConfig().getBoolean("enable-player-command")) {
                sender.sendMessage(ChatColor.RED+"Error: No Permission or player command not enabled.");
                return true;
            }
        if(args.length<=1)sender.sendMessage(ChatColor.RED+"Error: Wrong Command Usage.");
        CommandBridgeSpigot.checkRunningMode();
        if(ServerStatus.getRunningMode() == RunningMode.MESSAGE_QUEUE) {
            String[] out = new String[args.length-1];
            if(args.length>1)System.arraycopy(args, 1, out, 0, args.length - 1);
            CommandBridgeSpigot.getRunningModeItem().onCmdOtherServer(args[0],out);
        } else {
            sender.sendMessage(ChatColor.RED+"Error: Not Supported");
        }
        return true;
    }
}
