package cn.southplex.commandbridge;

import cn.southplex.commandbridge.enums.ServerType;
import net.md_5.bungee.api.plugin.Plugin;

public final class CommandBridgeBungee extends Plugin {

    @Override
    public void onEnable() {
        new LogUtil(getProxy().getLogger());
        new ServerStatus(ServerType.BUNGEE);
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
