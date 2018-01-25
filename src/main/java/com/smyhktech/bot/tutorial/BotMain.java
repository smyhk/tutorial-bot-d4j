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
    
    public static void main(String[] args) {
        
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
    }
}
