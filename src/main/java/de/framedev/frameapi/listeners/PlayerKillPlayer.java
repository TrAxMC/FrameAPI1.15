package de.framedev.frameapi.listeners;

import de.framedev.frameapi.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

public class PlayerKillPlayer implements Listener {

    public PlayerKillPlayer(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, (Plugin) plugin);
    }

    private Main plugin;

    @EventHandler
    public void onPlayerKillPlayer(PlayerDeathEvent event) {
        if ((Main.cfgm.getBoolean("MongoDB.LocalHost") || Main.cfgm.getBoolean("MongoDB.Boolean")) && event.getEntity() instanceof Player) {
            Player player = event.getEntity();
            Player killer = player.getKiller();
            if (killer != null && killer instanceof Player) {
                this.plugin.getBackendManager().createPlayerStats((OfflinePlayer) killer, "stats");
                int kills = (Integer) this.plugin.getBackendManager().get((OfflinePlayer) killer, "kills", "stats");
                kills++;
                this.plugin.getBackendManager().updateUser((OfflinePlayer) killer, "kills", kills, "stats");
                killer.sendMessage("§6Es wurde dir ein Kill gutgeschrieben!");
            }
        }
    }
}