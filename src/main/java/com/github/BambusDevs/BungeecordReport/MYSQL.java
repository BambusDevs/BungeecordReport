package com.github.BambusDevs.BungeecordReport;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class MYSQL {

    public static String host;
    public static Integer port;
    public static String database;
    public static String username;
    public static String password;
    public static Boolean isEnabled;


    public MYSQL(Main plugin) {
        Configuration cfg = null;
        try {
            cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(plugin.getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        host = cfg.getString(plugin.getDescription().getName() + ".MYSQL.Host");
        port = cfg.getInt(plugin.getDescription().getName() + ".MYSQL.Port");
        database = cfg.getString(plugin.getDescription().getName() + ".MYSQL.Database");
        username = cfg.getString(plugin.getDescription().getName() + ".MYSQL.User");
        password = cfg.getString(plugin.getDescription().getName() + ".MYSQL.Password");

        if (host.isEmpty()) {
            String value = "localhost";
            host = value;
            System.out.println(Main.Prefix + "MYSQL Host is empty, set to Default");
            cfg.set(plugin.getDescription().getName() + ".MYSQL.Host", value);
        }

        if (port == null) {
            Integer value = 3306;
            port = value;
            System.out.println(Main.Prefix + "MYSQL Port is empty, set to Default");
            cfg.set(plugin.getDescription().getName() + ".MYSQL.Port", value);
        }

        isEnabled = !(database.isEmpty() | username.isEmpty() | password.isEmpty());

        ArrayList<String> fails = new ArrayList<String>();

        if (database.isEmpty()) {
            fails.add("Database");
        }

        if (username.isEmpty()) {
            fails.add("User");
        }

        if (password.isEmpty()) {
            fails.add("Password");
        }

        if (!fails.isEmpty()) {
            System.out.println(Main.Prefix + "MYSQL Service deactivated, because this Values are empty (" + String.join(", ", fails) + ")");
        }
    }

    public static boolean isConnected() {
        return (con != null);
    }

    public static Connection con;

    public static void connect() {
        if (isEnabled) {
            if (!isConnected()) {
                try {
                    con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
                    if (!con.isClosed()) {
                        System.out.println(Main.Prefix + "MYSQL successfully connected");
                    } else {
                        System.out.println(Main.Prefix + "Connection failed");
                    }
                } catch (SQLException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }

            }
        }
    }

    public static void disconnect() {
        if (isEnabled) {
            if (isConnected()) {
                try {
                    con.close();
                    System.out.println(Main.Prefix + "MYSQL successfully disconnected");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static Connection getConnection() {
        return con;
    }
}
