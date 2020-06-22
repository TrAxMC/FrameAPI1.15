 package de.framedev.frameapi.listeners;

 import de.framedev.frameapi.api.API;
 import de.framedev.frameapi.api.SavePlayersInventory;
import de.framedev.frameapi.listeners.energy.CapacitorListener;
 import de.framedev.frameapi.main.Main;
 import de.framedev.frameapi.mysql.IsTableExist;
 import de.framedev.frameapi.mysql.MYSQL;
 import java.sql.PreparedStatement;
import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.sql.Statement;
 import org.bukkit.Bukkit;
 import org.bukkit.OfflinePlayer;
 import org.bukkit.event.EventHandler;
 import org.bukkit.event.Listener;
 import org.bukkit.event.player.PlayerKickEvent;
 import org.bukkit.event.player.PlayerQuitEvent;
 import org.bukkit.plugin.Plugin;
 import org.bukkit.scheduler.BukkitRunnable;
 
 public class LeaveListener implements Listener {
   public API api = new API();
   public Main main = Main.getInstance();
   public CapacitorListener cmd = new CapacitorListener();
 
 
 
 
   
   @EventHandler
   public void onKick(PlayerKickEvent e) {
     if (Main.cfgm.getBoolean("MongoDB.LocalHost") || Main.cfgm.getBoolean("MongoDB.Boolean")) {
       this.main.getBackendManager().updateUser((OfflinePlayer)e.getPlayer(), "offline", Boolean.valueOf(true), "money");
     }
       new SavePlayersInventory().savePlayerInventory(e.getPlayer());
     if (this.main.getConfig().getBoolean("MYSQL.Boolean")) {
       if (this.main.pays.contains(e.getPlayer().getName())) {
         this.main.pays.remove(e.getPlayer().getName());
       }
       try {
         if (IsTableExist.isExist("offline")) {
           Statement statement = MYSQL.getConnection().createStatement();
           ResultSet res = statement.executeQuery("SELECT * FROM offline WHERE PlayerName = '" + e.getPlayer().getName() + "';");
           if (res.next()) {
             if (res.getString("PlayerName") == null) {
               statement.executeUpdate("INSERT INTO offline (PlayerName,boolean) VALUES ('" + e.getPlayer().getName() + "','yes');");
               Bukkit.getConsoleSender().sendMessage(Main.FrameMainGet.getPrefix() + "§b §cInserted");
             } else {
               String sql2 = "UPDATE offline SET boolean = 'yes' WHERE PlayerName = '" + e.getPlayer().getName() + "'";
               statement.executeUpdate(sql2);
               Bukkit.getConsoleSender().sendMessage(Main.FrameMainGet.getPrefix() + "§b Updated info");
             } 
           } else {
             statement.executeUpdate("INSERT INTO offline (PlayerName,boolean) VALUES ('" + e.getPlayer().getName() + "','yes');");
           } 
         } else {
           String sql = "CREATE TABLE IF NOT EXISTS offline(PlayerName TEXT(11),boolean TEXT);";
           try {
             PreparedStatement stmt = (PreparedStatement)MYSQL.getConnection().prepareStatement(sql);
             stmt.executeUpdate();
           } catch (SQLException e1) {
             e1.printStackTrace();
           } 
         } 
       } catch (SQLException e1) {
         e1.printStackTrace();
       } finally {
         MYSQL.close();
       }

     } 
   }
   
   @EventHandler
   public void onLeave(PlayerQuitEvent e) {
     if (Main.cfgm.getBoolean("MongoDB.LocalHost") || Main.cfgm.getBoolean("MongoDB.Boolean")) {
       this.main.getBackendManager().updateUser((OfflinePlayer)e.getPlayer(), "offline", Boolean.valueOf(true), "money");
     }
    new SavePlayersInventory().savePlayerInventory(e.getPlayer());
    Bukkit.getConsoleSender().sendMessage("saved");
     if (this.main.getConfig().getBoolean("MYSQL.Boolean")) {
       if (this.main.pays.contains(e.getPlayer().getName())) {
         this.main.pays.remove(e.getPlayer().getName());
       }
       try {
         if (IsTableExist.isExist("offline")) {
           Statement statement = MYSQL.getConnection().createStatement();
           ResultSet res = statement.executeQuery("SELECT * FROM offline WHERE PlayerName = '" + e.getPlayer().getName() + "';");
           if (res.next()) {
             if (res.getString("PlayerName") == null) {
               statement.executeUpdate("INSERT INTO offline (PlayerName,boolean) VALUES ('" + e.getPlayer().getName() + "','yes');");
               Bukkit.getConsoleSender().sendMessage(Main.FrameMainGet.getPrefix() + "§b §cInserted");
             } else {
               String sql2 = "UPDATE offline SET boolean = 'yes' WHERE PlayerName = '" + e.getPlayer().getName() + "'";
               statement.executeUpdate(sql2);
               Bukkit.getConsoleSender().sendMessage(Main.FrameMainGet.getPrefix() + "§b Updated info");
             } 
           } else {
             statement.executeUpdate("INSERT INTO offline (PlayerName,boolean) VALUES ('" + e.getPlayer().getName() + "','yes');");
           } 
         } else {
           String sql = "CREATE TABLE IF NOT EXISTS offline(PlayerName TEXT(11),boolean TEXT);";
           try {
             PreparedStatement stmt = (PreparedStatement)MYSQL.getConnection().prepareStatement(sql);
             stmt.executeUpdate();
           } catch (SQLException e1) {
             e1.printStackTrace();
           } 
         } 
       } catch (SQLException e1) {
         e1.printStackTrace();
       } 
     } 
     (new CapacitorListener()).playeruuid.remove(e.getPlayer().getUniqueId());
     (new BukkitRunnable()
       {
         public void run() {
           if (Bukkit.getOnlinePlayers().size() == 0) {
             (new CapacitorListener()).runtime.forEach(task -> task.cancel());
 
           
           }
         }
       }).runTaskLater((Plugin)Main.getInstance(), 120L);
       new SavePlayersInventory().savePlayerInventory(e.getPlayer());

   }
 }


