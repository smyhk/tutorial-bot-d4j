/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smyhktech.bot.tutorial.objects;

import com.smyhktech.bot.tutorial.BotMain;
import java.util.Scanner;

/**
 *
 * @author skedzie
 */
public class ConsoleCommand implements Runnable {
    
    private String label;
    private ConsoleCommandMethod ccMethod;
    
    public ConsoleCommand(String label, ConsoleCommandMethod ccMethod) {
        this.label = label;
        this.ccMethod = ccMethod;
        new Thread(this).start();
    }
    
    @Override
    public void run() {
        while (BotMain.running) {
            Scanner console = new Scanner(System.in);
            String command = console.nextLine();
            String[] args = command.split(" ");
            if (args[0].equalsIgnoreCase(label)) {
                ccMethod.consoleMethod(args);
                console.close();
            }
            
        }
    }
    
}
