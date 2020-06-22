package de.framedev.frameapi.cmds;

import de.framedev.frameapi.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
 * =============================================
 * = This Plugin was Created by FrameDev       =
 * = Copyrighted by FrameDev                   =
 * = Please don't Change anything              =
 * =============================================
 * This Class was made at 07.03.2020, 12:26
 */
public class EnderChestCMD implements CommandExecutor {

    private final Main plugin;

    public EnderChestCMD(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("enderchest").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0) {
                if(player.hasPermission("frameapi.enderchest")) {
                    player.openInventory(player.getEnderChest());
                } else {
                    player.sendMessage(Main.FrameMainGet.getPrefix() + " " + Main.FrameMainGet.getNoPerm());
                }
            } else if (args.length == 1) {
                if(player.hasPermission("frameapi.enderchest.other")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if(target != null) {
                        player.openInventory(target.getEnderChest());
                    } else {
                        player.sendMessage(Main.FrameMainGet.getPrefix() + " §cThis Player i'snt Online!");
                    }
                } else {
                    player.sendMessage(Main.FrameMainGet.getPrefix() + " " + Main.FrameMainGet.getNoPerm());
                }
            }
        }
        return false;
    }
}
