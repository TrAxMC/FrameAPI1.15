package de.framedev.frameapi.cmds;

import de.framedev.frameapi.api.API;
import de.framedev.frameapi.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class PositionCMD
        implements CommandExecutor {
    private final Main plugin;

    HashMap<String, Location> locations = new HashMap<>();

    public PositionCMD(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("pos").setExecutor(this);
    }


    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                String name = args[0];
                if (!locations.containsValue(API.Locations.getLocation(name))) {
                    if (API.Locations.cfg.get(name) != null) {
                        Location loc = API.Locations.getLocation(name);
                        try {
                            API.Locations.cfg.load(API.Locations.file);
                        } catch (IOException | InvalidConfigurationException e) {
                            e.printStackTrace();
                        }
                        int x = loc.getBlockX();
                        int y = loc.getBlockY();
                        int z = loc.getBlockZ();
                        player.sendMessage("§aLocation = §b[" + name + "] §aLocation Koordinaten §c= §a[x§b" + x + "§a]§a[y§b" + y + "§a]§a[z§b" + z + "§a]");
                    }
                } else if (locations.containsValue(API.Locations.getLocation(name))) {
                    int x = locations.get(name).getBlockX();
                    int y = locations.get(name).getBlockY();
                    int z = locations.get(name).getBlockZ();
                    player.sendMessage("§aLocation = §b[" + name + "] §aLocation Koordinaten §c= §a[x§b" + x + "§a]§a[y§b" + y + "§a]§a[z§b" + z + "§a]");
                } else {
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        API.Locations.setLocation(name, player.getLocation());
                        int x = player.getLocation().getBlockX();
                        int y = player.getLocation().getBlockY();
                        int z = player.getLocation().getBlockZ();

                        if (locations.containsKey(name)) {
                            player.sendMessage("§cThis Location is Already exists!");
                        } else {
                            locations.put(name, player.getLocation());
                            all.sendMessage("§a" + player.getName() + " §bhat eine Location gespeichert = §a[" + name + "] §bLocation §c= §a[x§b" + x + "§a]§a[y§b" + y + "§a]§a[z§b" + z + "§a]");
                            Bukkit.getServer().reload();
                        }
                    }
                }
            } else {
                player.sendMessage("§cPlease use §b/pos <location name>");
            }
        }
        return false;
    }
}


