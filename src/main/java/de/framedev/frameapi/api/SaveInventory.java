 package de.framedev.frameapi.api;
 
 import de.framedev.frameapi.main.Main;
 import de.framedev.frameapi.mysql.InventoryStringDeSerializer;
 import de.framedev.frameapi.mysql.MYSQL;
 import de.framedev.frameapi.mysql.SQL;
 import de.framedev.frameapi.queries.UpdateQuery;
 import java.io.IOException;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.sql.Statement;
 import java.util.HashMap;
 import java.util.UUID;
 import org.bukkit.Bukkit;
 import org.bukkit.command.Command;
 import org.bukkit.command.CommandExecutor;
 import org.bukkit.command.CommandSender;
 import org.bukkit.entity.Player;
 import org.bukkit.event.EventHandler;
 import org.bukkit.event.Listener;
 import org.bukkit.event.inventory.InventoryCloseEvent;
 import org.bukkit.inventory.Inventory;
 import org.bukkit.plugin.Plugin;
 
 
 
 
 
 
 
 
 public class SaveInventory
   implements CommandExecutor, Listener
 {
   private final Main plugin;
   public HashMap<UUID, Inventory> inventorys = new HashMap<>();
   public HashMap<UUID, Integer> inventoryNumber = new HashMap<>();
   Inventory inventory = Bukkit.createInventory(null, 45);
   
   public SaveInventory(Main plugin) {
     this.plugin = plugin;
     plugin.getCommand("openinv").setExecutor(this);
     Bukkit.getPluginManager().registerEvents(this, (Plugin)plugin);
   }
 
   
   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
     if (sender instanceof Player) {
       Player player = (Player)sender;
       if (player.hasPermission("frameapi.saveinventory")) {
         if (command.getName().equalsIgnoreCase("openinv")) {
           loadInventory(player, 0);
         }
       } else {
         player.sendMessage(Main.FrameMainGet.getPrefix() + " " + Main.FrameMainGet.getNoPerm());
       } 
     } 
     return false;
   }
   
   public void loadInventory(Player player, int number) {
     if (!this.inventorys.containsKey(player.getUniqueId())) {
       if (SQL.isTableExists("inventory")) {
         if (SQL.exists("inventory", "UUID", player.getName())) {
           Inventory inventory = null;
           try {
             try {
               Statement statement = MYSQL.getConnection().createStatement();
               String sql = "SELECT * FROM inventory WHERE UUID = '" + player.getName() + "' AND InventoryNumber= '" + number + "'";
               ResultSet res = statement.executeQuery(sql);
               if (res.next()) {
                 inventory = InventoryStringDeSerializer.fromBase64((String)res.getObject("Inventory"));
               }
             } catch (SQLException sQLException) {}
           
           }
           catch (IOException e) {
             e.printStackTrace();
           } 
           if (inventory != null) {
             player.openInventory(inventory);
             this.inventorys.put(player.getUniqueId(), inventory);
             this.inventoryNumber.put(player.getUniqueId(), Integer.valueOf(number));
           } else {
             
             Inventory inventory1 = Bukkit.createInventory(null, 45);
             player.openInventory(inventory1);
             this.inventorys.put(player.getUniqueId(), inventory1);
             this.inventoryNumber.put(player.getUniqueId(), Integer.valueOf(number));
           } 
         } else {
           Inventory inventory1 = Bukkit.createInventory(null, 45);
           player.openInventory(inventory1);
           this.inventorys.put(player.getUniqueId(), inventory1);
           this.inventoryNumber.put(player.getUniqueId(), Integer.valueOf(number));
         } 
       } else {
         SQL.createTable("inventory", new String[] { "UUID VARCHAR(64)", "Inventory TEXT", "InventoryNumber INT" });
       } 
     }
   }
   
   @EventHandler
   public void saveInventory(InventoryCloseEvent event) {
     if (this.inventorys.containsKey(event.getPlayer().getUniqueId()) && 
       SQL.isTableExists("inventory"))
       try {
         Statement statement = MYSQL.getConnection().createStatement();
         String sql = "SELECT * FROM inventory WHERE UUID = '" + event.getPlayer().getName() + "' AND InventoryNumber = '" + this.inventoryNumber.get(event.getPlayer().getUniqueId()) + "';";
         ResultSet res = statement.executeQuery(sql);
         if (res.next()) {
           if (res.getString("Inventory") == null) {
             SQL.insertData("inventory", "'" + event.getPlayer().getName() + "','" + InventoryStringDeSerializer.toBase64(this.inventorys.get(event.getPlayer().getUniqueId())) + "','" + this.inventoryNumber
                 .get(event.getPlayer().getUniqueId()) + "'", new String[] { "UUID", "Inventory", "InventoryNumber" });
             this.inventorys.remove(event.getPlayer().getUniqueId());
             this.inventoryNumber.remove(event.getPlayer().getUniqueId());
           } else {
             try {
               String sql1 = (new UpdateQuery("inventory")).set("Inventory", "'" + InventoryStringDeSerializer.toBase64(this.inventorys.get(event.getPlayer().getUniqueId())) + "'").where("UUID = '" + event.getPlayer().getName() + "'").and("InventoryNumber = '" + this.inventoryNumber.get(event.getPlayer().getUniqueId()) + "'").build();
               statement.executeUpdate(sql1);
             } catch (SQLException e) {
               e.printStackTrace();
             } finally {
               if (MYSQL.con != null) {
                 MYSQL.close();
               }
             } 
             this.inventorys.remove(event.getPlayer().getUniqueId());
             this.inventoryNumber.remove(event.getPlayer().getUniqueId());
           } 
         } else {
           SQL.createTable("inventory", new String[] { "UUID VARCHAR(64)", "Inventory TEXT", "InventoryNumber INT" });
           SQL.insertData("inventory", "'" + event.getPlayer().getName() + "','" + InventoryStringDeSerializer.toBase64(this.inventorys.get(event.getPlayer().getUniqueId())) + "','" + this.inventoryNumber
               .get(event.getPlayer().getUniqueId()) + "'", new String[] { "UUID", "Inventory", "InventoryNumber" });
           this.inventorys.remove(event.getPlayer().getUniqueId());
           this.inventoryNumber.remove(event.getPlayer().getUniqueId());
         } 
       } catch (SQLException sQLException) {} 
   }
 }


