package de.framedev.frameapi.cmds;

import de.framedev.frameapi.managers.ChatManager;
import de.framedev.frameapi.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
 * =============================================
 * = This Plugin was Created by FrameDev       =
 * = Copyrighted by FrameDev                   =
 * = Please don't Change anything              =
 * =============================================
 * This Class was made at 23.05.2020, 21:37
 */
public class ChatCommand implements CommandExecutor {

    private final Main plugin;

    public ChatCommand(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("unmute").setExecutor(this);
        plugin.getCommand("chatmute").setExecutor(this);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("unmute")) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                ChatManager chatManager = new ChatManager(target.getName());
                if (chatManager.isMuted()) {
                    chatManager.setMuted(false,0);
                    sender.sendMessage("§aDu hast erfolgreich §b" + target.getName() + " §aentmuted!");
                    target.sendMessage("§aDu wurdest entmuted!");
                }
            } else {
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
                ChatManager chatManager = new ChatManager(offlinePlayer.getName());
                if (chatManager.isMuted()) {
                    chatManager.setMuted(false,0);
                    sender.sendMessage("§aDu hast erfolgreich §b" + offlinePlayer.getName() + " §aentmuted!");
                }
            }
        }
        if(command.getName().equalsIgnoreCase("chatmute")) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                ChatManager chatManager = new ChatManager(target.getName());
                if (chatManager.isMuted()) {
                    sender.sendMessage("§cDieser Spieler hat schon einen ChatMute!");
                } else {
                    chatManager.setMuted(true,Integer.parseInt(args[1]));
                }
            } else {
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
                ChatManager chatManager = new ChatManager(offlinePlayer.getName());
                if (chatManager.isMuted()) {
                    chatManager.setMuted(false,0);
                    sender.sendMessage("§cDieser Spieler hat schon einen ChatMute!");
                } else {
                    chatManager.setMuted(true,Integer.parseInt(args[1]));
                }
            }
        }
        return false;
    }

}
