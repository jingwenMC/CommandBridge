package cn.southplex.commandbridge.bungee.mqeasy;

import cn.southplex.commandbridge.CommandBridgeBungee;
import cn.southplex.commandbridge.common.mqeasy.CommandItem;
import top.jingwenmc.mqeasy.api.json.MQEasyJsonUtil;
import top.jingwenmc.mqeasy.api.message.CommonMessage;
import top.jingwenmc.mqeasy.api.message.MessageType;
import top.jingwenmc.mqeasy.api.message.Receipt;
import top.jingwenmc.mqeasy.api.plugin.MQEasyPlugin;
import top.jingwenmc.mqeasy.api.plugin.MQEasyPluginInfo;
import top.jingwenmc.mqeasy.common.MQEasyCommon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MQEasyListener extends MQEasyPlugin {
    @Override
    public MQEasyPluginInfo getPluginInfo() {
        return new MQEasyPluginInfo() {
            @Override
            public String getName() {
                return CommandBridgeBungee.getInstance().getDescription().getName();
            }

            @Override
            public List<String> getAuthors() {
                List<String> list = new ArrayList<>();
                list.add(CommandBridgeBungee.getInstance().getDescription().getAuthor());
                return list;
            }

            @Override
            public String getVersion() {
                return CommandBridgeBungee.getInstance().getDescription().getVersion();
            }

            @Override
            public String getDescription() {
                return CommandBridgeBungee.getInstance().getDescription().getDescription();
            }

            @Override
            public String getWebsite() {
                return "www.jingwenmc.top";
            }
        };
    }

    @Override
    public void onReceiveNoReturn(MessageType messageType, String s, CommonMessage<String> commonMessage) {
        MQEasyCommon.debug("CommonMessage Get:"+commonMessage);
        CommandItem commandItem;
        if(messageType.equals(MessageType.SERVER_NO_RETURN)) {
            try {
                commandItem = MQEasyJsonUtil.parseJSON(commonMessage.getBody(),CommandItem.class);
                MQEasyCommon.debug("Body Get:"+commonMessage);
                if(commandItem.getPassword().equals(CommandBridgeBungee.getInstance().getConfigUtil().getPassword())) {
                    StringBuilder s1 = new StringBuilder();
                    for(String ss : commandItem.getCommandLine()) {
                        s1.append(ss).append(" ");
                    }
                    MQEasyCommon.debug("Command Line:"+s1);
                    CommandBridgeBungee.getInstance().getProxy().getPluginManager()
                            .dispatchCommand(CommandBridgeBungee.getInstance().getProxy().getConsole(), s1.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Receipt onReceiveNeedReturn(MessageType messageType, String s, CommonMessage<String> commonMessage) {
        return null;
    }
}
