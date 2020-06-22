package de.framedev.frameapi.cmds;

import de.framedev.frameapi.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCMD
        implements CommandExecutor {
    private final Main plugin;

    public FlyCMD(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("fly").setExecutor(this);
    }


    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("fly")) {
            if (args.length == 0) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    if (player.hasPermission("frameapi.fly")) {
                        if (player.getAllowFlight() == true) {
                            player.setAllowFlight(false);
                            player.setFlying(false);
                            player.sendMessage("§aYou can no more Fly!");
                            return true;
                        }
                        player.setAllowFlight(true);
                        player.setFlying(true);
                        player.sendMessage("§aYou can now Fly!");
                        return true;
                    }

                }
            } else if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (sender.hasPermission("frameapi.fly.other")) {
                    if (target != null) {
                        if (target.getAllowFlight()) {
                            target.setAllowFlight(false);
                            target.setFlying(false);
                            target.sendMessage("§aYou can no more Fly!");
                            sender.sendMessage("§b" + target.getName() + " §aCan no more Fly!");
                            return true;
                        }
                        target.setAllowFlight(true);
                        target.setFlying(true);
                        target.sendMessage("§aYou can now Fly!");
                        sender.sendMessage("§b" + target.getName() + " §aCan now Fly!");
                        return true;
                    }

                    sender.sendMessage("§cThis Player i'snt Online!");
                }
            }
        }
        return false;
    }
}


