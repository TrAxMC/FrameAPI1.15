package de.framedev.frameapi.cmds;

import de.framedev.frameapi.api.energy.Energy;
import de.framedev.frameapi.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class EnergyCMD
        implements CommandExecutor {
    private final Main plugin;

    public EnergyCMD(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("getenergy").setExecutor(this);
        plugin.getCommand("adminenergy").setExecutor(this);
        plugin.getCommand("addenergy").setExecutor(this);
    }


    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("frameapi.energy")) {
                if (command.getName().equalsIgnoreCase("getenergy")) {
                    if (args.length == 0) {
                        int energy = (new Energy()).getEnergy((OfflinePlayer) player);
                        player.sendMessage("§aYour Energy = §b" + energy);
                    } else if (args.length == 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target != null) {
                            int energy = (new Energy()).getEnergy((OfflinePlayer) target);
                            player.sendMessage("§aThe Energy of §b" + target.getName() + " §ais §b" + energy);
                        } else {
                            OfflinePlayer offline = Bukkit.getOfflinePlayer(args[0]);
                            int energy = (new Energy()).getEnergy(offline);
                            player.sendMessage("§aThe Energy of §b" + offline.getName() + " §ais §b" + energy);
                        }
                    }
                }
                if (command.getName().equalsIgnoreCase("adminenergy")) {
                    (new Energy()).adminEnergy(player);
                }
                if (command.getName().equalsIgnoreCase("addenergy")) {
                    if (args.length == 1) {
                        int i = Integer.parseInt(args[0]);
                        (new Energy()).addEnergy((OfflinePlayer) player, i);
                    } else if (args.length == 2) {
                        int i = Integer.parseInt(args[1]);
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target != null) {
                            (new Energy()).addEnergy((OfflinePlayer) target, i);
                        } else {
                            OfflinePlayer offline = Bukkit.getOfflinePlayer(args[0]);
                            (new Energy()).addEnergy(offline, i);
                        }
                    }
                }
            }
        }

        return false;
    }
}


