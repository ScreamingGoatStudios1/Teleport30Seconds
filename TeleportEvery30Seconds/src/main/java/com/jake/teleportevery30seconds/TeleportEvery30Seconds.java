package com.jake.teleportevery30seconds;

import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;
import java.util.UUID;

public final class TeleportEvery30Seconds extends JavaPlugin {
    public static TeleportEvery30Seconds plugin;

    public static boolean isStarted = false;

    @Override
    public void onEnable() {
        getCommand("randomteleport").setExecutor(new StartCommand());

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                if (TeleportEvery30Seconds.isStarted) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        Random random = new Random();
                        int x = random.nextInt(10000);
                        int z = random.nextInt(10000);
                        int y = player.getWorld().getHighestBlockYAt(x, z) + 1;
                        Location location = new Location(player.getWorld(), x, y, z);
                        player.teleport(location);
                    }
                }
            }
        }, 600L, 600L);
    }
}
