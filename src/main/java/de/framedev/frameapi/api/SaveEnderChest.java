package de.framedev.frameapi.api;

import de.framedev.frameapi.main.Main;
import de.framedev.frameapi.mysql.InventoryStringDeSerializer;
import de.framedev.frameapi.mysql.SQL;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;


public class SaveEnderChest
        implements Listener {
    private final Main plugin;

    public SaveEnderChest(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, (Plugin) plugin);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (this.plugin.getConfig().getBoolean("SaveMYSQLEnderChest")) {
            if (SQL.isTableExists("enderchest")) {
                if (SQL.exists("enderchest", "UUID", event.getPlayer().getName())) {
                    SQL.updateData("enderchest", "EnderchestInventory", "'" + InventoryStringDeSerializer.toBase64(event.getPlayer().getEnderChest()) + "'", "UUID ='" + event.getPlayer().getName() + "'");
                } else {
                    SQL.insertData("enderchest", "'" + event.getPlayer().getName() + "','" + InventoryStringDeSerializer.toBase64(event.getPlayer().getEnderChest()) + "'", new String[]{"UUID", "EnderchestInventory"});
                }
            } else {
                SQL.createTable("enderchest", new String[]{"UUID VARCHAR(64)", "EnderchestInventory TEXT"});
                SQL.insertData("enderchest", "'" + event.getPlayer().getName() + "','" + InventoryStringDeSerializer.toBase64(event.getPlayer().getEnderChest()) + "'", new String[]{"UUID", "EnderchestInventory"});
            }
        }
    }

    @EventHandler
    public void onKick(PlayerKickEvent event) {
        if (this.plugin.getConfig().getBoolean("SaveMYSQLEnderChest")) {
            if (SQL.isTableExists("enderchest")) {
                if (SQL.exists("enderchest", "UUID", event.getPlayer().getName())) {
                    SQL.updateData("enderchest", "EnderchestInventory", "'" + InventoryStringDeSerializer.toBase64(event.getPlayer().getEnderChest()) + "'", "UUID ='" + event.getPlayer().getName() + "'");
                } else {
                    SQL.insertData("enderchest", "'" + event.getPlayer().getName() + "','" + InventoryStringDeSerializer.toBase64(event.getPlayer().getEnderChest()) + "'", new String[]{"UUID", "EnderchestInventory"});
                }
            } else {
                SQL.createTable("enderchest", new String[]{"UUID VARCHAR(64)", "EnderchestInventory TEXT"});
                SQL.insertData("enderchest", "'" + event.getPlayer().getName() + "','" + InventoryStringDeSerializer.toBase64(event.getPlayer().getEnderChest()) + "'", new String[]{"UUID", "EnderchestInventory"});
            }
        }
    }

    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        (new BukkitRunnable() {
            public void run() {
                if (SaveEnderChest.this.plugin.getConfig().getBoolean("SaveMYSQLEnderChest")) {
                    if (SQL.isTableExists("enderchest")) {
                        if (SQL.exists("enderchest", "UUID", event.getPlayer().getName())) {
                            Inventory inventory = null;
                            try {
                                inventory = InventoryStringDeSerializer.fromBase64((String) SQL.get("enderchest", "EnderchestInventory", "UUID", event.getPlayer().getName()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (inventory != null) {
                                event.getPlayer().getEnderChest().clear();
                                event.getPlayer().getEnderChest().setContents(inventory.getContents());
                            }
                        }
                    } else {
                        SQL.createTable("enderchest", new String[]{"UUID VARCHAR(64)", "EnderchestInventory TEXT"});
                    }
                }
            }
        }).runTaskLater((Plugin) this.plugin, 20L);
    }

    @EventHandler
    public void onJoinFirst(final PlayerJoinEvent event) {
        if (!event.getPlayer().hasPlayedBefore())
            if(plugin.getConfig().getBoolean("EnderChestStartKit")) {
                (new BukkitRunnable() {
                    public void run() {
                        Inventory inventory = Bukkit.createInventory(null, 27);
                        inventory.setItem(0, new ItemStack(Material.WHITE_SHULKER_BOX));
                        inventory.setItem(1, new ItemStack(Material.ORANGE_SHULKER_BOX));
                        inventory.setItem(2, new ItemStack(Material.MAGENTA_SHULKER_BOX));
                        inventory.setItem(3, new ItemStack(Material.LIGHT_BLUE_SHULKER_BOX));
                        inventory.setItem(4, new ItemStack(Material.YELLOW_SHULKER_BOX));
                        inventory.setItem(5, new ItemStack(Material.LIME_SHULKER_BOX));
                        inventory.setItem(6, new ItemStack(Material.PINK_SHULKER_BOX));
                        inventory.setItem(7, new ItemStack(Material.GRAY_SHULKER_BOX));
                        inventory.setItem(8, new ItemStack(Material.LIGHT_GRAY_SHULKER_BOX));
                        event.getPlayer().getEnderChest().setContents(inventory.getContents());
                    }
                }).runTaskLater((Plugin) this.plugin, 60L);
            }
    }
}


