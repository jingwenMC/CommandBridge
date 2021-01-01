import enums.ServerType;

public class ServerStatus {
    static ServerType serverType;
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
}
