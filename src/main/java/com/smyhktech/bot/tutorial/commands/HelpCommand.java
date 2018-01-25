/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smyhktech.bot.tutorial.commands;

import com.smyhktech.bot.tutorial.BotMain;
import com.smyhktech.bot.tutorial.objects.Command;
import java.util.List;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;

/**
 *
 * @author skedzie
 */
public class HelpCommand implements Command {

    @Override
    public String getLabel() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "The help command!";
    }

    @Override
    public void runCommand(IUser user, IChannel channel, IGuild guild, String label, List<String> args) {
        String helpMessage = "---  **Help**  ---\n\n";

        for (Command command : BotMain.bot.getCommands()) {
            String lbl = command.getLabel();
            String desc = command.getDescription();

            helpMessage += "   **" + BotMain.bot.getPrefix() + lbl + "**\n";
            helpMessage += "      - " + desc + "\n\n";
        }
        user.getOrCreatePMChannel().sendMessage(helpMessage);
    }

}
