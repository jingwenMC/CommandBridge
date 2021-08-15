package cn.southplex.commandbridge.spigot.mqeasy;

import cn.southplex.commandbridge.CommandBridgeSpigot;
import top.jingwenmc.mqeasy.api.message.CommonMessage;
import top.jingwenmc.mqeasy.api.message.MessageType;
import top.jingwenmc.mqeasy.api.message.Receipt;
import top.jingwenmc.mqeasy.api.plugin.BukkitMQEasyPluginInfo;
import top.jingwenmc.mqeasy.api.plugin.MQEasyPlugin;
import top.jingwenmc.mqeasy.api.plugin.MQEasyPluginInfo;

public class MQEasyListener extends MQEasyPlugin {
    @Override
    public MQEasyPluginInfo getPluginInfo() {
        return new BukkitMQEasyPluginInfo(CommandBridgeSpigot.getInstance());
    }

    @Override
    public void onReceiveNoReturn(MessageType messageType, String s, CommonMessage<String> commonMessage) {
        //TODO:Bungee To Bukkit
    }

    @Override
    public Receipt onReceiveNeedReturn(MessageType messageType, String s, CommonMessage<String> commonMessage) {
        return null;
    }
}
