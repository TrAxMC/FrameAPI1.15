package de.framedev.frameapi.api;
 
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import de.framedev.frameapi.main.Main;
import de.framedev.frameapi.mysql.InventoryStringDeSerializer;
import de.framedev.frameapi.mysql.MYSQL;
import de.framedev.frameapi.mysql.SQL;
import de.framedev.frameapi.queries.UpdateQuery;


public class OpenOtherInventory
   implements CommandExecutor, Listener {
    private final Main plugin;
    public HashMap<String, Inventory> inventorys = new HashMap<>();
    public HashMap<String, Integer> inventoryNumber = new HashMap<>();
    Inventory inventory = Bukkit.createInventory(null, 45);

    public OpenOtherInventory(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("openinvnumber").setExecutor(this);
        Bukkit.getPluginManager().registerEvents(this, (Plugin) plugin);
    }


    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            ConfigurationSection cs = Main.getInstance().getConfig().getConfigurationSection("SaveInventoryMultiple");
            for (String s : cs.getKeys(false)) {
                try {
                    if (player.hasPermission("frameapi.saveinventorynumber.multiple." + s) &&
                            Integer.parseInt(args[0]) <= getAmount(player, s) &&
                            command.getName().equalsIgnoreCase("openinvnumber")) {
                        loadInventory(player, Integer.parseInt(args[0]));
                        break;
                    }
                } catch (IllegalArgumentException ex) {
                    player.sendMessage("§cPlease use §b/openinvnumber (number)");
                }
            }
        }
        return false;
    }

    public int getAmount(Player player, String group) {
        for (String s : this.plugin.getConfig().getConfigurationSection("SaveInventoryMultiple").getKeys(false)) {
            if (s.equalsIgnoreCase(group)) {
                int x = this.plugin.getConfig().getInt("SaveInventoryMultiple." + s);
                return x;
            }
        }
        return 0;
    }

    public void loadInventory(Player player, int number) {
        if (!this.inventorys.containsKey(player.getName())) {
            if (SQL.isTableExists("inventory")) {
                if (SQL.exists("inventory", "UUID", player.getName())) {
                    Inventory inventory = null;
                    try {
                        try {
                            Statement statement = MYSQL.getConnection().createStatement();
                            String sql = "SELECT * FROM inventory WHERE UUID = '" + player.getName() + "' AND InventoryNumber= '" + number + "'";
                            ResultSet res = statement.executeQuery(sql);
                            if (res.next()) {
                                inventory = InventoryStringDeSerializer.fromBase64((String) res.getObject("Inventory"));
                            }
                        } catch (SQLException sQLException) {
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (inventory != null) {
                        player.openInventory(inventory);
                        this.inventorys.put(player.getName(), inventory);
                        this.inventoryNumber.put(player.getName(), Integer.valueOf(number));
                    } else {

                        Inventory inventory1 = Bukkit.createInventory(null, 45);
                        player.openInventory(inventory1);
                        this.inventorys.put(player.getName(), inventory1);
                        this.inventoryNumber.put(player.getName(), Integer.valueOf(number));
                    }
                } else {
                    Inventory inventory1 = Bukkit.createInventory(null, 45);
                    player.openInventory(inventory1);
                    this.inventorys.put(player.getName(), inventory1);
                    this.inventoryNumber.put(player.getName(), Integer.valueOf(number));
                }
            } else {
                SQL.createTable("inventory", new String[]{"UUID VARCHAR(64)", "Inventory TEXT", "InventoryNumber INT"});
                Inventory inventory1 = Bukkit.createInventory(null, 45);
                player.openInventory(inventory1);
                this.inventorys.put(player.getName(), inventory1);
                this.inventoryNumber.put(player.getName(), Integer.valueOf(number));
            }
        }
    }

    @EventHandler
    public void saveInventory(InventoryCloseEvent event) {
        if (this.inventorys.containsKey(event.getPlayer().getName()) &&
                SQL.isTableExists("inventory"))
            try {
                Statement statement = MYSQL.getConnection().createStatement();
                String sql = "SELECT * FROM inventory WHERE UUID = '" + event.getPlayer().getName() + "' AND InventoryNumber = '" + this.inventoryNumber.get(event.getPlayer().getName()) + "';";
                ResultSet res = statement.executeQuery(sql);
                if (res.next()) {
                    if (res.getString("Inventory") == null) {
                        SQL.insertData("inventory", "'" + event.getPlayer().getName() + "','" + InventoryStringDeSerializer.toBase64(this.inventorys.get(event.getPlayer().getName())) + "','" + this.inventoryNumber
                                .get(event.getPlayer().getName()) + "'", new String[]{"UUID", "Inventory", "InventoryNumber"});
                        this.inventorys.remove(event.getPlayer().getName());
                        this.inventoryNumber.remove(event.getPlayer().getName());
                    } else {

                        try {
                            String sql1 = (new UpdateQuery("inventory")).set("Inventory", "'" + InventoryStringDeSerializer.toBase64(this.inventorys.get(event.getPlayer().getName())) + "'").where("UUID = '" + event.getPlayer().getName() + "'").and("InventoryNumber = '" + this.inventoryNumber.get(event.getPlayer().getName()) + "'").build();
                            statement.executeUpdate(sql1);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            if (MYSQL.con != null) {
                                MYSQL.close();
                            }
                        }
                        this.inventorys.remove(event.getPlayer().getName());
                        this.inventoryNumber.remove(event.getPlayer().getName());
                    }
                } else {
                    SQL.createTable("inventory", new String[]{"UUID VARCHAR(64)", "Inventory TEXT", "InventoryNumber INT"});
                    SQL.insertData("inventory", "'" + event.getPlayer().getName() + "','" + InventoryStringDeSerializer.toBase64(this.inventorys.get(event.getPlayer().getName())) + "','" + this.inventoryNumber
                            .get(event.getPlayer().getName()) + "'", new String[]{"UUID", "Inventory", "InventoryNumber"});
                    this.inventorys.remove(event.getPlayer().getName());
                    this.inventoryNumber.remove(event.getPlayer().getName());
                }
            } catch (SQLException sQLException) {

            }
    }
}


