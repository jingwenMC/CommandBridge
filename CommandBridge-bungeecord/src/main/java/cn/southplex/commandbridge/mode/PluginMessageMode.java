package cn.southplex.commandbridge.mode;

import cn.southplex.commandbridge.CommandBridgeBungee;
import cn.southplex.commandbridge.LogUtil;
import cn.southplex.commandbridge.structure.RunningModeItem;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.logging.Level;

public class PluginMessageMode implements RunningModeItem, Listener {

    private boolean loaded = false;

    @Override
    public void onEnable() {
        LogUtil.log(Level.INFO,"Using PluginMessage mode - Listen Only.");
        if(loaded)return;
        CommandBridgeBungee.getInstance().getProxy().getPluginManager().registerListener(CommandBridgeBungee.getInstance(),this);
    }

    @Override
    public void onDisable() {
        LogUtil.log(Level.INFO,"Disable PluginMessage mode.");
    }

    @Override
    public void onCmd(String... commandLine) {
        //Not Supported
    }

    @Override
    public void onCmdOtherServer(String to, String... commandLine) {
        //Not Supported
    }

    @EventHandler
    public void onPluginMessage(PluginMessageEvent event) {
        if(event.getTag().equals("BungeeCord")) {
            ByteArrayDataInput in = ByteStreams.newDataInput(event.getData());
            String subchannel = in.readUTF();
            if(!subchannel.equals("commandbridge"))return;
            String password = in.readUTF();
            //System.out.println(password+"   "+password.equals(configUtil.getPassword()));
            if(!password.equals(CommandBridgeBungee.getInstance().getConfigUtil().getPassword()))return;
            CommandBridgeBungee.getInstance().getProxy().getPluginManager()
                    .dispatchCommand(CommandBridgeBungee.getInstance().getProxy().getConsole(),in.readUTF());
        }
    }
}
