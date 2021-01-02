package cn.southplex.commandbridge.manager;

import cn.southplex.commandbridge.CommandBridgeSpigot;
import cn.southplex.commandbridge.LogUtil;
import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class PluginMessageManager {
    public static void sendMessage(CommandSender sender, String password, String... command) {
        if(sender instanceof Player)return;
        if(command==null)sender.sendMessage(ChatColor.RED+"Error: Null Command");
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("commandbridge");
        out.writeUTF(password);
        String outs = null;
        for (String a : command)
            if(outs == null)
                outs = a;
            else outs = outs+" "+a;
        out.writeUTF(outs);
        Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
        if(player==null) LogUtil.log(Level.WARNING,"No player is online to send a plugin message");
        else player.sendPluginMessage(CommandBridgeSpigot.getInstance(),"BungeeCord",out.toByteArray());
    }
}
