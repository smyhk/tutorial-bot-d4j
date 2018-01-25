/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smyhktech.bot.tutorial.objects;

import java.util.ArrayList;
import java.util.List;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;

/**
 *
 * @author skedzie
 */
public class CommandManager {
    
    private String prefix;
    private List<Command> commands;
    
    // Constructor
    public CommandManager(String prefix) {
        this.prefix = prefix;
        commands = new ArrayList<>();
    }
    
    public String getPrefix() {
        return prefix;
    }
    
    public void addCommand(Command command) {
        
        commands.add(command);
    }
    
    public List<Command> getCommands() {
        return commands;
    }
    
    @EventSubscriber
    public void commandRan(MessageReceivedEvent event) {
        
        String message = event.getMessage().getContent();
        if (message.toLowerCase().startsWith(prefix)) {
            IUser user = event.getAuthor();
            IChannel channel = event.getChannel();
            IGuild guild = event.getGuild();
            
            String label = message.toLowerCase().replace(prefix, "").split(" ")[0];
            
            String[] argus = message.replace(prefix + label + " ", "").split(" ");
            List<String> args = new ArrayList<>();
            for (String arg : argus) {
                args.add(arg);
            }
            
            boolean worked = false;
            
            for (Command command : commands) {
                if (command.getLabel().equalsIgnoreCase(label)) {
                    worked = true;
                    command.runCommand(user, channel, guild, label, args);
                    if (!channel.isPrivate()) {
                        event.getMessage().delete();
                    }
                }
            } // end for
            
            if (!worked) {
                System.out.println("User typed invalid command.");
            }
        }
    }
}
