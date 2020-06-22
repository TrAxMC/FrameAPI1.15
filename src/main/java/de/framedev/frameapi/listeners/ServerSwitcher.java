package de.framedev.frameapi.listeners;

import de.framedev.frameapi.main.Main;
import de.framedev.frameapi.managers.ServerManager;
import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/*
 * =============================================
 * = This Plugin was Created by FrameDev       =
 * = Copyrighted by FrameDev                   =
 * = Please don't Change anything              =
 * =============================================
 * This Class was made at 23.05.2020, 01:24
 */
public class ServerSwitcher implements Listener {

    public ServerSwitcher(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onCreateSign(SignChangeEvent event) {
        if(event.getLine(0).equalsIgnoreCase("server")) {
            event.setLine(0,"§6[Server]");
            for(String s : new ServerManager().getCfg().getStringList("servers")) {
                if(event.getLine(1).equalsIgnoreCase(s)) {
                    event.setLine(1, s);
                }
            }
        }
    }

    @EventHandler
    public void onClickSign(PlayerInteractEvent event) {
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(event.getClickedBlock() != null) {
                if(event.getClickedBlock().getState() instanceof Sign) {
                    Sign sign = (Sign) event.getClickedBlock().getState();
                    if(sign.getLine(0).equalsIgnoreCase("§6[Server]")) {
                        new ServerManager().connect(event.getPlayer(),sign.getLine(1));
                    }
                }
            }
        }
    }
}
