package de.framedev.frameapi.cmds;

import de.framedev.frameapi.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class ChunkloaderCMD
        implements CommandExecutor {
    public static File file = new File("plugins/FrameAPI/chunkloader.yml");
    public static FileConfiguration cfg = (FileConfiguration) YamlConfiguration.loadConfiguration(file);
    private final Main plugin;

    public ChunkloaderCMD(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("loader").setExecutor(this);
    }


    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("loader")) {
                if (player.hasPermission("frameapi.chunkloader")) {
                    if (cfg.get(args[0] + ".boolean") != null) {
                        if (!player.getWorld().getChunkAt(player.getLocation()).isForceLoaded()) {
                            if (!cfg.getBoolean(args[0] + ".boolean")) {
                                if (cfg.getString(args[0] + ".name").equalsIgnoreCase(args[0])) {
                                    cfg.set(args[0] + ".boolean", Boolean.valueOf(true));
                                    cfg.set(args[0] + ".name", args[0]);
                                    cfg.set(args[0] + ".world", player.getWorld().getName());
                                    cfg.set(args[0] + ".x", Integer.valueOf(player.getLocation().getBlockX()));
                                    cfg.set(args[0] + ".y", Integer.valueOf(player.getLocation().getBlockY()));
                                    cfg.set(args[0] + ".z", Integer.valueOf(player.getLocation().getBlockZ()));
                                    try {
                                        cfg.save(file);
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
                                    player.sendMessage("§b" + args[0] + " §aAdded to ChunkLoader");
                                    player.getWorld().getChunkAt(player.getLocation()).setForceLoaded(true);
                                } else {
                                    cfg.set(args[0] + ".boolean", Boolean.valueOf(true));
                                    cfg.set(args[0] + ".name", args[0]);
                                    cfg.set(args[0] + ".world", player.getWorld().getName());
                                    cfg.set(args[0] + ".x", Integer.valueOf(player.getLocation().getBlockX()));
                                    cfg.set(args[0] + ".y", Integer.valueOf(player.getLocation().getBlockY()));
                                    cfg.set(args[0] + ".z", Integer.valueOf(player.getLocation().getBlockZ()));
                                    try {
                                        cfg.save(file);
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
                                    player.sendMessage("§b" + args[0] + " §aAdded to ChunkLoader");
                                    player.getWorld().getChunkAt(player.getLocation()).setForceLoaded(true);
                                }

                            }
                        } else if (cfg.getBoolean(args[0] + ".boolean")) {
                            player.getWorld().getChunkAt(player.getLocation()).setForceLoaded(false);
                            cfg.set(args[0] + ".boolean", false);
                            try {
                                cfg.save(file);
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
                            player.sendMessage("§b" + args[0] + " §aRemoved to ChunkLoader");
                        }

                    } else if (!player.getWorld().getChunkAt(player.getLocation()).isForceLoaded()) {
                        cfg.set(args[0] + ".boolean", Boolean.valueOf(true));
                        cfg.set(args[0] + ".name", args[0]);
                        cfg.set(args[0] + ".world", player.getWorld().getName());
                        cfg.set(args[0] + ".x", Integer.valueOf(player.getLocation().getBlockX()));
                        cfg.set(args[0] + ".y", Integer.valueOf(player.getLocation().getBlockY()));
                        cfg.set(args[0] + ".z", Integer.valueOf(player.getLocation().getBlockZ()));
                        try {
                            cfg.save(file);
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
                        player.sendMessage("§b" + args[0] + " §aAdded to ChunkLoader");
                        player.getWorld().getChunkAt(player.getLocation()).setForceLoaded(true);
                    } else {
                        player.getWorld().getChunkAt(player.getLocation()).setForceLoaded(false);
                        cfg.set(args[0] + ".boolean", Boolean.valueOf(false));
                        try {
                            cfg.save(file);
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
                        player.sendMessage("§b" + args[0] + " §aRemoved to ChunkLoader");
                    }
                } else {
                    player.sendMessage(Main.Variables.getPrefix() + " " + Main.Variables.getNoPerm());
                }
            }
        }
        return false;
    }
}


