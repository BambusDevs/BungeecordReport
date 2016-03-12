package com.github.BambusDevs.BungeecordReport;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class Main extends Plugin {
    @Override
    public void onEnable() {
        initServer();
    }

    @Override
    public void onDisable() {

    }

    private void initServer() {
        initConfig();

        PluginManager pm = getProxy().getPluginManager();
        // Register Commands and Events
    }

    private void initConfig() {
        // Create Config if not exists
        if (!getDataFolder().exists())
            getDataFolder().mkdir();

        File file = new File(getDataFolder(), "config.yml");

        if (!file.exists()) {
            try (InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
