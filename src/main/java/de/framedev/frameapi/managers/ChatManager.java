package de.framedev.frameapi.managers;

import de.framedev.frameapi.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.io.IOException;

/*
 * =============================================
 * = This Plugin was Created by FrameDev       =
 * = Copyrighted by FrameDev                   =
 * = Please don't Change anything              =
 * =============================================
 * This Class was made at 23.05.2020, 20:37
 */
public class ChatManager {

    private File file = new File(Main.getInstance().getDataFolder(), "chat.yml");
    private FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    private OfflinePlayer player;

    public BukkitTask bukkitTask;

    public ChatManager(String name) {
        this.player = Bukkit.getPlayer(name);
        if (player != null) {

        } else {
            this.player = Bukkit.getOfflinePlayer(name);
        }
    }

    public ChatManager() {
    }

    private void saveCfg() {
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setMuted(boolean muted, long time) {
        if (cfg.getBoolean("Chat.Muted." + player.getName() + ".boolean")) {
            if (muted) {
                cfg.set("Chat.Muted." + player.getName() + ".boolean", true);
                cfg.set("Chat.Muted." + player.getName() + ".Time", time);
                saveCfg();
                if(bukkitTask != null) {
                    if (bukkitTask.isCancelled()) {
                        runable();
                    }
                } else {
                    runable();
                }

            } else {
                cfg.set("Chat.Muted." + player.getName(), null);
                saveCfg();
            }
        } else {
            if (muted) {
                cfg.set("Chat.Muted." + player.getName() + ".boolean", true);
                cfg.set("Chat.Muted." + player.getName() + ".Time", time);
                saveCfg();
                if(bukkitTask != null) {
                    if (bukkitTask.isCancelled()) {
                        runable();
                    }
                } else {
                    runable();
                }
            } else {
                cfg.set("Chat.Muted." + player.getName(), null);
                saveCfg();
            }
        }
    }

    public boolean isMuted() {
        if (cfg.getBoolean("Chat.Muted." + player.getName() + ".boolean")) {
            return true;
        }
        return false;
    }

    public OfflinePlayer getPlayer() {
        return player;
    }

    public void runable() {
        ConfigurationSection cs = cfg.getConfigurationSection("Chat.Muted");
        bukkitTask = new BukkitRunnable() {
            @Override
            public void run() {
                if (cs != null) {
                    for (String s : cs.getKeys(false)) {
                        if (new ChatManager(s).getTime() <= 0) {
                            new ChatManager(s).setMuted(false, 0);
                            if(cs.getKeys(false).size() == 0) {
                                cancel();
                            }
                        } else {
                            long time = new ChatManager(s).getTime();
                            time--;
                            new ChatManager(s).setMuted(true, time);
                        }
                    }
                    if(cs.getKeys(false).size() == 0) {
                        cancel();
                    }
                } else {
                    cancel();
                }
            }
        }.runTaskTimerAsynchronously(Main.getInstance(), 0, 60);
    }

    public long getTime() {
        long value = cfg.getLong("Chat.Muted." + player.getName() + ".Time");
        return value;
    }

}

