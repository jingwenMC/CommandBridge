package cn.southplex.commandbridge.enums;

public enum RunningMode {
    MESSAGE_QUEUE("MessageQueue"),REDIS("Redis");
    private final String value;
    RunningMode(String name){
        this.value = name;
    }
    public String getValue() {
        return value;
    }
}
