package cn.southplex.commandbridge.command;

import cn.southplex.commandbridge.CommandBridgeBungee;
import cn.southplex.commandbridge.ServerStatus;
import cn.southplex.commandbridge.enums.RunningMode;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class SendCmdToServer extends Command {
    public SendCmdToServer() {
        super("sendcmdtoserver","commandbridge.spigot.send","scts","sctsbungee");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer)
            if(CommandBridgeBungee.getInstance().getConfig().getBoolean("enable-player-command")) {
                sender.sendMessage(ChatColor.RED+"[BC]Error: No Permission or player command not enabled.");
                return;
            }
        if(args.length<=1)sender.sendMessage(ChatColor.RED+"[BC]Error: Wrong Command Usage.");
        CommandBridgeBungee.checkRunningMode();
        if(ServerStatus.getRunningMode() == RunningMode.MESSAGE_QUEUE) {
            String[] out = new String[args.length-1];
            if(args.length>1)System.arraycopy(args, 1, out, 0, args.length - 1);
            CommandBridgeBungee.getRunningModeItem().onCmdOtherServer(args[0],out);
        } else {
            sender.sendMessage(ChatColor.RED+"[BC]Error: Not Supported");
        }
    }
}
