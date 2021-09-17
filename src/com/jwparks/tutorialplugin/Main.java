package com.jwparks.tutorialplugin;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.Banner;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.jwparks.tutorialplugin.ResetTutorialStart.*;

public class Main extends JavaPlugin implements Listener {
    public List<String> camera_tutorial_instruction = Arrays.asList(
            "카메라 조작 튜토리얼에 오신 것을 환영합니다.",
            "화면상에 빨간색, 노란색, 파란색 깃발이 있습니다.",
            "지금부터 각 깃발을 순서대로 응시해 주세요.");

    public List<String> camera_tutorial_complete = Arrays.asList(
            "잘 하셨습니다. 카메라 조작 튜토리얼을 마칩니다.");

    public List<String> move_tutorial_instruction = Arrays.asList(
            "이동 조작 튜토리얼에 오신 것을 환영합니다.",
            "화면상에 세개의 빨간색 깃발이 있습니다.",
            "빨간색 깃발에 다가가면 검은색 깃발이 됩니다.",
            "지금부터 모든 깃발에 다가가세요.");

    public List<String> move_tutorial_complete = Arrays.asList(
            "잘 하셨습니다. 이동 조작 튜토리얼을 마칩니다.");

    public List<String> easy_tutorial_instruction = Arrays.asList(
            "이 구역에는 총 10개의 빨간색 깃발이 있습니다.",
            "모든 깃발을 1분 안에 터치하면 미션을 성공하게 됩니다.");

    public List<String> easy_tutorial_complete = Arrays.asList(
            "잘 하셨습니다. 테스트를 통과하셨습니다.");

    public List<String> hard_tutorial_instruction = Arrays.asList(
            "이 구역에는 총 30개의 빨간색 깃발이 있습니다.",
            "20개의 깃발을 3분 안에 터치하면 미션을 성공하게 됩니다.");

    public List<String> hard_tutorial_complete = Arrays.asList(
            "잘 하셨습니다. 테스트를 통과하셨습니다.");


    public List<String> common_tutorial_event_sentences_fail = Arrays.asList(
            "과제에 실패하였습니다.");


    public static boolean camera_tutorial_start = false;
    public static boolean move_tutorial_start = false;
    public static boolean easy_tutorial_start = false;
    public static boolean hard_tutorial_start = false;
    public static int tutorial_type = 0;
    public static boolean tutorial_instruction = true;

    public static boolean tutorial_start = false;
    public static boolean disable_move = false;

    public float player_yaw;
    public float player_pitch;

    public static List<Block> easy_tutorial_blocks = getEasyBlocks();
    public int easy_tutorial_scores = 0;
    public int easy_tutorial_ticks = 0;

    public static List<Block> hard_tutorial_blocks = getHardBlocks();
    public int hard_tutorial_scores = 0;
    public int hard_tutorial_ticks = 0;
    public String player_name;


    @Override
    public void onEnable() {
        super.onEnable();
        // Step 1: Log the sheepherding task has been started
        getLogger().info("TutorialPlugin has been enabled!");

        // Step 2: Enable the "start" command to start the shepherding task
        Objects.requireNonNull(this.getCommand("camera")).setExecutor(new CameraTutorialStart());
        Objects.requireNonNull(this.getCommand("move")).setExecutor(new MoveTutorialStart());
        Objects.requireNonNull(this.getCommand("easy")).setExecutor(new EasyTutorialStart()); //need to edit
        Objects.requireNonNull(this.getCommand("hard")).setExecutor(new HardTutorialStart()); //need to edit
        Objects.requireNonNull(this.getCommand("reset")).setExecutor(new ResetTutorialStart()); //need to edit

        // Step 3: Set the world state(weather) clean
        for (World world : getServer().getWorlds()) {
            world.setStorm(false);
            world.setThundering(false);
        }
        getServer().getPluginManager().registerEvents(this, (Plugin) this);

        // Step 4: Run the PlayerMoveEvent
        // Bukkit.getPluginManager().registerEvents(new ShepherdEvent(), (Plugin)this);

        // Run the passive scheduler called at every server tick

        for(Player pls : Bukkit.getOnlinePlayers()){
            if(pls.hasPermission("ozril.staff")){
                player_name = pls.getName();
            }

        }
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, () -> {
            Player player = Bukkit.getPlayer(player_name);
            if (player != null) {

                if (tutorial_type == 1 && tutorial_instruction) {
                    PrintMessage(this, player, camera_tutorial_instruction);
                    tutorial_start = true;
                    tutorial_instruction = false;
                }
                if (tutorial_type == 1 && tutorial_start) {
                    List<Double> angle_dot = new ArrayList<>();
                    for(Block block : camera_tutorial_blocks) {

                        Location player_location = player.getLocation();
                        Vector player_vector = new Vector(player_location.getX(), player_location.getY(), player_location.getZ());
                        player_yaw = player_location.getYaw();
                        player_pitch = player_location.getPitch();

                        Location block_location = block.getLocation();
                        Vector block_vector = new Vector(block_location.getX(), block_location.getY(), block_location.getZ());

                        Vector player_to_block_vector = block_vector.subtract(player_vector).normalize();
                        Vector player_sight_angle_vector = new Vector(-Math.cos(Math.toRadians(player_yaw))*Math.sin(Math.toRadians(player_pitch)),
                                                                         -Math.sin(Math.toRadians(player_pitch)),
                                                                      -Math.sin(Math.toRadians(player_yaw))*Math.cos(Math.toRadians(player_pitch))).normalize();

                        player_to_block_vector.multiply(player_sight_angle_vector);

                        double direction_angle_dot = player_to_block_vector.getX()+player_to_block_vector.getY()+player_to_block_vector.getZ();
                        angle_dot.add(direction_angle_dot);

                    }
                        player.spigot().sendMessage(
                                ChatMessageType.ACTION_BAR,
                                new TextComponent(String.format("빨간색: %2.2f, 노란색: %2.2f, 파란색: %2.2f", angle_dot.get(0), angle_dot.get(1), angle_dot.get(2)))
                        );
                }

                if (tutorial_type == 3 && tutorial_instruction) {
                    PrintMessage(this, player, easy_tutorial_instruction);
                    tutorial_start = true;
                    tutorial_instruction = false;
                    easy_tutorial_ticks = 0;
                }

                if (tutorial_type == 3 && tutorial_start) {
                    easy_tutorial_ticks++;
                    for(Block block : easy_tutorial_blocks) {
                        Location player_location = player.getLocation();
                        Vector player_vector = new Vector(player_location.getX(), player_location.getY(), player_location.getZ());

                        Location block_location = block.getLocation();
                        Vector block_vector = new Vector(block_location.getX(), block_location.getY(), block_location.getZ());

                        double length = block_vector.subtract(player_vector).length();
                        // System.out.println(length);
                        if (block.getType()==Material.RED_BANNER && length < 1.5){
                            //List<MetadataValue> data = block.getState();
                            block.setType(Material.BLACK_BANNER);
//                            BlockData face = block.getBlockData().matches();
                            //System.out.println(data);

                            //block.setBlockData((BlockData) face);
//                            Banner banner = (Banner) block.getState();
//                            banner.setBaseColor(DyeColor.BLACK);
//                            block.setBlockData(banner.getBlockData());
                            easy_tutorial_scores++;
                        }

                    }
                    player.spigot().sendMessage(
                            ChatMessageType.ACTION_BAR,
                            new TextComponent(String.format("남은 깃발의 개수: %d, 경과 시간: %2.2f초", 10-easy_tutorial_scores, (double)easy_tutorial_ticks/20))
                    );

                    if (easy_tutorial_scores >= 10){
                        tutorial_start = false;
                    }
                }

                if (tutorial_type == 4 && tutorial_instruction) {
                    PrintMessage(this, player, hard_tutorial_instruction);
                    tutorial_start = true;
                    tutorial_instruction = false;
                    hard_tutorial_ticks = 0;
                }
                if (tutorial_type == 4 && tutorial_start) {
                    hard_tutorial_ticks++;
                    for(Block block : hard_tutorial_blocks) {
                        Location player_location = player.getLocation();
                        Vector player_vector = new Vector(player_location.getX(), player_location.getY(), player_location.getZ());

                        Location block_location = block.getLocation();
                        Vector block_vector = new Vector(block_location.getX(), block_location.getY(), block_location.getZ());

                        double length = block_vector.subtract(player_vector).length();
                        // System.out.println(length);
                        if (block.getType()==Material.RED_BANNER && length < 1.5){
                            block.setType(Material.BLACK_BANNER);
                            hard_tutorial_scores++;
                        }

                    }
                    player.spigot().sendMessage(
                            ChatMessageType.ACTION_BAR,
                            new TextComponent(String.format("남은 깃발의 개수: %d, 경과 시간: %2.2f초", 20-hard_tutorial_scores, (double)hard_tutorial_ticks/20))
                    );

                    if (hard_tutorial_scores >= 20){
                        tutorial_start = false;
                    }
                }


            }
        }, 0L, 1L);
    }


    @Override
    public void onDisable() {
        super.onDisable();
        getLogger().info("TutorialPlugin has been disabled!");
    }

    // Event to prevent the weather change
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();

        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        Block camera_flag = ResetTutorialStart.Camera_flag;
        Block move_flag = ResetTutorialStart.Move_flag;
        Block easy_flag = ResetTutorialStart.Easy_flag;
        Block hard_flag = ResetTutorialStart.Hard_flag;
        tutorial_instruction = true;

        if (camera_flag.getType() == Material.STONE) {
            tutorial_type = 1;
        }
        if (move_flag.getType() == Material.STONE) {
            tutorial_type = 2;
        }
        if (easy_flag.getType() == Material.STONE) {
            tutorial_type = 3;
            easy_tutorial_scores = 0;
            easy_tutorial_ticks = 0;
        }
        if (hard_flag.getType() == Material.STONE) {
            tutorial_type = 4;
            hard_tutorial_scores = 0;
            hard_tutorial_ticks = 0;
        }

        tutorial_start = false;


    }


    @EventHandler
    public void onBlockBuild(BlockPlaceEvent event) {
        //System.out.print("here");
        event.setCancelled(true);
    }

    public void ChangeBannerColor(Block block, BlockFace face){

    }


    public void BuildLocationIndicator(Vector location) {
        int X = (int) location.getX();
        int Y = (int) location.getY();
        int Z = (int) location.getZ();
        for (int y = 0; y < 12; y++) {
            Block block = Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(X, Y + y, Z);
            //System.out.println(block);
            block.setType(Material.RED_WOOL);
        }
    }

    public void RemoveLocationIndicator(Vector location) {
        int X = (int) location.getX();
        int Y = (int) location.getY();
        int Z = (int) location.getZ();
        for (int y = 0; y < 12; y++) {
            Block block = Objects.requireNonNull(getServer().getWorld("world")).getBlockAt(X, Y + y, Z);
            //System.out.println(block);
            block.setType(Material.AIR);
        }
    }

    public void PrintMessage(JavaPlugin instance, Player player, List<String> msg_list) {
        new BukkitRunnable() {
            @Override
            public void run() {
                disable_move = true;
                for (String msg : msg_list) {
                    switch (msg) {
                        default -> {
                            player.sendTitle(" ", msg, 10, 40, 10);
                            try {
                                Thread.sleep(2000);       // wait until a thread finish the death animation...
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                disable_move = false;
            }

        }.runTaskAsynchronously(instance);
    }

    @EventHandler
    public void onDrop(PlayerMoveEvent event) {
        if (!disable_move) {
            if (!event.getFrom().toVector().equals(event.getTo().toVector()))
                event.setCancelled(false);
        } else if (disable_move) {
            if (!event.getFrom().toVector().equals(event.getTo().toVector()))
                event.setCancelled(true);
        }
    }


}
