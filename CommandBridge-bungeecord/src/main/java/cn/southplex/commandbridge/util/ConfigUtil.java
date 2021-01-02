package cn.southplex.commandbridge.util;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ConfigUtil {
    Configuration configuration;
    public ConfigUtil(Plugin plugin) {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        File config = new File(plugin.getDataFolder(), "config.yml");
        if (!config.exists()) {
            try {
                Files.copy(plugin.getResourceAsStream("config.yml"), config.toPath());
                configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(plugin.getDataFolder(), "config.yml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public String getPassword() {
        return configuration.getString("password");
    }

    public Configuration getConfig() {
        return configuration;
    }
}
