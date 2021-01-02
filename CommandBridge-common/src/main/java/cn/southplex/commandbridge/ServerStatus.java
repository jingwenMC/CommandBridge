package cn.southplex.commandbridge;

import cn.southplex.commandbridge.enums.RunningMode;
import cn.southplex.commandbridge.enums.ServerType;

public class ServerStatus {
    static ServerType serverType;
    static RunningMode runningMode = RunningMode.PLUGIN_MESSAGE;
    public ServerStatus(ServerType serverType) {
        ServerStatus.serverType = serverType;
    }
    public static ServerType getServerType() {
        return serverType;
    }
    public static boolean isSpigot() {
        return serverType == ServerType.SPIGOT;
    }
    public static boolean isBungee() {
        return serverType == ServerType.BUNGEE;
    }

    public static void setRunningMode(RunningMode runningMode) {
        ServerStatus.runningMode = runningMode;
    }

    public static RunningMode getRunningMode() {
        return runningMode;
    }
}
