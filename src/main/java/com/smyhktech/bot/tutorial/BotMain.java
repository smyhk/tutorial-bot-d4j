/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smyhktech.bot.tutorial;

import com.smyhktech.bot.tutorial.commands.HelpCommand;
import com.smyhktech.bot.tutorial.config.Config;
import com.smyhktech.bot.tutorial.config.File;
import com.smyhktech.bot.tutorial.console.ConsoleCommand;
import com.smyhktech.bot.tutorial.console.ConsoleCommandManager;
import com.smyhktech.bot.tutorial.objects.Bot;
import com.smyhktech.bot.tutorial.objects.Command;
import java.util.List;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;

/**
 *
 * @author skedzie
 */
public class BotMain {
    
    private static Config token;
    public static Bot bot;
    
    public static boolean running;
    public static ConsoleCommandManager console;
    
    public static void main(String[] args) {
        
        running = true;
        console = new ConsoleCommandManager();
        token = new Config(new File("Token"));
        
        // this token will be regenerated and acquired via environment variables
        bot = new Bot(token.getObject("token", "[PUT TOKEN HERE]"));
        
        // bot commands
        bot.addCommand(new HelpCommand());
        bot.addCommand(new Command() {
            @Override
            public String getLabel() {
                return "ping";
            }

            @Override
            public String getDescription() {
                return "Pings the bot to get a pong! reply.";
            }

            @Override
            public void runCommand(IUser user, IChannel channel, IGuild guild, String label, List<String> args) {
                channel.sendMessage("Pong!");
            }
        });
        
        // console commands
        console.addCommand(new ConsoleCommand() {
            @Override
            public String getLabel() {
                return "stop";
            }

            @Override
            public String getDescription() {
                return "Stops the bot gracefully.";
            }

            @Override
            public void run(String[] args) {
                System.out.println("Shutting down bot.");
                running = false;
                bot.logout();
            }
        });
        
        console.addCommand(new ConsoleCommand() {
            @Override
            public String getLabel() {
                return "help";
            }

            @Override
            public String getDescription() {
                return "Displays the help info on console commands.";
            }

            @Override
            public void run(String[] args) {
                if (args.length == 2) {
                    boolean worked = false;
                    for (ConsoleCommand command : console.getCommands()) {
                        if (command.getLabel().equalsIgnoreCase(args[1])) {
                            worked = true;
                            System.out.println("***  - Console Help -  ***");
                            System.out.println("Command: " + command.getLabel());
                            System.out.println(" - Description: " + command.getDescription());
                        }
                    }
                    if (!worked) {
                        System.out.println("[" + args[0] + "] is an invalid command.\n");
                    }
                } else {
                    System.out.println("***  - Console Help -  ***");
                    for (ConsoleCommand command : console.getCommands()) {
                        System.out.println("Command: " + command.getLabel());
                        System.out.println(" - Description: " + command.getDescription());
                    }
                }
            }
        });
        
        console.addCommand(new ConsoleCommand() {
            @Override
            public String getLabel() {
                return "broadcast";
            }

            @Override
            public String getDescription() {
                return "Broadcasts a message to the Discord server.";
            }

            @Override
            public void run(String[] args) {
                for (IGuild guild : bot.getGuilds()) {
                    for (IChannel channel : guild.getChannels()) {
                        if (channel.getName().equalsIgnoreCase("general")) {
                            String message = "";
                            for (String lbl : args) {
                                if (lbl.equalsIgnoreCase("broadcast")) {
                                    continue;
                                }
                                message += lbl + " ";
                            }
                            message = message.trim();
                            channel.sendMessage(message);
                        }
                    }
                }
            }
        });
    }
}
