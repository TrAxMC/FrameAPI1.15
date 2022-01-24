package de.framedev.frameapi.api.money;

import de.framedev.frameapi.main.Main;
import de.framedev.frameapi.mongodb.BackendManager;
import de.framedev.frameapi.mysql.IsTableExist;
import de.framedev.frameapi.mysql.SQL;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.IOException;

public class Money implements Listener {

    private static boolean registered;
    private File file = new File("plugins/FrameAPI/MoneyFile", "money.yml");
    private FileConfiguration cfg = YamlConfiguration.loadConfiguration(this.file);

    public void createAccount(OfflinePlayer player) {
        if (Bukkit.getPluginManager().getPlugin("Vault") != null)
            Main.getInstance().getVaultManager().getEco().createPlayerAccount(player);
    }

    private void save() {
        try {
            this.cfg.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFile() {
        try {
            this.cfg.load(this.file);
        } catch (IOException | org.bukkit.configuration.InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public Double getMoney(OfflinePlayer player) {
        File file = new File("plugins/FrameAPI/MoneyFile", "money.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if (Main.getInstance().isMysql()) {
            return new MySQLManager().getMoney(player);
        } else if (Main.getInstance().isMongoDb()) {
            if (Main.getInstance().getBackendManager().exists(player, "money", "essentialsmini_data"))
                return (double) Main.getInstance().getBackendManager().get(player, "money", "essentialsmini_data");
        } else {
            if (Bukkit.getServer().getOnlineMode()) {
                return cfg.getDouble(player.getUniqueId().toString());
            } else {
                return cfg.getDouble(player.getName());
            }
        }
        return 0.0;
    }

    public void addMoney(OfflinePlayer player, double amount) {
        double balance = getMoney(player);
        balance += amount;
        if (Main.getInstance().isMysql()) {
            new MySQLManager().addMoney(player, amount);
        } else if (Main.getInstance().isMongoDb()) {
            if (Main.getInstance().getBackendManager().exists(player, "money", "eco"))
                Main.getInstance().getBackendManager().updateUser(player, "money", amount, "eco");
        } else {
            double money = getMoney(player);
            money += amount;
            setMoney(player, money);
        }
    }

    public double getMoneyFromMongo(OfflinePlayer player) {
        return Double.parseDouble(Main.getInstance().getBackendManager().get(player, "money", "money").toString());
    }

    public void setMoneyMongo(OfflinePlayer player, double money) {
        Main.getInstance().getBackendManager().updateUser(player, "money", Double.valueOf(money), "money");
    }

    public void removeMoney(OfflinePlayer player, double amount) {
        double balance = getMoney(player);
        balance -= amount;
        if (balance < Main.getInstance().getConfig().getDouble("Economy.MinBalance"))
            if (Main.getInstance().isMysql()) {
                new MySQLManager().removeMoney(player, amount);
            } else if (Main.getInstance().isMongoDb()) {
                if (Main.getInstance().getBackendManager().exists(player, "money", "eco"))
                    Main.getInstance().getBackendManager().updateUser(player, "money", amount, "eco");
            } else {
                setMoney(player, balance);
            }
    }

    public void setMoney(OfflinePlayer player, double amount) {
        if (Bukkit.getServer().getOnlineMode()) {
            cfg.set(player.getUniqueId().toString(), amount);
        } else {
            cfg.set(player.getName(), amount);
        }
        save();
    }

    public boolean isRegistered(OfflinePlayer player) {
        if (Main.getInstance().getConfig().getBoolean("MYSQL.Boolean")) {
            if (SQL.isTableExists("money")) {
                if (SQL.exists("money", "PlayerName", player.getName())) {
                    if (SQL.get("money", "Registered", "PlayerName", player.getName()) != null) {
                        registered = Boolean
                                .parseBoolean((String) SQL.get("money", "Registered", "PlayerName", player.getName()));
                        return registered;
                    } else {
                        System.err.println("null");
                        return registered = false;
                    }
                }
            } else {
                SQL.createTable("money", "PlayerName TEXT(64)", "balance_money DOUBLE", "bankmoney DOUBLE",
                        "Registered TEXT");
                return registered = false;
            }
        } else if (this.cfg.getBoolean(player.getUniqueId() + "." + player.getName() + ".registered")) {
            File file = new File(Main.getInstance().getDataFolder() + "/money", "eco.yml");
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            if (Bukkit.getServer().getOnlineMode()) {
                if (Main.getInstance().isMysql()) {
                    return new MySQLManager().hasAccount(player);
                }
                return cfg.getStringList("accounts").contains(player.getUniqueId().toString());
            } else {
                if (Main.getInstance().isMysql()) {
                    return new MySQLManager().hasAccount(player);
                }
                return cfg.getStringList("accounts").contains(player.getName());
            }
        }
        return registered;
    }

    public void setRegistered(OfflinePlayer player, boolean registered) {
        if (Main.getInstance().getConfig().getBoolean("MYSQL.Boolean")) {
            if (registered) {
                if (SQL.isTableExists("money")) {
                    if (SQL.exists("money", "PlayerName", player.getName())) {
                        SQL.updateData("money", "Registered", "'" + registered + "'",
                                "PlayerName = '" + player.getName() + "'");
                    } else {
                        SQL.insertData("money", "'" + player.getName() + "','" + registered + "'", "PlayerName",
                                "Registered");
                    }
                } else {
                    SQL.createTable("money", "PlayerName TEXT(64)", "balance_money DOUBLE", "bankmoney DOUBLE",
                            "Registered TEXT");
                    SQL.insertData("money", "'" + player.getName() + "','" + registered + "'", "PlayerName",
                            "Registered");
                }
            } else {
                if (SQL.exists("money", "PlayerName", player.getName())) {
                    SQL.deleteDataInTable("money", "PlayerName = '" + player.getName() + "'");
                }
            }
        } else if (this.cfg.getBoolean(player.getUniqueId() + "." + player.getName() + ".registered")) {
            createAccount(player);
        }
        Money.registered = registered;
    }

    private void saveMoneyInSQL(OfflinePlayer player, double amount) {
        if (IsTableExist.isExist("money")) {
            if (SQL.exists("money", "PlayerName", player.getName())) {
                SQL.updateData("money", "balance_money", "'" + amount + "'", "PlayerName ='" + player.getName() + "'");
            } else {
                SQL.insertData("money", "'" + player.getName() + "','" + amount + "'", "PlayerName", "balance_money");
            }
        } else {
            SQL.createTable("money", "PlayerName TEXT(64)", "balance_money DOUBLE", "bankmoney DOUBLE",
                    "Registered TEXT");
            SQL.insertData("money", "'" + player.getName() + "','" + amount + "'", "PlayerName", "balance_money");
        }
    }

    private Double getMoneyMySql(OfflinePlayer player) {
        if (IsTableExist.isExist("money")) {
            if (SQL.exists("money", "PlayerName", player.getName())) {
                if (SQL.get("money", "balance_money", "PlayerName", player.getName()) != null) {
                    Object x = SQL.get("money", "balance_money", "PlayerName", player.getName());
                    return Double.valueOf(x.toString());
                } else {
                    return 0D;
                }
            }
            return 0.0D;
        }

        SQL.createTable("money", "PlayerName TEXT(64)", "balance_money DOUBLE", "bankmoney DOUBLE", "Registered TEXT");
        return 0.0D;
    }

    public void SaveMoneyInBank(OfflinePlayer player, double amount) {
        if (Main.getInstance().getConfig().getBoolean("MYSQL.Boolean")) {
            if (IsTableExist.isExist("money")) {
                if (SQL.exists("money", "PlayerName", player.getName())) {
                    SQL.updateData("money", "bankmoney", "'" + amount + "'", "PlayerName ='" + player.getName() + "'");
                } else {
                    SQL.insertData("money", "'" + player.getName() + "','" + amount + "'", "PlayerName", "bankmoney");
                }
            } else {
                SQL.createTable("money", "PlayerName TEXT(64)", "balance_money DOUBLE", "bankmoney DOUBLE",
                        "Registered TEXT");
                SQL.insertData("money", "'" + player.getName() + "','" + amount + "'", "PlayerName", "bankmoney");
            }
        } else if (Main.cfgm.getBoolean("MongoDB.LocalHost") || Main.cfgm.getBoolean("MongoDB.Boolean")) {
            setMoneyFromMongoBank(player, amount);
        } else {
            this.cfg.set(player.getUniqueId() + "." + player.getName() + ".Bank", Double.valueOf(amount));
            save();
            loadFile();
        }
    }

    public Double getMoneyFromBankMySQL(OfflinePlayer player) {
        if (Main.getInstance().getConfig().getBoolean("MYSQL.Boolean")) {
            double x = 0.0D;
            if (IsTableExist.isExist("money")) {
                if (SQL.exists("money", "PlayerName", player.getName())) {
                    if (SQL.get("money", "bankmoney", "PlayerName", player.getName()) != null) {
                        x = (Double) SQL.get("money", "bankmoney", "PlayerName", player.getName());
                        return Double.valueOf(x);
                    }
                    return Double.valueOf(0.0D);
                }

                return Double.valueOf(0.0D);
            }

            SQL.createTable("money", "PlayerName TEXT(64)", "balance_money DOUBLE", "bankmoney DOUBLE",
                    "Registered TEXT");
            return Double.valueOf(0.0D);
        }
        if (Main.cfgm.getBoolean("MongoDB.LocalHost") || Main.cfgm.getBoolean("MongoDB.Boolean")) {
            return getMoneyFromMongoBank(player);
        }
        return this.cfg.getDouble(player.getUniqueId() + "." + player.getName() + ".Bank");
    }

    public double getMoneyFromMongoBank(OfflinePlayer player) {
        return Double.parseDouble(Main.getInstance().getBackendManager().get(player, "bank", "money").toString());
    }

    public void setMoneyFromMongoBank(OfflinePlayer player, double money) {
        Main.getInstance().getBackendManager().updateUser(player, "bank", Double.valueOf(money), "money");
    }

    public void RemoveMoneyFromBank(OfflinePlayer player, double amount) {
        if (Main.getInstance().getConfig().getBoolean("MYSQL.Boolean")) {
            double money = getMoneyFromBankMySQL(player).doubleValue();
            money -= amount;
            SaveMoneyInBank(player, money);
        } else if (Main.cfgm.getBoolean("MongoDB.LocalHost") || Main.cfgm.getBoolean("MongoDB.Boolean")) {
            double money = getMoneyFromMongoBank(player);
            money -= amount;
            setMoneyFromMongoBank(player, money);
        }
    }

    public void AddMoneyFromBank(OfflinePlayer player, double amount) {
        if (Main.getInstance().getConfig().getBoolean("MYSQL.Boolean")) {
            double money = getMoneyFromBankMySQL(player).doubleValue();
            money += amount;
            SaveMoneyInBank(player, money);
        } else if (Main.cfgm.getBoolean("MongoDB.LocalHost") || Main.cfgm.getBoolean("MongoDB.Boolean")) {
            double money = getMoneyFromMongoBank(player);
            money += amount;
            setMoneyFromMongoBank(player, money);
        }
    }

    public boolean hasPlayerAmount(OfflinePlayer player, double amount) {
        double money = getMoney(player).doubleValue();
        if (money < amount) {
            return false;
        }
        return true;
    }

    public boolean hasPlayerMoneyBank(OfflinePlayer player, double amount) {
        double money = getMoneyFromBankMySQL(player).doubleValue();
        if (money < amount) {
            return false;
        }
        return true;
    }

}
