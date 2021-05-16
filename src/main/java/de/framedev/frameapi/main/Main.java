package de.framedev.frameapi.main;

import de.framedev.frameapi.api.API;
import de.framedev.frameapi.api.SavePlayersInventory;
import de.framedev.frameapi.glass.Cocktail;
import de.framedev.frameapi.kits.KitManager;

import de.framedev.frameapi.listeners.money.GetMoneyInTime;
import de.framedev.frameapi.managers.*;
import de.framedev.frameapi.mongodb.BackendManager;
import de.framedev.frameapi.mongodb.MongoManager;
import de.framedev.frameapi.mysql.MYSQL;
import de.framedev.frameapi.mysql.SQL;
import de.framedev.frameapi.utils.Config;
import de.framedev.frameapi.utils.Init;
import de.framedev.frameapi.utils.Lags;
import de.framedev.frameapi.utils.ReplaceCharConfig;
import de.framedev.frameapi.warps.WarpSigns;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class Main extends JavaPlugin implements Listener {

    private static final int resource = 68558;
    static File file = new File("plugins/FrameAPI/entitys.yml");
    private static Main mi;
    private static final API api = new API();
    private static final KitManager kit = new KitManager();
    private static String noperm;
    private static String prefix;
    public ArrayList<String> pays = new ArrayList<>();
    public int j = 0;
    public Integer animationCount;
    FileConfiguration cfg = (FileConfiguration) YamlConfiguration.loadConfiguration(file);
    ArrayList<String> entitys = new ArrayList<>();
    private MongoManager mongoManager;
    private BackendManager backendManager;
    private String namethis = "";
    public static File filem = new File("plugins/MDBConnection/config.yml");
    public static FileConfiguration cfgm = (FileConfiguration) YamlConfiguration.loadConfiguration(filem);
    private boolean mongodb;
    private VaultManager vaultManager;


    public void onEnable() {
        mi = this;
        kit.createCustomConfig();
        api.createCustomConfig();
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        Config.loadConfig();
        Config.updateConfig();
        new MultiWorldManager().createWorldifNotExists();
        if (getConfig().getBoolean("MYSQL.Boolean")) {
            try {
                MYSQL.connect();
                SQL.createTable("money", "PlayerName TEXT(64)", "balance_money DOUBLE", "bankmoney DOUBLE", "Registered TEXT");
            } catch (NullPointerException ex) {
                Bukkit.getConsoleSender().sendMessage("§cPlease edit Config.yml :: MySQL Section!!!");
            }
        }
        if (getConfig().getBoolean("BungeeCord")) {
            this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
            this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new ServerManager());
        }
        if (Bukkit.getPluginManager().getPlugin("MDBConnection") != null) {
            if (cfgm.getBoolean("MongoDB.LocalHost")) {
                this.mongoManager = new MongoManager();
                this.mongoManager.connectLocalHost();
                mongodb = true;
            }
            if (cfgm.getBoolean("MongoDB.Boolean")) {
                this.mongoManager = new MongoManager();
                this.mongoManager.connect();
                mongodb = true;
            }
            if (cfgm.getBoolean("MongoDB.LocalHost")) {
                this.backendManager = new BackendManager(this);
            }
            if (cfgm.getBoolean("MongoDB.Boolean")) {
                this.backendManager = new BackendManager(this);
            }
        }
        if (getConfig().getString("language").equalsIgnoreCase("en_EN")) {
            if (getInstance().getConfig().getBoolean("NoNight")) {
                api.NoNight();
            } else {
                Bukkit.getConsoleSender().sendMessage(Variables.getPrefix() + " §cNo Night Disabled!!");
            }
            if (getConfig().getBoolean("MessageOfReload.Boolean")) {
                api.sendMessageofReload();
            }
            if (getConfig().getBoolean("MYSQL.Boolean")) {
                SQL.createTable("pays", "PlayerTo TEXT(11)", "payAmount INT", " PlayerFrom TEXT");
                SQL.createTable("offline", "PlayerName TEXT(11)", "boolean TEXT");
            } else {
                Bukkit.getConsoleSender().sendMessage(Variables.getPrefix() + " §cNo MySQL not Activated, §aFuctions §care Disabled");
            }
            api.onUpdate();
        } else if (getConfig().getString("language").equalsIgnoreCase("de_DE")) {
            if (getInstance().getConfig().getBoolean("NoNight")) {
                api.NoNight();
            } else {
                Bukkit.getConsoleSender().sendMessage(Variables.getPrefix() + " §cNo Night ausgeschaltet!");
            }
            if (getConfig().getBoolean("MessageOfReload.Boolean")) {
                api.sendMessageofReload();
            }
            if (getConfig().getBoolean("MYSQL.Boolean")) {
                if (WarpSigns.UtilinConfigList("Bank")) {

                }
                SQL.createTable("pays", "PlayerTo TEXT(11)", "payAmount INT", " PlayerFrom TEXT");
                SQL.createTable("offline", "PlayerName TEXT(11)", "boolean TEXT");
            } else {
                Bukkit.getConsoleSender().sendMessage(Variables.getPrefix() + " §cMYSQL ist nicht aktiviert");
            }
            api.onUpdate();
        } else {
            Bukkit.getConsoleSender().sendMessage(Variables.getPrefix() + " §4Please use en_EN or de_DE!!!");
        }
        Bukkit.getConsoleSender().sendMessage(Variables.getPrefix() + " §c§lEnergy is Work in Progress!");
        Thread thread = new Thread(new SchedulerManager());
        new BukkitRunnable() {
            @Override
            public void run() {
                thread.start();
            }
        }.runTaskLater(this, 320);
        api.init();
        new Init(this);
        if(Bukkit.getPluginManager().getPlugin("Vault") != null) {
            setup();
        }
        Bukkit.getConsoleSender().sendMessage(Variables.getPrefix() + " §6Successfully Loaded!!!");
    }

    public boolean isMongoDb() {
        return mongodb;
    }

    public boolean isMysql() {
        return getConfig().getBoolean("MYSQL.Boolean");
    }

    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(Variables.getPrefix() + " §aDeactivated!");
        api.onUpdate();
        if (getConfig().getBoolean("MYSQL.Boolean") &&
                MYSQL.con != null) {
            MYSQL.close();
            Bukkit.getConsoleSender().sendMessage(Variables.getPrefix() + " " + MYSQL.MySQLPrefix + " §bMYSQL Closed!");
        }
    }

    public static Main getInstance() {
        return mi;
    }

    public static void updateCheckerPlayer(Player player) {
        player.sendMessage(Variables.getPrefix() + " Checking for updates...");
        try {
            URLConnection conn = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + resource).openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String oldVersion = Main.getInstance().getDescription().getVersion();
            String newVersion = br.readLine();
            if (!newVersion.equalsIgnoreCase(oldVersion)) {
                player.sendMessage(Variables.getPrefix() + " A new update is available: version " + newVersion);
                TextComponent tc = new TextComponent();
                tc.setText(Variables.getPrefix() + "§aDonation §c: §6[§bKlick Hier für die Seite zu öffnen§6]");
                tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(" öffne Download Seite").create()));
                tc.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/resources/frameapi.68558/"));
                player.spigot().sendMessage(tc);
            } else {
                player.sendMessage(Variables.getPrefix() + " You're running the newest plugin version!");
            }
        } catch (IOException e) {
            player.sendMessage(Variables.getPrefix() + " Failed to check for updates on spigotmc.org");
        }
    }

    public static void dispatch(final CommandSender sender, final String command) {
        try {
            boolean bool = (Boolean) Bukkit.getScheduler().callSyncMethod((Plugin) getInstance(), new Callable<Boolean>() {
                public Boolean call() {
                    return Bukkit.dispatchCommand(sender, command);
                }
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public MongoManager getMongoManager() {
        return this.mongoManager;
    }

    public BackendManager getBackendManager() {
        return this.backendManager;
    }


    public Runnable savePlayersInventory() {
        return () -> new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(o -> {
                    new SavePlayersInventory().savePlayerInventory(o);
                });
            }
        }.runTaskTimer(Main.getInstance(), 0, 1200);
    }

    public String getNameThis() {
        this.namethis = getConfig().getString("Tablist.Header");
        return this.namethis;
    }

    public void setname(String namethis) {
        namethis = getConfig().getString("Tablist.Header");
        this.namethis = namethis;
    }

    public ArrayList<OfflinePlayer> getPlayers() {
        ArrayList<OfflinePlayer> playertop = new ArrayList<>();
        if (getConfig().getBoolean("MYSQL.Boolean")) {
            try {
                Statement statement = MYSQL.getConnection().createStatement();
                ResultSet res = statement.executeQuery("SELECT * FROM offline WHERE boolean = 'yes';");
                int spalten = res.getMetaData().getColumnCount();
                while (res.next()) {
                    String[] str = new String[8];
                    for (int k = 1; k <= spalten; k++) {
                        str[k - 1] = res.getString(k);
                        OfflinePlayer p = Bukkit.getOfflinePlayer(res.getString(k));
                        if (!playertop.contains(p)) {
                            playertop.add(p);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return playertop;
    }

    @EventHandler
    public void onColorChatMessage(AsyncPlayerChatEvent e) {
        String msg = e.getMessage();
        msg = msg.replace('&', '§');
        e.setMessage(msg);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("getcocktail")) {
            Cocktail cocktail = new Cocktail(100, 100, true);
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.getInventory().addItem(cocktail.build());
            }
        }
        if (command.getName().equalsIgnoreCase("getwheat")) {
            if (sender instanceof BlockCommandSender) {
                Block player = ((BlockCommandSender) sender).getBlock();
                new CropsManager().getWheat(player.getWorld(), player.getLocation().getBlockX() + 10, player.getLocation().getBlockX() - 10, player.getLocation().getBlockY() + 10, player.getLocation().getBlockY() - 10, player.getLocation().getBlockZ() + 10, player.getLocation().getBlockZ() - 10);
            }
        }
        if (command.getName().equalsIgnoreCase("setwheat")) {
            if (sender instanceof BlockCommandSender) {
                Block player = ((BlockCommandSender) sender).getBlock();
                new CropsManager().setWheat(player.getWorld(), player.getLocation().getBlockX() + 10, player.getLocation().getBlockX() - 10, player.getLocation().getBlockY() + 10, player.getLocation().getBlockY() - 10, player.getLocation().getBlockZ() + 10, player.getLocation().getBlockZ() - 10);
            }
        }
        if (command.getName().equalsIgnoreCase("getcarrot")) {
            if (sender instanceof BlockCommandSender) {
                Block player = ((BlockCommandSender) sender).getBlock();
                new CropsManager().getCarrot(player.getWorld(), player.getLocation().getBlockX() + 10, player.getLocation().getBlockX() - 10, player.getLocation().getBlockY() + 10, player.getLocation().getBlockY() - 10, player.getLocation().getBlockZ() + 10, player.getLocation().getBlockZ() - 10);
            }
        }
        if (command.getName().equalsIgnoreCase("setcarrot")) {
            if (sender instanceof BlockCommandSender) {
                Block player = ((BlockCommandSender) sender).getBlock();
                new CropsManager().setCarrot(player.getWorld(), player.getLocation().getBlockX() + 10, player.getLocation().getBlockX() - 10, player.getLocation().getBlockY() + 10, player.getLocation().getBlockY() - 10, player.getLocation().getBlockZ() + 10, player.getLocation().getBlockZ() - 10);
            }
        }
        if (command.getName().equalsIgnoreCase("getpotato")) {
            if (sender instanceof BlockCommandSender) {
                Block player = ((BlockCommandSender) sender).getBlock();
                new CropsManager().getPotato(player.getWorld(), player.getLocation().getBlockX() + 10, player.getLocation().getBlockX() - 10, player.getLocation().getBlockY() + 10, player.getLocation().getBlockY() - 10, player.getLocation().getBlockZ() + 10, player.getLocation().getBlockZ() - 10);
            }
        }
        if (command.getName().equalsIgnoreCase("setpotato")) {
            if (sender instanceof BlockCommandSender) {
                Block player = ((BlockCommandSender) sender).getBlock();
                new CropsManager().setPotato(player.getWorld(), player.getLocation().getBlockX() + 10, player.getLocation().getBlockX() - 10, player.getLocation().getBlockY() + 10, player.getLocation().getBlockY() - 10, player.getLocation().getBlockZ() + 10, player.getLocation().getBlockZ() - 10);
            }
        }
        if (command.getName().equalsIgnoreCase("setsugarcane")) {
            if (sender instanceof BlockCommandSender) {
                Block player = ((BlockCommandSender) sender).getBlock();
                new CropsManager().setSugarCane(player.getWorld(), player.getLocation().getBlockX() + 10, player.getLocation().getBlockX() - 10, player.getLocation().getBlockY() + 10, player.getLocation().getBlockY() - 10, player.getLocation().getBlockZ() + 10, player.getLocation().getBlockZ() - 10);
            }
        }
        if (command.getName().equalsIgnoreCase("getsugarcane")) {
            if (sender instanceof BlockCommandSender) {
                Block player = ((BlockCommandSender) sender).getBlock();
                new CropsManager().getSugarCane(player.getWorld(), player.getLocation().getBlockX() + 10, player.getLocation().getBlockX() - 10, player.getLocation().getBlockY() + 10, player.getLocation().getBlockY() - 10, player.getLocation().getBlockZ() + 10, player.getLocation().getBlockZ() - 10);
            }
        }
        if (command.getName().equalsIgnoreCase("tps")) {
            if (sender.hasPermission("frameapi.tps")) {
                double TPS = Lags.getTPS();
                DecimalFormat TpsFormat = new DecimalFormat("#.##");
                if (TPS > 20.0D) {
                    sender.sendMessage(ChatColor.DARK_GREEN + "TPS : " + TpsFormat.format(TPS));
                    return true;
                }
                if (TPS > 19.0D) {
                    sender.sendMessage(ChatColor.GREEN + "TPS : " + TpsFormat.format(TPS));
                    return true;
                }
                if (TPS > 14.0D) {
                    sender.sendMessage(ChatColor.YELLOW + "TPS : " + TpsFormat.format(TPS));
                    return true;
                }
                if (TPS > 9.0D) {
                    sender.sendMessage(ChatColor.RED + "TPS : " + TpsFormat.format(TPS));
                    return true;
                }
                if (TPS < 9.0D) {
                    sender.sendMessage(ChatColor.DARK_RED + "TPS : " + TpsFormat.format(TPS));
                    return true;
                }
            }
        } else if (command.getName().equalsIgnoreCase("cleartps") && sender.hasPermission("frameapi.cleartps")) {
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) this, new Runnable() {
                public void run() {
                    for (World world : Bukkit.getWorlds()) {
                        for (Chunk chunk : world.getLoadedChunks()) {
                            for (Entity ent : chunk.getEntities()) {
                                if (!(ent instanceof Player)) {
                                    Main.this.entitys = (ArrayList<String>) Main.this.cfg.getStringList("Entitys");
                                    if (!Main.this.entitys.contains(ent.getType().name())) {
                                        ent.remove();
                                    }
                                }
                            }
                        }
                    }
                    Bukkit.broadcastMessage(Variables.getPrefix() + " §cAll Entitys Deleted!");
                }
            }, 60L);
        }

        if (command.getName().equalsIgnoreCase("addentity")) {
            if (sender.hasPermission("frameapi.addentity")) {
                if (args.length == 1) {
                    String ent = args[0];
                    this.entitys = (ArrayList<String>) this.cfg.getStringList("Entitys");
                    if (this.entitys.contains(EntityType.fromName(ent).name())) {
                        this.entitys.remove(EntityType.fromName(ent).name());
                        this.cfg.set("Entitys", this.entitys);
                        try {
                            this.cfg.save(file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        sender.sendMessage("§a" + EntityType.fromName(ent).name() + " §bremoved");
                    } else {
                        this.entitys.add(EntityType.fromName(ent).name());
                        this.cfg.set("Entitys", this.entitys);
                        try {
                            this.cfg.save(file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (!file.exists()) {
                            try {
                                file.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        sender.sendMessage("§a" + EntityType.fromName(ent).name() + " §bAdded");
                    }
                } else {
                    sender.sendMessage("§cPlease use §b/addentity (entitytype)");
                }
            } else {
                sender.sendMessage(Variables.getPrefix() + " " + Variables.getNoPerm());
            }
        }
        if (command.getName().equalsIgnoreCase("health") && sender instanceof Player) {
            GetMoneyInTime timeMoney = new GetMoneyInTime(getInstance());
            Player p = (Player) sender;
            if (p.getHealth() <= 6.0D) {
                p.setMaxHealth(20.0D);
                p.sendTitle("§aAlle Herzen erstellt", "");
                Bukkit.getScheduler().cancelTasks((Plugin) this);
                api.onUpdate();
                timeMoney.getMoneyinTime();
            } else {
                runTask(p);
                p.setMaxHealth(6.0D);
                p.sendTitle("§aDrei Herzen erstellt", "");
            }
        }

        if (command.getName().equalsIgnoreCase("sethealth") && sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 1) {
                double x = Double.parseDouble(args[0]);
                p.setMaxHealth(x * 2.0D);
                return true;
            }
            if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[0]);
                double x = Double.parseDouble(args[1]);
                if (target != null) {
                    target.setMaxHealth(x * 2.0D);
                    p.sendMessage(args[0] + " §a sind seine herzen nun auf " + x);
                } else {
                    p.sendMessage(args[0] + " §aist nicht Online");
                }
            }
        }
        return super.onCommand(sender, command, label, args);
    }

    public void runTask(final Player p) {
        Bukkit.getScheduler().runTaskTimer((Plugin) this, new Runnable() {
            public void run() {
                if (p.getLevel() == 3) {
                    p.setMaxHealth(7.0D);
                } else if (p.getLevel() == 6) {
                    p.setMaxHealth(8.0D);
                } else if (p.getLevel() == 8) {
                    p.setMaxHealth(10.0D);
                } else if (p.getLevel() == 10) {
                    p.setMaxHealth(12.0D);
                } else if (p.getLevel() == 12) {
                    p.setMaxHealth(14.0D);
                } else if (p.getLevel() == 14) {
                    p.setMaxHealth(16.0D);
                } else if (p.getLevel() == 16) {
                    p.setMaxHealth(18.0D);
                } else if (p.getLevel() == 18) {
                    p.setMaxHealth(20.0D);
                } else if (p.getLevel() == 20) {
                    p.setMaxHealth(22.0D);
                } else if (p.getLevel() == 22) {
                    p.setMaxHealth(24.0D);
                }
            }
        }, 0L, 20L);
    }

    public void checkUpdate() {
        Bukkit.getConsoleSender().sendMessage(Variables.getPrefix() + " Checking for updates...");
        try {
            URLConnection conn = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + resource).openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String oldVersion = Main.getInstance().getDescription().getVersion();
            String newVersion = br.readLine();
            if (!newVersion.equalsIgnoreCase(oldVersion)) {
                Bukkit.getConsoleSender().sendMessage(Variables.getPrefix() + " A new update is available: version " + newVersion);
            } else {
                Bukkit.getConsoleSender().sendMessage(Variables.getPrefix() + " You're running the newest plugin version!");
            }
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage(Variables.getPrefix() + " Failed to check for updates on spigotmc.org");
        }
    }

    public boolean deleteWorld(File path) {

        if (path.exists()) {
            File[] files = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteWorld(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return path.delete();
    }

    public void setup() {
        if(getServer().getPluginManager().getPlugin("Vault") != null)
            this.vaultManager = new VaultManager(this);
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("addentity")) {
            ArrayList<String> type = new ArrayList<>();
            for (EntityType types : EntityType.values()) {
                type.add(types.name());
            }
            return type;
        }
        return super.onTabComplete(sender, command, alias, args);
    }

    public static class Variables {
        public static String getPrefix() {
            Main.prefix = Main.getInstance().getConfig().getString("Prefix");
            Main.prefix = Main.prefix.replace('&', '§');
            return Main.prefix;
        }

        public static void setPrefix(String prefix) {
            prefix = Main.getInstance().getConfig().getString("Prefix");
            prefix = prefix.replace('&', '§');
            Main.noperm = Main.noperm.replace('\"', '\'');
            Main.prefix = prefix;

        }

        public static String getNoPerm() {
            Main.noperm = Main.getInstance().getConfig().getString("NoPerm");
            Main.noperm = ReplaceCharConfig.replaceParagraph(Main.noperm);
            Main.noperm = Main.noperm.replace('\"', '\'');
            return Main.noperm;
        }

        public static void setNoPerm(String noperm) {
            noperm = Main.getInstance().getConfig().getString("NoPerm");
            noperm = ReplaceCharConfig.replaceParagraph(noperm);
            Main.noperm = noperm;
        }
    }

    public String getPrefix() {
        return Main.Variables.getPrefix();
    }

    private void setPrefix(String prefix) {
        prefix = Main.getInstance().getConfig().getString("Prefix");
        prefix = prefix.replace('&', '§');
        Main.noperm = Main.noperm.replace('\"', '\'');
        Main.prefix = prefix;
    }

    public String getNoPerm() {
        return Main.Variables.getNoPerm();
    }

    private void setNoPerm(String noperm) {
        noperm = Main.getInstance().getConfig().getString("NoPerm");
        noperm = ReplaceCharConfig.replaceParagraph(noperm);
        Main.noperm = noperm;
    }

    public VaultManager getVaultManager() {
        return vaultManager;
    }
}