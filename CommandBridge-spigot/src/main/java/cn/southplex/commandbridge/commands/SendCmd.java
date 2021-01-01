package cn.southplex.commandbridge.commands;

import cn.southplex.commandbridge.CommandBridgeSpigot;
import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SendCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player)return false;
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("commandbridge");
        String outs = null;
        for (String a : args)
            if(outs == null)
            outs = a;
            else outs = outs+" "+a;
        out.writeUTF(outs);
        Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
        player.sendPluginMessage(CommandBridgeSpigot.getInstance(),"BungeeCord",out.toByteArray());
        return true;
    }
}
