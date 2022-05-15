package cn.southplex.commandbridge.structure;

public interface RunningModeItem {
    void onEnable();

    void onDisable();

    void onCmd(String player, String... commandLine);

    void onCmdOtherServer(String to, String... commandLine);
}
