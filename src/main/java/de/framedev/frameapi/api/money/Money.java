package de.framedev.frameapi.api.money;

import java.io.File;
import java.io.IOException;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;

import de.framedev.frameapi.main.Main;
import de.framedev.frameapi.mysql.IsTableExist;
import de.framedev.frameapi.mysql.SQL;

public class Money implements Listener {

    private static boolean registered;
    private File fileMoney = new File("plugins/FrameAPI/MoneyFile", "money.yml");
    private FileConfiguration cfgMoney = YamlConfiguration.loadConfiguration(this.fileMoney);

    public void createAccount(OfflinePlayer player) {
        if (this.cfgMoney.getBoolean(player.getUniqueId() + "." + player.getName() + ".registered")) {
            setRegistered(player,true);
            saveMoneyFile();
            loadFile();
        } else {
            this.cfgMoney.set(player.getUniqueId() + "." + player.getName() + ".registered", Boolean.valueOf(true));
            this.cfgMoney.set(player.getUniqueId() + "." + player.getName() + ".Money", Integer.valueOf(0));
            this.cfgMoney.set(player.getUniqueId() + "." + player.getName() + ".Bank", Integer.valueOf(0));
            setRegistered(player,true);
            saveMoneyFile();
            loadFile();
        }
    }

    public void removeAccount(OfflinePlayer player) {
        this.cfgMoney.set(player.getUniqueId() + "." + player.getName() + ".registered", null);
        this.cfgMoney.set(player.getUniqueId() + "." + player.getName() + ".Money", null);
        this.cfgMoney.set(player.getUniqueId() + "." + player.getName() + ".Bank", null);
        setRegistered(player,false);
        saveMoneyFile();
        loadFile();
    }

    private void saveMoneyFile() {
        try {
            this.cfgMoney.save(this.fileMoney);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFile() {
        try {
            this.cfgMoney.load(this.fileMoney);
        } catch (IOException | org.bukkit.configuration.InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public Double getMoney(OfflinePlayer player) {
        if (Main.getInstance().getConfig().getBoolean("MYSQL.Boolean"))
            try {
                if (this.cfgMoney.getBoolean(player.getUniqueId() + "." + player.getName() + ".registered")) {
                    loadFile();
                    double d = getMoneyMySql(player);
                    return d;
                }
                createAccount(player);
                saveMoneyFile();
                loadFile();
                double money = getMoneyMySql(player).doubleValue();
                return Double.valueOf(money);
            } catch (NullPointerException e) {
                return Double.valueOf(this.cfgMoney.getDouble(player.getUniqueId() + "." + player.getName() + ".Money"));
            }
        if (Main.cfgm.getBoolean("MongoDB.LocalHost") || Main.cfgm.getBoolean("MongoDB.Boolean")) {
            return Double.valueOf(getMoneyFromMongo(player));
        }
        if (this.cfgMoney.getBoolean(player.getUniqueId() + "." + player.getName() + ".registered")) {
            return Double.valueOf(this.cfgMoney.getDouble(player.getUniqueId() + "." + player.getName() + ".Money"));
        }
        createAccount(player);
        return Double.valueOf(this.cfgMoney.getDouble(player.getUniqueId() + "." + player.getName() + ".Money"));
    }


    public void addMoney(OfflinePlayer player, double amount) {
        if (Main.getInstance().getConfig().getBoolean("MYSQL.Boolean")) {
            if (isRegistered(player)) {
                if (IsTableExist.isExist("money")) {
                    double money = getMoneyMySql(player).doubleValue();
                    money += amount;
                    saveMoneyInSQL(player, money);
                    saveMoneyFile();
                    loadFile();
                } else {
                    SQL.createTable("money", new String[]{"PlayerName TEXT(64)", "balance_money DOUBLE", "bankmoney DOUBLE","Registered TEXT"});
                    SQL.insertData("money", "'" + player.getName() + "','" + amount + "'", "PlayerName", "balance_money");
                }
            } else {
                createAccount(player);
                if (IsTableExist.isExist("money")) {
                    double money = getMoneyMySql(player).doubleValue();
                    money += amount;
                    saveMoneyInSQL(player, money);
                    saveMoneyFile();
                    loadFile();
                } else {
                    SQL.createTable("money", "PlayerName TEXT(64)", "balance_money DOUBLE", "bankmoney DOUBLE","Registered TEXT");
                    SQL.insertData("money", "'" + player.getName() + "','" + amount + "'", "PlayerName", "balance_money");
                }
            }
        } else if (Main.cfgm.getBoolean("MongoDB.LocalHost") || Main.cfgm.getBoolean("MongoDB.Boolean")) {
            createAccount(player);
            double money = getMoneyFromMongo(player);
            money += amount;
            setMoneyMongo(player, money);
        } else if (this.cfgMoney.getBoolean(player.getUniqueId() + "." + player.getName() + ".registered")) {
            double money = getMoney(player).doubleValue();
            money += amount;
            this.cfgMoney.set(player.getUniqueId() + "." + player.getName() + ".Money", Double.valueOf(money));
            saveMoneyFile();
            loadFile();
            createAccount(player);
        } else {
            createAccount(player);
            double money = getMoney(player).doubleValue();
            money += amount;
            this.cfgMoney.set(player.getUniqueId() + "." + player.getName() + ".Money", Double.valueOf(money));
            saveMoneyFile();
            loadFile();
        }
        createAccount(player);
    }

    public double getMoneyFromMongo(OfflinePlayer player) {
        return Double.parseDouble(Main.getInstance().getBackendManager().get(player, "money", "money").toString());
    }

    public void setMoneyMongo(OfflinePlayer player, double money) {
        Main.getInstance().getBackendManager().updateUser(player, "money", Double.valueOf(money), "money");
    }


    public void removeMoney(OfflinePlayer player, double amount) {
        if (isRegistered(player)) {
            if (Main.getInstance().getConfig().getBoolean("MYSQL.Boolean")) {
                double money = getMoneyMySql((OfflinePlayer) player).doubleValue();

                money -= amount;
                saveMoneyInSQL((OfflinePlayer) player, money);
                saveMoneyFile();
                loadFile();
            } else if (Main.cfgm.getBoolean("MongoDB.LocalHost") || Main.cfgm.getBoolean("MongoDB.Boolean")) {
                double money = getMoneyFromMongo((OfflinePlayer) player);
                money -= amount;
                setMoneyMongo((OfflinePlayer) player, money);
            } else {
                createAccount((OfflinePlayer) player);
                double money = getMoney((OfflinePlayer) player).doubleValue();
                money -= amount;
                this.cfgMoney.set(player.getUniqueId() + "." + player.getName() + ".Money", Double.valueOf(money));
                saveMoneyFile();
                loadFile();
            }
        } else if (Main.getInstance().getConfig().getBoolean("MYSQL.Boolean")) {
            createAccount((OfflinePlayer) player);
            double money = getMoneyMySql((OfflinePlayer) player).doubleValue();
            money -= amount;
            saveMoneyInSQL((OfflinePlayer) player, money);
            saveMoneyFile();
            loadFile();
        } else if (Main.cfgm.getBoolean("MongoDB.LocalHost") || Main.cfgm.getBoolean("MongoDB.Boolean")) {
            double money = getMoneyFromMongo((OfflinePlayer) player);
            money -= amount;
            setMoneyMongo((OfflinePlayer) player, money);
            createAccount(player);
        } else {
            createAccount((OfflinePlayer) player);
            double money = getMoney((OfflinePlayer) player).doubleValue();
            money -= amount;
            this.cfgMoney.set(player.getUniqueId() + "." + player.getName() + ".Money", Double.valueOf(money));
            saveMoneyFile();
            loadFile();
        }
    }

    public void setMoney(OfflinePlayer offline, double amount) {
        if (isRegistered(offline)) {
            if (Main.getInstance().getConfig().getBoolean("MYSQL.Boolean")) {
                saveMoneyInSQL(offline, amount);
            } else if (Main.cfgm.getBoolean("MongoDB.LocalHost") || Main.cfgm.getBoolean("MongoDB.Boolean")) {
                setMoneyMongo(offline, amount);
            } else {
                this.cfgMoney.set(offline.getUniqueId() + "." + offline.getName() + ".Money", Double.valueOf(amount));
                saveMoneyFile();
                loadFile();
            }
        } else {
            if (Main.getInstance().getConfig().getBoolean("MYSQL.Boolean")) {
                saveMoneyInSQL(offline, amount);
            } else if (Main.cfgm.getBoolean("MongoDB.LocalHost") || Main.cfgm.getBoolean("MongoDB.Boolean")) {
                setMoneyMongo(offline, amount);
            } else {
                this.cfgMoney.set(offline.getUniqueId() + "." + offline.getName() + ".Money", Double.valueOf(amount));
                saveMoneyFile();
                loadFile();
            }
            createAccount(offline);
        }
    }

    public boolean isRegistered(OfflinePlayer player) {
        if (Main.getInstance().getConfig().getBoolean("MYSQL.Boolean")) {
            if (SQL.isTableExists("money")) {
                if (SQL.exists("money", "PlayerName", player.getName())) {
                    if(SQL.get("money", "Registered", "PlayerName", player.getName()) != null) {
                        registered = Boolean.parseBoolean((String)SQL.get("money", "Registered", "PlayerName", player.getName()));
                        return registered;
                    } else {
                        System.err.println("null");
                        return registered = false;
                    }
                }
            } else {
                SQL.createTable("money", "PlayerName TEXT(64)", "balance_money DOUBLE", "bankmoney DOUBLE", "Registered TEXT");
                return registered = false;
            }
        } else if (this.cfgMoney.getBoolean(player.getUniqueId() + "." + player.getName() + ".registered")) {
            return true;
        }
        return registered;
    }

    public void setRegistered(OfflinePlayer player, boolean registered) {
        if (Main.getInstance().getConfig().getBoolean("MYSQL.Boolean")) {
            if(registered) {
                if (SQL.isTableExists("money")) {
                    if (SQL.exists("money", "PlayerName", player.getName())) {
                        SQL.updateData("money", "Registered", "'" + registered + "'", "PlayerName = '" + player.getName() + "'");
                    } else {
                        SQL.insertData("money", "'" + player.getName() + "','" + registered + "'", "PlayerName", "Registered");
                    }
                } else {
                    SQL.createTable("money", "PlayerName TEXT(64)", "balance_money DOUBLE", "bankmoney DOUBLE", "Registered TEXT");
                    SQL.insertData("money", "'" + player.getName() + "','" + registered + "'", "PlayerName", "Registered");
                }
            } else {
                if(SQL.exists("money","PlayerName",player.getName())) {
                    SQL.deleteDataInTable("money","PlayerName = '" + player.getName() + "'");
                }
            }
        } else if (this.cfgMoney.getBoolean(player.getUniqueId() + "." + player.getName() + ".registered")) {
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
            SQL.createTable("money", "PlayerName TEXT(64)", "balance_money DOUBLE", "bankmoney DOUBLE","Registered TEXT");
            SQL.insertData("money", "'" + player.getName() + "','" + amount + "'", "PlayerName", "balance_money");
        }
    }


    private Double getMoneyMySql(OfflinePlayer player) {
        if (IsTableExist.isExist("money")) {
            if (SQL.exists("money", "PlayerName", player.getName())) {
                if(SQL.get("money", "balance_money", "PlayerName", player.getName()) != null) {
                    Object x = SQL.get("money", "balance_money", "PlayerName", player.getName());
                    return Double.valueOf(x.toString());
                } else {
                    return 0D;
                }
            }
            return 0.0D;
        }

        SQL.createTable("money", "PlayerName TEXT(64)", "balance_money DOUBLE", "bankmoney DOUBLE","Registered TEXT");
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
                SQL.createTable("money", "PlayerName TEXT(64)", "balance_money DOUBLE", "bankmoney DOUBLE","Registered TEXT");
                SQL.insertData("money", "'" + player.getName() + "','" + amount + "'", "PlayerName", "bankmoney");
            }
        } else if (Main.cfgm.getBoolean("MongoDB.LocalHost") || Main.cfgm.getBoolean("MongoDB.Boolean")) {
            setMoneyFromMongoBank(player, amount);
        } else {
            this.cfgMoney.set(player.getUniqueId() + "." + player.getName() + ".Bank", Double.valueOf(amount));
            saveMoneyFile();
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

            SQL.createTable("money", "PlayerName TEXT(64)", "balance_money DOUBLE", "bankmoney DOUBLE","Registered TEXT");
            return Double.valueOf(0.0D);
        }
        if (Main.cfgm.getBoolean("MongoDB.LocalHost") || Main.cfgm.getBoolean("MongoDB.Boolean")) {
            return Double.valueOf(getMoneyFromMongoBank(player));
        }
        return Double.valueOf(this.cfgMoney.getDouble(player.getUniqueId() + "." + player.getName() + ".Bank"));
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


