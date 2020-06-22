package de.framedev.frameapi.managers;

import de.framedev.frameapi.api.API;
import de.framedev.frameapi.main.Main;
import de.framedev.frameapi.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

public class FileManager {
    private static File customPlayersConfigFile;
    private static FileConfiguration customPlayersConfig;
    private static File customConfigFile;
    private static FileConfiguration customConfig;
    private static File informationFile;
    private static FileConfiguration informationCfg;
    private final Main plugin;
    public File locationFile = new File("plugins/FrameAPI/locations.yml");
    public FileConfiguration locationCfg = (FileConfiguration) YamlConfiguration.loadConfiguration(this.locationFile);

    public FileManager(Main plugin) {
        this.plugin = plugin;
    }


    public FileConfiguration getCustomPlayersConfig() {
        return customPlayersConfig;
    }


    public void createCustomPlayersConfig() {
        customPlayersConfigFile = new File(Main.getInstance().getDataFolder(), "players.yml");
        if (!customPlayersConfigFile.exists()) {
            customPlayersConfigFile.getParentFile().mkdirs();
            Main.getInstance().saveResource("messages.yml", false);
        }

        customPlayersConfig = (FileConfiguration) new YamlConfiguration();
        try {
            customPlayersConfig.load(customPlayersConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void savecustomPlayersConfig() {
        try {
            getCustomConfig().save(customPlayersConfigFile = new File(Main.getInstance().getDataFolder(), "players.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void reloadCustomPlayersConfig() throws UnsupportedEncodingException {
        if (getCustomPlayersConfig() == null) ;

        customPlayersConfig = (FileConfiguration) YamlConfiguration.loadConfiguration(customPlayersConfigFile);


        Reader defConfigStream = new InputStreamReader(Main.getInstance().getResource("players.yml"), "UTF8");
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            customPlayersConfig.setDefaults((Configuration) defConfig);
        }
    }


    public FileConfiguration getCustomMessagesConfig() {
        return customConfig;
    }


    public void createCustomMessagesConfig() {
        customConfigFile = new File(Main.getInstance().getDataFolder(), "messages.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            Main.getInstance().saveResource("messages.yml", false);
        }

        customConfig = (FileConfiguration) new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void savecustomMessagesConfig() {
        try {
            getCustomConfig().save(customConfigFile = new File(Main.getInstance().getDataFolder(), "messages.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void reloadCustomConfig() throws UnsupportedEncodingException {
        if (API.CreateConfig.getConfig() == null) ;

        CreateConfig.myConfig = (FileConfiguration) YamlConfiguration.loadConfiguration(CreateConfig.configFile);


        Reader defConfigStream = new InputStreamReader(Main.getInstance().getResource("messages.yml"), "UTF8");
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            CreateConfig.myConfig.setDefaults((Configuration) defConfig);
        }
    }

    public void loadLocationCfg() {
        try {
            this.locationCfg.load(this.locationFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InvalidConfigurationException ex) {
            ex.printStackTrace();
        }
    }


    public void saveCfgLocation() {
        try {
            this.locationCfg.save(this.locationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public FileConfiguration getCustomConfig() {
        return informationCfg;
    }


    public void createCustomConfig() {
        informationFile = new File(Main.getInstance().getDataFolder(), "Information.yml");
        if (!informationFile.exists()) {
            informationFile.getParentFile().mkdirs();
            Main.getInstance().saveResource("Information.yml", false);
        }

        informationCfg = (FileConfiguration) new YamlConfiguration();
        try {
            informationCfg.load(informationFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void saveCustomConfig() {
        try {
            informationCfg.save(informationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            informationCfg.load(informationFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }


    public static class CreateConfig {
        private static FileConfiguration myConfig;


        private static File configFile;


        private static boolean loaded = false;


        public static FileConfiguration getConfig() {
            try {
                if (!loaded) {
                    loadConfig();
                }
            } catch (NullPointerException ex) {
                Bukkit.getConsoleSender().sendMessage("Â§cNo Section Detected!");
            }
            return myConfig;
        }

        public static void saveConfig() {
            try {
                myConfig.save(configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public static File getConfigFile() {
            return configFile;
        }


        public static void loadConfig() {
            configFile = new File(Bukkit.getServer().getPluginManager().getPlugin("FrameAPI").getDataFolder(), "messages.yml");
            if (configFile.exists()) {
                new YamlConfiguration();
                myConfig = (FileConfiguration) YamlConfiguration.loadConfiguration(configFile);
                try {
                    myConfig.load(configFile);
                } catch (IOException | InvalidConfigurationException e1) {
                    e1.printStackTrace();
                }
                try {
                    if ((new File(Main.getInstance().getDataFolder() + "/messages.yml")).exists()) {
                        boolean changesMade = false;
                        YamlConfiguration tmp = new YamlConfiguration();
                        tmp.load(Main.getInstance().getDataFolder() + "/messages.yml");
                        for (String str : myConfig.getKeys(true)) {
                            if (!tmp.getKeys(true).contains(str)) {
                                tmp.set(str, myConfig.get(str));
                                changesMade = true;
                            }
                        }
                        if (changesMade) {
                            tmp.save(Main.getInstance().getDataFolder() + "/messages.yml");
                        }
                    }
                } catch (IOException | InvalidConfigurationException e) {
                    e.printStackTrace();
                }
                loaded = true;
            } else {
                try {
                    Bukkit.getServer().getPluginManager().getPlugin("FrameAPI").getDataFolder().mkdir();
                    InputStream jarURL = Config.class.getResourceAsStream("/messages.yml");
                    copyFile(jarURL, configFile);
                    new YamlConfiguration();
                    myConfig = (FileConfiguration) YamlConfiguration.loadConfiguration(configFile);
                    myConfig.save(configFile);
                    myConfig.options().copyDefaults(true);
                    Main.getInstance().getResource("messages.yml");
                    myConfig.load(configFile);
                    loaded = true;
                    try {
                        if ((new File(Main.getInstance().getDataFolder() + "/messages.yml")).exists()) {
                            boolean changesMade = false;
                            YamlConfiguration tmp = new YamlConfiguration();
                            tmp.load(Main.getInstance().getDataFolder() + "/messages.yml");
                            for (String str : myConfig.getKeys(true)) {
                                if (!tmp.getKeys(true).contains(str)) {
                                    tmp.set(str, myConfig.get(str));
                                    changesMade = true;
                                }
                            }
                            if (changesMade) {
                                tmp.save(Main.getInstance().getDataFolder() + "/messages.yml");
                            }
                        }
                    } catch (IOException | InvalidConfigurationException e) {
                        e.printStackTrace();
                    }
                    myConfig.load(configFile);
                } catch (Exception exception) {
                }
            }
        }


        public static void copyFile(InputStream in, File out) throws Exception {
            InputStream fis = in;
            FileOutputStream fos = new FileOutputStream(out);
            try {
                byte[] buf = new byte[1024];
                int i = 0;
                while ((i = fis.read(buf)) != -1) {
                    fos.write(buf, 0, i);
                }
            } catch (Exception e) {
                throw e;
            } finally {
                if (fis != null) {
                    fis.close();
                }
                if (fos != null) {
                    fos.close();
                }
            }
        }

        public static void onloadedfalse() {
            loaded = false;
        }
    }
}


