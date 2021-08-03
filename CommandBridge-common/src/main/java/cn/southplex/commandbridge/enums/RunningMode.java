package cn.southplex.commandbridge.enums;

public enum RunningMode {
    PLUGIN_MESSAGE("PluginMessage"),MESSAGE_QUEUE("MessageQueue"),REDIS("Redis");
    private String value;
    RunningMode(String name){
        this.value = name;
    }
    public String getValue() {
        return value;
    }
}
