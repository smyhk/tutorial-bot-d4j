/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smyhktech.bot.tutorial.objects;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

/**
 *
 * @author skedzie
 */
public class Bot {
    
    IDiscordClient bot;

    public Bot(String token) {
        
        bot = createClient(token);
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
