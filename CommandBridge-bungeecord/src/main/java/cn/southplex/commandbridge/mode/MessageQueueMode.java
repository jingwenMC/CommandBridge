package cn.southplex.commandbridge.mode;

import cn.southplex.commandbridge.CommandBridgeBungee;
import cn.southplex.commandbridge.LogUtil;
import cn.southplex.commandbridge.bungee.mqeasy.MQEasyListener;
import cn.southplex.commandbridge.common.mqeasy.CommandItem;
import cn.southplex.commandbridge.structure.RunningModeItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import top.jingwenmc.mqeasy.api.MQEasyApi;
import top.jingwenmc.mqeasy.api.exception.MQEasyNotLoadException;
import top.jingwenmc.mqeasy.api.json.MQEasyJsonUtil;
import top.jingwenmc.mqeasy.api.plugin.MQEasyPlugin;

import java.util.logging.Level;

public class MessageQueueMode implements RunningModeItem {
    private boolean loaded = false;

    private final MQEasyPlugin plugin = new MQEasyListener();

    @Override
    public void onEnable() {
        if(loaded)return;
        try {
            MQEasyApi.registerPlugin(plugin);
            loaded=true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        LogUtil.log(Level.INFO,"Using MessageQueue mode.");
    }

    @Override
    public void onDisable() {
        LogUtil.log(Level.INFO,"Disable MessageQueue mode.");
    }

    @Override
    public void onCmd(String dest,String... commandLine) {
        try {
            plugin.getApi().sendMessageToBukkitPlayerNoReturn(dest, MQEasyJsonUtil.parseObject(
                    new CommandItem(CommandBridgeBungee.getInstance().getConfigUtil().getPassword(),commandLine)));
        } catch (MQEasyNotLoadException | JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCmdOtherServer(String to, String... commandLine) {
        try {
            plugin.getApi().sendMessageToServerNoReturn(to, MQEasyJsonUtil.parseObject(
                    new CommandItem(CommandBridgeBungee.getInstance().getConfigUtil().getPassword(),commandLine)));
        } catch (MQEasyNotLoadException | JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
