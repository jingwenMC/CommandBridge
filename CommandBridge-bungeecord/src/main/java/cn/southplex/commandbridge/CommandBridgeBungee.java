package cn.southplex.commandbridge;

import cn.southplex.commandbridge.enums.ServerType;
import cn.southplex.commandbridge.util.ConfigUtil;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.event.EventHandler;

import java.util.logging.Level;

public final class CommandBridgeBungee extends Plugin implements Listener {

    ConfigUtil configUtil = null;

    @Override
    public void onEnable() {
        new LogUtil(getProxy().getLogger());
        new ServerStatus(ServerType.BUNGEE);
        configUtil = new ConfigUtil(this);
        LogUtil.log(Level.INFO,"Setting Up...");
        getProxy().getPluginManager().registerListener(this,this);
        LogUtil.log(Level.INFO,"Done! Plugin By: jingwenMC");
    }

    @Override
    public void onDisable() {
        LogUtil.log(Level.INFO,"Shutting Down...");
        LogUtil.log(Level.INFO,"Goodbye!");
    }

    @EventHandler
    public void onPluginMessage(PluginMessageEvent event) {
        if(event.getTag().equals("BungeeCord")) {
            ByteArrayDataInput in = ByteStreams.newDataInput(event.getData());
            String subchannel = in.readUTF();
            if(!subchannel.equals("commandbridge"))return;
            String password = in.readUTF();
            System.out.println(password+"   "+password.equals(configUtil.getPassword()));
            if(!password.equals(configUtil.getPassword()))return;
            getProxy().getPluginManager().dispatchCommand(getProxy().getConsole(),in.readUTF());
        }
    }

    public Configuration getConfig() {
        return configUtil.getConfig();
    }

}
