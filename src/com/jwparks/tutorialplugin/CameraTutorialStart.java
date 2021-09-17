package com.jwparks.tutorialplugin;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.Gate;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_17_R1.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_17_R1.block.impl.CraftBanner;
import org.bukkit.entity.Player;
import org.bukkit.block.data.Rotatable;
import org.bukkit.material.MaterialData;

import java.util.Objects;

import static org.bukkit.Bukkit.getServer;
import com.jwparks.tutorialplugin.ResetTutorialStart;

public class CameraTutorialStart implements CommandExecutor {

    private Block BLOCK_RED = Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(502, 71, -250);
    private Block BLOCK_YELLOW = Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(502, 71, -251);
    private Block BLOCK_BLUE = Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(502, 71, -252);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {


            // Step 1: Set the reference coordinates (it can be changed soon!)
            Player player = (Player) sender;
            Block reference_block = Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(490, 71, -240);
            Location reference_location = reference_block.getLocation();

            // Step 4: Teleport the player to the reference location
            reference_location.setPitch(0);
            reference_location.setYaw(90);
            player.teleport(reference_location);

            Block red_flag = Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(482, 71, -234);
            Location red_location = red_flag.getLocation();

            Block yellow_flag = Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(482, 71, -240);
            Location yellow_location = yellow_flag.getLocation();

            Block blue_flag = Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(482, 71, -246);
            Location blue_location = blue_flag.getLocation();

            BannerDirection(red_location, BLOCK_RED.getBlockData());
            BannerDirection(yellow_location, BLOCK_YELLOW.getBlockData());
            BannerDirection(blue_location, BLOCK_BLUE.getBlockData());


            Block flag_block = Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(505, 71, -252);
            flag_block.setType(Material.STONE);

        }
        return true;
    }

    public void BannerDirection(Location loc, BlockData blockdata) {
        Block block = loc.getBlock();
        block.setBlockData(blockdata);
    }
}