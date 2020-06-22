package de.framedev.frameapi.listeners;

import de.framedev.frameapi.managers.ChatManager;
import de.framedev.frameapi.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/*
 * =============================================
 * = This Plugin was Created by FrameDev       =
 * = Copyrighted by FrameDev                   =
 * = Please don't Change anything              =
 * =============================================
 * This Class was made at 23.05.2020, 21:38
 */
public class ChatListener implements Listener {

    public ChatListener(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (new ChatManager(event.getPlayer().getName()).isMuted()) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("§cDu bist zurzeit gemuted §bZeit = §6" + new ChatManager(event.getPlayer().getName()).getTime());
        }
    }
}
