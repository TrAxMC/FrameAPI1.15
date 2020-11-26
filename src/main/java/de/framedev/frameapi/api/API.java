package de.framedev.frameapi.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Difficulty;
import org.bukkit.FireworkEffect;
import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.SkullType;
import org.bukkit.Statistic;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import de.framedev.frameapi.api.money.Money;
import de.framedev.frameapi.enums.MaterialInID;
import de.framedev.frameapi.kits.KitManager;
import de.framedev.frameapi.main.Main;
import de.framedev.frameapi.managers.FileManager;
import de.framedev.frameapi.managers.InventoryManager;
import de.framedev.frameapi.managers.ItemBuilder;
import de.framedev.frameapi.mysql.IsTableExist;
import de.framedev.frameapi.mysql.MYSQL;
import de.framedev.frameapi.mysql.SQL;
import de.framedev.frameapi.pets.Pets;
import de.framedev.frameapi.utils.Config;
import de.framedev.frameapi.utils.Information;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;


@SuppressWarnings("deprecation")
public class API implements Listener {
    private static HashMap<String, Location> oldloc = new HashMap<>();
    private static String version = Information.getVersion();
    private static String Author = Information.getAuthor();
    private static String name = Information.getName();
    private static String apiversion = Information.getApiversion();
    private static File file = new File("plugins/FrameAPI/locations.yml");
    private static FileConfiguration cfg = (FileConfiguration) YamlConfiguration.loadConfiguration(file);
    private static FileConfiguration cfglocation;
    private static File filelocation;
    private static String messageOfreload;
    private static Money eco = new Money();
    private static Inventory inv = Bukkit.createInventory(null, 27, "§aTeleporter");

    public API() {

    }

    public static HashMap<String, Location> getOldloc() {
        return oldloc;
    }

    static {
        for (Player all : Bukkit.getOnlinePlayers()) {
            ItemStack skull = ItemStackSkull(all.getName());
            inv.addItem(new ItemStack[]{skull});
            inv.setItem(26, (new ItemBuilder(Material.BLUE_STAINED_GLASS_PANE)).setDisplayName("§aNext Page").build());
        }
    }

    private ArrayList<String> hide = new ArrayList<>();
    private SimpleDateFormat date2 = new SimpleDateFormat("HH.mm.ss");
    private String Uhrzeit2 = this.date2.format(new Date());
    private File deniedFile = new File("plugins/FrameAPI", "DeniedWords.yml");
    private FileConfiguration deniedcfg = (FileConfiguration) YamlConfiguration.loadConfiguration(this.deniedFile);
    private File fileplayer = new File("plugins/FrameAPI/playerlist.yml");
    private FileConfiguration cfgplayer = (FileConfiguration) YamlConfiguration.loadConfiguration(this.fileplayer);
    private ArrayList<String> names = new ArrayList<>();


    @Deprecated
    public static ItemStack ItemStackSkull(String name) {
        ItemStack skull = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());

        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setDisplayName("§a" + name);
        skull.setItemMeta((ItemMeta) meta);
        return skull;
    }

    public static ItemStack createItemBuilder(Material material, int amount, String displayname, boolean unbreakable) {
        return (new ItemBuilder(material)).setAmount(amount).setDisplayName(displayname).setUnbreakable(unbreakable).build();
    }


    public void onUpdate() {
        Bukkit.getConsoleSender().sendMessage(Main.FrameMainGet.getPrefix() + "§a version 4.4.6 = §bBug Fixes, §aMoney Fix");
        Bukkit.getConsoleSender().sendMessage(Main.FrameMainGet.getPrefix() + " §aThis Plugin was written by §bFrameDev");
    }

    public void init() {
        StartUp(Main.getInstance());
    }


    public void getInformation(Player p) {
        ArrayList<String> string = new ArrayList<>();
        string.add(0, Main.FrameMainGet.getPrefix() + " §bPlugin Name = §a" + name);
        string.add(1, Main.FrameMainGet.getPrefix() + " §bAuthor = §a" + Author);
        string.add(2, Main.FrameMainGet.getPrefix() + " §bVersion = §a" + version);
        string.add(3, Main.FrameMainGet.getPrefix() + " §bApi Version = §a" + apiversion);
        for (String s : string) {
            p.sendMessage(s);
        }
        ArrayList<String> issues = new ArrayList<>();
        issues.add("§cEnergy is Work in progress");
        p.sendMessage(issues.get(0));
    }


    public void InformationStartup() {
        ArrayList<String> string = new ArrayList<>();
        string.add(0, Main.FrameMainGet.getPrefix() + " §bPlugin Name = §a" + name);
        string.add(1, Main.FrameMainGet.getPrefix() + " §bAuthor = §a" + Author);
        string.add(2, Main.FrameMainGet.getPrefix() + " §bVersion = §a" + version);
        string.add(3, Main.FrameMainGet.getPrefix() + " §bApi Version = §a" + apiversion);
        ArrayList<String> issues = new ArrayList<>();
        issues.add("§cEnergy is Work in progress");
        for (String s : string) {
            Bukkit.getConsoleSender().sendMessage(s);
        }
        Bukkit.getConsoleSender().sendMessage(issues.get(0));
    }


    public void StartUp(Main plugin) {
        if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("en_EN")) {
            Bukkit.getConsoleSender().sendMessage(Main.FrameMainGet.getPrefix() + " §4Attention this Plugin work's Only in this Versions 1.13 - 1.15!");
            Bukkit.getConsoleSender().sendMessage(Main.FrameMainGet.getPrefix() + " §4if you want a specific version then write me in the forum!");
            Bukkit.getConsoleSender().sendMessage("§b==========================================");
            Bukkit.getConsoleSender().sendMessage("§b                                          ");
            Bukkit.getConsoleSender().sendMessage("§b=         §a[§aFrameAPI]§b                     =");
            Bukkit.getConsoleSender().sendMessage("§b                                          ");
            Bukkit.getConsoleSender().sendMessage("§b                                          ");
            Bukkit.getConsoleSender().sendMessage("§b==========================================");
            ConfigStartup();
            Bukkit.getConsoleSender().sendMessage(Main.FrameMainGet.getPrefix() + " §bLoaded");
        } else if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("de_DE")) {
            Bukkit.getConsoleSender().sendMessage(Main.FrameMainGet.getPrefix() + " §4Achtung dieses Plugin funktioniert nur in den Versionen 1.13 - 1.15");
            Bukkit.getConsoleSender().sendMessage(Main.FrameMainGet.getPrefix() + " §4Wenn du eine eigene Version haben moechtest Kontaktiere mich im Forum");
            Bukkit.getConsoleSender().sendMessage("§b==========================================");
            Bukkit.getConsoleSender().sendMessage("§b                                          ");
            Bukkit.getConsoleSender().sendMessage("§b=         §a[§aFrameAPI]§b                     =");
            Bukkit.getConsoleSender().sendMessage("§b                                          ");
            Bukkit.getConsoleSender().sendMessage("§b                                          ");
            Bukkit.getConsoleSender().sendMessage("§b==========================================");
            ConfigStartup();
            Bukkit.getConsoleSender().sendMessage(Main.FrameMainGet.getPrefix() + " §bgeladen");
        }
    }


    public void sendMessageofReload() {
        Bukkit.getOnlinePlayers().forEach(current -> {
            try {
                messageOfreload = Main.getInstance().getConfig().getString("MessageOfReload.Text");
                messageOfreload = messageOfreload.replace("[Player]", current.getName());
                messageOfreload = messageOfreload.replace('&', '§');
                current.sendMessage(messageOfreload);
            } catch (NullPointerException ex) {
                Bukkit.getConsoleSender().sendMessage("§c§lNo Text Found in MessagesOfReload!");
                Config.updateConfig();
                Config.loadConfig();
            }
        });
    }

    @EventHandler
    public void onClickPlayerSetPassenger(PlayerInteractAtEntityEvent e) {
        if (Main.getInstance().getConfig().getBoolean("SetPlayerPassenger") &&
                e.getRightClicked() instanceof Player) {
            Player target = (Player) e.getRightClicked();
            Player p = e.getPlayer();
            p.setPassenger(target);
        }
    }


    public ItemStack getWrittenBook(Player p) {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) book.getItemMeta();
        meta.setAuthor(Author);
        meta.setDisplayName("§aFrameAPI Book Info");
        try {
            if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("en_EN")) {
                meta.addPage("§aWelcome §b" + p.getName() + "\n§aThis Book was Written by §b\n" + Author + "\n§aif you have any Questions contact me in the Forum");
                book.setItemMeta((ItemMeta) meta);
                return book;
            }
            if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("de_DE")) {
                meta.addPage("§aWillkommen §b" + p.getName() + "\n§aDieses Buch wurde geschrieben von §b\n" + Author + "\n§aWenn du Fragen hast Kontaktiere mich im Forum!");
                book.setItemMeta((ItemMeta) meta);
                return book;
            }
        } catch (NullPointerException ex) {
            Bukkit.getConsoleSender().sendMessage("§cAn issue while creating Book, wrong Language §f" + ex.getMessage());
        }
        return book;
    }


    public Integer getAllOnlinePlayersInInt() {
        return Integer.valueOf(Bukkit.getOnlinePlayers().size());
    }

    public void addAllPlayerMoney(double money) {
        Bukkit.getOnlinePlayers().forEach(current -> eco.addMoney((OfflinePlayer) current, money));
    }

    public ItemStack createWrittenBook(String Displayname, String... pages) {
        ItemStack item = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Displayname));
        for (String page : pages) {
            meta.addPage(page);
        }

        item.setItemMeta(meta);
        return item;
    }

    @Deprecated
    public void getSkull(String name, Player p) {
        ItemStack skull = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner(name);
        meta.setDisplayName(ChatColor.GREEN + name + "'s Head!");
        skull.setItemMeta(meta);
        p.getInventory().addItem(skull);
    }

    public void SetTime(long time) {
        for (World world : Bukkit.getWorlds()) {
            world.setTime(time);
        }
    }

    public void SetTimeMessage(long time, Player p) {
        for (World world : Bukkit.getWorlds()) {
            world.setTime(time);
            if (Main.getInstance().getConfig().getBoolean("SetTime.Message")) {
                String msg = Main.getInstance().getConfig().getString("SetTime.Text");
                msg = msg.replace("[Time]", time + "");
                msg = msg.replace('&', '§');
                p.sendMessage(msg);
            }
        }
    }

    public void shootArrow(Player shooter, Player target) {
        if (target != null) {
            if (target.isOnline()) {
                target.launchProjectile(Arrow.class);
            } else {
                shooter.sendMessage(target.getDisplayName() + " i'snt Online");
            }
        } else {
            shooter.sendMessage("§cThis Player is not exists!");
        }
    }

    public void CreateFireWorktoSpawn(Player player, FireworkEffect.Type type, boolean flicker, Color color, int power) {
        Firework firework = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);


        FireworkEffect eff = FireworkEffect.builder().with(type).flicker(flicker).withColor(color).build();
        FireworkMeta meta = firework.getFireworkMeta();
        meta.addEffect(eff);
        meta.setPower(power);
        firework.setFireworkMeta(meta);
    }

    @Deprecated
    public void HidePlayers(Player p) {
        for (Player players : Bukkit.getOnlinePlayers()) {
            if (this.hide.contains(p.getName())) {
                p.showPlayer(players);
                this.hide.remove(p.getName());
                continue;
            }
            this.hide.add(p.getName());
            p.hidePlayer(players);
        }
    }

    private String getDate() {
        return this.Uhrzeit2;
    }

    public void sendHelp(Player p) {
        if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("en_EN")) {
            if (p.hasPermission("frameapi.help")) {
                p.sendMessage("§6Please use  /frameapi time || /frameapi getmysql");
                p.sendMessage("§6or use /frameapi startbudget || /frameapi info ");
                p.sendMessage("§6or use /frameapi help || /frameapi reload");
                p.sendMessage("§6or use /back");
                p.sendMessage("§6or use /spawnmob (entitytype)|/spawnmob (entitytype) count");
                p.sendMessage("§6 use /getitem itemname name lore unbreakable true/false");
                p.sendMessage("§6 use /heal or /heal (PlayerName)");
                p.sendMessage("§6 use /denied add (word)|/denied remove (word)");
            }
        } else if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("de_DE") &&
                p.hasPermission("frameapi.help")) {
            p.sendMessage("§6Bitte Benutze  /frameapi time || /frameapi getmysql");
            p.sendMessage("§6oder /frameapi startbudget || /frameapi info ");
            p.sendMessage("§6oder /frameapi help || /frameapi reload");
            p.sendMessage("§6oder /back");
            p.sendMessage("§6oder /spawnmob (entitytype)|/spawnmob (entitytype) count");
            p.sendMessage("§6oder /getitem itemname name lore unbreakable true/false");
            p.sendMessage("§6oder /heal or /heal (PlayerName)");
            p.sendMessage("§6oder /denied add (word)|/denied remove (word)");
        }
    }

    public void setOP(Player p, boolean op) {
        p.setOp(op);
    }

    public String playerNames() {
        String names1 = "";
        for (Player all : Bukkit.getOnlinePlayers()) {
            String names = all.getDisplayName();
            names = names + names1;
        }
        return names1;
    }

    public void openEnderChest(Player player) {
        player.openInventory(player.getEnderChest());
    }

    public void openOtherEnderChest(Player player, Player target) {
        player.openInventory(target.getEnderChest());
    }

    public void openWorkBench(Player player) {
        player.openWorkbench(player.getLocation(), true);
    }

    public void openInventoryOther(Player player, Player target) {
        player.openInventory((Inventory) target.getInventory());
    }

    @Deprecated
    public void SetPassengetToTarget(Player target, Entity entity) {
        target.setPassenger(entity);
    }

    public void godMode(Player player, String message, boolean godmode) {
        player.setInvulnerable(godmode);
        player.sendMessage(message);
    }

    public void savePlayerlocation(Player player) {
        Location loc = player.getLocation();
        filelocation = new File("plugins/FrameAPI/PlayerLocations.yml");
        cfglocation = (FileConfiguration) YamlConfiguration.loadConfiguration(filelocation);
        cfglocation.set(player.getName() + ".world", loc.getWorld().getName());
        cfglocation.set(player.getName() + ".x", Double.valueOf(loc.getX()));
        cfglocation.set(player.getName() + ".y", Double.valueOf(loc.getY()));
        cfglocation.set(player.getName() + ".z", Double.valueOf(loc.getZ()));
        cfglocation.set(player.getName() + ".yaw", Float.valueOf(loc.getYaw()));
        cfglocation.set(player.getName() + ".pitch", Float.valueOf(loc.getPitch()));
        saveCfgLocationplayer();
    }

    private void saveCfgLocationplayer() {
        try {
            cfglocation.save(filelocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Location getPlayerLocation(Player player) {
        try {
            World world = Bukkit.getWorld(cfglocation.getString(player.getName() + ".world"));
            double x = cfglocation.getDouble(player.getName() + ".x");
            double y = cfglocation.getDouble(player.getName() + ".y");
            double z = cfglocation.getDouble(player.getName() + ".z");
            int yaw = cfglocation.getInt(player.getName() + ".yaw");
            int pitch = cfglocation.getInt(player.getName() + ".pitch");
            Location loc = new Location(world, x, y, z, yaw, pitch);
            return loc;
        } catch (IllegalArgumentException ex) {
            Bukkit.getConsoleSender().sendMessage("§cThis Location doesn't exists! " + ex.getMessage());
            player.sendMessage("§cThis Location doesn't exists! " + ex.getMessage());

            return null;
        }
    }

    @SuppressWarnings("unused")
	private void giveDateinChat(Player p) {
        if (p.isOnline()) {
            p.sendMessage("Date = " + getDate());
        }
    }

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent e) {
        Player p = e.getEntity();
        oldloc.put(p.getName(), p.getLocation());
        if (Main.getInstance().getConfig().getBoolean("OldLoc.Teleport")) {
            if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("en_EN")) {
                p.sendMessage("§a/back §cto the DeathLocation");
            } else if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("de_DE")) {
                p.sendMessage("§a/back §czu deiner DeathLocation");
            }
        }
        double amount = Main.getInstance().getConfig().getDouble("RemoveMoneyDeath.amount");
        if (Main.getInstance().getConfig().getBoolean("RemoveMoneyDeath.boolean")) {
            Money eco = new Money();
            if (eco.hasPlayerAmount((OfflinePlayer) p, amount)) {
                eco.removeMoney(p, amount);
            }
        }
    }

    public void createPet(Player player, EntityType type, String name, double speed, boolean cangetBabies) {
        Pets pet = new Pets();
        pet.createPet(player, type, name, speed, cangetBabies);
    }
    public void removePet(Player player) {
        Pets pet = new Pets();
        pet.removePet(player);
    }

    @Deprecated
    public String getHoursPlayed(Player player) {
        int ticks = player.getStatistic(Statistic.PLAY_ONE_MINUTE);
        int seconds = ticks / 20;
        Date date = new Date();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        date.setSeconds(seconds);
        String time = new SimpleDateFormat("HH").format(date);
        return time;
    }

    @Deprecated
    public String getTimePlayed(Player player) {
        int ticks = player.getStatistic(Statistic.PLAY_ONE_MINUTE);
        int seconds = ticks / 20;
        Date date = new Date();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        date.setSeconds(seconds);
        String time = new SimpleDateFormat("HH:mm:ss").format(date);
        return time;
    }

    @EventHandler
    public void AsyncChat(AsyncPlayerChatEvent e) {
        if (Main.getInstance().getConfig().getBoolean("Chat.Denied")) {
            final Player p = e.getPlayer();
            final String msg = e.getMessage();
            for (String words : this.deniedcfg.getStringList("deniedwords")) {
                if (msg.equalsIgnoreCase(words)) {
                    e.setCancelled(true);
                    Bukkit.getScheduler().runTask((Plugin) Main.getInstance(), new Runnable() {
                        public void run() {
                            p.kickPlayer("§aDon't use §4" + msg);
                        }
                    });
                    p.kickPlayer("Don't use " + msg);
                    for (Player op : Bukkit.getOnlinePlayers()) {
                        if (op.isOp() == true) {
                            op.sendMessage("§a" + p.getName() + " §fwrite §4" + words);
                        }
                    }
                }
            }
        }
    }

    public boolean showOpPlayer(Player p) {
        if (p.isOp()) {
            return true;
        }
        return false;
    }

    private void ConfigStartup() {
        Information.setInformationInConfig();
        Config.loadConfig();
        Bukkit.getConsoleSender().sendMessage(Main.FrameMainGet.getPrefix() + " §bConfig Updated");
        KitManager kits = new KitManager();
        createCustomConfig();
        kits.createCustomConfig();
        Bukkit.getConsoleSender().sendMessage(Main.FrameMainGet.getPrefix() + " §bCustom Files Created!");
    }

    public ItemStack getItem(ItemStack mat, int itemamount, String displayname) {
        ItemStack item = mat;
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        item.setAmount(itemamount);
        item.setItemMeta(meta);
        return item;
    }

    public void HealPlayer(Player player, int amount) {
        player.setHealth(amount);
        player.setFoodLevel(amount);
        player.setSaturation(amount);
        player.setFireTicks(0);
        player.setLastDamage(0.0D);
    }

    public void getInformationMYSQL(Player p) {
        ArrayList<String> issues = new ArrayList<>();
        issues.add(0, Main.FrameMainGet.getPrefix() + " " + MYSQL.getConnection());
        p.sendMessage(issues.get(0));
    }

    public ItemStack CreateItem(ItemStack item, String displayname, boolean unbreakable, Enchantment enchantment, int level, String... itemlore) {
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(enchantment, level, true);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayname));
        meta.setLore(Arrays.asList(itemlore));
        meta.setUnbreakable(unbreakable);
        item.setItemMeta(meta);
        return item;
    }

    public Long getGameTicks(World world) {
        if (world != null) {
            long time = world.getTime();
            return Long.valueOf(time);
        }
        Bukkit.getConsoleSender().sendMessage(Main.FrameMainGet.getPrefix() + " §cThis World doesn't exists!");

        return null;
    }

    public ItemStack signItem(Material material, List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore1 = new ArrayList<>();
        for (String s : lore) {
            s = s.replace('&', '§');
            lore1.add(s);
        }
        meta.setLore(lore1);
        item.setItemMeta(meta);
        return item;
    }

    public void NoNight() {
        if (Main.getInstance().getConfig().getBoolean("NoNight")) {
            (new BukkitRunnable() {
                public void run() {
                    for (World world : Bukkit.getWorlds()) {
                        if (API.this.getGameTicks(world).longValue() == 10000L) {
                            world.setTime(0L);
                        }
                    }
                }
            }).runTaskTimer((Plugin) Main.getInstance(), 0L, 120L);
        }
    }

    public void TeleporterWithHeads(Player p) {
        if (Main.getInstance().getConfig().getBoolean("Teleporter.Inventory")) {
            p.openInventory(inv);
        } else {
            String text = CreateConfig.getConfig().getString("message.notactivated");
            text = text.replace('&', '§');
            p.sendMessage(Main.FrameMainGet.getPrefix() + " " + text);
        }
    }

    @EventHandler
    public void onClickSkull(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getInventory().equals(inv) &&
                e.getSlot() == e.getRawSlot() &&
                e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta()) {
            String playername = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
            Player player_ = Bukkit.getPlayerExact(playername);
            if (player_ != null) {
                e.setCancelled(true);
                p.closeInventory();
                p.teleport((Entity) player_);
            } else {
                e.setCancelled(true);
                p.closeInventory();
                p.sendTitle("§c§lThis Player", "§cisn't Online!");
            }
        }
    }

    @EventHandler
    public void onClickSkullPage(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equalsIgnoreCase("§aTeleporter 2") &&
                e.getSlot() == e.getRawSlot() &&
                e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta()) {
            String playername = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
            Player player_ = Bukkit.getPlayerExact(playername);
            if (player_ != null) {
                e.setCancelled(true);
                p.closeInventory();
                p.teleport((Entity) player_);
            } else {
                e.setCancelled(true);
                p.closeInventory();
                p.sendTitle("§c§lThis Player", "§cisn't Online!");
            }
        }
    }

    @EventHandler
    public void onClickNextPage(InventoryClickEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase("§aTeleporter") &&
                e.getCurrentItem() != null &&
                e.getCurrentItem().hasItemMeta() &&
                e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aNext Page")) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                ItemStack skull = ItemStackSkull(all.getName());
                for (int i = 0; i < inv.getSize(); i++) {
                    InventoryManager manager = new InventoryManager();
                    manager.setName("§aTeleporter 2");
                    manager.setSize(5);
                    manager.build();
                    manager.setInventory(manager.getInventory());
                    if (all != null) {
                        if (inv.getItem(i) != null &&
                                !inv.getItem(i).getItemMeta().getDisplayName().equalsIgnoreCase(all.getName()) &&
                                all != e.getWhoClicked()) {
                            manager.getInventory().addItem(new ItemStack[]{skull});
                        }


                        manager.showInv((Player) e.getWhoClicked());
                    }
                }
            }
        }
    }

    @EventHandler( priority = EventPriority.HIGH )
    public void firstJoinAsOP(PlayerJoinEvent e) {
        if (e.getPlayer().isOp() &&
                !e.getPlayer().hasPlayedBefore()) {
            Player player = e.getPlayer();
            if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("en_EN")) {
                player.sendMessage(Main.FrameMainGet.getPrefix() + " §aVisit the Config.yml");
                player.sendMessage(Main.FrameMainGet.getPrefix() + " §aLocation = §bplugins/FrameAPI/config.yml");
            } else if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("de_DE")) {
                player.sendMessage(Main.FrameMainGet.getPrefix() + " §aBesuche die Config.yml");
                player.sendMessage(Main.FrameMainGet.getPrefix() + " §aPfad zur Datei = §bplugins/FrameAPI/config.yml");
            }
        }
    }

    private String createNewMotd() {
        String name = Main.getInstance().getConfig().getString("Motd.Name");
        name = name.replace('&', '§');
        return name;
    }

    @EventHandler
    public void setMotd(ServerListPingEvent e) {
        try {
            if (Main.getInstance().getConfig().getBoolean("Motd.Boolean")) {
                e.setMotd(createNewMotd());
            }
        } catch (NullPointerException ex) {
            Bukkit.getConsoleSender().sendMessage("§cAn Issue while setMOTD §f" + ex.getMessage());
        }
    }

    public void reloadCustomConfig() throws UnsupportedEncodingException {
        (new FileManager(Main.getInstance())).getCustomMessagesConfig();
    }

    public void addPlayerToPlayerList(Player player) {
        this.names = (ArrayList<String>) this.cfgplayer.getStringList("Players");
        if (this.names.contains(player.getName())) {
            return;
        }
        this.names.add(player.getName());
        this.cfgplayer.set("Players", this.names);
        try {
            this.cfgplayer.save(this.fileplayer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void CreateNewFilePlayerList() {
        this.names = (ArrayList<String>) this.cfgplayer.getStringList("Players");
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (this.cfgplayer.getStringList("Players").contains(player.getName())) {
                break;
            }
            this.names.add(player.getName());
            this.cfgplayer.set("Players", this.names);
            try {
                this.cfgplayer.save(this.fileplayer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Integer showPlayersSize() {
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (all == null) {
                return Integer.valueOf(0);
            }
        }
        return Bukkit.getOnlinePlayers().size();
    }

    public FileConfiguration getCustomConfig() {
        return (new FileManager(Main.getInstance())).getCustomMessagesConfig();
    }

    public void createCustomConfig() {
        (new FileManager(Main.getInstance())).createCustomMessagesConfig();
    }

    public GameMode getGamemodeInInt(int gamemode) {
        if (gamemode == 0)
            return GameMode.SURVIVAL;
        if (gamemode == 1)
            return GameMode.CREATIVE;
        if (gamemode == 2)
            return GameMode.ADVENTURE;
        if (gamemode == 3) {
            return GameMode.SPECTATOR;
        }
        return null;
    }

    public void createPay(OfflinePlayer target, int amount, Player from) {
        if (Main.getInstance().getConfig().getBoolean("MYSQL.Boolean")) {
            try {
                if (SQL.isTableExists("pays")) {
                    Statement statement = MYSQL.getConnection().createStatement();
                    ResultSet res = statement.executeQuery("SELECT * FROM pays WHERE PlayerTo = '" + target.getName() + "';");
                    if (res.next()) {
                        if (res.getString("PlayerTo") == null) {
                            statement.executeUpdate("INSERT INTO pays (PlayerTo,payAmount,PlayerFrom) VALUES ('" + target.getName() + "','" + amount + "','" + from.getName() + "');");
                        } else {
                            int x = res.getInt("payAmount");
                            x += amount;
                            statement.executeUpdate("UPDATE pays SET payAmount = '" + x + "' WHERE PlayerTo = '" + target.getName() + "'");
                        }
                    } else {
                        statement.executeUpdate("INSERT INTO pays (PlayerTo,payAmount,PlayerFrom) VALUES ('" + target.getName() + "','" + amount + "','" + from.getName() + "');");
                    }
                } else {
                    SQL.createTable("pays", "PlayerTo TEXT(11)", "payAmount INT", "PlayerFrom TEXT");
                    Statement statement = MYSQL.getConnection().createStatement();
                    statement.executeUpdate("INSERT INTO pays (PlayerTo,payAmount,PlayerFrom) VALUES ('" + target.getName() + "','" + amount + "','" + from.getName() + "');");
                    Bukkit.getConsoleSender().sendMessage("§a[FrameAPI] §bBill was Createt to §a" + target.getName() + " §bin amount of §a" + amount);
                }
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage("§cAn Issue §f" + e.getSQLState());
                Bukkit.getConsoleSender().sendMessage("§f" + e.getErrorCode());
                Bukkit.getConsoleSender().sendMessage("§f" + e.getMessage());
            }
        }
    }

    public Integer getPays(OfflinePlayer offline) {
        if (Main.getInstance().getConfig().getBoolean("MYSQL.Boolean")) {
            try {
                if (IsTableExist.isExist("pays")) {
                    Statement statement = MYSQL.getConnection().createStatement();
                    ResultSet res = statement.executeQuery("SELECT * FROM pays WHERE PlayerTo = '" + offline.getName() + "';");
                    if (res.next()) {
                        if (res.getInt("payAmount") == 0) {
                            return Integer.valueOf(0);
                        }
                        int x = res.getInt("payAmount");
                        return Integer.valueOf(x);
                    }
                    return Integer.valueOf(0);
                }
                String sql = "CREATE TABLE IF NOT EXISTS pays(PlayerTo TEXT(11),payAmount INT, PlayerFrom TEXT);";
                try {
                    PreparedStatement stmt = MYSQL.getConnection().prepareStatement(sql);
                    stmt.executeUpdate();
                } catch (SQLException e1) {
                    Bukkit.getConsoleSender().sendMessage("§cAn Issue §f" + e1.getSQLState());
                    Bukkit.getConsoleSender().sendMessage("§f" + e1.getErrorCode());
                    Bukkit.getConsoleSender().sendMessage("§f" + e1.getMessage());
                }
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage("§cAn Issue §f" + e.getSQLState());
                Bukkit.getConsoleSender().sendMessage("§f" + e.getErrorCode());
                Bukkit.getConsoleSender().sendMessage("§f" + e.getMessage());
            }
        }
        return null;
    }

    public ArrayList<OfflinePlayer> getPaysFrom(OfflinePlayer from) {
        ArrayList<OfflinePlayer> playertop = new ArrayList<>();
        if (Main.getInstance().getConfig().getBoolean("MYSQL.Boolean")) {
            if (IsTableExist.isExist("pays")) {
                try {
                    Statement statement = MYSQL.getConnection().createStatement();
                    ResultSet res = statement.executeQuery("SELECT * FROM pays WHERE PlayerFrom = '" + from.getName() + "';");
                    if (res.next()) {
                        String uuid = res.getString("PlayerTo");
                        playertop.add(Bukkit.getOfflinePlayer(uuid));
                        return playertop;
                    }
                } catch (SQLException e) {
                    Bukkit.getConsoleSender().sendMessage("§cAn Issue §f" + e.getSQLState());
                    Bukkit.getConsoleSender().sendMessage("§f" + e.getErrorCode());
                    Bukkit.getConsoleSender().sendMessage("§f" + e.getMessage());
                }
            } else {
                String sql = "CREATE TABLE IF NOT EXISTS pays(PlayerTo TEXT(11),payAmount INT, PlayerFrom TEXT);";
                try {
                    PreparedStatement stmt = MYSQL.getConnection().prepareStatement(sql);
                    stmt.executeUpdate();
                } catch (SQLException e1) {
                    Bukkit.getConsoleSender().sendMessage("§cAn Issue §f" + e1.getSQLState());
                    Bukkit.getConsoleSender().sendMessage("§f" + e1.getErrorCode());
                    Bukkit.getConsoleSender().sendMessage("§f" + e1.getMessage());
                }
            }
        }
        return playertop;
    }

    public void saveLevelsinMYSQL(Player player) {
        try {
            Statement statement = MYSQL.getConnection().createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM PlayerData WHERE PlayerUUID = '" + player.getUniqueId().toString() + "';");
            if (IsTableExist.isExist("PlayerData")) {
                if (res.next()) {
                    if (res.getString("PlayerUUID") == null) {
                        String sql = "INSERT INTO PlayerData(PlayerUUID,PlayerName,Levels) VALUES ('" + player.getUniqueId().toString() + "','" + player.getName() + "','" + player.getLevel() + "');";
                        statement.executeUpdate(sql);
                    } else {
                        String sql = "UPDATE PlayerData SET Levels = '" + player.getLevel() + "' WHERE PlayerUUID = '" + player.getUniqueId().toString() + "';";
                        statement.executeUpdate(sql);
                    }
                } else {
                    try {
                        String sql1 = "INSERT INTO PlayerData(PlayerUUID,PlayerName,Levels) VALUES ('" + player.getUniqueId().toString() + "','" + player.getName() + "','" + player.getLevel() + "');";
                        statement.executeUpdate(sql1);
                    } catch (SQLException ex) {
                        Bukkit.getConsoleSender().sendMessage("§cAn Issue §f" + ex.getSQLState());
                        Bukkit.getConsoleSender().sendMessage("§f" + ex.getErrorCode());
                        Bukkit.getConsoleSender().sendMessage("§f" + ex.getMessage());
                    }
                }
            } else {
                try {
                    String sql = "CREATE TABLE IF NOT EXISTS PlayerData(PlayerUUID VARCHAR(64),PlayerName TEXT,Levels INT);";
                    PreparedStatement stmt = MYSQL.getConnection().prepareStatement(sql);
                    stmt.executeUpdate();
                    String sql1 = "INSERT INTO PlayerData(PlayerUUID,PlayerName,Levels) VALUES ('" + player.getUniqueId().toString() + "','" + player.getName() + "','" + player.getLevel() + "');";
                    statement.executeUpdate(sql1);
                } catch (SQLException ex) {
                    Bukkit.getConsoleSender().sendMessage("§cAn Issue §f" + ex.getSQLState());
                    Bukkit.getConsoleSender().sendMessage("§f" + ex.getErrorCode());
                    Bukkit.getConsoleSender().sendMessage("§f" + ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            Bukkit.getConsoleSender().sendMessage("§cAn Issue §f" + ex.getSQLState());
            Bukkit.getConsoleSender().sendMessage("§f" + ex.getErrorCode());
            Bukkit.getConsoleSender().sendMessage("§f" + ex.getMessage());
        }
    }

    public Integer getLevelMYSQL(Player player) {
        try {
            Statement statement = MYSQL.getConnection().createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM PlayerData WHERE PlayerUUID = '" + player.getUniqueId().toString() + "';");
            if (IsTableExist.isExist("PlayerData")) {
                if (res.next()) {
                    if (res.getString("PlayerUUID") == null) {
                        String str = "INSERT INTO PlayerData(PlayerUUID,PlayerName,Levels) VALUES ('" + player.getUniqueId().toString() + "','" + player.getName() + "','" + player.getLevel() + "');";
                        statement.executeUpdate(str);
                        int j = res.getInt("Levels");
                        return Integer.valueOf(j);
                    }
                    int i = res.getInt("Levels");
                    return Integer.valueOf(i);
                }

                String sql = "INSERT INTO PlayerData(PlayerUUID,PlayerName,Levels) VALUES ('" + player.getUniqueId().toString() + "','" + player.getName() + "','" + player.getLevel() + "');";
                statement.executeUpdate(sql);
                int levels = res.getInt("Levels");
                return Integer.valueOf(levels);
            }

            try {
                String sql = "CREATE TABLE IF NOT EXISTS PlayerData(PlayerUUID VARCHAR(64),PlayerName TEXT,Levels INT);";
                PreparedStatement stmt = MYSQL.getConnection().prepareStatement(sql);
                stmt.executeUpdate();
                String sql1 = "INSERT INTO PlayerData(PlayerUUID,PlayerName,Levels) VALUES ('" + player.getUniqueId().toString() + "','" + player.getName() + "','" + player.getLevel() + "');";
                statement.executeUpdate(sql1);
                int levels = res.getInt("Levels");
                return Integer.valueOf(levels);
            } catch (SQLException ex) {
                Bukkit.getConsoleSender().sendMessage("§cAn Issue §f" + ex.getSQLState());
                Bukkit.getConsoleSender().sendMessage("§f" + ex.getErrorCode());
                Bukkit.getConsoleSender().sendMessage("§f" + ex.getMessage());
                return Integer.valueOf(0);
            }

        } catch (SQLException ex) {
            Bukkit.getConsoleSender().sendMessage("§cAn Issue §f" + ex.getSQLState());
            Bukkit.getConsoleSender().sendMessage("§f" + ex.getErrorCode());
            Bukkit.getConsoleSender().sendMessage("§f" + ex.getMessage());
            return Integer.valueOf(0);
        }
    }

    public void getPaysDelete(Player target, OfflinePlayer from) {
        if (Main.getInstance().getConfig().getBoolean("MYSQL.Boolean")) {
            try {
                if (IsTableExist.isExist("pays")) {
                    Statement statement = MYSQL.getConnection().createStatement();
                    ResultSet res = statement.executeQuery("SELECT * FROM pays WHERE PlayerFrom = '" + from.getName() + "';");
                    res.next();
                    if (res.getString("PlayerFrom") != null) {
                        statement.execute("DELETE FROM pays WHERE PlayerTo = '" + target.getName() + "'");
                    }
                } else {
                    String sql = "CREATE TABLE IF NOT EXISTS pays(PlayerTo TEXT(11),payAmount INT, PlayerFrom TEXT);";
                    try {
                        PreparedStatement stmt = MYSQL.getConnection().prepareStatement(sql);
                        stmt.executeUpdate();
                    } catch (SQLException e1) {
                        Bukkit.getConsoleSender().sendMessage("§cAn Issue §f" + e1.getSQLState());
                        Bukkit.getConsoleSender().sendMessage("§f" + e1.getErrorCode());
                        Bukkit.getConsoleSender().sendMessage("§f" + e1.getMessage());
                    }
                }
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage("§cAn Issue §f" + e.getSQLState());
                Bukkit.getConsoleSender().sendMessage("§f" + e.getErrorCode());
                Bukkit.getConsoleSender().sendMessage("§f" + e.getMessage());
            } finally {
            }
        }
    }

    public ItemStack getItem(String materialname) {
        ItemStack item = new ItemStack(Material.getMaterial(materialname));
        return item;
    }

    public ItemStack getMaterial(int id) {
        int i = 0;
        if (i <= id) {
            ItemStack item = new ItemStack(MaterialInID.ItemsinID(id));
            return item;
        }
        return null;
    }

    public String getPlayerUUIDInString(OfflinePlayer player) {
        return player.getUniqueId().toString();
    }

    public void donationLink(Player player) {
        TextComponent tc = new TextComponent();
        tc.setText(Main.FrameMainGet.getPrefix() + " §6[§bKlick Hier für die Seite zu öffnen§6]");
        tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aöffne Donation Seite").create()));
        tc.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://streamlabs.com/framedesigner_plays/tip"));
        player.spigot().sendMessage(tc);

    }

    public static class Locations {
        public static File file = new File(Main.getInstance().getDataFolder(), "locations.yml");
        public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        public static void setLocation(String locationname, Location loc) {
            cfg.set(locationname + ".world", loc.getWorld().getName());
            cfg.set(locationname + ".x", Double.valueOf(loc.getX()));
            cfg.set(locationname + ".y", Double.valueOf(loc.getY()));
            cfg.set(locationname + ".z", Double.valueOf(loc.getZ()));
            cfg.set(locationname + ".yaw", Float.valueOf(loc.getYaw()));
            cfg.set(locationname + ".pitch", Float.valueOf(loc.getPitch()));

            try {
                cfg.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!file.exists()) {
                try {
                    file.mkdir();
                    file.createNewFile();
                } catch (Exception e) {
                    Bukkit.getConsoleSender().sendMessage("§cAn Issue while Create File §f" + e.getMessage());
                }
            }

            try {
                cfg.load(file);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }

        public static Location getLocation(String locationname) {
            try {
                try {
                    cfg.load(file);
                } catch (IOException | InvalidConfigurationException e) {
                    e.printStackTrace();
                }
                World world = Bukkit.getWorld(API.cfg.getString(locationname + ".world"));
                double x = cfg.getDouble(locationname + ".x");
                double y = cfg.getDouble(locationname + ".y");
                double z = cfg.getDouble(locationname + ".z");
                int yaw = cfg.getInt(locationname + ".yaw");
                int pitch = cfg.getInt(locationname + ".pitch");
                Location loc = new Location(world, x, y, z, yaw, pitch);
                return loc;
            } catch (IllegalArgumentException e) {
                if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("en_EN")) {
                    Bukkit.getConsoleSender().sendMessage("§4Can't find this Location " + e.getMessage());
                } else if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("de_DE")) {
                    Bukkit.getConsoleSender().sendMessage("§4Es konnte keine Location gefunden werden " + e.getMessage());
                }

                return null;
            }
        }
    }

    public void setGameRule(World world, String rule, String value) {
        world.setGameRuleValue(rule, value);
    }

    public void setGameRule(World world, GameRule<Object> gamerule, Object value) {
        world.setGameRule(gamerule, value);
    }

    public void setDifficulty(Difficulty difficulty, World world) {
        world.setDifficulty(difficulty);
    }

    public static class SendMessageEvent
            extends Event {
        private static HandlerList handlers = new HandlerList();
        private Player player;
        private String message;

        public SendMessageEvent(Player player, String message) {
            this.player = player;
            this.message = message;
            getMessage();
        }
        public SendMessageEvent(Player player) {
            this.player = player;
        }

        public static HandlerList getHandlerList() {
            return handlers;
        }

        public String getMessage() {
            getPlayer().sendMessage(this.message);
            return this.message;
        }
        public void setMessage(String message) {
            this.message = message;
        }

        public HandlerList getHandlers() {
            return handlers;
        }

        public static void setHandlers(HandlerList handlers) {
            SendMessageEvent.handlers = handlers;
        }

        public Player getPlayer() {
            return this.player;
        }

        public void setPlayer(Player player) {
            this.player = player;
        }
    }

    public static class Warp {
        static Location locwarp;
        private static File warpfile = new File("plugins/FrameAPI/WarpFile/warps.yml");
        private static FileConfiguration cfgwarp = (FileConfiguration) YamlConfiguration.loadConfiguration(warpfile);


        public static void setWarpLocation(String name, Location loc) {
            cfgwarp.set("warps." + name + ".boolean", Boolean.valueOf(true));
            cfgwarp.set("warps." + name + ".world", loc.getWorld().getName());
            cfgwarp.set("warps." + name + ".x", Double.valueOf(loc.getX()));
            cfgwarp.set("warps." + name + ".y", Double.valueOf(loc.getY()));
            cfgwarp.set("warps." + name + ".z", Double.valueOf(loc.getZ()));
            cfgwarp.set("warps." + name + ".yaw", Float.valueOf(loc.getYaw()));
            cfgwarp.set("warps." + name + ".pitch", Float.valueOf(loc.getPitch()));
            saveWarpFile();
            createWarpFile();
        }

        public static void createWarpFile() {
            if (!warpfile.exists()) {
                try {
                    warpfile.mkdir();
                } catch (Exception exception) {
                }
            }
        }

        public static void saveWarpFile() {
            try {
                cfgwarp.save(warpfile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static Location getWarpLocation(String name) {
            try {
                cfgwarp.getBoolean("warps." + name + ".boolean");
                World world = Bukkit.getWorld(cfgwarp.getString("warps." + name + ".world"));
                double x = cfgwarp.getDouble("warps." + name + ".x");
                double y = cfgwarp.getDouble("warps." + name + ".y");
                double z = cfgwarp.getDouble("warps." + name + ".z");
                int yaw = cfgwarp.getInt("warps." + name + ".yaw");
                int pitch = cfgwarp.getInt("warps." + name + ".pitch");
                locwarp = new Location(world, x, y, z, yaw, pitch);
            } catch (IllegalArgumentException e) {
                if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("en_EN")) {
                    Bukkit.getConsoleSender().sendMessage("§4Can't find this Location " + e.getMessage());
                } else if (Main.getInstance().getConfig().getString("language").equalsIgnoreCase("de_DE")) {
                    Bukkit.getConsoleSender().sendMessage("§4Es konnte keine Location gefunden werden " + e.getMessage());
                }
            }
            if (locwarp == null) {
                try {
                    locwarp.getWorld();
                } catch (NullPointerException ex) {
                    Bukkit.getConsoleSender().sendMessage("§cA Issue " + ex.getMessage());
                }
            } else {
                return locwarp;
            }
            return locwarp;
        }
    }

    public static class CreateConfig {
        @SuppressWarnings("unused")
		private static FileConfiguration myConfig;


        @SuppressWarnings("unused")
		private static File configFile;


        private static boolean loaded = false;


        public static FileConfiguration getConfig() {
            if (!loaded) {
                loadConfig();
            }
            return FileManager.CreateConfig.getConfig();
        }


        public static File getConfigFile() {
            return FileManager.CreateConfig.getConfigFile();
        }


        public static void loadConfig() {
            FileManager.CreateConfig.loadConfig();
        }


        @SuppressWarnings("unused")
		private static void copyFile(InputStream in, File out) throws Exception {
            FileManager.CreateConfig.copyFile(in, out);
        }

        public static void onloadedfalse() {
            FileManager.CreateConfig.onloadedfalse();
        }
    }
}


