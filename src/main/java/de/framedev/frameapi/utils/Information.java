package de.framedev.frameapi.utils;

import de.framedev.frameapi.main.Main;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Information {
    static File file = new File("plugins/FrameAPI/Permissions.yml");
    static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
    private static String version = "4.4.6";
    private static String Author = "FrameDev";
    private static String name = "FrameAPI";
    private static String apiversion = "1.13";


    public static String getVersion() {
        return version;
    }

    public static void setVersion(String version) {
        version = Main.getInstance().getDescription().getVersion();
        Information.version = version;
    }


    public static String getAuthor() {
        return Author;
    }


    public static String getName() {
        return name;
    }


    public static String getApiversion() {
        return apiversion;
    }

    public static void setInformationInConfig() {
        cfg.set("Name", name);
        cfg.set("Author", Author);
        cfg.set("Api-Version", apiversion);
        cfg.set("Version", version);
        ArrayList<String> perm = new ArrayList<>();
        perm.add("frameapi.*");
        perm.add("frameapi.startbudget");
        perm.add("frameapi.teleporter");
        perm.add("frameapi.backtp");
        perm.add("frameapi.spawnmob");
        perm.add("frameapi.denied");
        perm.add("frameapi.getitem");
        perm.add("frameapi.heal");
        perm.add("frameapi.mysql");
        perm.add("frameapi.set");
        perm.add("frameapi.balance.other");
        perm.add("frameapi.balance");
        perm.add("frameapi.set.other");
        perm.add("frameapi.signs.use");
        perm.add("frameapi.signs.create");
        perm.add("frameapi.bankbalance");
        perm.add("frameapi.bankbalance.other");
        perm.add("frameapi.setbank");
        perm.add("frameapi.setbank.other");
        perm.add("frameapi.transfer");
        perm.add("frameapi.withdraw");
        perm.add("frameapi.deposit");
        perm.add("frameapi.(kitname)");
        perm.add("frameapi.warp");
        perm.add("frameapi.setwarp");
        perm.add("frameapi.warpremove");
        perm.add("frameapi.admin");
        perm.add("frameapi.msg");
        perm.add("frameapi.update");
        perm.add("frameapi.fly");
        perm.add("frameapi.fly.other");
        perm.add("frameapi.afk");
        perm.add("frameapi.vanish");
        perm.add("frameapi.item");
        perm.add("frameapi.saveinventory");
        perm.add("frameapi.saveinventorynumber.multiple.(group)");
        perm.add("frameapi.gamemode");
        perm.add("frameapi.gamemode.other");
        perm.add("frameapi.godmode");
        perm.add("frameapi.addentity");
        perm.add("frameapi.tps");
        perm.add("frameapi.cleartps");
        perm.add("frameapi.balancetop");
        perm.add("frameapi.enderchest");
        perm.add("frameapi.enderchest.other");
        perm.add("frameapi.invsee");

        cfg.set("Permissions", perm);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            cfg.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}


