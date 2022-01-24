package de.framedev.frameapi.api.money;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import de.framedev.frameapi.main.Main;
import de.framedev.frameapi.mysql.MYSQL;
import de.framedev.frameapi.mysql.SQL;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static de.framedev.frameapi.mysql.SQL.*;

/**
 * This Plugin was Created by FrameDev
 * Package : de.framedev.essentialsmin.api
 * Date: 23.11.2020
 * Project: EssentialsMini
 * Copyrighted by FrameDev
 */
public class MySQLManager {

    protected String tableName = "frameapi_eco";

    protected boolean isOnlineMode() {
        return Bukkit.getServer().getOnlineMode();
    }

    /**
     * set the Money in the Database
     *
     * @param player the Player
     * @param amount Money amount
     */
    protected void setMoney(OfflinePlayer player, double amount) {
        if (isOnlineMode()) {
            if (isTableExists(tableName)) {
                if (exists(tableName, "Player", player.getUniqueId().toString())) {
                    updateData(tableName, "Money", "'" + amount + "'", "Player = '" + player.getUniqueId().toString() + "'");
                } else {
                    insertData(tableName, "'" + player.getUniqueId().toString() + "','" + player.getName() + "','" + amount + "'", "Player", "Name", "Money");
                }
            } else {
                createTable(tableName, "Player TEXT(256)", "Name TEXT(255)", "Money DOUBLE", "BankBalance DOUBLE", "BankName TEXT", "BankOwner TEXT", "BankMembers TEXT");
                insertData(tableName, "'" + player.getUniqueId().toString() + "','" + player.getName() + "','" + amount + "'", "Player", "Name", "Money");
            }
        } else {
            if (isTableExists(tableName)) {
                if (exists(tableName, "Player", player.getName())) {
                    updateData(tableName, "Money", "'" + amount + "'", "Player = '" + player.getName() + "'");
                } else {
                    insertData(tableName, "'" + player.getName() + "','" + amount + "'", "Player", "Money");
                }
            } else {
                createTable(tableName, "Player TEXT(256)", "Name TEXT(255)", "Money DOUBLE", "BankBalance DOUBLE", "BankName TEXT", "BankOwner TEXT", "BankMembers TEXT");
                insertData(tableName, "'" + player.getName() + "','" + amount + "'", "Player", "Money");
            }
        }
    }

    /**
     * @param player the Player
     * @return the Money from the selected Player
     */
    protected double getMoney(OfflinePlayer player) {
        if (isOnlineMode()) {
            if (isTableExists(tableName)) {
                if (exists(tableName, "Player", player.getUniqueId().toString())) {
                    if (get(tableName, "Money", "Player", player.getUniqueId().toString()) != null) {
                        return (double) get(tableName, "Money", "Player", player.getUniqueId().toString());
                    }
                }
            } else {
                createTable(tableName, "Player TEXT(256)", "Name TEXT(255)", "Money DOUBLE", "BankBalance DOUBLE", "BankName TEXT", "BankOwner TEXT", "BankMembers TEXT");
            }
        } else {
            if (isTableExists(tableName)) {
                if (exists(tableName, "Player", player.getName())) {
                    if (get(tableName, "Money", "Player", player.getName()) != null) {
                        return (double) get(tableName, "Money", "Player", player.getName());
                    }
                }
            } else {
                createTable(tableName, "Player TEXT(256)", "Name TEXT(255)", "Money DOUBLE", "BankBalance DOUBLE", "BankName TEXT", "BankOwner TEXT", "BankMembers TEXT");
            }
        }
        return 0.0D;
    }

    protected void addMoney(OfflinePlayer player, double amount) {
        double money = getMoney(player);
        money += amount;
        setMoney(player, money);
    }

    protected void removeMoney(OfflinePlayer player, double amount) {
        double money = getMoney(player);
        money -= amount;
        setMoney(player, money);
    }

    /**
     * @param player   the BankOwner
     * @param bankName the BankName
     * @return Successfully creating the Bank or not
     */
    protected boolean createBank(OfflinePlayer player, String bankName) {
        if (isTableExists(tableName)) {
            if (isOnlineMode()) {
                if (exists(tableName, "Player", player.getUniqueId().toString())) {
                    if (get(tableName, "BankName", "Player", player.getUniqueId().toString()) != null) {
                        return false;
                    } else {
                        updateData(tableName, "BankName", "'" + bankName + "'", "Player = '" + player.getUniqueId().toString() + "'");
                        updateData(tableName, "BankOwner", "'" + player.getUniqueId().toString() + "'", "Player = '" + player.getUniqueId().toString() + "'");
                        return true;
                    }
                } else {
                    insertData(tableName, "'" + player.getUniqueId().toString() + "','" + bankName + "','" + player.getUniqueId().toString() + "'", "Player", "BankName", "BankOwner");
                    return true;
                }
            } else {
                if (exists(tableName, "Player", player.getName())) {
                    if (get(tableName, "BankName", "Player", player.getName()) == null) {
                        return false;
                    } else {
                        updateData(tableName, "BankName", "'" + bankName + "'", "Player = '" + player.getName() + "'");
                        updateData(tableName, "BankOwner", "'" + player.getName() + "'", "Player = '" + player.getName() + "'");
                        return true;
                    }
                } else {
                    insertData(tableName, "'" + player.getName() + "','" + bankName + "','" + player.getName() + "'", "Player", "BankName", "BankOwner");
                    return true;
                }
            }
        } else {
            createTable(tableName, "Player TEXT(256)", "Name TEXT(255)", "Money DOUBLE", "BankBalance DOUBLE", "BankName TEXT", "BankOwner TEXT", "BankMembers TEXT");
            if (isOnlineMode()) {
                insertData(tableName, "'" + player.getUniqueId().toString() + "','" + bankName + "','" + player.getUniqueId().toString() + "'", "Player", "BankName", "BankOwner");
            } else {
                insertData(tableName, "'" + player.getName() + "','" + bankName + "','" + player.getName() + "'", "Player", "BankName", "BankOwner");
            }
        }
        return false;
    }

    /**
     * set the Bank Money
     *
     * @param name   the BankName
     * @param amount amount to adding to the Bank
     */
    protected void setBankMoney(String name, double amount) {
        int i = 0;
        List<String> players = new ArrayList<>();
        try {
            if (Main.getInstance().isMysql()) {
                Statement statement = MYSQL.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + " WHERE BankName ='" + name + "';");
                while (resultSet.next()) {
                    i++;
                    players.add(resultSet.getString("Player"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (Main.getInstance().isMysql()) {
                MYSQL.close();
            }
        }
        if (Main.getInstance().isMysql()) {
            for (int x = 0; x <= i; x++) {
                for (String player : players) {
                    updateData(tableName, "BankBalance", "'" + amount + "'", "Player = '" + player + "'");
                }
            }
        }
    }

    /**
     * @param name the BankName
     * @return Amount of the Bank
     */
    protected double getBankMoney(String name) {
        try {
            if (Main.getInstance().isMysql()) {
                Statement statement = MYSQL.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + " WHERE BankName ='" + name + "';");
                if (resultSet.next())
                    return resultSet.getDouble("BankBalance");
            }
        } catch (Exception ignored) {
        } finally {
            if (Main.getInstance().isMysql()) {
                MYSQL.close();
            }
        }
        return 0.0;
    }

    protected void addBankMoney(String name, double amount) {
        double money = getBankMoney(name);
        money += amount;
        setBankMoney(name, money);
    }

    protected void removeBankMoney(String name, double amount) {
        double money = getBankMoney(name);
        money -= amount;
        setBankMoney(name, money);
    }

    /**
     * @param name   the BankName
     * @param player the Player
     * @return if the user is the BankOwner
     */
    protected boolean isBankOwner(String name, OfflinePlayer player) {
        try {
            if (Main.getInstance().isMysql()) {
                Statement statement = MYSQL.getConnection().createStatement();
                ResultSet resultSet = null;
                if (isOnlineMode()) {
                    resultSet = statement.executeQuery("SELECT * FROM " + tableName + " WHERE Player = '" + player.getUniqueId().toString() + "';");
                    if (resultSet.next())
                        if (resultSet.getString("BankName").equalsIgnoreCase(name) && resultSet.getString("BankOwner").equalsIgnoreCase(player.getUniqueId().toString()))
                            return true;
                } else {
                    resultSet = statement.executeQuery("SELECT * FROM " + tableName + " WHERE Player = '" + player.getName() + "';");
                    if (resultSet.next())
                        if (resultSet.getString("BankName").equalsIgnoreCase(name) && resultSet.getString("BankOwner").equalsIgnoreCase(player.getName()))
                            return true;
                }
            }
        } catch (Exception ignored) {
        } finally {
            if (Main.getInstance().isMysql()) {
                MYSQL.close();
            }
        }
        return false;
    }

    /**
     * Adding user to the Bank as Member
     *
     * @param bankName the BankName
     * @param player   the Player
     */
    public void addBankMember(String bankName, OfflinePlayer player) {
        if (SQL.isTableExists(tableName)) {
            if (exists(tableName, "BankName", bankName)) {
                if (get(tableName, "BankMembers", "BankName", bankName) != null) {
                    Type type = new TypeToken<List<String>>() {
                    }.getType();
                    List<String> players = new Gson().fromJson((String) get(tableName, "BankMembers", "BankName", bankName), type);
                    if (!players.contains(player.getName()))
                        players.add(player.getName());
                    if (isOnlineMode()) {
                        updateData(tableName, "BankOwner", "'" + get(tableName, "BankOwner", "BankName", bankName) + "'", "Player = '" + player.getUniqueId().toString() + "'");
                        updateData(tableName, "BankName", "'" + bankName + "'", "Player = '" + player.getUniqueId().toString() + "'");
                    } else {
                        updateData(tableName, "BankOwner", "'" + get(tableName, "BankOwner", "BankName", bankName) + "'", "Player = '" + player.getName() + "'");
                        updateData(tableName, "BankName", "'" + bankName + "'", "Player = '" + player.getName() + "'");
                    }
                    updateData(tableName, "BankMembers", "'" + new Gson().toJson(players) + "'", "BankName = '" + bankName + "'");
                } else {
                    List<String> players = new ArrayList<>();
                    players.add(player.getName());
                    if (isOnlineMode()) {
                        updateData(tableName, "BankOwner", "'" + get(tableName, "BankOwner", "BankName", bankName) + "'", "Player = '" + player.getUniqueId().toString() + "'");
                        updateData(tableName, "BankName", "'" + bankName + "'", "Player = '" + player.getUniqueId().toString() + "'");
                    } else {
                        updateData(tableName, "BankOwner", "'" + get(tableName, "BankOwner", "BankName", bankName) + "'", "Player = '" + player.getName() + "'");
                        updateData(tableName, "BankName", "'" + bankName + "'", "Player = '" + player.getName() + "'");
                    }
                    updateData(tableName, "BankMembers", "'" + new Gson().toJson(players) + "'", "BankName = '" + bankName + "'");
                }
            }
        }
    }

    /**
     * @param bankName the BankName
     * @param player   the Player
     * @return if the Player is a BankMember
     */
    public boolean isBankMember(String bankName, OfflinePlayer player) {
        if (isTableExists(tableName)) {
            if (exists(tableName, "BankName", bankName)) {
                if (get(tableName, "BankMembers", "BankName", bankName) != null) {
                    Type type = new TypeToken<List<String>>() {
                    }.getType();
                    List<String> players = new Gson().fromJson((String) get(tableName, "BankMembers", "BankName", bankName), type);
                    return players.contains(player.getName());
                }
            }
        }
        return false;
    }

    /**
     * @param bankName the Bank Name
     * @param player   the Player
     */
    public void removeBankMember(String bankName, OfflinePlayer player) {
        List<String> pls = new ArrayList<>();
        List<String> members = new ArrayList<>();
        if (isTableExists(tableName)) {
            if (exists(tableName, "BankName", bankName)) {
                if (get(tableName, "BankMembers", "BankName", bankName) != null) {
                    Type type = new TypeToken<List<String>>() {
                    }.getType();
                    List<String> players = new Gson().fromJson((String) get(tableName, "BankMembers", "BankName", bankName), type);
                    players.remove(player.getName());
                    if (isOnlineMode()) {
                        updateData(tableName, "BankOwner", "'" + null + "'", "Player = '" + player.getUniqueId().toString() + "'");
                        updateData(tableName, "BankName", "'" + null + "'", "Player = '" + player.getUniqueId().toString() + "'");
                        updateData(tableName, "BankBalance", "'" + null + "'", "Player = '" + player.getUniqueId().toString() + "'");
                        updateData(tableName, "BankMembers", "'" + null + "'", "Player = '" + player.getUniqueId().toString() + "'");
                    } else {
                        updateData(tableName, "BankOwner", "'" + null + "'", "Player = '" + player.getName() + "'");
                        updateData(tableName, "BankName", "'" + null + "'", "Player = '" + player.getName() + "'");
                        updateData(tableName, "BankBalance", "'" + null + "'", "Player = '" + player.getName() + "'");
                        updateData(tableName, "BankMembers", "'" + null + "'", "Player = '" + player.getName() + "'");
                    }
                    members.addAll(players);
                    if (Main.getInstance().isMysql()) {
                        try {
                            Statement statement = MYSQL.getConnection().createStatement();
                            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + " WHERE BankName ='" + bankName + "';");
                            while (resultSet.next()) {
                                pls.add(resultSet.getString("Player"));
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        } finally {
                            MYSQL.close();
                        }
                    }
                }
            }
            if (Main.getInstance().isMysql()) {
                if (isTableExists(tableName)) {
                    if (exists(tableName, "BankName", bankName)) {
                        if (get(tableName, "BankMembers", "BankName", bankName) != null) {
                            for (String players : pls) {
                                updateData(tableName, "BankMembers", "'" + new Gson().toJson(members) + "'", "Player = '" + players + "'");
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Create an Account for Vault
     *
     * @param player the Player
     */
    protected void createAccount(OfflinePlayer player) {
        if (!isTableExists("essentialsmini_accounts")) {
            createTable("essentialsmini_accounts", "name TEXT(255)", "uuid VARCHAR(2003)");
        }
        if (isOnlineMode()) {
            if (!exists("essentialsmini_accounts", "uuid", "" + player.getUniqueId())) {
                insertData("essentialsmini_accounts", "'" + player.getName() + "','" + player.getUniqueId() + "'", "name", "uuid");
            }
        } else {
            if (!exists("essentialsmini_accounts", "name", "" + player.getName())) {
                insertData("essentialsmini_accounts", "'" + player.getName() + "'", "name");
            }
        }
    }

    /**
     * @param player the Player
     * @return if the Player has an Account or not
     */
    protected boolean hasAccount(OfflinePlayer player) {
        if (isTableExists("essentialsmini_accounts")) {
            if (isOnlineMode()) {
                return exists("essentialsmini_accounts", "uuid", "" + player.getUniqueId());
            } else {
                return exists("essentialsmini_accounts", "name", "" + player.getName());
            }
        }
        return false;
    }

    /**
     * @param bankName the Bank
     * @return all BankMembers
     */
    public List<String> getBankMembers(String bankName) {
        if (isTableExists(tableName)) {
            if (exists(tableName, "BankName", bankName)) {
                if (get(tableName, "BankMembers", "BankName", bankName) != null) {
                    Type type = new TypeToken<List<String>>() {
                    }.getType();
                    return new Gson().fromJson((String) get(tableName, "BankMembers", "BankName", bankName), type);
                }
            }
        }
        return null;
    }

    /**
     * @return all Banks
     */
    protected List<String> getBanks() {
        List<String> banks = new ArrayList<>();
        if (isTableExists(tableName)) {
            try {
                if (Main.getInstance().isMysql()) {
                    Statement statement = MYSQL.getConnection().createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + " WHERE BankName IS NOT NULL");
                    while (resultSet.next()) {
                        banks.add(resultSet.getString("BankName"));
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (Main.getInstance().isMysql())
                    MYSQL.close();
            }
        }
        return banks;
    }

    public boolean removeBank(String bankName) {
        if (isTableExists(tableName)) {
            if (getBanks().contains(bankName)) {
                List<String> members = getBankMembers(bankName);
                try {
                    if (Main.getInstance().isMysql()) {
                        Statement statement = MYSQL.getConnection().createStatement();
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + " WHERE BankName = '" + bankName + "'");
                        while (resultSet.next()) {
                            resultSet.updateDouble("BankBalance", 0.0);
                            resultSet.updateString("BankName", null);
                            resultSet.updateString("BankOwner", null);
                            resultSet.updateString("BankMembers", null);
                        }
                        return true;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    if (Main.getInstance().isMysql()) {
                        MYSQL.close();
                    }
                }
            }
        }
        return false;
    }
}
