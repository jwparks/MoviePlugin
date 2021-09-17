package com.jwparks.tutorialplugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Llama;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static org.bukkit.Bukkit.getServer;
import com.jwparks.tutorialplugin.ResetTutorialStart;
import static com.jwparks.tutorialplugin.ResetTutorialStart.getEasyBlocks;
import static com.jwparks.tutorialplugin.ResetTutorialStart.Easy_flag;

public class EasyTutorialStart implements CommandExecutor {
    private Block BLOCK_E = Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(502, 71, -245);
    private Block BLOCK_N = Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(502, 71, -246);
    private Block BLOCK_W = Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(502, 71, -247);
    private Block BLOCK_S = Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(502, 71, -248);
    private Block BLOCK_NE = Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(504, 71, -245);
    private Block BLOCK_SE = Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(504, 71, -246);
    private Block BLOCK_SW = Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(504, 71, -247);
    private Block BLOCK_NW = Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(504, 71, -248);

    private final List<Block> block_directions =  Arrays.asList(BLOCK_E,BLOCK_N,BLOCK_W,BLOCK_S,BLOCK_NE,BLOCK_SE,BLOCK_SW,BLOCK_NW);
    private final List<Block> easy_tutorial_blocks = getEasyBlocks();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {

            Player player = (Player) sender;
            Block reference_block = Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(490, 71, -240);
            Location reference_location = reference_block.getLocation();

            // Step 4: Teleport the player to the reference location
            reference_location.setPitch(0);
            reference_location.setYaw(90);
            player.teleport(reference_location);

            for(Block block : easy_tutorial_blocks) {
                Location block_location = block.getLocation();
                Random rand = new Random();
                BannerDirection(block_location, block_directions.get(rand.nextInt(block_directions.size())).getBlockData());
            }

            Easy_flag.setType(Material.STONE);

        }
        return true;
    }

    public void BannerDirection(Location loc, BlockData blockdata) {
        Block block = loc.getBlock();
        block.setBlockData(blockdata);
    }


}