package cn.southplex.commandbridge.mode;

import cn.southplex.commandbridge.LogUtil;
import cn.southplex.commandbridge.structure.RunningModeItem;

import java.util.logging.Level;

public class NotsetMode implements RunningModeItem {
    @Override
    public void onEnable() {
        LogUtil.log(Level.SEVERE,"RUNNING MODE NOT SET");
    }

    @Override
    public void onDisable() {
        //Do nothing
    }

    @Override
    public void onCmd(String password, String... commandLine) {
        LogUtil.log(Level.SEVERE,"RUNNING MODE NOT SET");
    }
}
