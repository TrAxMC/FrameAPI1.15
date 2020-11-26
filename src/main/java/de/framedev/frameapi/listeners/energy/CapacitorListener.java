package de.framedev.frameapi.listeners.energy;

import de.framedev.frameapi.api.energy.Energy;
import de.framedev.frameapi.interfaces.EnergyListenerInterface;
import de.framedev.frameapi.main.Main;
import de.framedev.frameapi.managers.InventoryManager;
import de.framedev.frameapi.managers.ItemBuilder;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class CapacitorListener
        implements Listener, EnergyListenerInterface {
    public ArrayList<BukkitRunnable> runtime = new ArrayList<>();
    public ArrayList<UUID> playeruuid = new ArrayList<>();
    File file = new File("plugins/FrameAPI/Pistons.yml");
    FileConfiguration cfg = (FileConfiguration) YamlConfiguration.loadConfiguration(this.file);

    @EventHandler
    public void getCapacator(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK &&
                event.getClickedBlock().getType() == Material.CAULDRON) {
            event.setCancelled(true);
            InventoryManager manager = new InventoryManager();
            manager.setName("§aEnergy");
            manager.setSize(3);
            manager.build();
            manager.setItem((new ItemBuilder(Material.NETHER_STAR)).setDisplayName("§aYour Energy").setLore(new String[]{"§aEnergy = " + (new Energy()).getEnergy((OfflinePlayer) event.getPlayer())}).build(), 10);
            manager.setItem((new ItemBuilder(Material.BLUE_CONCRETE)).setDisplayName("§b§lVerkaufen").build(), 12);
            manager.FillNull();
            manager.showInv(event.getPlayer());
        }
    }


    @EventHandler
    public void onClickEnergy(final InventoryClickEvent event) {
        if (event.getInventory() != null &&
                event.getView().getTitle().equalsIgnoreCase("§aEnergy") &&
                event.getCurrentItem() != null &&
                event.getCurrentItem().hasItemMeta()) {
            if (event.getCurrentItem().getType() == Material.NETHER_STAR && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aYour Energy")) {
                event.setCancelled(true);
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§b§lVerkaufen")) {
                event.setCancelled(true);
                final InventoryManager verkaufen = new InventoryManager();
                verkaufen.setName("§b§lVerkaufen Lege ein Item in den Mittleren Slot");
                verkaufen.setSize(1);
                verkaufen.build();
                verkaufen.showInv((Player) event.getWhoClicked());
                (new BukkitRunnable() {
                    public void run() {
                        for (int i = 0; i < verkaufen.getInventory().getSize(); i++) {
                            if (verkaufen.getInventory().getItem(i) != null) {
                                int durability = 100;
                                (new Energy()).addEnergy((OfflinePlayer) event.getWhoClicked(), durability);
                                int amount = durability;
                                verkaufen.setItem(new ItemStack(Material.AIR), i);
                                verkaufen.setName("§a§lErfolgreich Verkauft fuer §c§l" + amount + " §a§lEnergy!");
                                verkaufen.build();
                                verkaufen.showInv((Player) event.getWhoClicked());
                                (new BukkitRunnable() {
                                    public void run() {
                                        event.getWhoClicked().closeInventory();
                                        cancel();
                                    }
                                }).runTaskLater((Plugin) Main.getInstance(), 40L);
                            }
                        }
                    }
                }).runTaskTimer((Plugin) Main.getInstance(), 0L, 20L);
            } else {
                event.setCancelled(true);
            }
        }
    }


    public void run(Player player, Block block) {
        if (block.getType() == Material.PISTON) {
            int amount = 1000;
            if ((new Energy()).getEnergy((OfflinePlayer) player) >= amount) {
                if (this.playeruuid.contains(player.getUniqueId())) {
                    player.sendMessage("§cCapacität Voll!");
                    this.playeruuid.remove(player.getUniqueId());

                    return;
                }
            } else if (!this.playeruuid.contains(player.getUniqueId())) {
                this.playeruuid.add(player.getUniqueId());
                if (block.getBlockPower() == 15) {
                    (new Energy()).addEnergy((OfflinePlayer) player, 15);
                } else if (block.getBlockPower() < 15) {
                    (new Energy()).addEnergy((OfflinePlayer) player, 5);
                }

            } else if (block.getBlockPower() == 15) {
                (new Energy()).addEnergy((OfflinePlayer) player, 15);
            } else if (block.getBlockPower() < 15) {
                (new Energy()).addEnergy((OfflinePlayer) player, 5);

            }

        } else if (block.getType() == Material.STICKY_PISTON) {
            int amount = 2500;
            if ((new Energy()).getEnergy((OfflinePlayer) player) >= amount) {
                player.sendMessage("§cCapacität Voll!");
                this.playeruuid.remove(player.getUniqueId());
                return;
            }
            if (block.getBlockPower() == 15) {
                (new Energy()).addEnergy((OfflinePlayer) player, 25);
            } else if (block.getBlockPower() < 15) {
                (new Energy()).addEnergy((OfflinePlayer) player, 10);
            }
        }
    }


    public void runOnBeacon(Player player, Block block) {
        Location block1 = new Location(player.getWorld(), block.getX(), block.getY(), (block.getZ() + 1));
        Location block2 = new Location(player.getWorld(), block.getX(), block.getY(), (block.getZ() - 1));
        Location block3 = new Location(player.getWorld(), (block.getX() + 1), block.getY(), block.getZ());
        Location block4 = new Location(player.getWorld(), (block.getX() - 1), block.getY(), block.getZ());
        if (block1.getBlock().getType() == Material.OBSIDIAN && block2.getBlock().getType() == Material.OBSIDIAN && block3
                .getBlock().getType() == Material.OBSIDIAN && block4.getBlock().getType() == Material.OBSIDIAN &&
                block.getType() == Material.BEACON) {
            int amount = 100000;
            if ((new Energy()).getEnergy((OfflinePlayer) player) >= amount) {
                if (this.playeruuid.contains(player.getUniqueId())) {
                    player.sendMessage("§cCapacität Voll!");
                    this.playeruuid.remove(player.getUniqueId());

                    return;
                }
            } else if (this.playeruuid.contains(player.getUniqueId())) {
                (new Energy()).addEnergy((OfflinePlayer) player, 100);
            } else {
                (new Energy()).addEnergy((OfflinePlayer) player, 100);
                this.playeruuid.add(player.getUniqueId());
            }
        }
    }


    public void updateWorld(final World world, final Player player) {
        (new BukkitRunnable() {
            public void run() {
                if (!CapacitorListener.this.runtime.contains(this)) {
                    CapacitorListener.this.runtime.add(this);
                }
                if (CapacitorListener.this.playeruuid.contains(player.getUniqueId())) {


                    Location loc4 = new Location(world, CapacitorListener.this.cfg.getInt("Piston." + player.getName() + ".x"), CapacitorListener.this.cfg.getInt("Piston." + player.getName() + ".y"), CapacitorListener.this.cfg.getInt("Piston." + player.getName() + ".z"));
                    if (world.getBlockAt(loc4).getType() == Material.PISTON) {
                        CapacitorListener.this.run(player, world.getBlockAt(loc4));
                    }


                    try {
                        Location loc = new Location(world, CapacitorListener.this.cfg.getInt("Beacon." + player.getName() + ".x"), CapacitorListener.this.cfg.getInt("Beacon." + player.getName() + ".y"), CapacitorListener.this.cfg.getInt("Beacon." + player.getName() + ".z"));
                        Block block = loc.getBlock();
                        Location block1 = new Location(player.getWorld(), block.getX(), block.getY(), (block.getZ() + 1));
                        Location block2 = new Location(player.getWorld(), block.getX(), block.getY(), (block.getZ() - 1));
                        Location block3 = new Location(player.getWorld(), (block.getX() + 1), block.getY(), block.getZ());
                        Location block4 = new Location(player.getWorld(), (block.getX() - 1), block.getY(), block.getZ());


                        CapacitorListener.this.runOnBeacon(player, world.getBlockAt(loc));
                        if (block1.getBlock().getType() != Material.OBSIDIAN || block2.getBlock().getType() != Material.OBSIDIAN || block3.getBlock().getType() != Material.OBSIDIAN || block4.getBlock().getType() != Material.OBSIDIAN || block.getType() != Material.BEACON || (new Energy()).getEnergy((OfflinePlayer) player) >= 10000)
                            ;


                    } catch (NullPointerException nullPointerException) {
                    }


                    try {
                        Location loc = new Location(world, CapacitorListener.this.cfg.getInt("Piston." + player.getName() + ".x"), CapacitorListener.this.cfg.getInt("Piston." + player.getName() + ".y"), CapacitorListener.this.cfg.getInt("Piston." + player.getName() + ".z"));

                        CapacitorListener.this.run(player, world.getBlockAt(loc));
                        if (world.getBlockAt(loc).getType() != Material.PISTON || (new Energy()).getEnergy((OfflinePlayer) player) >= 500)
                            ;


                    } catch (NullPointerException nullPointerException) {
                    }


                    try {
                        Location loc = new Location(world, CapacitorListener.this.cfg.getInt("Glass." + player.getName() + ".x"), CapacitorListener.this.cfg.getInt("Glass." + player.getName() + ".y"), CapacitorListener.this.cfg.getInt("Glass." + player.getName() + ".z"));
                        Block block = loc.getBlock();
                        Location block1 = new Location(player.getWorld(), block.getX(), block.getY(), (block.getZ() + 1));
                        Location block2 = new Location(player.getWorld(), block.getX(), block.getY(), (block.getZ() - 1));
                        Location block3 = new Location(player.getWorld(), (block.getX() + 1), block.getY(), block.getZ());
                        Location block4 = new Location(player.getWorld(), (block.getX() - 1), block.getY(), block.getZ());


                        CapacitorListener.this.runOnSolar(player, world.getBlockAt(loc));
                        if (block1.getBlock().getType() != Material.OBSIDIAN || block2.getBlock().getType() != Material.OBSIDIAN || block3.getBlock().getType() != Material.OBSIDIAN || block4.getBlock().getType() != Material.OBSIDIAN || block.getType() != Material.GLASS || (new Energy()).getEnergy((OfflinePlayer) player) >= 10000)
                            ;


                    } catch (NullPointerException nullPointerException) {
                    }
                } else {

                    CapacitorListener.this.playeruuid.add(player.getUniqueId());
                }
            }
        }).runTaskTimer((Plugin) Main.getInstance(), 0L, 200L);
    }

    private int getInt(Player player) {
        return player.getEntityId();
    }

    public void runOnSolar(Player player, Block block) {
        Location block1 = new Location(player.getWorld(), block.getX(), block.getY(), (block.getZ() + 1));
        Location block2 = new Location(player.getWorld(), block.getX(), block.getY(), (block.getZ() - 1));
        Location block3 = new Location(player.getWorld(), (block.getX() + 1), block.getY(), block.getZ());
        Location block4 = new Location(player.getWorld(), (block.getX() - 1), block.getY(), block.getZ());
        if (block1.getBlock().getType() == Material.OBSIDIAN && block2.getBlock().getType() == Material.OBSIDIAN && block3
                .getBlock().getType() == Material.OBSIDIAN && block4.getBlock().getType() == Material.OBSIDIAN &&
                block.getType() == Material.GLASS) {
            int amount = 2000000;
            if ((new Energy()).getEnergy((OfflinePlayer) player) >= amount) {
                if (this.playeruuid.contains(player.getUniqueId())) {
                    player.sendMessage("§cCapacität Voll!");
                    this.playeruuid.remove(player.getUniqueId());

                    return;
                }
            } else if (this.playeruuid.contains(player.getUniqueId())) {
                (new Energy()).addEnergy((OfflinePlayer) player, 5);
            } else {
                (new Energy()).addEnergy((OfflinePlayer) player, 5);
                this.playeruuid.add(player.getUniqueId());
            }
        }
    }


    public void saveCfg() {
        try {
            this.cfg.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void blockUpdate(final BlockPlaceEvent event) {
        if (event.getBlock().getType() == Material.BEACON) {
            Player player = event.getPlayer();
            Block block = event.getBlock();
            Location block1 = new Location(player.getWorld(), block.getX(), block.getY(), (block.getZ() + 1));
            Location block2 = new Location(player.getWorld(), block.getX(), block.getY(), (block.getZ() - 1));
            Location block3 = new Location(player.getWorld(), (block.getX() + 1), block.getY(), block.getZ());
            Location block4 = new Location(player.getWorld(), (block.getX() - 1), block.getY(), block.getZ());
            if (block1.getBlock().getType() == Material.OBSIDIAN && block2.getBlock().getType() == Material.OBSIDIAN && block3
                    .getBlock().getType() == Material.OBSIDIAN && block4.getBlock().getType() == Material.OBSIDIAN &&
                    block.getType() == Material.BEACON) {
                if (!this.cfg.getBoolean("Beacon." + event.getPlayer().getName() + ".boolean")) {
                    this.cfg.set("Beacon." + event.getPlayer().getName() + ".x", Integer.valueOf(event.getBlock().getX()));
                    this.cfg.set("Beacon." + event.getPlayer().getName() + ".y", Integer.valueOf(event.getBlock().getY()));
                    this.cfg.set("Beacon." + event.getPlayer().getName() + ".z", Integer.valueOf(event.getBlock().getZ()));
                    this.cfg.set("Beacon." + event.getPlayer().getName() + ".boolean", Boolean.valueOf(true));

                    saveCfg();
                    (new BukkitRunnable() {
                        public void run() {
                            CapacitorListener.this.runtime.add(this);
                            if (event.getPlayer().isOnline()) {
                                CapacitorListener.this.updateWorld(event.getPlayer().getWorld(), event.getPlayer());
                            }
                        }
                    }).runTaskLater((Plugin) Main.getInstance(), 120L);
                    event.getPlayer().sendMessage("§aCapacitor Beacon Aktiviert!");
                } else {
                    event.getPlayer().sendMessage("§a[§6Energy§a] §fNur eine Speicher Kapazität dieser grösse kann verwendet werden!");
                }
            }
        }

        if (event.getBlock().getType() == Material.GLASS) {
            Player player = event.getPlayer();
            Block block = event.getBlock();
            Location block1 = new Location(player.getWorld(), block.getX(), block.getY(), (block.getZ() + 1));
            Location block2 = new Location(player.getWorld(), block.getX(), block.getY(), (block.getZ() - 1));
            Location block3 = new Location(player.getWorld(), (block.getX() + 1), block.getY(), block.getZ());
            Location block4 = new Location(player.getWorld(), (block.getX() - 1), block.getY(), block.getZ());
            if (block1.getBlock().getType() == Material.OBSIDIAN && block2.getBlock().getType() == Material.OBSIDIAN && block3
                    .getBlock().getType() == Material.OBSIDIAN && block4.getBlock().getType() == Material.OBSIDIAN &&
                    block.getType() == Material.GLASS) {
                if (!this.cfg.getBoolean("Glass." + event.getPlayer().getName() + ".boolean")) {
                    this.cfg.set("Glass." + event.getPlayer().getName() + ".x", event.getBlock().getX());
                    this.cfg.set("Glass." + event.getPlayer().getName() + ".y", event.getBlock().getY());
                    this.cfg.set("Glass." + event.getPlayer().getName() + ".z", event.getBlock().getZ());
                    this.cfg.set("Glass." + event.getPlayer().getName() + ".boolean", Boolean.valueOf(true));

                    saveCfg();
                    (new BukkitRunnable() {
                        public void run() {
                            CapacitorListener.this.runtime.add(this);
                            if (event.getPlayer().isOnline()) {
                                CapacitorListener.this.updateWorld(event.getPlayer().getWorld(), event.getPlayer());
                            }
                        }
                    }).runTaskLater((Plugin) Main.getInstance(), 120L);
                    event.getPlayer().sendMessage("§aCapacitor Solar Aktiviert!");
                } else {
                    event.getPlayer().sendMessage("§a[§6Energy§a] §fNur eine Speicher Kapazität dieser grösse kann verwendet werden!");
                }
            }
        }

        if (event.getBlock().getType() == Material.PISTON &&
                !this.cfg.getBoolean("Piston." + event.getPlayer().getName() + ".boolean")) {
            this.cfg.set("Piston." + event.getPlayer().getName() + ".x", Integer.valueOf(event.getBlock().getX()));
            this.cfg.set("Piston." + event.getPlayer().getName() + ".y", Integer.valueOf(event.getBlock().getY()));
            this.cfg.set("Piston." + event.getPlayer().getName() + ".z", Integer.valueOf(event.getBlock().getZ()));
            this.cfg.set("Piston." + event.getPlayer().getName() + ".boolean", Boolean.valueOf(true));
            saveCfg();
            if (!this.file.exists()) {
                try {
                    this.file.mkdir();
                } catch (Exception e) {
                    Bukkit.getConsoleSender().sendMessage("§cAn Issue while Create File §f" + e.getMessage());
                }
            }
            (new BukkitRunnable() {
                public void run() {
                    CapacitorListener.this.runtime.add(this);
                    if (event.getPlayer().isOnline()) {
                        CapacitorListener.this.updateWorld(event.getPlayer().getWorld(), event.getPlayer());
                    }
                }
            }).runTaskLater((Plugin) Main.getInstance(), 120L);
            event.getPlayer().sendMessage("§aCapacitor Piston Aktiviert!");
        }
    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() == Material.PISTON) {


            Location loc = new Location(event.getPlayer().getWorld(), this.cfg.getInt("Piston." + event.getPlayer().getName() + ".x"), this.cfg.getInt("Piston." + event.getPlayer().getName() + ".y"), this.cfg.getInt("Piston." + event.getPlayer().getName() + ".z"));
            if (event.getBlock().getLocation().equals(loc)) {
                this.cfg.set("Piston." + event.getPlayer().getName() + ".boolean", Boolean.valueOf(false));
                saveCfg();
                event.getPlayer().sendMessage("§aCapacitor Piston entfernt!");
            }
        } else if (event.getBlock().getType() == Material.BEACON) {


            Location loc1 = new Location(event.getPlayer().getWorld(), this.cfg.getInt("Beacon." + event.getPlayer().getName() + ".x"), this.cfg.getInt("Beacon." + event.getPlayer().getName() + ".y"), this.cfg.getInt("Beacon." + event.getPlayer().getName() + ".z"));
            if (event.getBlock().getLocation().equals(loc1)) {
                this.cfg.set("Beacon." + event.getPlayer().getName() + ".boolean", Boolean.valueOf(false));
                saveCfg();
                event.getPlayer().sendMessage("§aCapacitor Beacon entfernt!");
            }
        } else if (event.getBlock().getType() == Material.GLASS) {
            Player player = event.getPlayer();
            Block block = event.getBlock();
            Location block1 = new Location(player.getWorld(), block.getX(), block.getY(), (block.getZ() + 1));
            Location block2 = new Location(player.getWorld(), block.getX(), block.getY(), (block.getZ() - 1));
            Location block3 = new Location(player.getWorld(), (block.getX() + 1), block.getY(), block.getZ());
            Location block4 = new Location(player.getWorld(), (block.getX() - 1), block.getY(), block.getZ());
            if (block1.getBlock().getType() == Material.OBSIDIAN && block2.getBlock().getType() == Material.OBSIDIAN && block3
                    .getBlock().getType() == Material.OBSIDIAN && block4.getBlock().getType() == Material.OBSIDIAN) {


                Location loc1 = new Location(event.getPlayer().getWorld(), this.cfg.getInt("Glass." + event.getPlayer().getName() + ".x"), this.cfg.getInt("Glass." + event.getPlayer().getName() + ".y"), this.cfg.getInt("Glass." + event.getPlayer().getName() + ".z"));
                if (event.getBlock().getLocation().equals(loc1)) {
                    this.cfg.set("Glass." + event.getPlayer().getName() + ".boolean", Boolean.valueOf(false));
                    saveCfg();
                    event.getPlayer().sendMessage("§aCapacitor Solar entfernt!");
                }
            }
        }
    }
}


