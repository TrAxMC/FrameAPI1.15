package de.framedev.frameapi.api;

import de.framedev.frameapi.main.Main;
import de.framedev.frameapi.mysql.InventoryStringDeSerializer;
import de.framedev.frameapi.mysql.MYSQL;
import de.framedev.frameapi.mysql.SQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SavePlayersInventory implements Listener {

    public SavePlayersInventory() {
        plugin = Main.getInstance();
    }

    private final Main plugin;

    public SavePlayersInventory(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, (Plugin) plugin);

    }


    public void savePlayerInventory(Player player) {
        if (plugin.getConfig().getBoolean("SavePlayersInventory")) {
            if (SQL.isTableExists("PlayerInventory")) {
                try {
                    Statement statement = MYSQL.getConnection().createStatement();
                    String sql = "SELECT * FROM PlayerInventory WHERE UUID = '" + player.getName() + "';";
                    ResultSet res = statement.executeQuery(sql);
                    if (res.next()) {
                        if (res.getString("PlayerInventory") == null) {
                            SQL.insertData("PlayerInventory", "'" + player.getName() + "','" +
                                    InventoryStringDeSerializer.itemStackArrayToBase64(player.getInventory().getContents()) + "','" +
                                    InventoryStringDeSerializer.itemStackArrayToBase64(player.getInventory().getArmorContents()) + "'", "UUID", "PlayerInventory", "PlayerARMOR");
                        } else {
                            try {
                                String sql1 = "UPDATE PlayerInventory SET PlayerInventory = '"
                                        + InventoryStringDeSerializer.itemStackArrayToBase64(player.getInventory().getContents())
                                        + "', PlayerARMOR = '" + InventoryStringDeSerializer.itemStackArrayToBase64(player.getInventory().getArmorContents())
                                        + "' WHERE UUID = '" + player.getName() + "';";
                                statement.executeUpdate(sql1);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            } finally {
                                if (MYSQL.con != null) {
                                    MYSQL.close();
                                }
                            }
                        }
                    } else {
                        SQL.insertData("PlayerInventory", "'" + player.getName() + "','" +
                                InventoryStringDeSerializer.itemStackArrayToBase64(player.getInventory().getContents()) + "','" +
                                InventoryStringDeSerializer.itemStackArrayToBase64(player.getInventory().getArmorContents())
                                + "'", "UUID", "PlayerInventory", "PlayerARMOR");
                    }
                } catch (SQLException sQLException) {

                }
            } else {
                SQL.createTable("PlayerInventory", "UUID VARCHAR(64)", "PlayerInventory TEXT", "PlayerARMOR TEXT");
                SQL.insertData("PlayerInventory", "'" + player.getName() + "','" +
                        InventoryStringDeSerializer.itemStackArrayToBase64(player.getInventory().getContents()) + "','" +
                        InventoryStringDeSerializer.itemStackArrayToBase64(player.getInventory().getArmorContents())
                        + "'", "UUID", "PlayerInventory", "PlayerARMOR");
            }
        }
    }

    public void loadPlayerInventory(Player player) {
        if (this.plugin.getConfig().getBoolean("SavePlayersInventory") &&
                SQL.isTableExists("PlayerInventory")) {
            try {
                Statement statement = MYSQL.getConnection().createStatement();
                String sql = "SELECT * FROM PlayerInventory WHERE UUID = '" + player.getName() + "';";
                ResultSet res = statement.executeQuery(sql);
                if (res.next()) {
                    try {
                        player.getInventory().setContents(InventoryStringDeSerializer.itemStackArrayFromBase64(res.getString("PlayerInventory")));
                        player.getInventory().setArmorContents(InventoryStringDeSerializer.itemStackArrayFromBase64(res.getString("PlayerARMOR")));
                    } catch (SQLException | IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (MYSQL.con != null) {
                            MYSQL.close();
                        }
                    }
                } else {
                    SQL.createTable("PlayerInventory", "UUID VARCHAR(64)", "PlayerInventory TEXT", "PlayerARMOR TEXT");
                }
            } catch (SQLException sQLException) {
            }
        }
    }
}



