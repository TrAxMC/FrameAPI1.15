package de.framedev.frameapi.listeners.money;

import de.framedev.frameapi.api.API;
import de.framedev.frameapi.api.money.Money;
import de.framedev.frameapi.interfaces.Constructors;
import de.framedev.frameapi.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;


public class GetMoneyInTime implements Constructors {
    private Main plugin;

    public GetMoneyInTime(Main plugin) {
        this.plugin = plugin;
    }


    public void getMoneyinTime() {
        final double money = this.plugin.getConfig().getDouble("Money.Amount");
        if (this.plugin.getConfig().getBoolean("Money.Boolean")) {
            int time = this.plugin.getConfig().getInt("Money.Time");
            new BukkitRunnable() {
                public void run() {

                    Bukkit.getOnlinePlayers().forEach(current -> {

                        if (API.CreateConfig.getConfig().getBoolean("Payload.Boolean")) {

                            try {
                                String message = API.CreateConfig.getConfig().getString("Payload.Text");
                                message = message.replace("[Money]", String.valueOf(money));
                                message = message.replace('&', '§');
                                current.sendMessage(message);
                                eco.addMoney(current, money);
                                current.playSound(current.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
                            } catch (NullPointerException e) {
                                if (current.isOp()) {
                                    current.sendMessage("§cThere was a mistake in forgiving his money");
                                }
                                e.printStackTrace();
                            }
                        } else {
                            eco.addMoney(current, money);
                        }

                    });
                }
            }.runTaskTimer(this.plugin, 0L, (time * 20));
        }
    }


    public void getOfflinePlayerMoney() {
        final double money = this.plugin.getConfig().getDouble("OfflinePlayers.Money.Amount");
        if (this.plugin.getConfig().getBoolean("OfflinePlayers.Money.Boolean")) {
            int time = this.plugin.getConfig().getInt("OfflinePlayers.Money.Time");
            try {
                new BukkitRunnable() {
                    public void run() {
                        if (GetMoneyInTime.this.plugin.getConfig().getBoolean("MYSQL.Boolean")) {
                            for (OfflinePlayer player : Main.getInstance().getPlayers()) {
                                if (!player.isOnline()) {
                                    if (API.CreateConfig.getConfig().getBoolean("Payload.Boolean")) {
                                        try {
                                            String message = API.CreateConfig.getConfig().getString("Payload.Text");
                                            message = message.replace("[Money]", String.valueOf(money));
                                            message = message.replace('&', '§');
                                            GetMoneyInTime.this.eco.addMoney(player, money);
                                        } catch (NullPointerException e) {
                                            if (player.isOp()) ;
                                        }
                                        continue;
                                    }
                                    GetMoneyInTime.this.eco.addMoney(player, money);
                                }

                            }
                        } else if (Main.cfgm.getBoolean("MongoDB.LocalHost") || Main.cfgm.getBoolean("MongoDB.Boolean")) {
                            for (OfflinePlayer player : GetMoneyInTime.this.plugin.getBackendManager().getOfflinePlayers("money")) {
                                if (!player.isOnline()) {
                                    if (API.CreateConfig.getConfig().getBoolean("Payload.Boolean")) {
                                        try {
                                            String message = API.CreateConfig.getConfig().getString("Payload.Text");
                                            message = message.replace("[Money]", String.valueOf(money));
                                            message = message.replace('&', '§');
                                            GetMoneyInTime.this.eco.addMoney(player, money);
                                        } catch (NullPointerException e) {
                                            if (player.isOp()) ;
                                        }
                                        continue;
                                    }
                                    GetMoneyInTime.this.eco.addMoney(player, money);
                                }

                            }
                        }
                    }
                }.runTaskTimer(this.plugin, 0L, (time * 20));
            } catch (NullPointerException e) {
                Bukkit.getConsoleSender().sendMessage("§cNo Offline Players");
            }
        }
    }
}


