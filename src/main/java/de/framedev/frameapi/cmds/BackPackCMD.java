package de.framedev.frameapi.cmds;

import de.framedev.frameapi.main.Main;
import de.framedev.frameapi.mysql.InventoryStringDeSerializer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import javax.naming.InvalidNameException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/*
 * =============================================
 * = This Plugin was Created by FrameDev       =
 * = Copyrighted by FrameDev                   =
 * = Please don't Change anything              =
 * =============================================
 * This Class was made at 07.03.2020, 12:48
 */
public class BackPackCMD implements CommandExecutor, Listener {

    HashMap<Player,Inventory> backpack = new HashMap<>();
    File file = new File(Main.getInstance().getDataFolder(),"backpack.yml");
    FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
    private final Main plugin;

    public BackPackCMD(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("backpack").setExecutor(this);
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0) {
                if(player.hasPermission("frameapi.backpack")) {
                    player.openInventory(loadBackPack(player));
                }
            } else if(args.length == 1) {
                if(player.hasPermission("frameapi.backpack.other")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    player.openInventory(loadBackPack(target));
                }
            }
        }
        return false;
    }

    public void saveBackPack(Player player, Inventory inventory) {
        if(backpack.containsKey(player)) {
                cfg.set(player.getName(), InventoryStringDeSerializer.toBase64(inventory));
                try {
                    cfg.save(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            backpack.remove(player);
        }
    }

    public Inventory loadBackPack(Player player) {
        if(!backpack.containsKey(player.getName())) {
            if(cfg.getString(player.getName()) != null) {
                Inventory inventory = null;
                try {
                    inventory = InventoryStringDeSerializer.fromBase64(cfg.getString(player.getName()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                backpack.put(player,inventory);
                return inventory;

            } else {
                Inventory inventory = Bukkit.createInventory(null,3*9);
                backpack.put(player,inventory);
                return inventory;
            }
        }
        return null;
    }

    @EventHandler
    public void onCloseBackpack(InventoryCloseEvent event) {
        for(Player online : Bukkit.getOnlinePlayers()) {
            if (backpack.containsKey(online)) {
                saveBackPack(online, backpack.get(online));
            }
        }
    }
}
