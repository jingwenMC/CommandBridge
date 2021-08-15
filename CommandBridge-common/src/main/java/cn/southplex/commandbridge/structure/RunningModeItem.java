package cn.southplex.commandbridge.structure;

public interface RunningModeItem {
    void onEnable();

    void onDisable();

    void onCmd(String... commandLine);

    void onCmdOtherServer(String to, String... commandLine);
}
