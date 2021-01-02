package cn.southplex.commandbridge.enums;

public enum RunningMode {
    PLUGIN_MESSAGE("PluginMessage"),SIDE_CHANNEL("SideChannel");
    private String value;
    RunningMode(String name){
        this.value = name;
    }
    public String getValue() {
        return value;
    }
}
