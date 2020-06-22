package de.framedev.frameapi.api.energy;

import de.framedev.frameapi.interfaces.EnergyInterface;
import de.framedev.frameapi.main.Main;
import de.framedev.frameapi.mysql.SQL;
import de.framedev.frameapi.utils.ReplaceCharConfig;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;



public class Energy implements EnergyInterface {
    public static String energyprefix = "§a[§6Energy§a] §f";


    public void setEnergy(OfflinePlayer player, int amount) {
        if (SQL.isTableExists("Energy")) {
            if (SQL.exists("Energy", "PlayerUUID", player.getUniqueId().toString())) {
                SQL.updateData("Energy", "Energy", "'" + amount + "'", "PlayerUUID = '" + player.getUniqueId().toString() + "'");
            } else {
                SQL.insertData("Energy", "'" + player.getUniqueId().toString() + "','" + amount + "'", new String[]{"PlayerUUID", "Energy"});
            }
        } else {
            SQL.createTable("Energy", new String[]{"PlayerUUID VARCHAR(64)", "Energy INT"});
            SQL.insertData("Energy", "'" + player.getUniqueId().toString() + "','" + amount + "'", new String[]{"PlayerUUID", "Energy"});
        }
    }

    public void adminEnergy(Player player) {
        Bukkit.getOnlinePlayers().forEach(current -> {
            if (SQL.isTableExists("Energy")) {
                if (SQL.exists("Energy", "PlayerUUID", player.getUniqueId().toString()) && SQL.get("Energy", "Energy", "PlayerUUID", player.getUniqueId().toString()) != null) {
                    Object energy = SQL.get("Energy", "Energy", "PlayerUUID", player.getUniqueId().toString());

                    player.sendMessage("§6[OnlinePlayers§6] §aEnergie von §b" + current.getName() + " §aBeträgt §b" + ReplaceCharConfig.convertObjectToInteger(energy));
                }
            } else {
                SQL.createTable("Energy", new String[]{"PlayerUUID VARCHAR(64)", "Energy INT"});
            }
        });

        Main.getInstance().getPlayers().forEach(current -> {
            if (SQL.isTableExists("Energy")) {
                if (SQL.exists("Energy", "PlayerUUID", player.getUniqueId().toString()) && SQL.get("Energy", "Energy", "PlayerUUID", player.getUniqueId().toString()) != null) {
                    Object energy = SQL.get("Energy", "Energy", "PlayerUUID", player.getUniqueId().toString());
                    player.sendMessage("§6[OfflinePlayers§6] §aEnergie von §b" + current.getName() + " §aBeträgt §b" + ReplaceCharConfig.convertObjectToInteger(energy));
                }
            } else {
                SQL.createTable("Energy", new String[]{"PlayerUUID VARCHAR(64)", "Energy INT"});
            }
        });
    }


    public int getEnergy(OfflinePlayer player) {
        if (SQL.isTableExists("Energy")) {
            if (SQL.exists("Energy", "PlayerUUID", player.getUniqueId().toString())) {
                if (SQL.get("Energy", "Energy", "PlayerUUID", player.getUniqueId().toString()) != null) {
                    Object energy = SQL.get("Energy", "Energy", "PlayerUUID", player.getUniqueId().toString());
                    return ReplaceCharConfig.convertObjectToInteger(energy).intValue();
                }
                return 0;
            }

            return 0;
        }

        SQL.createTable("Energy", new String[]{"PlayerUUID VARCHAR(64)", "Energy INT"});
        return 0;
    }


    public void addEnergy(OfflinePlayer player, int amount) {
        int energy = getEnergy(player);
        energy += amount;
        setEnergy(player, energy);
    }


    public void removeEnergy(OfflinePlayer player, int amount) {
        int energy = getEnergy(player);
        energy -= amount;
        setEnergy(player, energy);
    }
}


