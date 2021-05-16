 package de.framedev.frameapi.cmds;
 
 import de.framedev.frameapi.main.Main;
 import java.util.ArrayList;
 import org.bukkit.Bukkit;
 import org.bukkit.command.Command;
 import org.bukkit.command.CommandExecutor;
 import org.bukkit.command.CommandSender;
 import org.bukkit.entity.Player;
 import org.bukkit.plugin.Plugin;
 
 public class VanishCMD
   implements CommandExecutor {
   private final Main plugin;
   public ArrayList<String> hided = new ArrayList<>();
   
   public VanishCMD(Main plugin) {
     this.plugin = plugin;
     plugin.getCommand("vanish").setExecutor(this);
   }
 
   
   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
     if (command.getName().equalsIgnoreCase("vanish")) {
       if (sender.hasPermission("frameapi.vanish")) {
         if (args.length == 0) {
           if (sender instanceof Player) {
             Player player = (Player)sender;
             if (this.hided.contains(player.getName())) {
               Bukkit.getOnlinePlayers().forEach(o -> {
                     o.showPlayer((Plugin)this.plugin, player);
                     this.hided.remove(player.getName());
                     player.sendMessage("§aYou are no longer in Vanish!");
                   });
               return true;
             } 
             Bukkit.getOnlinePlayers().forEach(o -> {
                   o.hidePlayer((Plugin)this.plugin, player);
                   this.hided.add(player.getName());
                   player.sendMessage("§aYou are now in Vanish!");
                 });
             return true;
           } 
           
           sender.sendMessage("§cOnly Players can use this Command!");
         } else {
           
           sender.sendMessage("§cPlease use §b/v §cor §b/vanish");
         } 
       } else {
         sender.sendMessage(Main.Variables.getPrefix() + " " + Main.Variables.getNoPerm());
       } 
     }
     return false;
   }
 }


