package com.jwparks.tutorialplugin;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Llama;
import org.bukkit.entity.Player;

import java.util.Objects;

import static org.bukkit.Bukkit.getServer;

public class MoveTutorialStart implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            System.out.println("** Common tutorial started **");
            // Step 1: Set the reference coordinates (it can be changed soon!)
            Player player = (Player) sender;
            Block reference_block = Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(1150, 70, 2600);
            Location reference_location = reference_block.getLocation();

            // Step 4: Teleport the player to the reference location
            reference_location.setPitch(0);
            reference_location.setYaw(-120);
            player.teleport(reference_location);
            player.performCommand("kill @e[type=!player]");    // kill all entity
            player.performCommand("kill @e[type=!player]");    // kill all entity

            // Step 5: summon sheep
            Location spawn_location = Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(1213, 69, 2589).getLocation();
            Llama llama = (Llama) Objects.requireNonNull(getServer().getWorld("world")).spawnEntity(spawn_location, EntityType.LLAMA);
            llama.setColor(Llama.Color.WHITE);

            player.teleport(reference_location.add(0.05, 0, 0));

        }
        return true;
    }



}