package cn.southplex.commandbridge.structure;

public interface RunningModeItem {
    void onEnable();

    void onDisable();

    void onCmd(String password, String... commandLine);
}
