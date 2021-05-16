 package de.framedev.frameapi.listeners.money;
 
 import de.framedev.frameapi.api.money.Money;
 import de.framedev.frameapi.main.Main;
 import org.bukkit.Bukkit;
 import org.bukkit.Material;
 import org.bukkit.OfflinePlayer;
 import org.bukkit.block.Sign;
 import org.bukkit.event.EventHandler;
 import org.bukkit.event.Listener;
 import org.bukkit.event.block.Action;
 import org.bukkit.event.block.SignChangeEvent;
 import org.bukkit.event.player.PlayerInteractEvent;
 import org.bukkit.inventory.EquipmentSlot;
 import org.bukkit.inventory.ItemStack;
 import org.bukkit.plugin.Plugin;
 
 
 
 
 
 public class MoneySigns
   implements Listener
 {
   private static Money eco = new Money();
   private final Main plugin;
   
   public MoneySigns(Main plugin) {
     this.plugin = plugin;
     Bukkit.getPluginManager().registerEvents(this, (Plugin)plugin);
   }
   
   @EventHandler
   public void onclickSignBalance(SignChangeEvent e) {
     if (e.getLine(0).equalsIgnoreCase("[balance]")) {
       if (e.getPlayer().hasPermission("frameapi.signs.create")) {
         String signName = Main.getInstance().getConfig().getString("MoneySign.Balance");
         signName = signName.replace('&', '§');
         e.setLine(0, signName);
       } else {
         e.getPlayer().sendMessage(Main.Variables.getPrefix() + " " + Main.Variables.getNoPerm());
       } 
     }
   }
   
   @EventHandler
   public void onClickBalance(PlayerInteractEvent e) {
     if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
       if (e.getHand() == EquipmentSlot.OFF_HAND) {
         return;
       }
       if (e.getHand().equals(EquipmentSlot.HAND) && 
         e.getClickedBlock().getState() instanceof Sign) {
         Sign s = (Sign)e.getClickedBlock().getState();
         String signName = Main.getInstance().getConfig().getString("MoneySign.Balance");
         signName = signName.replace('&', '§');
         if (s.getLine(0).equalsIgnoreCase(signName)) {
           if (e.getPlayer().hasPermission("frameapi.signs.use")) {
             String money = String.valueOf(eco.getMoney((OfflinePlayer)e.getPlayer()));
             String text = Main.getInstance().getConfig().getString("Money.MSG.Balance");
             text = text.replace("[Money]", money);
             text = text.replace('&', '§');
             e.getPlayer().sendMessage(text);
           } else {
             e.getPlayer().sendMessage(Main.Variables.getPrefix() + " " + Main.Variables.getNoPerm());
           } 
         }
       } 
     } 
   }
 
 
   
   @EventHandler
   public void signChange(SignChangeEvent e) {
     String signName = Main.getInstance().getConfig().getString("MoneySign.Buy");
     signName = signName.replace('&', '§');
     if (e.getLine(0).equalsIgnoreCase("buy")) {
       String[] args = e.getLines();
       Material name = Material.getMaterial(args[1].toUpperCase());
       int amount = Integer.parseInt(args[2]);
       int money = Integer.parseInt(args[3]);
       if (e.getPlayer().hasPermission("frameapi.signs.create")) {
         if (e.getLine(1).equalsIgnoreCase(name.name()) && 
           e.getLine(2).equalsIgnoreCase(amount + "") && 
           e.getLine(3).equalsIgnoreCase(money + "")) {
           e.setLine(0, signName);
           e.setLine(1, name.name());
           e.setLine(2, amount + "");
           e.setLine(3, money + "");
         }
       
       } else {
         
         e.getPlayer().sendMessage(Main.Variables.getPrefix() + " " + Main.Variables.getNoPerm());
       } 
     } 
   }
   
   @EventHandler
   public void SignChangeFree(SignChangeEvent e) {
     String signName = Main.getInstance().getConfig().getString("MoneySign.Free");
     signName = signName.replace('&', '§');
     if (e.getLine(0).equalsIgnoreCase("free")) {
       if (e.getPlayer().hasPermission("frameapi.signs.create")) {
         String[] args = e.getLines();
         Material name = Material.getMaterial(args[1].toUpperCase());
         int amount = Integer.parseInt(args[2]);
         if (e.getLine(1).equalsIgnoreCase(name.name()) && 
           e.getLine(2).equalsIgnoreCase(amount + "")) {
           e.setLine(0, signName);
           e.setLine(1, name.name());
           e.setLine(2, amount + "");
         }
       
       } else {
         
         e.getPlayer().sendMessage(Main.Variables.getPrefix() + " " + Main.Variables.getNoPerm());
       } 
     }
   }
   
   @EventHandler
   public void onInteractFree(PlayerInteractEvent e) {
     if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
       if (e.getHand() == EquipmentSlot.OFF_HAND) {
         return;
       }
       if (e.getHand().equals(EquipmentSlot.HAND)) {
         String signName = Main.getInstance().getConfig().getString("MoneySign.Free");
         signName = signName.replace('&', '§');
         if (e.getClickedBlock().getState() instanceof Sign) {
           Sign s = (Sign)e.getClickedBlock().getState();
           if (s.getLine(0).equalsIgnoreCase(signName)) {
             if (e.getPlayer().hasPermission("frameapi.signs.use")) {
               String[] args = s.getLines();
               Material name = Material.getMaterial(args[1].toUpperCase());
               
               int amount = Integer.parseInt(args[2]);
               if (s.getLine(1).equalsIgnoreCase(name.name()) && s.getLine(2).equalsIgnoreCase(amount + "")) {
                 e.getPlayer().getInventory().addItem(new ItemStack[] { new ItemStack(name, amount) });
                 
                 return;
               } 
             } else {
               e.getPlayer().sendMessage(Main.Variables.getPrefix() + " " + Main.Variables.getNoPerm());
             } 
           }
         } 
       } 
     } 
   }
 
   
   @EventHandler
   public void onInteract(PlayerInteractEvent e) {
     if (e.getAction() == Action.RIGHT_CLICK_BLOCK && 
       e.getClickedBlock().getState() instanceof Sign) {
       if (e.getHand() == EquipmentSlot.OFF_HAND) {
         return;
       }
       if (e.getHand().equals(EquipmentSlot.HAND)) {
         String signName = Main.getInstance().getConfig().getString("MoneySign.Buy");
         signName = signName.replace('&', '§');
         Sign s = (Sign)e.getClickedBlock().getState();
         if (s.getLine(0).equalsIgnoreCase(signName)) {
           if (e.getPlayer().hasPermission("frameapi.signs.use")) {
             String[] args = s.getLines();
             Material name = Material.getMaterial(args[1].toUpperCase());
             
             int amount = Integer.parseInt(args[2]);
             
             int money = Integer.parseInt(args[3]);
             if (s.getLine(1).equalsIgnoreCase(name.name()) && s.getLine(2).equalsIgnoreCase(amount + "") && s.getLine(3).equalsIgnoreCase(money + "")) {
               if (eco.getMoney((OfflinePlayer)e.getPlayer()).doubleValue() < money) {
                 e.getPlayer().sendMessage("Not enought Money");
                 return;
               } 
               ItemStack item = new ItemStack(name);
               item.setAmount(amount);
               e.getPlayer().getInventory().addItem(new ItemStack[] { item });
               eco.removeMoney(e.getPlayer(), money);
 
 
               
               return;
             } 
           } else {
             e.getPlayer().sendMessage(Main.Variables.getPrefix() + " " + Main.Variables.getNoPerm());
           } 
         }
       } 
     } 
   }
 
   
   @EventHandler
   public void signChangeSell(SignChangeEvent e) {
     String signName = Main.getInstance().getConfig().getString("MoneySign.Sell");
     signName = signName.replace('&', '§');
     if (e.getLine(0).equalsIgnoreCase("sell")) {
       if (e.getPlayer().hasPermission("frameapi.signs.create")) {
         String[] args = e.getLines();
         Material name = Material.getMaterial(args[1].toUpperCase());
         int amount = Integer.parseInt(args[2]);
         int money = Integer.parseInt(args[3]);
         if (e.getLine(1).equalsIgnoreCase(name.name()) && 
           e.getLine(2).equalsIgnoreCase(amount + "") && 
           e.getLine(3).equalsIgnoreCase(money + "")) {
           e.setLine(0, signName);
           e.setLine(1, name.name());
           e.setLine(2, amount + "");
           e.setLine(3, money + "");
         }
       
       }
       else {
         
         e.getPlayer().sendMessage(Main.Variables.getPrefix() + " " + Main.Variables.getNoPerm());
       } 
     }
   }
   
   @EventHandler
   public void onInteractSell(PlayerInteractEvent e) {
     if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
       if (e.getHand() == EquipmentSlot.OFF_HAND) {
         return;
       }
       if (e.getHand().equals(EquipmentSlot.HAND) && 
         e.getClickedBlock().getState() instanceof Sign) {
         String signName = Main.getInstance().getConfig().getString("MoneySign.Sell");
         signName = signName.replace('&', '§');
         Sign s = (Sign)e.getClickedBlock().getState();
         if (s.getLine(0).equalsIgnoreCase(signName))
           if (e.getPlayer().hasPermission("frameapi.signs.use")) {
             String[] args = s.getLines();
             Material name = Material.getMaterial(args[1].toUpperCase());
             
             int amount = Integer.parseInt(args[2]);
             
             int money = Integer.parseInt(args[3]);
             if (s.getLine(1).equalsIgnoreCase(name.name()) && s.getLine(2).equalsIgnoreCase(amount + "") && s.getLine(3).equalsIgnoreCase(money + "")) {
               if (e.getPlayer().getInventory().contains(name, amount)) {
                 ItemStack item = new ItemStack(name);
                 item.setAmount(amount);
                 e.getPlayer().getInventory().removeItem(new ItemStack[] { item });
                 eco.addMoney((OfflinePlayer)e.getPlayer(), money);
                 return;
               } 
               e.getPlayer().sendMessage("Not enought " + name.name());
 
 
               
               return;
             } 
           } else {
             e.getPlayer().sendMessage(Main.Variables.getPrefix() + " " + Main.Variables.getNoPerm());
           }  
       } 
     } 
   }
 }


