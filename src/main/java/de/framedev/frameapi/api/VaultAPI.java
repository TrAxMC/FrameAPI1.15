package de.framedev.frameapi.api;

import de.framedev.frameapi.api.money.Money;
import de.framedev.frameapi.main.Main;
import de.framedev.frameapi.mongodb.BackendManager;
import net.milkbowl.vault.economy.AbstractEconomy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This Plugin was Created by FrameDev
 * Package : de.framedev.essentialsmin.api
 * Date: 22.11.2020
 * Project: EssentialsMini
 * Copyrighted by FrameDev
 */
public class VaultAPI extends AbstractEconomy {

    @Override
    public boolean isEnabled() {
        if (Main.getInstance() != null) {
            return true;
        }
        return false;
    }

    @Override
    public String getName() {
        return Main.getInstance().getDescription().getName();
    }

    @Override
    public boolean hasBankSupport() {
        return true;
    }

    @Override
    public int fractionalDigits() {
        return -1;
    }

    @Override
    public String format(double v) {
        return String.valueOf(v);
    }

    @Override
    public String currencyNamePlural() {
        return "";
    }

    @Override
    public String currencyNameSingular() {
        return "";
    }

    @Override
    public boolean hasAccount(OfflinePlayer player) {
        return hasAccount(player.getName());
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
        return depositPlayer(player.getName(), amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
        return withdrawPlayer(player.getName(), amount);
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player) {
        return createPlayerAccount(player.getName());
    }

    @Override
    public boolean has(OfflinePlayer player, double amount) {
        return has(player.getName(), amount);
    }

    @Override
    public boolean hasAccount(String s) {
        File file = new File(Main.getInstance().getDataFolder() + "/money", "eco.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if (Bukkit.getServer().getOnlineMode()) {
            if (cfg.getStringList("accounts").contains(Bukkit.getOfflinePlayer(s).getUniqueId().toString())) {
                return true;
            }
        } else {
            if (cfg.getStringList("accounts").contains(Bukkit.getOfflinePlayer(s).getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasAccount(String s, String s1) {
        return hasAccount(s);
    }

    @Override
    public double getBalance(String s) {
        return new Money().getMoney(Bukkit.getOfflinePlayer(s));
    }

    @Override
    public double getBalance(String s, String s1) {
        return getBalance(s);
    }

    @Override
    public boolean has(String s, double v) {
        if (getBalance(s) < v) {
            return false;
        }
        return true;
    }

    @Override
    public boolean has(String s, String s1, double v) {
        return has(s, v);
    }

    @Override
    public EconomyResponse withdrawPlayer(String s, double v) {
        if (!hasAccount(s))
            return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "The Player does not have an Account!");
        new Money().removeMoney(Bukkit.getOfflinePlayer(s), v);
        return new EconomyResponse(v, new Money().getMoney(Bukkit.getOfflinePlayer(s)), EconomyResponse.ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse withdrawPlayer(String s, String s1, double v) {
        return withdrawPlayer(s, v);
    }

    @Override
    public EconomyResponse depositPlayer(String s, double v) {
        if (!hasAccount(s))
            return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "The Player does not have an Account!");
        if (v < 0.0D)
            return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "Value is less than zero!");
        double balance = getBalance(s);
        new Money().addMoney(Bukkit.getOfflinePlayer(s), v);
        return new EconomyResponse(v, balance, EconomyResponse.ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse depositPlayer(String s, String s1, double v) {
        return depositPlayer(s, v);
    }

    @Override
    public EconomyResponse createBank(String name, OfflinePlayer player) {
        return createBank(name, player.getName());
    }

    @Override
    public EconomyResponse createBank(String name, String player) {
        if (Main.getInstance().isMysql()) {
        } else {
            File file = new File(Main.getInstance().getDataFolder() + "/money", "eco.yml");
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            cfg.set("Banks." + name + ".Owner", player);
            try {
                cfg.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse deleteBank(String s) {
        return null;
    }

    @Override
    public EconomyResponse bankBalance(String name) {
            File file = new File(Main.getInstance().getDataFolder() + "/money", "eco.yml");
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            if (!cfg.contains("Banks." + name))
                return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "Bank doesn't exists!");
            return new EconomyResponse(cfg.getDouble("Banks." + name + ".balance"), cfg.getDouble("Banks." + name + ".balance"), EconomyResponse.ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse bankHas(String name, double v) {
        if (bankBalance(name).amount < v) {
            return new EconomyResponse(v, bankBalance(name).amount, EconomyResponse.ResponseType.FAILURE, "Not enought Money!");
        }
        return new EconomyResponse(v, bankBalance(name).amount, EconomyResponse.ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse bankWithdraw(String name, double amount) {
        File file = new File(Main.getInstance().getDataFolder() + "/money", "eco.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        double balance = bankBalance(name).amount;
        if (!bankHas(name, amount).transactionSuccess())
            return new EconomyResponse(amount, balance, EconomyResponse.ResponseType.FAILURE, "Not enought Money");
        balance -= amount;
        cfg.set("Banks." + name + ".balance", balance);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new EconomyResponse(amount, balance, EconomyResponse.ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse bankDeposit(String name, double amount) {
        File file = new File(Main.getInstance().getDataFolder() + "/money", "eco.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        double balance = bankBalance(name).amount;
        balance += amount;
        cfg.set("Banks." + name + ".balance", balance);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new EconomyResponse(amount, balance, EconomyResponse.ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse isBankOwner(String name, String player) {
        File file = new File(Main.getInstance().getDataFolder() + "/money", "eco.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if (!cfg.contains("Banks." + name + ".Owner"))
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "Doesn't Exsits");
        if (!player.equalsIgnoreCase(cfg.getString("Banks." + name + ".Owner")))
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "Isn't the Owner");
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse isBankMember(String name, String player) {
        File file = new File(Main.getInstance().getDataFolder() + "/money", "eco.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if (!cfg.contains("Banks." + name + ".members"))
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "Doesn't have any Members!");
        List<String> players = cfg.getStringList("Banks." + name + ".members");
        if (!players.contains(player))
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "Isn't a member");
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.SUCCESS, "");
    }

    @Override
    public List<String> getBanks() {
        File file = new File(Main.getInstance().getDataFolder() + "/money", "eco.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        List<String> banks = new ArrayList<>();
        ConfigurationSection cs = cfg.getConfigurationSection("Banks");
        if (cs != null) {
            for (String s : cs.getKeys(false)) {
                if (s != null) {
                    banks.add(s);
                }
            }
        }
        return banks;
    }

    @Override
    public boolean createPlayerAccount(String s) {
        if (!hasAccount(s)) {
            File file = new File(Main.getInstance().getDataFolder() + "/money", "eco.yml");
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            if (Bukkit.getServer().getOnlineMode()) {
                List<String> accounts = cfg.getStringList("accounts");
                accounts.add(Bukkit.getOfflinePlayer(s).getUniqueId().toString());
                cfg.set("accounts", accounts);
            } else {
                List<String> accounts = cfg.getStringList("accounts");
                accounts.add(Bukkit.getOfflinePlayer(s).getName());
                cfg.set("accounts", accounts);
            }
            try {
                cfg.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean createPlayerAccount(String s, String s1) {
        return createPlayerAccount(s);
    }


}
