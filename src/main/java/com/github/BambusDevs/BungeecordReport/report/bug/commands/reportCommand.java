package com.github.BambusDevs.BungeecordReport.report.bug.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.io.Console;

/**
 * Created by Karl24 on 13.03.2016.
 */
public class reportCommand extends Command {

    public reportCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if(args.length == 0){
                if(sender instanceof ProxiedPlayer) {
                    sender.sendMessage(ChatColor.RED + "[Bungeecord Report] Wrong Command!");
                    sender.sendMessage(ChatColor.RED + "[Bungeecord Report] /report (player/bug/suggestion) (Player/Bug/suggestion) [More Information]");
                } else {
                    System.out.println("[Bungeecord Report] Wrong Command!");
                    System.out.println("[Bungeecord Report] /report (player/bug/suggestion) (Player/Bug/suggestion) [More Information]");
                }
            if(args[0].equalsIgnoreCase("Bug")) {
                String Bug = args[1];
                if(args.length != 2){
                   /* for () {
                           //Alle Informationen abfragen
                    } */
                }
            }
        }

    }
}
