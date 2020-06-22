package de.framedev.frameapi.cmds;

import de.framedev.frameapi.api.API;
import de.framedev.frameapi.interfaces.Constructors;
import de.framedev.frameapi.kits.KitManager;
import de.framedev.frameapi.main.Main;
import de.framedev.frameapi.managers.FileManager;
import de.framedev.frameapi.utils.ReplaceCharConfig;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FrameAPICMDS
        implements CommandExecutor, TabCompleter, Listener, Constructors {
    private static File warpfile = new File("plugins/FrameAPI/WarpFile/warps.yml");
    private static FileConfiguration cfgwarp = (FileConfiguration) YamlConfiguration.loadConfiguration(warpfile);
    private static File deniedFile = new File("plugins/FrameAPI", "DeniedWords.yml");
    private static FileConfiguration deniedcfg = (FileConfiguration) YamlConfiguration.loadConfiguration(deniedFile);
    private static FileManager fileManager = new FileManager(Main.getInstance());
    private static FileConfiguration cfg = Main.getInstance().getConfig();
    private static SimpleDateFormat date2 = new SimpleDateFormat("HH:mm:ss");
    private static String Uhrzeit2 = date2.format(new Date());
    private final Main plugin;

    public FrameAPICMDS(Main plugin) {

        this.plugin = plugin;

        plugin.getCommand("back").setExecutor(this);

        plugin.getCommand("spawnmob").setExecutor(this);

        plugin.getCommand("denied").setExecutor(this);

        plugin.getCommand("getitem").setExecutor(this);

        plugin.getCommand("heal").setExecutor(this);

        plugin.getCommand("teleporter").setExecutor(this);

        plugin.getCommand("setwarp").setExecutor(this);

        plugin.getCommand("warp").setExecutor(this);

        plugin.getCommand("removewarp").setExecutor(this);

        plugin.getCommand("warp").setTabCompleter(this);

        plugin.getCommand("removewarp").setTabCompleter(this);

        plugin.getCommand("setwarp").setTabCompleter(this);

        plugin.getCommand("kits").setExecutor(this);

        plugin.getCommand("msg").setExecutor(this);

        plugin.getCommand("gm").setExecutor(this);

        plugin.getCommand("ultrahardcore").setExecutor(this);

        plugin.getCommand("createpay").setExecutor(this);

        plugin.getCommand("paythebill").setExecutor(this);

        Bukkit.getPluginManager().registerEvents(this, (Plugin) plugin);
    }

    private static void sendHelp(Player p) {
        try {

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

        } catch (Exception e) {

            p.sendMessage("§6you don't have Permission to use /frameapi help");
        }
    }

    private static String getDate() {

        return Uhrzeit2;
    }


    private static Enchantment getEnchantment(String enchString) {

        enchString = enchString.toLowerCase().replaceAll("[ _-]", "");


        Map<String, String> aliases = new HashMap<>();

        aliases.put("aspectfire", "fireaspect");

        aliases.put("sharpness", "damageall");

        aliases.put("smite", "damageundead");

        aliases.put("punch", "arrowknockback");

        aliases.put("looting", "lootbonusmobs");

        aliases.put("fortune", "lootbonusblocks");

        aliases.put("baneofarthropods", "damageundead");

        aliases.put("power", "arrowdamage");

        aliases.put("flame", "arrowfire");

        aliases.put("infinity", "arrowinfinite");

        aliases.put("unbreaking", "durability");

        aliases.put("efficiency", "digspeed");

        aliases.put("smite", "damageundead");


        String alias = aliases.get(enchString);

        if (alias != null) {

            enchString = alias;
        }


        for (Enchantment value : Enchantment.values()) {

            if (enchString.equalsIgnoreCase(value.getName().replaceAll("[ _-]", ""))) {

                return value;
            }
        }


        return null;
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("teleporter") &&
                sender instanceof Player) {

            Player p = (Player) sender;

            if (p.hasPermission("frameapi.teleporter")) {

                api.TeleporterWithHeads(p);

                return true;
            }

            p.sendMessage(Main.FrameMainGet.getPrefix() + " " + Main.FrameMainGet.getNoPerm());

            return true;
        }


        if (cmd.getName().equalsIgnoreCase("back") &&
                sender instanceof Player) {

            Player p = (Player) sender;

            if (Main.getInstance().getConfig().getBoolean("OldLoc.Teleport")) {

                if (API.getOldloc().containsKey(p.getName())) {

                    if (p.hasPermission("frameapi.backtp")) {

                        Location loc = (Location) API.getOldloc().get(p.getName());

                        p.teleport(loc);

                        String str1 = API.CreateConfig.getConfig().getString("message.TeleportBack");

                        str1 = str1.replace('&', '§');

                        p.sendMessage(str1);

                        return true;
                    }

                    p.sendMessage(Main.FrameMainGet.getPrefix() + " " + Main.FrameMainGet.getNoPerm());

                    return true;
                }


                String str = API.CreateConfig.getConfig().getString("message.NoLocation");

                str = str.replace('&', '§');

                p.sendMessage(str);

                return true;
            }


            String message = API.CreateConfig.getConfig().getString("message.notactivated");

            message = message.replace('&', '§');

            p.sendMessage(message);

            return true;
        }


        if (cmd.getName().equalsIgnoreCase("spawnmob") &&
                sender instanceof Player) {

            Player p = (Player) sender;

            if (args.length == 0) {

                p.sendMessage(Main.FrameMainGet.getPrefix() + " §aPlease use §b/spawnmob (type) §aor §b/spawnmob (type) (amount)");

                return true;

            }
            if (args.length == 1) {

                if (p.hasPermission("frameapi.spawnmob")) {

                    EntityType type = EntityType.fromName(args[0]);

                    World world = p.getWorld();

                    Block block = p.getTargetBlock(null, 100);

                    Location bl = block.getLocation();

                    bl.setY(block.getLocation().getY() + 1.0D);

                    world.spawnEntity(bl, type);
                }

            } else if (args.length == 2) {

                for (int i = 1; i <= Integer.parseInt(args[1]); i++) {

                    EntityType type = EntityType.fromName(args[0]);

                    World world = p.getWorld();

                    Block block = p.getTargetBlock(null, 100);

                    Location bl = block.getLocation();

                    bl.setY(block.getLocation().getY() + 1.0D);

                    world.spawnEntity(bl, type);
                }

            } else {


                p.sendMessage("§c§lPlease use /spawnmob (type) or /spawnmob (type) (amount)");
            }
        }


        if (cmd.getName().equalsIgnoreCase("denied") &&
                sender instanceof Player) {

            Player p = (Player) sender;

            if (Main.getInstance().getConfig().getBoolean("Chat.Denied")) {

                if (args[0].equalsIgnoreCase("remove")) {

                    if (p.hasPermission("frameapi.denied")) {

                        List<String> words = deniedcfg.getStringList("deniedwords");

                        if (words.contains(args[1])) {

                            words.remove(args[1]);

                            p.sendMessage(args[1] + " wurde entfernt!");

                            deniedcfg.set("deniedwords", words);
                            try {

                                deniedcfg.save(deniedFile);

                            } catch (IOException e) {

                                e.printStackTrace();
                            }

                            return true;
                        }

                        p.sendMessage(args[1] + " ist nicht in der Liste!");

                        return true;
                    }


                    p.sendMessage(Main.FrameMainGet.getPrefix() + " " + Main.FrameMainGet.getNoPerm());

                    return true;
                }

                if (args[0].equalsIgnoreCase("add")) {

                    List<String> words = deniedcfg.getStringList("deniedwords");

                    words.add(args[1]);

                    p.sendMessage(args[1] + " wurde gesperrt!");

                    deniedcfg.set("deniedwords", words);
                    try {

                        deniedcfg.save(deniedFile);

                    } catch (IOException e) {

                        e.printStackTrace();
                    }

                    return true;
                }

                p.sendMessage(Main.FrameMainGet.getPrefix() + " §aPlease use §b/denied add (word) §aor §b/denied remove (word)");
            } else {


                String text = API.CreateConfig.getConfig().getString("message.notactivated");

                text = text.replace('&', '§');

                p.sendMessage(Main.FrameMainGet.getPrefix() + " " + text);
            }
        }


        if (cmd.getName().equalsIgnoreCase("getitem") &&
                sender instanceof Player) {

            Player p = (Player) sender;

            if (p.hasPermission("frameapi.getitem")) {

                if (args.length == 0) {

                    p.sendMessage("§cPlease use /getitem (Material) (DisplayName) (ItemLore) (unbreakable) (enchantment) (enchantmentlevel)");

                } else if (args.length == 6) {
                    try {

                        String[] lore = {args[2]};

                        ItemStack mat = new ItemStack(Material.getMaterial(args[0].toUpperCase()));

                        String name = args[1];

                        ItemStack item2 = api.CreateItem(mat, name, Boolean.parseBoolean(args[3]), getEnchantment(args[4]), Integer.parseInt(args[5]), lore);

                        p.getInventory().addItem(new ItemStack[]{item2});

                        return true;

                    } catch (ArrayIndexOutOfBoundsException ex) {

                        p.sendMessage("§cPlease use /getitem (Material) (DisplayName) (ItemLore) (unbreakable) (enchantment) (enchantmentlevel)");

                    } catch (IllegalArgumentException ex) {

                        p.sendMessage("§6" + args[0] + " §cis not a Item!!!");
                    }

                } else if (args.length == 2) {

                    int x = Integer.parseInt(args[1]);

                    ItemStack item = api.getMaterial(Integer.parseInt(args[0]));

                    item.setAmount(x);

                    p.getInventory().addItem(new ItemStack[]{new ItemStack(item)});
                } else {

                    p.sendMessage("§cPlease use /getitem (id) (amount)");
                }

            } else {


                p.sendMessage(Main.FrameMainGet.getPrefix() + " " + Main.FrameMainGet.getNoPerm());

                return true;
            }
        }


        if (cmd.getName().equalsIgnoreCase("setwarp")) {

            if (sender instanceof Player) {

                Player p = (Player) sender;

                if (p.hasPermission("frameapi.setwarp")) {

                    API.Warp.setWarpLocation(args[0], p.getLocation());

                    p.sendMessage(Main.FrameMainGet.getPrefix() + " " + args[0] + " §aWarp has been set");
                    try {

                        cfgwarp.load(warpfile);

                    } catch (IOException | org.bukkit.configuration.InvalidConfigurationException e) {

                        e.printStackTrace();
                    }
                }
            } else {

                sender.sendMessage("§cYou must be a Player!");
            }
        }

        if (cmd.getName().equalsIgnoreCase("warp")) {

            if (sender instanceof Player) {

                Player p = (Player) sender;

                if (Main.getInstance().getConfig().getBoolean("Use.Warps")) {

                    if (p.hasPermission("frameapi.warp")) {

                        if (args.length == 1) {

                            ConfigurationSection cs = cfgwarp.getConfigurationSection("warps");
                            try {

                                for (String s : cs.getKeys(false)) {

                                    if (s.equalsIgnoreCase(args[0])) {

                                        if (cfgwarp.getBoolean("warps." + s + ".boolean")) {
                                            try {

                                                Location loc = API.Warp.getWarpLocation(args[0]);

                                                String name = API.CreateConfig.getConfig().getString("Warp.Warp");

                                                name = name.replace("[warp]", args[0]);

                                                name = name.replace('&', '§');

                                                p.teleport(loc);

                                                p.sendMessage(name);

                                            } catch (NullPointerException e) {

                                                String str = API.CreateConfig.getConfig().getString("Warp.warpdoesntexist");

                                                str = str.replace('&', '§');

                                                p.sendMessage(str);
                                            }
                                            continue;
                                        }

                                        String message = API.CreateConfig.getConfig().getString("Warp.warpdoesntexist");

                                        message = message.replace('&', '§');

                                        p.sendMessage(message);
                                    }

                                }

                            } catch (NullPointerException e) {

                                p.sendMessage("§cNo Warps Found!");
                            }
                        } else {

                            p.sendMessage("§cPlease use §a/warp (warpname)");
                        }
                    } else {

                        p.sendMessage(Main.FrameMainGet.getPrefix() + " " + Main.FrameMainGet.getNoPerm());

                        return true;
                    }
                } else {

                    String text = API.CreateConfig.getConfig().getString("message.notactivated");

                    text = text.replace('&', '§');

                    p.sendMessage(Main.FrameMainGet.getPrefix() + " " + text);
                }
            } else {

                sender.sendMessage("§cThis Command is only for Players");
            }
        }

        if (cmd.getName().equalsIgnoreCase("ultrahardcore") &&
                sender instanceof Player) {

            Player player = (Player) sender;

            if (player.hasPermission("frameapi.ultrahardcore")) {

                if (!((Boolean) player.getWorld().getGameRuleValue(GameRule.NATURAL_REGENERATION)).booleanValue()) {

                    Bukkit.dispatchCommand((CommandSender) player, "gamerule naturalRegeneration true");

                    player.sendMessage(Main.FrameMainGet.getPrefix() + " §bUltrahardcore disabled");
                } else {

                    Bukkit.dispatchCommand((CommandSender) player, "gamerule naturalRegeneration false");

                    player.sendMessage(Main.FrameMainGet.getPrefix() + " §bUltrahardcore enabled");
                }
            } else {


                player.sendMessage(Main.FrameMainGet.getPrefix() + " " + Main.FrameMainGet.getNoPerm());

                return true;
            }
        }


        if (cmd.getName().equalsIgnoreCase("removewarp")) {

            if (sender.hasPermission("frameapi.warpremove")) {

                ConfigurationSection cs = cfgwarp.getConfigurationSection("warps");

                for (String s : cs.getKeys(false)) {

                    if (s.equalsIgnoreCase(args[0])) {

                        cfgwarp.set("warps." + s + ".boolean", Boolean.valueOf(false));

                        String message = API.CreateConfig.getConfig().getString("Warp.warpremoved");

                        message = message.replace("[Prefix]", Main.FrameMainGet.getPrefix());

                        message = message.replace("[Warpname]", args[0]);

                        message = message.replace('&', '§');

                        sender.sendMessage(message);
                        try {

                            cfgwarp.save(warpfile);

                        } catch (IOException e) {

                            e.printStackTrace();
                        }
                        continue;
                    }
                    try {

                        if (s.equalsIgnoreCase(args[0])) ;

                    } catch (IllegalArgumentException e) {

                        sender.sendMessage("§c" + args[0] + " §ais not in warps.yml");
                    }
                }
            } else {


                sender.sendMessage(Main.FrameMainGet.getPrefix() + " " + Main.FrameMainGet.getNoPerm());

                return true;
            }
        }

        if (cmd.getName().equalsIgnoreCase("heal")) {

            if (sender instanceof Player) {

                Player p = (Player) sender;

                if (p.hasPermission("frameapi.heal")) {

                    if (args.length == 0) {

                        api.HealPlayer(p, 20);

                        String text1 = API.CreateConfig.getConfig().getString("message.heal");

                        text1 = text1.replace('&', '§');

                        p.sendMessage(text1);
                    } else if (args.length == 1) {
                        try {

                            Player target = Bukkit.getPlayer(args[0]);

                            api.HealPlayer(target, 20);

                            String text1 = API.CreateConfig.getConfig().getString("message.targetheal");

                            text1 = text1.replace('&', '§');

                            text1 = text1.replace("[player]", p.getDisplayName());

                            String text2 = API.CreateConfig.getConfig().getString("message.targethealplayer");

                            text2 = text2.replace('&', '§');

                            text2 = text2.replace("[target]", target.getDisplayName());

                            target.sendMessage(text1);

                            p.sendMessage(text2);

                        } catch (ArrayIndexOutOfBoundsException e) {

                            p.sendMessage(args[0] + " §cis not a Player");
                        }
                    } else {

                        p.sendMessage("§aPlease use §b/heal §aor §b/heal (player)");

                        return true;
                    }
                } else {

                    p.sendMessage(Main.FrameMainGet.getPrefix() + " " + Main.FrameMainGet.getNoPerm());

                    return true;
                }
            } else {

                sender.sendMessage("§cYou must be a Player");
            }
        }

        if (cmd.getName().equalsIgnoreCase("kits") &&
                sender instanceof Player) {

            Player p = (Player) sender;

            if (args.length != 0) {

                String name = args[0];

                if (p.hasPermission("frameapi." + name)) {

                    if (args.length == 1) {

                        KitManager kit1 = new KitManager();

                        kit1.loadKits(name, p);
                    } else {

                        p.sendMessage("§aPlease use §b/kits (kitname)");
                    }
                } else {

                    p.sendMessage(Main.FrameMainGet.getPrefix() + " " + Main.FrameMainGet.getNoPerm());
                }
            } else {

                p.sendMessage("§aPlease use §b/kits (kitname)");
            }
        }
        if (cmd.getName().equalsIgnoreCase("msg")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                String message = "";
                if (p.hasPermission("frameapi.msg")) {
                    if (args.length >= 2) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target != null) {
                            for (int i = 1; i < args.length; i++) {
                                message = message + args[i] + " ";
                            }
                            p.sendMessage("§cme §r-> §a " + target.getName() + " §f-> " + message);
                            target.sendMessage("§a" + p.getName() + " §r-> §cme  §f-> " + message);
                            message = "";
                        }
                    } else {
                        p.sendMessage("§c§lPlease use /msg (player) (message)");
                    }
                } else {

                    p.sendMessage(Main.FrameMainGet.getPrefix() + " " + Main.FrameMainGet.getNoPerm());
                }
            } else {

                sender.sendMessage("§cYou must be a Player");
            }
        }

        if (cmd.getName().equalsIgnoreCase("createpay") &&
                cfg.getBoolean("MYSQL.Boolean")) {

            if (args.length == 2) {

                if (sender instanceof Player) {

                    Player p = (Player) sender;

                    Player target = Bukkit.getPlayer(args[0]);

                    if (target != null) {

                        int amount = Integer.parseInt(args[1]);

                        api.createPay((OfflinePlayer) target, amount, p);

                        String message = api.getCustomConfig().getString("Bill.Create");

                        message = message.replace("[Target]", target.getName());

                        message = message.replace('&', '§');

                        p.sendMessage(message);

                        if (api.getPays((OfflinePlayer) target) != null) {

                            (Main.getInstance()).pays.add(target.getName());

                            String message2 = api.getCustomConfig().getString("Bill.Get");

                            message2 = message2.replace("[Target]", p.getName());

                            message2 = message2.replace("[Amount]", String.valueOf(amount));

                            message2 = message2.replace('&', '§');

                            target.sendMessage("§aYou get a Pay use /paythebill to sale it of §b" + amount);
                        }
                    } else {

                        OfflinePlayer offline = Bukkit.getOfflinePlayer(args[0]);

                        int amount = Integer.parseInt(args[1]);

                        api.createPay(offline, amount, p);

                        String message = api.getCustomConfig().getString("Bill.Create");

                        message = message.replace("[Target]", args[0]);

                        message = message.replace('&', '§');

                        p.sendMessage(message);
                    }

                }

            } else if (sender instanceof Player) {

                Player player = (Player) sender;

                player.sendMessage("§cPlease use §b/createpay (playername) (amount)");
            }
        }


        if (cmd.getName().equalsIgnoreCase("paythebill") &&
                cfg.getBoolean("MYSQL.Boolean") &&
                sender instanceof Player) {

            Player p = (Player) sender;

            if ((Main.getInstance()).pays.contains(p.getName())) {

                if (api.getPays(p) != null) {

                    int amounts = api.getPays((OfflinePlayer) p).intValue();

                    if (eco.getMoney((OfflinePlayer) p).doubleValue() < amounts) {

                        double money = amounts - eco.getMoney((OfflinePlayer) p).doubleValue();

                        p.sendMessage("§cNot enought Money. §aIf you have Money in your Bank please withdraw it and Pay it!");

                        p.sendMessage("§cYou need = " + money);
                    } else {
                        for (Player from : Bukkit.getOnlinePlayers()) {

                            if (from != null) {

                                if (api.getPaysFrom(from).contains(p)) {
                                    eco.addMoney((OfflinePlayer) from, amounts);

                                    String message2 = api.getCustomConfig().getString("Bill.Pay");

                                    message2 = message2.replace("[Target]", from.getName());

                                    message2 = message2.replace("[Amount]", String.valueOf(amounts));

                                    message2 = message2.replace('&', '§');

                                    from.sendMessage("§aYou get the Pay from §b" + p.getName() + " §ain Amount of §b" + amounts);

                                    p.sendMessage(message2);

                                    api.getPaysDelete(p, from);

                                    eco.removeMoney(p, amounts);
                                } else {
                                    for (OfflinePlayer offline : Main.getInstance().getPlayers()) {
                                        if (api.getPaysFrom(offline).contains(p)) {
                                            String message2 = api.getCustomConfig().getString("Bill.Pay");
                                            message2 = message2.replace("[Target]", offline.getName());

                                            message2 = message2.replace("[Amount]", String.valueOf(amounts));

                                            message2 = message2.replace('&', '§');

                                            eco.addMoney(offline, amounts);

                                            p.sendMessage(message2);

                                            api.getPaysDelete(p, offline);

                                            eco.removeMoney(p, amounts);
                                        }
                                    }
                                }
                            } else {
                                for (OfflinePlayer offline : Main.getInstance().getPlayers()) {
                                    if (api.getPaysFrom(offline).contains(p)) {

                                        String message2 = api.getCustomConfig().getString("Bill.Pay");

                                        message2 = message2.replace("[Target]", offline.getName());

                                        message2 = message2.replace("[Amount]", String.valueOf(amounts));

                                        message2 = message2.replace('&', '§');

                                        eco.addMoney(offline, amounts);

                                        p.sendMessage(message2);

                                        api.getPaysDelete(p, from);

                                        eco.removeMoney(p, amounts);
                                    }
                                }
                            }
                        }
                    }
                } else {


                    sender.sendMessage("§6You have no Bill!");
                }
            } else {


                sender.sendMessage("§6You have no Bill!");
            }
        }

        if (cmd.getName().equalsIgnoreCase("gm") &&
                sender instanceof Player) {

            Player player = (Player) sender;

            if (args.length == 1) {

                if (player.hasPermission("frameapi.gamemode")) {
                    try {

                        String message = api.getCustomConfig().getString("message.GamemodeChanged");

                        if (message != null) {

                            message = ReplaceCharConfig.replaceParagraph(message);

                            message = ReplaceCharConfig.replaceObjectWithData(message, "[GameMode]", api.getGamemodeInInt(Integer.parseInt(args[0])).name());

                            player.setGameMode(api.getGamemodeInInt(Integer.parseInt(args[0])));

                            player.sendMessage(message);
                        }

                    } catch (IllegalArgumentException ex) {

                        player.sendMessage("§6" + args[0] + " §cis not a number!");

                    } catch (NullPointerException ex) {

                        ex.printStackTrace();
                    }
                }

            } else if (args.length == 2) {

                if (player.hasPermission("frameapi.gamemode.other")) {

                    Player target = Bukkit.getPlayer(args[1]);

                    if (target != null) {
                        try {

                            target.setGameMode(api.getGamemodeInInt(Integer.parseInt(args[0])));

                            String message = api.getCustomConfig().getString("message.GamemodeChanged");

                            message = ReplaceCharConfig.replaceParagraph(message);

                            message = ReplaceCharConfig.replaceObjectWithData(message, "[GameMode]", api.getGamemodeInInt(Integer.parseInt(args[0])).name());

                            target.sendMessage(message);

                        } catch (IllegalArgumentException ex) {

                            sender.sendMessage("§6" + args[0] + " §cis not a number!");

                        } catch (NullPointerException ex) {

                            ex.printStackTrace();
                        }
                    }
                } else {

                    player.sendMessage(Main.FrameMainGet.getPrefix() + " " + Main.FrameMainGet.getNoPerm());
                }
            } else {

                player.sendMessage("§cPlease use §l§b/gm (number) §cor §l§b/gm (number) (player)");
            }
        }
        return false;

    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if (command.getName().equalsIgnoreCase("frameapi")) {


            if (args.length == 1) {

                ArrayList<String> cmdframeapi = new ArrayList<>();

                cmdframeapi.add("startbudget");

                cmdframeapi.add("reload");

                cmdframeapi.add("getmysql");

                cmdframeapi.add("help");

                cmdframeapi.add("info");

                cmdframeapi.add("time");

                if (!sender.hasPermission("frameapi.startbudget")) {

                    cmdframeapi.remove("startbudget");
                }

                if (!sender.hasPermission("frameapi.reload")) {

                    cmdframeapi.remove("reload");
                }

                if (!sender.hasPermission("frameapi.mysql")) {

                    cmdframeapi.remove("getmysql");
                }

                if (!sender.hasPermission("frameapi.help")) {

                    cmdframeapi.remove("help");
                }

                if (!sender.hasPermission("frameapi.info")) {

                    cmdframeapi.remove("info");
                }

                if (!sender.hasPermission("frameapi.time")) {

                    cmdframeapi.remove("time");
                }


                return cmdframeapi;

            }
            if (args[0].equalsIgnoreCase("startbudget")) {

                ArrayList<String> money = new ArrayList<>();

                money.add("on");

                money.add("off");

                money.add("set");

                return money;
            }

        } else if (command.getName().equalsIgnoreCase("warp")) {

            if (args.length == 1 &&
                    sender.hasPermission("frameapi.warp")) {

                ArrayList<String> warps = new ArrayList<>();

                ConfigurationSection cs = cfgwarp.getConfigurationSection("warps");

                if (!warpfile.exists()) {
                    try {

                        warpfile.mkdir();

                        sender.sendMessage("§4No Warps found");

                        return warps;

                    } catch (NullPointerException e) {

                        sender.sendMessage("§4No Warps found");
                    }
                } else {

                    for (String s : cs.getKeys(false)) {

                        if (cfgwarp.getBoolean("warps." + s + ".boolean")) {
                            try {

                                if (cfgwarp.getBoolean("warps." + s + ".boolean")) {

                                    warps.add(s);
                                }

                            } catch (NullPointerException e) {

                                String message = API.CreateConfig.getConfig().getString("Warp.warpdoesntexist");

                                message = message.replace('&', '§');

                                sender.sendMessage(message);
                            }
                        }
                    }

                    return warps;
                }

            }

        } else if (command.getName().equalsIgnoreCase("removewarp")) {

            if (args.length == 1 &&
                    sender.hasPermission("frameapi.warpremove")) {

                ArrayList<String> warps = new ArrayList<>();

                ConfigurationSection cs = cfgwarp.getConfigurationSection("warps");

                for (String s : cs.getKeys(false)) {

                    if (cfgwarp.getBoolean("warps." + s + ".boolean")) {
                        try {

                            if (cfgwarp.getBoolean("warps." + s + ".boolean")) {

                                warps.add(s);
                            }

                        } catch (NullPointerException e) {

                            String message = API.CreateConfig.getConfig().getString("Warp.warpdoesntexist");

                            message = message.replace('&', '§');

                            sender.sendMessage(message);
                        }
                    }
                }

                return warps;
            }


        } else if (command.getName().equalsIgnoreCase("kits") &&
                args.length == 1) {

            ArrayList<String> list = new ArrayList<>();

            ConfigurationSection cs = KitManager.getCustomConfig().getConfigurationSection("Items");

            for (String s : cs.getKeys(false)) {

                if (sender.hasPermission("frameapi." + s)) {

                    list.add(s);
                }
            }

            return list;
        }


        return null;
    }

}
