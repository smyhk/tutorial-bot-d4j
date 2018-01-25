/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smyhktech.bot.tutorial;

import com.smyhktech.bot.tutorial.commands.HelpCommand;
import com.smyhktech.bot.tutorial.config.Config;
import com.smyhktech.bot.tutorial.config.File;
import com.smyhktech.bot.tutorial.objects.Bot;
import com.smyhktech.bot.tutorial.objects.Command;
import com.smyhktech.bot.tutorial.objects.ConsoleCommand;
import com.smyhktech.bot.tutorial.objects.ConsoleCommandMethod;
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
    
    public static void main(String[] args) {
        
        running = true;
        token = new Config(new File("Token"));
        
        // this token will be regenerated and acquired via environment variables
        bot = new Bot(token.getObject("token", "[PUT TOKEN HERE]"));
        
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
        
        new ConsoleCommand("stop", new ConsoleCommandMethod() {
            
            @Override
            public void consoleMethod(String[] args) {
                bot.logout();
                running = false;
                System.out.println("-----------------");
                System.out.println("Stopping the bot.");
                System.out.println("-----------------");
                System.out.println("");
                bot.logout();
                running = false;
            }
        });
        
        /*new ConsoleCommand("broadcast", new ConsoleCommandMethod() {
            
            @Override
            public void consoleMethod(String[] args) {
                for (IGuild guild : bot.getGuilds()) {
                    for (IChannel channel : guild.getChannels()) {
                        if (channel.getName().equalsIgnoreCase("announcements")) {
                            String message = "";
                            for (String arg : args) {
                                message += arg + " ";
                            }
                            message.replaceFirst(args[0] + " ", "");
                            channel.sendMessage(message);
                        }
                    }
                }
            }
        }); */
    }
}
