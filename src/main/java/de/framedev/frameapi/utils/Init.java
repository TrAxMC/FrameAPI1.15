package de.framedev.frameapi.utils;

import de.framedev.frameapi.afk.AFK;
import de.framedev.frameapi.api.*;
import de.framedev.frameapi.api.energy.Energy;
import de.framedev.frameapi.managers.ChatManager;
import de.framedev.frameapi.api.money.Money;
import de.framedev.frameapi.cmds.*;
import de.framedev.frameapi.glass.Cocktail;
import de.framedev.frameapi.interfaces.Constructors;
import de.framedev.frameapi.listeners.*;
import de.framedev.frameapi.listeners.energy.CapacitorListener;
import de.framedev.frameapi.listeners.money.MoneyBankSigns;
import de.framedev.frameapi.listeners.money.MoneySigns;
import de.framedev.frameapi.main.Main;
import de.framedev.frameapi.managers.FileManager;
import de.framedev.frameapi.managers.ServerManager;
import de.framedev.frameapi.mysql.MYSQL;
import de.framedev.frameapi.warps.WarpSigns;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.io.IOException;
import java.util.ArrayList;

public class Init implements Constructors {

    public Init(Main plugin) {
        this.plugin = plugin;
        PluginManager pm = Bukkit.getPluginManager();
        new TrashCMD(plugin);
        if (WarpSigns.UtilinConfigList("Energy")) {
            if (plugin.getConfig().getBoolean("MYSQL.Boolean")) {
                new Energy();
                new EnergyCMD(plugin);
                pm.registerEvents((Listener) new CapacitorListener(), (Plugin) plugin);
                Bukkit.getConsoleSender().sendMessage(Main.FrameMainGet.getPrefix() + " §6successfully Energy Registered!");
            }
        }
        if(plugin.getConfig().getBoolean("BungeeCord")) {
            new ServerSwitcher(plugin);
            ServerManager man = new ServerManager();
            ArrayList<String> servers = new ArrayList<>();
            ArrayList<String> cfgServers = (ArrayList<String>) man.getCfg().getStringList("servers");
            if(cfgServers != null) {
                for(String s : cfgServers) {
                    servers.add(s);
                    man.getCfg().set("servers", servers);
                    try {
                        man.getCfg().save(man.getFile());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                servers.add("lobby");
                servers.add("yourServer");
                man.getCfg().set("servers", servers);
                try {
                    man.getCfg().save(man.getFile());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //Nick Sachen
        new ChatCommand(plugin);
        new ChatListener(plugin);
        new FrameCMDS(plugin);
        new FrameAPICMDS(plugin);
        new FlyCMD(plugin);
        new CommandBlocker(plugin);
        new EnderChestCMD(plugin);
        new InvSeeCMD(plugin);
        new BackPackCMD(plugin);
        if (WarpSigns.UtilinConfigList("Money")) {
            new MoneyCMD(plugin);
            new MoneySigns(plugin);
        }
        if (WarpSigns.UtilinConfigList("Bank")) {
            new MoneyBankCMD(plugin);
            new MoneyBankSigns(plugin);
        }

        new ChatManager().runable();
        new PositionCMD(Main.getInstance());
        new FileManager(Main.getInstance());
        new ChunkloaderCMD(Main.getInstance());
        new VanishCMD(plugin);
        if (plugin.getConfig().getBoolean("AFK.Boolean")) {
            new AFK(plugin);
        }
        Cocktail.register();
        if (WarpSigns.UtilinConfigList("Money")) {
            pm.registerEvents(new Money(), plugin); 
        }
        pm.registerEvents(new API(), plugin);
        pm.registerEvents(new WarpSigns(), plugin);
        new PlayerKillPlayer(plugin);
        if (plugin.getConfig().getBoolean("MYSQL.Boolean")) {
            new SaveEnderChest(plugin);
            if (plugin.getConfig().getBoolean("SavePlayersInventory")) {
                new SavePlayersInventory(plugin);
            }
            if (plugin.getConfig().getBoolean("SaveInventoryMYSQL")) {
                new SaveInventory(plugin);
                new OpenOtherInventory(plugin);
            }
        } else {
            Bukkit.getConsoleSender().sendMessage(MYSQL.MySQLPrefix + " §cNot Found, Fuctions are Disabled");
        }
        pm.registerEvents((Listener) new LeaveListener(), (Plugin) plugin);
        pm.registerEvents((Listener) new JoinListener(), (Plugin) plugin);
        pm.registerEvents(new SleepListener(),plugin);
        Bukkit.getConsoleSender().sendMessage(Main.FrameMainGet.getPrefix() + " §6successfully initialized");
    }

    private final Main plugin;
}