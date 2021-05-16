package de.framedev.frameapi.cmds;

import de.framedev.frameapi.api.money.Money;
import de.framedev.frameapi.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;


public class MoneyBankCMD
        implements CommandExecutor, TabCompleter {
    private final Main plugin;
    private Money eco = new Money();

    public MoneyBankCMD(Main plugin) {
        this.plugin = plugin;
        if (Main.getInstance().getConfig().getBoolean("MYSQL.Boolean") || Main.cfgm.getBoolean("MongoDB.LocalHost") || Main.cfgm.getBoolean("MongoDB.Boolean")) {

            plugin.getCommand("transfer").setTabCompleter(this);
            plugin.getCommand("deposit").setTabCompleter(this);
            plugin.getCommand("withdraw").setTabCompleter(this);
            plugin.getCommand("deposit").setExecutor(this);
            plugin.getCommand("withdraw").setExecutor(this);
            plugin.getCommand("transfer").setExecutor(this);
            plugin.getCommand("setbank").setExecutor(this);
            plugin.getCommand("bankbalance").setExecutor(this);
        }
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, final String[] args) {
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("deposit")) {
                final Player p = (Player) sender;
                if (p.hasPermission("frameapi.deposit")) {
                    if (args.length != 0) {
                        final double x = Double.parseDouble(args[0]);
                        if (args.length == 1) {
                            if (!this.eco.hasPlayerAmount((OfflinePlayer) p, x)) {
                                if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("en_EN")) {
                                    p.sendMessage("§cNot enought Money!");
                                } else if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("de_DE")) {
                                    p.sendMessage("§cNicht genug Geld!");
                                }
                            } else {
                                if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("en_EN")) {
                                    p.sendMessage("§aTransaction is in progress");
                                } else if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("de_DE")) {
                                    p.sendMessage("§aTransaktion wird verarbeitet!");
                                }
                                (new BukkitRunnable() {
                                    public void run() {
                                        MoneyBankCMD.this.eco.AddMoneyFromBank((OfflinePlayer) p, x);
                                        MoneyBankCMD.this.eco.removeMoney(p, x);
                                        if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("en_EN")) {
                                            p.sendMessage("§aYou have been successful added §b" + x + " §ato your Bank Account");
                                        } else if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("de_DE")) {
                                            p.sendMessage("§aDu hast erfolgreich §b" + x + " §azu deinem Bank Account hinzugefügt!");
                                        }

                                    }
                                }).runTaskLater((Plugin) Main.getInstance(), 300L);
                            }
                        }
                    } else {
                        p.sendMessage("§cPlease use §a/deposit (amount");
                    }
                } else {
                    p.sendMessage(Main.Variables.getPrefix() + " " + Main.Variables.getNoPerm());
                }
            } else if (cmd.getName().equalsIgnoreCase("withdraw")) {
                final Player p = (Player) sender;
                if (p.hasPermission("frameapi.withdraw")) {
                    if (args.length != 0) {
                        final double x = Double.parseDouble(args[0]);
                        if (args.length == 1) {
                            if (!this.eco.hasPlayerMoneyBank((OfflinePlayer) p, x)) {
                                if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("en_EN")) {
                                    p.sendMessage("§cNot enought Money in your Bank!");
                                } else if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("de_DE")) {
                                    p.sendMessage("§cNicht genug Geld auf der Bank!");
                                }
                            } else {
                                if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("en_EN")) {
                                    p.sendMessage("§aTransaction is in progress");
                                } else if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("de_DE")) {
                                    p.sendMessage("§aTransaktion wird verarbeitet!");
                                }
                                (new BukkitRunnable() {
                                    public void run() {
                                        MoneyBankCMD.this.eco.addMoney((OfflinePlayer) p, x);
                                        MoneyBankCMD.this.eco.RemoveMoneyFromBank((OfflinePlayer) p, x);
                                        if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("en_EN")) {
                                            p.sendMessage("§aYou have been successful removed §b" + x + " §ato your Bank Account");
                                        } else if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("de_DE")) {
                                            p.sendMessage("§aDu hast erfolgreich §b" + x + " §avon deinem Bank Account entfernt!");
                                        }

                                    }
                                }).runTaskLater((Plugin) Main.getInstance(), 300L);
                            }
                        } else {
                            p.sendMessage("§cPlease use §a/withdraw (amount");
                        }
                    } else {
                        p.sendMessage("§cPlease use §a/withdraw (amount");
                    }
                } else {
                    p.sendMessage(Main.Variables.getPrefix() + " " + Main.Variables.getNoPerm());
                }
            } else if (cmd.getName().equalsIgnoreCase("transfer")) {
                final Player p = (Player) sender;
                if (p.hasPermission("frameapi.transfer")) {
                    if (args.length != 0) {
                        final Player target = Bukkit.getPlayer(args[0]);
                        final double x = Double.parseDouble(args[1]);
                        if (args.length == 2) {
                            if (!this.eco.hasPlayerMoneyBank((OfflinePlayer) p, x)) {
                                if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("en_EN")) {
                                    p.sendMessage("§cNot enought Money in your Bank!");
                                } else if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("de_DE")) {
                                    p.sendMessage("§cNicht genug Geld auf der Bank!");
                                }
                            } else {
                                if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("en_EN")) {
                                    p.sendMessage("§aTransaction is in progress");
                                } else if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("de_DE")) {
                                    p.sendMessage("§aTransaktion wird verarbeitet!");
                                }
                                (new BukkitRunnable() {
                                    public void run() {
                                        if (target != null) {
                                            MoneyBankCMD.this.eco.AddMoneyFromBank((OfflinePlayer) target, x);
                                            MoneyBankCMD.this.eco.RemoveMoneyFromBank((OfflinePlayer) p, x);
                                            if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("en_EN")) {
                                                target.sendMessage("§aYou got §b" + x + " §afrom §b" + p.getName() + " §ato your Bank Account");
                                                p.sendMessage("§aYou have been successful transfered §b" + x + " §ato §b" + target.getName() + "'s §aBank Account");
                                            } else if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("de_DE")) {
                                                target.sendMessage("§aDu hast §b" + x + " §avon §b" + p.getName() + " §aGeld auf deine Bank bekommen!");
                                                p.sendMessage("§aDu hast Erfolgreich §b" + x + " §aauf §b" + target.getName() + "'s §asein Bank Account transferiert!");
                                            }
                                        } else {
                                            OfflinePlayer offline = Bukkit.getOfflinePlayer(args[0]);
                                            MoneyBankCMD.this.eco.AddMoneyFromBank(offline, x);
                                            MoneyBankCMD.this.eco.RemoveMoneyFromBank((OfflinePlayer) p, x);
                                            if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("en_EN")) {
                                                p.sendMessage("§aYou have been successful transfered §b" + x + " §ato §b" + offline.getName() + "'s §aBank Account");
                                            } else if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("de_DE")) {
                                                p.sendMessage("§aDu hast Erfolgreich §b" + x + " §aauf §b" + offline.getName() + "'s §asein Bank Account transferiert!");
                                            }
                                        }
                                    }
                                }).runTaskLater((Plugin) Main.getInstance(), 300L);
                            }
                        } else {
                            p.sendMessage("§cPlease use §a/transfer (target) (amount");
                        }
                    } else {
                        p.sendMessage("§cPlease use §a/transfer (target) (amount");
                    }
                } else {
                    p.sendMessage(Main.Variables.getPrefix() + " " + Main.Variables.getNoPerm());
                }
            } else if (cmd.getName().equalsIgnoreCase("setbank")) {
                if (args.length != 0) {
                    if (args.length == 1) {
                        final Player p = (Player) sender;
                        if (p.hasPermission("frameapi.setbank")) {
                            final double x = Double.parseDouble(args[0]);
                            this.eco.SaveMoneyInBank((OfflinePlayer) p, x);
                            p.sendMessage("§aYour Bank Account have been Succssesfully set to §b" + x);
                        } else {
                            p.sendMessage(Main.Variables.getPrefix() + " " + Main.Variables.getNoPerm());
                        }
                    } else if (args.length == 2) {
                        final Player p = (Player) sender;
                        if (p.hasPermission("frameapi.setbank.other")) {
                            final double x = Double.parseDouble(args[1]);
                            if (args[0].equalsIgnoreCase("@a")) {
                                Bukkit.getOnlinePlayers().forEach(target -> {
                                    this.eco.SaveMoneyInBank((OfflinePlayer) target, x);
                                    if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("en_EN")) {
                                        p.sendMessage("§aYour have been successful set the Bank Account from §b" + target.getName() + "§a to §b" + x);
                                        target.sendMessage("§aYour Bank account have been set to §b" + x);
                                    } else if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("de_DE")) {
                                        p.sendMessage("§aYour have been successful set the Bank Account from §b" + target.getName() + "§a to §b" + x);
                                        target.sendMessage("§aYour Bank account have been set to §b" + x);
                                    }
                                });
                            }
                            final Player target = Bukkit.getPlayer(args[0]);
                            if (target != null) {
                                this.eco.SaveMoneyInBank((OfflinePlayer) target, x);
                                if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("en_EN")) {
                                    p.sendMessage("§aYour have been successful set the Bank Account from §b" + target.getName() + "§a to §b" + x);
                                    target.sendMessage("§aYour Bank account have been set to §b" + x);
                                } else if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("de_DE")) {
                                    p.sendMessage("§aYour have been successful set the Bank Account from §b" + target.getName() + "§a to §b" + x);
                                    target.sendMessage("§aYour Bank account have been set to §b" + x);
                                }
                            } else {
                                OfflinePlayer offline = Bukkit.getOfflinePlayer(args[0]);
                                this.eco.SaveMoneyInBank(offline, x);
                                p.sendMessage("§aYour have been successful set the Bank Account from §b" + offline.getName() + "§a to §b" + x);
                            }
                        } else {
                            p.sendMessage(Main.Variables.getPrefix() + " " + Main.Variables.getNoPerm());
                        }
                    } else {
                        final Player p = (Player) sender;
                        p.sendMessage("§cPlease use §a/setbank (amount) §cor §a/setbank (target) (amount)");
                    }
                }
            } else if (cmd.getName().equalsIgnoreCase("bankbalance")) {
                if (args.length == 0) {
                    final Player p = (Player) sender;
                    if (p.hasPermission("frameapi.bankbalance")) {
                        final double x = this.eco.getMoneyFromBankMySQL((OfflinePlayer) p).doubleValue();
                        p.sendMessage("§aYour Bank Account have §b" + x);
                    } else {
                        p.sendMessage(Main.Variables.getPrefix() + " " + Main.Variables.getNoPerm());
                    }
                } else if (args.length == 1) {
                    final Player target = Bukkit.getPlayer(args[0]);
                    final Player p = (Player) sender;
                    if (p.hasPermission("frameapi.bankbalance.other")) {
                        if (target != null) {
                            final double x = this.eco.getMoneyFromBankMySQL((OfflinePlayer) target).doubleValue();
                            p.sendMessage("§aThe Bank Account from §b" + target.getName() + " §a is §b" + x);
                        } else {
                            OfflinePlayer offline = Bukkit.getOfflinePlayer(args[0]);
                            final double x = this.eco.getMoneyFromBankMySQL(offline).doubleValue();
                            p.sendMessage("§aThe Bank Account from §b" + offline.getName() + "§a is §b" + x);
                        }
                    } else {
                        p.sendMessage(Main.Variables.getPrefix() + " " + Main.Variables.getNoPerm());
                    }
                } else {
                    final Player p = (Player) sender;
                    p.sendMessage("§cPlease use §a/bankbalance §cor §a/bankbalance (target)");
                }
            }
        }
        return false;
    }


    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("transfer")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 2) {
                    ArrayList<String> transfer = new ArrayList<>();
                    transfer.add((new Money()).getMoneyFromBankMySQL((OfflinePlayer) player) + "");
                    return transfer;
                }
            }
        } else if (command.getName().equalsIgnoreCase("deposit")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 1) {
                    ArrayList<String> deposit = new ArrayList<>();
                    deposit.add((new Money()).getMoney((OfflinePlayer) player) + "");
                    return deposit;
                }
            }
        } else if (command.getName().equalsIgnoreCase("withdraw") &&
                sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                ArrayList<String> deposit = new ArrayList<>();
                deposit.add((new Money()).getMoneyFromBankMySQL((OfflinePlayer) player) + "");
                return deposit;
            }
        }

        return null;
    }
}


