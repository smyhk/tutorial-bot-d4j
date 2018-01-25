/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smyhktech.bot.tutorial.objects;

import java.util.List;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;

/**
 *
 * @author skedzie
 */
public interface Command {
    
    public String getLabel();
    public String getDescription();
    
    public void runCommand(IUser user, IChannel channel, IGuild guild, String label, List<String> args);
}
