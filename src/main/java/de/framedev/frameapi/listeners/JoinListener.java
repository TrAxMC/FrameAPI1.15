package de.framedev.frameapi.listeners;

import de.framedev.frameapi.api.API;
import de.framedev.frameapi.api.SavePlayersInventory;
import de.framedev.frameapi.api.money.Money;
import de.framedev.frameapi.listeners.energy.CapacitorListener;
import de.framedev.frameapi.main.Main;
import de.framedev.frameapi.mysql.IsTableExist;
import de.framedev.frameapi.mysql.MYSQL;
import de.framedev.frameapi.utils.ReplaceCharConfig;
import de.framedev.frameapi.warps.WarpSigns;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class JoinListener
        implements Listener {
    public static int task;
    public Main main = Main.getInstance();
    public FileConfiguration config = this.main.getConfig();
    public API api = new API();
    public CapacitorListener cmd = new CapacitorListener();


    @EventHandler
    public void onjoin(final PlayerJoinEvent e) {

        if (Main.cfgm.getBoolean("MongoDB.LocalHost") || Main.cfgm.getBoolean("MongoDB.Boolean")) {

            (new BukkitRunnable() {

                public void run() {

                    JoinListener.this.main.getBackendManager().createUserMoney((OfflinePlayer) e.getPlayer(), "money");

                    JoinListener.this.main.getBackendManager().updateUser((OfflinePlayer) e.getPlayer(), "offline", Boolean.valueOf(false), "money");

                    JoinListener.this.main.getBackendManager().updateUser((OfflinePlayer) e.getPlayer(), "lastLogin", Long.valueOf(System.currentTimeMillis()), "money");

                }

            }).runTaskLater((Plugin) this.main, 40L);

        }

        if (Main.getInstance().getConfig().getBoolean("Join.Message.Boolean")) {

            String message = this.api.getCustomConfig().getString("JoinMessage");

            message = ReplaceCharConfig.replaceObjectWithData(message, "[Player]", e.getPlayer().getDisplayName());

            message = ReplaceCharConfig.replaceParagraph(message);

            e.setJoinMessage(message);

            Bukkit.getConsoleSender().sendMessage(message);

        }
        new BukkitRunnable() {
            public void run() {
                new SavePlayersInventory().loadPlayerInventory(e.getPlayer());
                Bukkit.getConsoleSender().sendMessage("Inventory Loaded!");
            }
        }.runTaskLater((Plugin) Main.getInstance(), 20L);
    }

    @EventHandler
    public void onleave(PlayerQuitEvent e) {
        if (Main.getInstance().getConfig().getBoolean("Leave.Message.Boolean")) {
            String message = this.api.getCustomConfig().getString("LeaveMessage");
            message = ReplaceCharConfig.replaceObjectWithData(message, "[Player]", e.getPlayer().getDisplayName());
            message = ReplaceCharConfig.replaceParagraph(message);
            e.setQuitMessage(message);
        }
    }


    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        Player player = e.getPlayer();
        (new BukkitRunnable() {
            public void run() {
                if (JoinListener.this.config.getBoolean("MYSQL.Boolean")) {
                    (new Money()).getMoney((OfflinePlayer) e.getPlayer());
                    if (IsTableExist.isExist("offline")) {
                        try {
                            Statement statement = MYSQL.getConnection().createStatement();
                            ResultSet res = statement.executeQuery("SELECT * FROM offline WHERE PlayerName = '" + e.getPlayer().getName() + "';");
                            if (res.next()) {
                                if (res.getString("PlayerName") == null) {
                                    statement.executeUpdate("INSERT INTO offline (PlayerName,boolean) VALUES ('" + e.getPlayer().getName() + "','no');");
                                    Bukkit.getConsoleSender().sendMessage(Main.Variables.getPrefix() + "§b §cInserted");
                                    return;
                                }
                                String sql2 = "UPDATE offline SET boolean = 'no' WHERE PlayerName = '" + e.getPlayer().getName() + "'";
                                statement.executeUpdate(sql2);
                                Bukkit.getConsoleSender().sendMessage(Main.Variables.getPrefix() + "§b Updated info");

                                return;
                            }
                            statement.executeUpdate("INSERT INTO offline (PlayerName,boolean) VALUES ('" + e.getPlayer().getName() + "','no');");

                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        } finally {
                        }
                    } else {

                        String sql = "CREATE TABLE IF NOT EXISTS offline(PlayerName TEXT(11),boolean TEXT);";
                        try {
                            PreparedStatement stmt = (PreparedStatement) MYSQL.getConnection().prepareStatement(sql);
                            stmt.executeUpdate();
                            MYSQL.close();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }

                    }
                }
            }
        }).runTaskLater((Plugin) Main.getInstance(), 60L);
        if (WarpSigns.UtilinConfigList("Energy").booleanValue()) {
            if (Main.getInstance().getConfig().getBoolean("MYSQL.Boolean")) {
                startUpdateWorld(player.getWorld(), player, player.getUniqueId());
            }
        }
        (new BukkitRunnable() {
            public void run() {
                if (JoinListener.this.config.getBoolean("MYSQL.Boolean")) {
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        try {
                            Statement statement = MYSQL.getConnection().createStatement();
                            ResultSet res = statement.executeQuery("SELECT * FROM pays WHERE PlayerTo = '" + online.getName() + "';");
                            if (res.next()) {
                                int amounts = JoinListener.this.api.getPays((OfflinePlayer) online).intValue();
                                JoinListener.this.main.j = amounts;
                                if (JoinListener.this.main.j == 0)
                                    continue;
                                online.sendMessage("§aYou get a Pay use /paythebill to sale it of §b" + JoinListener.this.main.j);
                                JoinListener.this.main.pays.add(online.getName());
                                JoinListener.this.main.j = 0;
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }).runTaskLater((Plugin) Main.getInstance(), 120L);
    }

    public void startUpdateWorld(World world, Player player, UUID uuid) {
        if (WarpSigns.UtilinConfigList("Energy").booleanValue()) {
            if (Main.getInstance().getConfig().getBoolean("MYSQL.Boolean")) {
                (new CapacitorListener()).playeruuid.add(uuid);
                this.cmd.updateWorld(world, player);
            }

        }
    }
}


