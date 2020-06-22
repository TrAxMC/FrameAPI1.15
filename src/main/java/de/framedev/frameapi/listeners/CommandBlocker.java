package de.framedev.frameapi.listeners;

import de.framedev.frameapi.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;

import java.util.ArrayList;
import java.util.List;

/*
 * =============================================
 * = This Plugin was Created by FrameDev       =
 * = Copyrighted by FrameDev                   =
 * = Please don't Change anything              =
 * =============================================
 * This Class was made at 07.03.2020, 10:06
 */
public class CommandBlocker implements Listener {

    private final Main plugin;

    public CommandBlocker(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }

    @EventHandler
    public void onTabDisable(PlayerCommandSendEvent e) {

        List<String> blockedCommands = new ArrayList<>();

        if (!e.getPlayer().hasPermission("frameapi.help")) {
            blockedCommands.add("frameapi:help");
        }
        if (!e.getPlayer().hasPermission("frameapi.getitem")) {
            blockedCommands.add("getitem");
        }
        if (!e.getPlayer().hasPermission("frameapi.startbudget")) {
            blockedCommands.add("frameapi:startbudget");
        }
        if (!e.getPlayer().hasPermission("frameapi.teleporter")) {
            blockedCommands.add("teleporter");
        }

        if (!e.getPlayer().hasPermission("frameapi.backtp")) {
            blockedCommands.add("back");
        }
        if (!e.getPlayer().hasPermission("frameapi.spawnmob")) {
            blockedCommands.add("spawnmob");
        }

        if (!e.getPlayer().hasPermission("frameapi.denied")) {
            blockedCommands.add("denied");
        }

        if (!e.getPlayer().hasPermission("frameapi.heal")) {
            blockedCommands.add("heal");
        }

        if (!e.getPlayer().hasPermission("frameapi.mysql")) {
            blockedCommands.add("frameapi:getmysql");
        }

        if (!e.getPlayer().hasPermission("frameapi.set")) {
            blockedCommands.add("set");
        }

        if (!e.getPlayer().hasPermission("frameapi.balance")) {
            blockedCommands.add("balance");
            blockedCommands.add("bal");
        }

        if (!e.getPlayer().hasPermission("frameapi.bankbalance")) {
            blockedCommands.add("bankbalance");
        }

        if (!e.getPlayer().hasPermission("frameapi.setbank")) {
            blockedCommands.add("setbank");
        }

        if (!e.getPlayer().hasPermission("frameapi.transfer")) {
            blockedCommands.add("transfer");
        }

        if (!e.getPlayer().hasPermission("frameapi.withdraw")) {
            blockedCommands.add("withdraw");
        }

        if (!e.getPlayer().hasPermission("frameapi.deposit")) {
            blockedCommands.add("deposit");
        }

        if (!e.getPlayer().hasPermission("frameapi.warp")) {
            blockedCommands.add("warp");
        }

        if (!e.getPlayer().hasPermission("frameapi.setwarp")) {
            blockedCommands.add("setwarp");
        }

        if (!e.getPlayer().hasPermission("frameapi.warpremove")) {
            blockedCommands.add("removewarp");
        }

        if (!e.getPlayer().hasPermission("frameapi.admin")) {
            blockedCommands.add("frameapi");
        }

        if (!e.getPlayer().hasPermission("frameapi.afk")) {
            blockedCommands.add("afk");
            blockedCommands.add("aafk");
            blockedCommands.add("awayfromkeyboard");
            blockedCommands.add("away");
        }
        if (!e.getPlayer().hasPermission("frameapi.fly")) {
            blockedCommands.add("fly");
        }
        if (!e.getPlayer().hasPermission("frameapi.vanish")) {
            blockedCommands.add("vanish");
            blockedCommands.add("v");
        }
        if (!e.getPlayer().hasPermission("frameapi.item")) {
            blockedCommands.add("item");
            blockedCommands.add("i");
        }
        if (!e.getPlayer().hasPermission("frameapi.tps")) {
            blockedCommands.add("tps");
        }
        if (!e.getPlayer().hasPermission("frameapi.cleartps")) {
            blockedCommands.add("cleartps");
        }
        if (!e.getPlayer().hasPermission("frameapi.fly")) {
            blockedCommands.add("fly");
        }
        if(!e.getPlayer().hasPermission("frameapi.balancetop")) {
            blockedCommands.add("balancetop");
            blockedCommands.add("baltop");
        }
        e.getCommands().removeAll(blockedCommands);
        e.getCommands().removeIf(string -> string.contains(":"));
    }

}
