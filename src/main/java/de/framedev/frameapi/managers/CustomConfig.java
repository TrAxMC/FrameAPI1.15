package de.framedev.frameapi.managers;

import de.framedev.frameapi.interfaces.CustomConfigInterface;
import de.framedev.frameapi.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationOptions;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

/*
 * =============================================
 * = This Plugin was Created by FrameDev       =
 * = Copyrighted by FrameDev                   =
 * = Please don't Change anything              =
 * =============================================
 * This Class was made at 24.05.2020, 01:15
 */
public class CustomConfig implements CustomConfigInterface {

    String fileName;
    File file = null;
    FileConfiguration cfg = null;

    public CustomConfig(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void createConfig() {
        file = new File(Main.getInstance().getDataFolder(), fileName + ".yml");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            Main.getInstance().saveResource(fileName+".yml", false);
        }

        cfg = (FileConfiguration) new YamlConfiguration();
        try {
            cfg.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveConfig() {
        try {
           cfg.save(file = new File(Main.getInstance().getDataFolder(),fileName+".yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void set(String path,Object object) {
        cfg.set(path,object);
    }

    public String getString(String path) {
        return cfg.getString(path);
    }

    public Integer getInt(String path) {
        return cfg.getInt(path);
    }

    public Double getDouble(String path) {
        return cfg.getDouble(path);
    }

    public Object get(String path) {
        return cfg.get(path);
    }
}
