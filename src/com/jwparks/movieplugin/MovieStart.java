package com.jwparks.movieplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class MovieStart implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // "C:\Program Files\Google\Chrome\Application\chrome.exe" -kiosk -fullscreen http://google.com
        String url = "plugins/Movies/"+args[0]+".html";
        File htmlFile = new File(url);
        try {
            Runtime.getRuntime().exec("\"C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe\" -kiosk -fullscreen "+htmlFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}



