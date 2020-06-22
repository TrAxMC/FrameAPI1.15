package de.framedev.frameapi.kits;

import de.framedev.frameapi.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;


public class KitManager {
    private static File customConfigFile;
    private static FileConfiguration customConfig;
    public Inventory kitname = Bukkit.createInventory(null, 36);


    public static FileConfiguration getCustomConfig() {
        return customConfig;
    }


    public void createCustomConfig() {
        customConfigFile = new File(Main.getInstance().getDataFolder(), "kits.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            Main.getInstance().saveResource("kits.yml", false);
        }

        customConfig = (FileConfiguration) new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | org.bukkit.configuration.InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }


    public void loadKits(String name, Player player) {
        try {
            for (String s : getCustomConfig().getStringList("Items." + name)) {
                String[] x = s.split(",");
                ItemStack item = new ItemStack(Material.getMaterial(x[0]), Integer.valueOf(x[1]));
                this.kitname.addItem(item);
            }
            for (ItemStack items : this.kitname.getContents()) {
                if (items != null) {
                    player.getInventory().addItem(items);
                    clearKitInventory();
                } else {
                    return;
                }
            }
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage("§cNo Kit Found §f" + ex.getMessage());
        }
    }


    private void clearKitInventory() {
        this.kitname.clear();
    }
}


