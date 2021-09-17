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

        String url = "plugins/Movies/"+args[0]+".html";
        System.out.println("String check: True: "+ url);
        File htmlFile = new File(url);
        System.out.println("File load check: True");
        try {
            Desktop.getDesktop().browse(htmlFile.toURI());
            System.out.println("Open browse check: True");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
