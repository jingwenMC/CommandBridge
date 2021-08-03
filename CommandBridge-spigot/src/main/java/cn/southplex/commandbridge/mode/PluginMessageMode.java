package cn.southplex.commandbridge.mode;

import cn.southplex.commandbridge.CommandBridgeSpigot;
import cn.southplex.commandbridge.LogUtil;
import cn.southplex.commandbridge.structure.RunningModeItem;
import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class PluginMessageMode implements RunningModeItem {

    public static boolean isLoaded = false;

    @Override
    public void onEnable() {
        if(!isLoaded) {
            LogUtil.log(Level.INFO, "Registering Outgoing Channel...");
            CommandBridgeSpigot.getInstance().getServer().getMessenger().registerOutgoingPluginChannel(CommandBridgeSpigot.getInstance(), "BungeeCord");
        }
        isLoaded=true;
        LogUtil.log(Level.INFO,"Using PluginMessage mode.");
    }

    @Override
    public void onDisable() {
        LogUtil.log(Level.INFO,"Disable PluginMessage mode.");
    }

    @Override
    public void onCmd(String password, String... commandLine) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("commandbridge");
        out.writeUTF(password);
        String outs = null;
        for (String a : commandLine)
            if(outs == null)
                outs = a;
            else outs = outs+" "+a;
        out.writeUTF(outs);
        Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
        if(player==null) LogUtil.log(Level.WARNING,"No player is online to send a plugin message");
        else player.sendPluginMessage(CommandBridgeSpigot.getInstance(),"BungeeCord",out.toByteArray());
    }
}
