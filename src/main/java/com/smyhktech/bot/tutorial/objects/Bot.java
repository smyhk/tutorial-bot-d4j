/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smyhktech.bot.tutorial.objects;

import java.util.List;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

/**
 *
 * @author skedzie
 */
public class Bot {
    
    private IDiscordClient bot;
    private CommandManager commands;

    public Bot(String token) {
        bot = createClient(token);
        commands = new CommandManager("!");
        bot.getDispatcher().registerListener(commands);
    }
    
    public String getPrefix() {
        return commands.getPrefix();
    }
    
    public void addCommand(Command command) {
        commands.addCommand(command);
    }
    
    public List<Command> getCommands() {
        return commands.getCommands();
    }
    
    private IDiscordClient createClient(String token) {
        ClientBuilder clientBuilder = new ClientBuilder();
        clientBuilder.withToken(token);
        try {
            return clientBuilder.login();
        }
        catch (DiscordException e) {
            e.printStackTrace();
            return null;
        }
    }
}
