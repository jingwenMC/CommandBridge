package cn.southplex.commandbridge.mode;

import cn.southplex.commandbridge.CommandBridgeSpigot;
import cn.southplex.commandbridge.common.mqeasy.CommandItem;
import cn.southplex.commandbridge.spigot.mqeasy.MQEasyListener;
import cn.southplex.commandbridge.structure.RunningModeItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import top.jingwenmc.mqeasy.api.MQEasyApi;
import top.jingwenmc.mqeasy.api.exception.MQEasyNotLoadException;
import top.jingwenmc.mqeasy.api.json.MQEasyJsonUtil;
import top.jingwenmc.mqeasy.api.plugin.MQEasyPlugin;

public class MessageQueueMode implements RunningModeItem {
    private boolean loaded = false;

    private MQEasyPlugin plugin = new MQEasyListener();

    @Override
    public void onEnable() {
        if(loaded)return;
        try {
            MQEasyApi.registerPlugin(plugin);
            loaded=true;
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onCmd(String... commandLine) {
        try {
            plugin.getApi().sendMessageToServerNoReturn("bungee",
                    MQEasyJsonUtil.parseObject(new CommandItem(CommandBridgeSpigot.getPluginConfig().getString("password"),commandLine)));
        } catch (MQEasyNotLoadException | JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCmdOtherServer(String to, String... commandLine) {
        //todo
    }
}
