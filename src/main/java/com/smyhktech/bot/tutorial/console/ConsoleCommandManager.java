/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smyhktech.bot.tutorial.console;

import com.smyhktech.bot.tutorial.BotMain;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author skedzie
 */
public class ConsoleCommandManager implements Runnable {
    
    private Scanner scanner;
    private List<ConsoleCommand> commands;
    
    public ConsoleCommandManager() {
        commands = new ArrayList<>();
        new Thread(this).start();
    }
    
    public void addCommand(ConsoleCommand command) {
        commands.add(command);
    }
    
    public List<ConsoleCommand> getCommands() {
        return commands;
    }

    @Override
    public void run() {
        while (BotMain.running) {
            scanner = new Scanner(System.in);
            String[] args = scanner.nextLine().split(" ");
            boolean worked = false;
            for (ConsoleCommand command : commands) {
                if (args[0].equalsIgnoreCase(command.getLabel())) {
                    worked = true;
                    command.run(args);
                    System.out.println("");
                }
            } // end for
            if (!worked) {
                System.out.println("[" + args[0] + "] is an invalid command.\n");
            }
        }
    }
}
