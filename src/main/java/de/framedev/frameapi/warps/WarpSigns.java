package de.framedev.frameapi.warps;

import de.framedev.frameapi.api.API;
import de.framedev.frameapi.main.Main;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class WarpSigns
        implements Listener {
    public static Boolean inConfigList(String name) {
        List<String> signs = Main.getInstance().getConfig().getStringList("Signs");
        if (signs.contains(name)) {
            return true;
        }
        return false;
    }


    public static Boolean UtilinConfigList(String name) {
        List<String> signs = Main.getInstance().getConfig().getStringList("Util");
        return signs.contains(name);
    }


    @EventHandler
    public void signChange(SignChangeEvent e) {
        if (inConfigList("Warp") &&
                e.getLine(0).equalsIgnoreCase("warp")) {
            if (e.getPlayer().hasPermission("frameapi.signs.create")) {
                String[] args = e.getLines();
                String name = args[1];
                e.setLine(0, "§1[Warp]");
                e.setLine(1, name);
            } else {
                e.getPlayer().sendMessage(Main.FrameMainGet.getPrefix() + " " + Main.FrameMainGet.getNoPerm());
            }
        }
    }


    @EventHandler
    public void onInteractFree(PlayerInteractEvent e) {
        if (inConfigList("Warp").booleanValue() &&
                e.getAction() == Action.RIGHT_CLICK_BLOCK &&
                e.getClickedBlock().getState() instanceof Sign) {
            Sign s = (Sign) e.getClickedBlock().getState();
            if (s.getLine(0).equalsIgnoreCase("§1[Warp]"))
                if (e.getPlayer().hasPermission("frameapi.signs.use")) {
                    String[] args = s.getLines();
                    String name = args[1];
                    Location loc = API.Warp.getWarpLocation(name);
                    if (s.getLine(1).equalsIgnoreCase(name)) {
                        e.getPlayer().teleport(loc);
                    }
                } else {
                    e.getPlayer().sendMessage(Main.FrameMainGet.getPrefix() + " " + Main.FrameMainGet.getNoPerm());
                }
        }
    }
}

