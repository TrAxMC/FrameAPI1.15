package de.framedev.frameapi.managers;

import de.framedev.frameapi.cmds.ChunkloaderCMD;
import de.framedev.frameapi.main.Main;
import de.framedev.frameapi.mysql.MYSQL;
import de.framedev.frameapi.mysql.SQL;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

/*
 * =============================================
 * = This Plugin was Created by FrameDev       =
 * = Copyrighted by FrameDev                   =
 * = Please don't Change anything              =
 * =============================================
 * This Class was made at 07.03.2020, 13:13
 */
public class SchedulerManager implements Runnable {

    @Override
    public void run() {
        Main.getInstance().saveDefaultConfig();
        if (Main.getInstance().getConfig().getBoolean("MYSQL.Boolean")) {
        }
        Main.getInstance().savePlayersInventory();
        startRunnables();
    }

    public void startRunnables() {
        new BukkitRunnable() {
            public void run() {
                for (String s : ChunkloaderCMD.cfg.getKeys(false)) {
                    if (ChunkloaderCMD.cfg.getBoolean(s + ".boolean")) {
                        Location loc = new Location(Bukkit.getWorld(ChunkloaderCMD.cfg.getString(s + ".world")), ChunkloaderCMD.cfg.getInt(s + ".x"), ChunkloaderCMD.cfg.getInt(s + ".y"), ChunkloaderCMD.cfg.getInt(s + ".z"));
                        loc.getChunk().setForceLoaded(true);
                    }
                }
                Bukkit.getConsoleSender().sendMessage(Main.Variables.getPrefix() + " §aChunkloader Activated!");
            }
        }.runTaskLater(Main.getInstance(), 60L);
        if (Main.getInstance().getConfig().getBoolean("MYSQL.Boolean")) {
            (new BukkitRunnable() {
                public void run() {
                    if (MYSQL.con != null) {
                        MYSQL.close();
                        return;
                    }
                }
            }).runTaskTimer(Main.getInstance(), 0L, 1200L);
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (SQL.exists("money", "PlayerName", "yes")) {
                        SQL.deleteDataInTable("money", "PlayerName = 'yes';");
                    }
                }
            }.runTaskTimer(Main.getInstance(), 0, 320);
        }
        (new BukkitRunnable() {
            public void run() {
                Main.getInstance().checkUpdate();
            }
        }).runTaskLater(Main.getInstance(), 200L);
    }
}
