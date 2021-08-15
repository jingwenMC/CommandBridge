package cn.southplex.commandbridge.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class SendCmdToServer extends Command {
    public SendCmdToServer() {
        super("sendcmdtoserver","commandbridge.spigot.send","scts","sctsbungee");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

    }
}
