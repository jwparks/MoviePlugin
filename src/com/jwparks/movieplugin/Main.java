package com.jwparks.movieplugin;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Objects;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        super.onEnable();
        System.out.println("Moive plugin loaded");
        Objects.requireNonNull(this.getCommand("movie")).setExecutor(new MovieStart());

    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

}
