package com.github.BambusDevs.BungeecordReport;

import com.github.BambusDevs.BungeecordReport.report.bug.commands.BugReportCommand;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main extends Plugin {

    public static String Prefix = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "Bungee Report" + ChatColor.DARK_GRAY + "] ";

    @Override
    public void onEnable() {
        initServer();
    }

    @Override
    public void onDisable() {

    }

    private void initServer() {
        initConfig();
        initMYSQL();

        PluginManager pm = getProxy().getPluginManager();
        // Register Commands and Events
        pm.registerCommand(this, new BugReportCommand());
    }

    private void initMYSQL() {
        new MYSQL(this);

        MYSQL.connect();

        if (MYSQL.isConnected()) {
            try {
                PreparedStatement pps = MYSQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS PlayerReport (Player TEXT, Reason TEXT, Info TEXT)");
                PreparedStatement pbs = MYSQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS BugReport (Bug TEXT, Info TEXT)");
                PreparedStatement pis = MYSQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS IdeaReport (Idea TEXT, Info TEXT)");
                pps.executeUpdate();
                pbs.executeUpdate();
                pis.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
