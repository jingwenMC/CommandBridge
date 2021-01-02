package cn.southplex.commandbridge.commands;

import cn.southplex.commandbridge.CommandBridgeSpigot;
import cn.southplex.commandbridge.LogUtil;
import cn.southplex.commandbridge.ServerStatus;
import cn.southplex.commandbridge.enums.RunningMode;
import cn.southplex.commandbridge.manager.PluginMessageManager;
import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class SendCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player)return true;
        if(ServerStatus.getRunningMode() == RunningMode.PLUGIN_MESSAGE) {
            String[] out = new String[args.length-1];
            if(args.length>1)
            for(int i=1;i<args.length;i++) {
                out[i-1] = args[i];
            }
            PluginMessageManager.sendMessage(sender,args[0],out);
        }
        return true;
    }
}
