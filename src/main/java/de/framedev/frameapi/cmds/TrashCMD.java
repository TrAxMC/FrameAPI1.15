package de.framedev.frameapi.cmds;

import de.framedev.frameapi.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TrashCMD implements CommandExecutor {

    private final Main plugin;

    public TrashCMD(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("trash").setExecutor(this::onCommand);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("frameapi.trash")) {
                //Create Trash Inventory
                Inventory inventory = Bukkit.createInventory(null,5*9,"§cTrash");

                //Open the Trash Inventory
                player.openInventory(inventory);
                return true;
            } else {
                player.sendMessage(Main.FrameMainGet.getPrefix() + " " + Main.FrameMainGet.getNoPerm());
                return true;
            }
        } else {
            Bukkit.getConsoleSender().sendMessage(Main.FrameMainGet.getPrefix() + " §cYou need to be a Player!");
            return true;
        }
    }
}
