package de.framedev.frameapi.listeners;

import de.framedev.frameapi.main.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.scheduler.BukkitRunnable;

/*
 * =============================================
 * = This Plugin was Created by FrameDev       =
 * = Copyrighted by FrameDev                   =
 * = Please don't Change anything              =
 * =============================================
 * This Class was made at 06.03.2020, 17:45
 */
public class SleepListener implements Listener {

    @EventHandler
    public void onSleep(PlayerBedEnterEvent event) {
        if(Main.getInstance().getConfig().getBoolean("SkipNight")) {
            if (event.getPlayer().getWorld().getTime() >= 12541) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        event.getPlayer().getWorld().setTime(50);
                    }
                }.runTaskLater(Main.getInstance(), 240);
            }
        }
    }
}
