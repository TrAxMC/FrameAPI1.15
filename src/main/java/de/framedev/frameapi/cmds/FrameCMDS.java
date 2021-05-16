 package de.framedev.frameapi.cmds;
 import de.framedev.frameapi.api.API;
 import de.framedev.frameapi.interfaces.Constructors;
 import de.framedev.frameapi.main.Main;
 import de.framedev.frameapi.utils.Config;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import org.bukkit.Bukkit;
 import org.bukkit.ChatColor;
 import org.bukkit.command.Command;
 import org.bukkit.command.CommandExecutor;
 import org.bukkit.command.CommandSender;
 import org.bukkit.command.TabCompleter;
 import org.bukkit.configuration.file.FileConfiguration;
 import org.bukkit.entity.Player;
 import org.bukkit.event.Event;
 import org.bukkit.inventory.ItemStack;
 import org.bukkit.plugin.Plugin;
 
 public class FrameCMDS implements CommandExecutor, TabCompleter, Constructors {
   private static FileConfiguration cfg = Main.getInstance().getConfig();
   private static SimpleDateFormat date2 = new SimpleDateFormat("HH.mm.ss");
   private static String Uhrzeit2 = date2.format(new Date());
   private final Main plugin;
   
   public FrameCMDS(Main plugin) {
     this.plugin = plugin;
     plugin.getCommand("frameapi").setTabCompleter(this);
     plugin.getCommand("frameapi").setExecutor(this);
   }
   
   private static String getDate() {
     return Uhrzeit2;
   }
 
 
   
   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
     if (cmd.getName().equalsIgnoreCase("frameapi")) {
       try {
         if (args[0].equalsIgnoreCase("getmysql") && 
           sender instanceof Player) {
           Player p = (Player)sender;
           if (p.hasPermission("frameapi.mysql")) {
             api.getInformationMYSQL(p);
             API.SendMessageEvent sendMessageEvent = new API.SendMessageEvent(p, "§bhi Dude");
             Bukkit.getPluginManager().callEvent((Event)sendMessageEvent);
             return true;
           } 
           p.sendMessage(Main.Variables.getPrefix() + " " + Main.Variables.getNoPerm());
           return true;
         } 
 
         
         if (args[0].equalsIgnoreCase("info") && 
           sender instanceof Player) {
           Player p = (Player)sender;
           api.getInformation(p);
           api.donationLink(p);
           p.getInventory().addItem(new ItemStack[] { api.getWrittenBook(p) });
           return true;
         } 
         
         if (args[0].equalsIgnoreCase("load")) {
           API.CreateConfig.onloadedfalse();
         }
         if (args[0].equalsIgnoreCase("reload") && 
           sender instanceof Player) {
           Player p = (Player)sender;
           if (p.hasPermission("frameapi.config")) {
             Main.getInstance().reloadConfig();
             Config.loadConfig();
             Bukkit.getPluginManager().disablePlugin((Plugin)Main.getInstance());
             Bukkit.getPluginManager().enablePlugin((Plugin)Main.getInstance());
             p.sendMessage("§a[§aFrameAPI]§b Reload Config successfully!");
             return true;
           } 
           p.sendMessage(Main.Variables.getPrefix() + " " + Main.Variables.getNoPerm());
           return true;
         } 
 
         
         if (args[0].equalsIgnoreCase("updates") && 
           sender instanceof Player) {
           Player player = (Player)sender;
           if (player.hasPermission("frameapi.update"))
           {
             Main.updateCheckerPlayer(player);
             return true;
           }
         } 
         
         if (args[0].equalsIgnoreCase("help") && 
           sender instanceof Player) {
           Player p = (Player)sender;
           api.sendHelp(p);
           api.donationLink(p);
           return true;
         } 
         
         if (args[0].equalsIgnoreCase("donation") && 
           sender instanceof Player) {
           Player p = (Player)sender;
           api.donationLink(p);
           return true;
         } 
         
         if (args[0].equalsIgnoreCase("startbudget") && 
           sender instanceof Player) {
           Player p = (Player)sender;
           if (p.hasPermission("frameapi.startbudget")) {
             try {
               if (args[1].equalsIgnoreCase("on")) {
                 Main.getInstance().getConfig().set("StartMoney", Boolean.valueOf(true));
                 Main.getInstance().saveConfig();
                 p.sendMessage(Main.Variables.getPrefix() + " §bStartBudget Enabled");
                 return true;
               }  if (args[1].equalsIgnoreCase("off")) {
                 Main.getInstance().getConfig().set("StartMoney", Boolean.valueOf(false));
                 Main.getInstance().saveConfig();
                 p.sendMessage(Main.Variables.getPrefix() + " §bStartBudget Disabled");
                 return true;
               }  if (args[1].equalsIgnoreCase("set")) {
 
                 
                 double money = Double.parseDouble(args[2]);
                 Main.getInstance().getConfig().set("StartBudget", Double.valueOf(money));
                 Main.getInstance().saveConfig();
                 String text = API.CreateConfig.getConfig().getString("Startbudget.Text.Set");
                 String money1 = String.valueOf(money);
                 text = text.replace("[Money]", money1);
                 text = text.replace('&', '§');
                 p.sendMessage(Main.Variables.getPrefix() + " " + text);
                 return true;
               } 
             } catch (ArrayIndexOutOfBoundsException e) {
               p.sendMessage("§cuse /frameapi startbudget (on/off) or (amount)");
               return true;
             } 
           } else {
             p.sendMessage(Main.Variables.getPrefix() + " " + Main.Variables.getNoPerm());
             return true;
           } 
         } 
 
         
         if (args[0].equalsIgnoreCase("time")) {
           if (sender instanceof Player) {
             Player p = (Player)sender;
             if (p.hasPermission("frameapi.time")) {
               int time = (int)p.getWorld().getTime();
               p.sendMessage("§aTicks = §b" + time);
               p.sendMessage("§a13000 = §bNight");
               p.sendMessage("§a0 = §bDay");
               p.sendMessage(ChatColor.GOLD + " " + getDate());
               return true;
             }
           
           } else if (sender instanceof Player) {
             Player p = (Player)sender;
             p.sendMessage(Main.Variables.getPrefix() + " " + Main.Variables.getNoPerm());
             return true;
           }
         
         }
         else if (sender instanceof Player) {
           Player p = (Player)sender;
           api.sendHelp(p);
         }
       
       } catch (ArrayIndexOutOfBoundsException e) {
         if (sender instanceof Player) {
           Player p = (Player)sender;
           api.sendHelp(p);
           return true;
         } 
       } 
     }
     return false;
   }
 
   
   public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
     ArrayList<String> cmdframeapi = new ArrayList<>();
     cmdframeapi.add("info");
     cmdframeapi.add("time");
     cmdframeapi.add("getmysql");
     cmdframeapi.add("reload");
     cmdframeapi.add("startbudget");
     cmdframeapi.add("spawnmob");
     cmdframeapi.add("help");
     cmdframeapi.add("updates");
     cmdframeapi.add("donation");
     if (command.getName().equalsIgnoreCase("frameapi")) {
       if (args.length == 1)
         return cmdframeapi; 
       if (args[0].equalsIgnoreCase("startbudget")) {
         ArrayList<String> money = new ArrayList<>();
         money.add("on");
         money.add("off");
         return money;
       } 
     } 
     return null;
   }
 }


