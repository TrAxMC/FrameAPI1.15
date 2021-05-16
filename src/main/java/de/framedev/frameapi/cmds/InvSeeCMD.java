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
 * This Class was made at 07.03.2020, 12:34
 */
public class InvSeeCMD implements CommandExecutor {

    private final Main plugin;

    public InvSeeCMD(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("invsee").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 1) {
                if(player.hasPermission("frameapi.invsee")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        if (target != player) {
                            player.openInventory(target.getInventory());
                        } else {
                            player.sendMessage(Main.Variables.getPrefix() + " §cYou can't see your own Inventory!");
                        }
                    } else {
                        player.sendMessage(Main.Variables.getPrefix() + " §cThis Player doesn't exist!");
                    }
                } else {
                    player.sendMessage(Main.Variables.getPrefix() + " " + Main.Variables.getNoPerm());
                }
            } else {
                player.sendMessage(Main.Variables.getPrefix() + " §cPlease use /invsee <PlayerName>!");
            }
        }
        return false;
    }
}
