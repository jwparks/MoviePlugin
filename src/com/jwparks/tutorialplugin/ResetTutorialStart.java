package com.jwparks.tutorialplugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.bukkit.Bukkit.getServer;

public class ResetTutorialStart implements CommandExecutor {

    public static int num_easy_blocks = 10;


    public static Block Camera_flag = Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(505, 71, -252);
    public static Block Move_flag = Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(506, 71, -252);
    public static Block Easy_flag = Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(507, 71, -252);
    public static Block Hard_flag = Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(508, 71, -252);
    public static List<Block> tutorial_flags = Arrays.asList(Camera_flag,Move_flag, Easy_flag, Hard_flag);

    public static Block camera_red = Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(482, 71, -234);
    public static Block camera_yellow = Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(482, 71, -240);
    public static Block camera_blue = Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(482, 71, -246);
    public static List<Block> camera_tutorial_blocks = Arrays.asList(camera_red,camera_yellow, camera_blue);

    //public static List<Block> easy_tutorial_blocks = getEasyBlocks();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {

            for(Block block : tutorial_flags) {
                block.setType(Material.AIR);
            }

            // Reset camera tutorial
            for(Block block : camera_tutorial_blocks) {
                block.setType(Material.AIR);
            }

            List<Block> easy_tutorial_blocks = getEasyBlocks();
            for(Block block : easy_tutorial_blocks) {
                block.setType(Material.AIR);
            }

            Player player = (Player) sender;
            player.teleport(player.getLocation());

        }
        return true;
    }

    public static List<Block> getEasyBlocks(){
        List<Block> easy_blocks = new ArrayList<>();
        easy_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(465, 71, -251));
        easy_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(461, 71, -231));
        easy_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(490, 71, -252));
        easy_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(436, 71, -230));
        easy_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(407, 71, -238));
        easy_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(427, 71, -249));
        easy_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(445, 71, -253));
        easy_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(442, 73, -236));
        easy_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(479, 71, -247));
        easy_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(478, 71, -231));
        return easy_blocks;
    }

    public static List<Block> getHardBlocks(){
        List<Block> hard_blocks = new ArrayList<>();
        hard_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(467, 68, -270));
        hard_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(457, 72, -278));
        hard_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(437, 64, -309));
        hard_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(452, 62, -310));
        hard_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(471, 64, -309));
        hard_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(481, 63, -325));
        hard_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(485, 71, -375));
        hard_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(512, 64, -387));
        hard_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(523, 60, -360));
        hard_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(513, 67, -328));

        hard_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(532, 66, -312));
        hard_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(519, 70, -283));
        hard_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(499, 69, -270));
        hard_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(488, 66, -300));
        hard_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(498, 64, -344));
        hard_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(485, 64, -364));
        hard_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(477, 69, -337));
        hard_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(470, 69, -343));
        hard_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(463, 62, -357));
        hard_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(534, 64, -322));

        hard_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(464, 68, -287));
        hard_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(483, 69, -277));
        hard_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(483, 63, -344));
        hard_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(521, 64, -392));
        hard_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(545, 63, -380));
        hard_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(518, 63, -353));
        hard_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(471, 64, -402));
        hard_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(435, 69, -277));
        hard_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(529, 63, -373));
        hard_blocks.add(Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(494, 64, -318));

        return hard_blocks;
    }
}