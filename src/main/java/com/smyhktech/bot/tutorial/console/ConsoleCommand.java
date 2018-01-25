/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smyhktech.bot.tutorial.console;

/**
 *
 * @author skedzie
 */
public interface ConsoleCommand {
    
    public String getLabel();
    public String getDescription();
    
    public void run(String[] args);
    
}
