package cn.southplex.commandbridge.mode;

import cn.southplex.commandbridge.CommandBridgeSpigot;
import cn.southplex.commandbridge.common.mqeasy.CommandItem;
import cn.southplex.commandbridge.structure.RunningModeItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.bukkit.Bukkit;
import top.jingwenmc.mqeasy.api.MQEasyApi;
import top.jingwenmc.mqeasy.api.exception.MQEasyNotLoadException;
import top.jingwenmc.mqeasy.api.json.MQEasyJsonUtil;
import top.jingwenmc.mqeasy.api.message.CommonMessage;
import top.jingwenmc.mqeasy.api.message.MessageType;
import top.jingwenmc.mqeasy.api.message.Receipt;
import top.jingwenmc.mqeasy.api.plugin.BukkitMQEasyPluginInfo;
import top.jingwenmc.mqeasy.api.plugin.MQEasyPlugin;
import top.jingwenmc.mqeasy.api.plugin.MQEasyPluginInfo;
import top.jingwenmc.mqeasy.common.MQEasyCommon;

import java.io.IOException;

public class MessageQueueMode extends MQEasyPlugin implements RunningModeItem {
    private boolean loaded = false;

    @Override
    public void onEnable() {
        if(loaded)return;
        try {
            MQEasyApi.registerPlugin(this);
            loaded=true;
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onCmd(String dest,String... commandLine) {
        try {
            this.getApi().sendMessageToBungeePlayerNoReturn(dest,
                    MQEasyJsonUtil.parseObject(new CommandItem(CommandBridgeSpigot.getPluginConfig().getString("password"),commandLine)));
        } catch (MQEasyNotLoadException | JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCmdOtherServer(String to, String... commandLine) {
        try {
            this.getApi().sendMessageToServerNoReturn(to,
                    MQEasyJsonUtil.parseObject(new CommandItem(CommandBridgeSpigot.getPluginConfig().getString("password"),commandLine)));
        } catch (MQEasyNotLoadException | JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MQEasyPluginInfo getPluginInfo() {
        return new BukkitMQEasyPluginInfo(CommandBridgeSpigot.getInstance());
    }

    @Override
    public void onReceiveNoReturn(MessageType messageType, String s, CommonMessage<String> commonMessage) {
        MQEasyCommon.debug("CommonMessage Get:"+commonMessage);
        CommandItem commandItem;
        if(messageType.equals(MessageType.SERVER_NO_RETURN)) {
            processCommandMessage(commonMessage);
        }
        if(messageType.equals(MessageType.BUKKIT_PLAYER_NO_RETURN)) {
            processCommandMessage(commonMessage);
        }
    }

    public void processCommandMessage(CommonMessage<String> commonMessage) {
        CommandItem commandItem;
        try {
            commandItem = MQEasyJsonUtil.parseJSON(commonMessage.getBody(), CommandItem.class);
            MQEasyCommon.debug("Body Get:"+commonMessage);
            if(commandItem.getPassword().equals(CommandBridgeSpigot.getPluginConfig().getString("password"))) {
                StringBuilder s1 = new StringBuilder();
                for(String ss : commandItem.getCommandLine()) {
                    s1.append(ss).append(" ");
                }
                MQEasyCommon.debug("Command Line:"+s1);
                Bukkit.getScheduler().runTask(CommandBridgeSpigot.getInstance(),
                        () ->Bukkit.dispatchCommand(CommandBridgeSpigot.getInstance().getServer().getConsoleSender(), s1.toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Receipt onReceiveNeedReturn(MessageType messageType, String s, CommonMessage<String> commonMessage) {
        return null;
    }
}
