package de.framedev.frameapi.managers;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import de.framedev.frameapi.main.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.File;

/*
 * =============================================
 * = This Plugin was Created by FrameDev       =
 * = Copyrighted by FrameDev                   =
 * = Please don't Change anything              =
 * =============================================
 * This Class was made at 23.05.2020, 01:24
 */
public class ServerManager implements PluginMessageListener {
    File file = new File(Main.getInstance().getDataFolder(),"servers.yml");
    FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public FileConfiguration getCfg() {
        return cfg;
    }

    public File getFile() {
        return file;
    }

    @Override
        public void onPluginMessageReceived(String channel, Player player, byte[] message) {
            if (!channel.equals("BungeeCord")) {
                return;
            }
            ByteArrayDataInput in = ByteStreams.newDataInput(message);
            String subchannel = in.readUTF();

        }

        /**
         * @param player Player
         * @param server server to connect
         */
        public void connect(Player player, String server) {
            ByteArrayDataOutput output = ByteStreams.newDataOutput();
            output.writeUTF("Connect");
            output.writeUTF(server);
            player.sendPluginMessage(Main.getInstance(), "BungeeCord", output.toByteArray());
        }


}
