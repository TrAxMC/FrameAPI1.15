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

import java.util.*;


public class MoneyCMD
        implements CommandExecutor, TabCompleter {
    private static Money eco = new Money();
    private final Main plugin;

    public MoneyCMD(Main plugin) {
        this.plugin = plugin;
        if (Main.getInstance().getConfig().getBoolean("Money.CMDS")) {
            plugin.getCommand("set").setExecutor(this);
            plugin.getCommand("balance").setExecutor(this);
            plugin.getCommand("pay").setExecutor(this);
            plugin.getCommand("pay").setTabCompleter(this);
            plugin.getCommand("balancetop").setExecutor(this);
        } else if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("en_EN")) {
            Bukkit.getConsoleSender().sendMessage(Main.Variables.getPrefix() + " §4Money Commands are Disabled");
        } else if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("de_DE")) {
            Bukkit.getConsoleSender().sendMessage(Main.Variables.getPrefix() + " §4Money Commands sind Deaktiviert");
        }
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("balance")) {
                if (p.hasPermission("frameapi.balance")) {
                    if (args.length == 0) {
                        String money = String.valueOf(eco.getMoney((OfflinePlayer) p));
                        String text = Main.getInstance().getConfig().getString("Money.MSG.Balance");
                        text = text.replace("[Money]", money);
                        text = text.replace('&', '§');
                        p.sendMessage(text);
                        return true;
                    }
                    if (args.length == 1) {
                        if (p.hasPermission("frameapi.balance.other")) {
                            Player target = Bukkit.getPlayer(args[0]);
                            if (target != null) {
                                String str1 = String.valueOf(eco.getMoney((OfflinePlayer) target));
                                String str2 = Main.getInstance().getConfig().getString("MoneyBalance.Other.MSG");
                                str2 = str2.replace("[Money]", str1);
                                str2 = str2.replace('&', '§');
                                str2 = str2.replace("[Target]", target.getName());
                                p.sendMessage(str2);
                                return true;
                            }
                            OfflinePlayer Offline = Bukkit.getOfflinePlayer(args[0]);
                            String money = String.valueOf(eco.getMoney(Offline));
                            String text = Main.getInstance().getConfig().getString("MoneyBalance.Other.MSG");
                            text = text.replace("[Money]", money);
                            text = text.replace('&', '§');
                            text = text.replace("[Target]", Offline.getName());
                            p.sendMessage(text);
                            return true;
                        } else {

                            p.sendMessage(Main.Variables.getPrefix() + " " + Main.Variables.getNoPerm());
                            return true;
                        }
                    } else {
                        p.sendMessage("§cPlease use §b/balance §cor §b/balance (player)");
                        return true;
                    }
                } else {
                    p.sendMessage(Main.Variables.getPrefix() + " " + Main.Variables.getNoPerm());
                    return true;
                }
            }
            if (cmd.getName().equalsIgnoreCase("pay")) {
                try {
                    double amount = Double.parseDouble(args[1]);
                    if (!eco.hasPlayerAmount((OfflinePlayer) p, amount)) {
                        p.sendMessage("§4Not enought Money");
                        return true;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        OfflinePlayer Offline = Bukkit.getOfflinePlayer(args[0]);
                        eco.addMoney(Offline, amount);
                        eco.removeMoney(p, amount);
                        String str1 = String.valueOf(amount);
                        String str2 = Main.getInstance().getConfig().getString("Money.MSG.Pay");
                        str2 = str2.replace('&', '§');
                        str2 = str2.replace("[Target]", Offline.getName());
                        str2 = str2.replace("[Money]", str1);
                        p.sendMessage(str2);
                        return true;
                    }
                    eco.addMoney((OfflinePlayer) target, amount);
                    eco.removeMoney(p, amount);
                    String money = String.valueOf(amount);
                    String Text = Main.getInstance().getConfig().getString("Money.MSG.Pay");
                    Text = Text.replace('&', '§');
                    Text = Text.replace("[Target]", target.getName());
                    Text = Text.replace("[Money]", money);
                    p.sendMessage(Text);
                    target.sendMessage("§aYou got §b" + money + " §afrom §b" + p.getName());
                    return true;

                } catch (ArrayIndexOutOfBoundsException e) {
                    p.sendMessage("§cPlease use /pay (target) (amount)");
                    return true;
                }
            }
            if (cmd.getName().equalsIgnoreCase("set")) {
                if (p.hasPermission("frameapi.set")) {
                    if (args.length == 1) {
                        double amount = Double.parseDouble(args[0]);
                        eco.setMoney((OfflinePlayer) p, amount);
                        String Text = Main.getInstance().getConfig().getString("Money.MSG.Set");
                        String money = String.valueOf(amount);
                        Text = Text.replace('&', '§');
                        Text = Text.replace("[Money]", money);
                        p.sendMessage(Text);
                        return true;
                    }

                    if (args.length == 2) {
                        if (p.hasPermission("frameapi.set.other")) {
                            double amount = Double.parseDouble(args[1]);
                            if (args[0].equalsIgnoreCase("**")) {
                                Bukkit.getOnlinePlayers().forEach(target -> {
                                    String str1 = String.valueOf(amount);
                                    eco.setMoney((OfflinePlayer) target, amount);
                                    String str2 = Main.getInstance().getConfig().getString("MoneySet.Other.MSG");
                                    String Text1 = Main.getInstance().getConfig().getString("Money.MSG.Set");
                                    Text1 = Text1.replace('&', '§');
                                    Text1 = Text1.replace("[Money]", String.valueOf(amount));
                                    str2 = str2.replace('&', '§');
                                    str2 = str2.replace("[Target]", target.getName());
                                    str2 = str2.replace("[Money]", str1);
                                    target.sendMessage(Text1);
                                    p.sendMessage(str2);
                                });
                                return true;
                            } else {
                                Player target = Bukkit.getPlayer(args[0]);
                                if (target != null) {
                                    String str1 = String.valueOf(amount);
                                    eco.setMoney((OfflinePlayer) target, amount);
                                    String str2 = Main.getInstance().getConfig().getString("MoneySet.Other.MSG");
                                    String Text1 = Main.getInstance().getConfig().getString("Money.MSG.Set");
                                    Text1 = Text1.replace('&', '§');
                                    Text1 = Text1.replace("[Money]", String.valueOf(amount));
                                    str2 = str2.replace('&', '§');
                                    str2 = str2.replace("[Target]", target.getName());
                                    str2 = str2.replace("[Money]", str1);
                                    target.sendMessage(Text1);
                                    p.sendMessage(str2);
                                    return true;
                                } else {
                                    OfflinePlayer Offline = Bukkit.getOfflinePlayer(args[0]);
                                    String money = String.valueOf(amount);
                                    eco.setMoney(Offline, amount);
                                    String Text = Main.getInstance().getConfig().getString("MoneySet.Other.MSG");
                                    Text = Text.replace('&', '§');
                                    Text = Text.replace("[Target]", Offline.getName());
                                    Text = Text.replace("[Money]", money);
                                    p.sendMessage(Text);
                                    return true;
                                }
                            }
                        }
                        p.sendMessage(Main.Variables.getPrefix() + " " + Main.Variables.getNoPerm());
                        return true;
                    }

                    p.sendMessage("§cPlease use §b/set (amount) §cor §b/set (palyer) (amount)");
                } else {

                    p.sendMessage(Main.Variables.getPrefix() + " " + Main.Variables.getNoPerm());
                    return true;
                }
            }
            if (cmd.getName().equalsIgnoreCase("balancetop")) {
                if (p.hasPermission("frameapi.balancetop")) {
                    HashMap<String, Double> mostplayers = new HashMap<>();
                    ValueComparator bvc = new ValueComparator(mostplayers);
                    TreeMap<String, Double> sorted_map = new TreeMap<String, Double>(bvc);
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        mostplayers.put(all.getName(), eco.getMoney(all) + eco.getMoneyFromBankMySQL(all));
                    }
                    for (OfflinePlayer alloffline : Main.getInstance().getPlayers()) {
                        mostplayers.put(alloffline.getName(), eco.getMoney(alloffline) + eco.getMoneyFromBankMySQL(alloffline));
                    }
                    sorted_map.putAll(mostplayers);
                    int i = 0;
                    for (Map.Entry<String, Double> e : sorted_map.entrySet()) {
                        i++;
                        p.sendMessage("§a" + i + "st [§6" + e.getKey() + " §b: " + e.getValue() + "§a]");
                        if (i == 3) {
                            break;
                        }
                    }
                    return true;

                } else {
                    p.sendMessage(Main.Variables.getPrefix() + " " + Main.Variables.getNoPerm());
                    return true;
                }
            }
        } else {
            if (cmd.getName().equalsIgnoreCase("set") &&
                    args.length == 2) {
                try {
                    if (sender.hasPermission("frameapi.set.other")) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target != null) {
                            double d = Double.parseDouble(args[1]);
                            eco.setMoney((OfflinePlayer) target, d);
                            String str1 = Main.getInstance().getConfig().getString("Money.MSG.Set");
                            String str2 = String.valueOf(d);
                            str1 = str1.replace('&', '§');
                            str1 = str1.replace("[Money]", str2);
                            sender.sendMessage(str1);
                            return true;
                        }
                        double amount = Double.parseDouble(args[1]);
                        OfflinePlayer Offline = Bukkit.getOfflinePlayer(args[0]);
                        String money = String.valueOf(amount);
                        eco.setMoney(Offline, amount);
                        String Text = Main.getInstance().getConfig().getString("MoneySet.Other.MSG");
                        Text = Text.replace('&', '§');
                        Text = Text.replace("[Target]", Offline.getName());
                        Text = Text.replace("[Money]", money);
                        sender.sendMessage(Text);
                        return true;
                    } else {
                        sender.sendMessage(Main.Variables.getPrefix() + " " + Main.Variables.getNoPerm());
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    sender.sendMessage("§cuse /set (target) (amount)");
                }
            }

            if (cmd.getName().equalsIgnoreCase("balance") &&
                    args.length == 1) {
                if (sender.hasPermission("frameapi.balance.other")) {
                    try {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target != null) {
                            double amount = eco.getMoney((OfflinePlayer) target).doubleValue();
                            String Text = Main.getInstance().getConfig().getString("MoneySet.Other.MSG");
                            Text = Text.replace('&', '§');
                            Text = Text.replace("[Target]", target.getName());
                            Text = Text.replace("[Money]", String.valueOf(amount));
                            sender.sendMessage(Text);
                            return true;
                        }
                        OfflinePlayer Offline = Bukkit.getOfflinePlayer(args[0]);
                        String money = String.valueOf(eco.getMoney(Offline));
                        String text = Main.getInstance().getConfig().getString("MoneyBalance.Other.MSG");
                        text = text.replace("[Money]", money);
                        text = text.replace('&', '§');
                        text = text.replace("[Target]", Offline.getName());
                        sender.sendMessage(text);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        sender.sendMessage("§cuse /balance (target)");
                    }
                } else {
                    sender.sendMessage(Main.Variables.getPrefix() + " " + Main.Variables.getNoPerm());
                }
            }
        }

        return false;
    }


    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("pay") &&
                sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 2) {
                ArrayList<String> transfer = new ArrayList<>();
                transfer.add((new Money()).getMoney((OfflinePlayer) player) + "");
                return transfer;
            }
        }

        return null;
    }

    class ValueComparator implements Comparator<String> {


        Map<String, Double> base;

        public ValueComparator(Map<String, Double> base) {
            this.base = base;
        }


        public int compare(String a, String b) {
            if (base.get(a) >= base.get(b)) {
                return -1;
            } else {
                return 1;
            } // returning 0 would merge keys
        }
    }
}


