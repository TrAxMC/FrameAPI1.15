package de.framedev.frameapi.managers;

import de.framedev.frameapi.cmds.ChunkloaderCMD;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;
import java.util.ArrayList;


public class ChunkloaderManager {
    public void addOrRemoveChunkloader(Location location, String chunkname) {
        if (!location.getChunk().isLoaded()) {
            if (!location.getChunk().isForceLoaded()) {
                location.getChunk().setForceLoaded(true);
                double x = location.getX();
                double y = location.getY();
                double z = location.getZ();
                ChunkloaderCMD.cfg.set("Chunks." + chunkname + ".x", Double.valueOf(x));
                ChunkloaderCMD.cfg.set("Chunks." + chunkname + ".y", Double.valueOf(y));
                ChunkloaderCMD.cfg.set("Chunks." + chunkname + ".z", Double.valueOf(z));
                ChunkloaderCMD.cfg.set("Chunks." + chunkname + ".boolean", Boolean.valueOf(true));
                try {
                    ChunkloaderCMD.cfg.save(ChunkloaderCMD.file);
                    if (!ChunkloaderCMD.file.exists()) {
                        ChunkloaderCMD.file.createNewFile();
                        ChunkloaderCMD.file.mkdir();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } else if (location.getChunk().isForceLoaded() &&
                ChunkloaderCMD.cfg.getBoolean("Chunks." + chunkname + ".boolean")) {
            location.getChunk().setForceLoaded(false);
        }
    }


    FileConfiguration cfg = ChunkloaderCMD.cfg;

    public ArrayList<Chunk> getLoadedChunks() throws NullPointerException {
        ConfigurationSection cs = ChunkloaderCMD.cfg.getConfigurationSection("Chunks");
        ArrayList<Chunk> chunks = new ArrayList<>();
        if (cs != null) {
            for (String s : cs.getKeys(false)) {
                if (this.cfg.getBoolean("Chunks." + s + ".boolean")) {
                    Location loc = new Location(Bukkit.getWorld("world"), this.cfg.getDouble("Chunks." + s + ".x"), this.cfg.getDouble("Chunks." + s + ".y"), this.cfg.getDouble("Chunks." + s + ".z"));
                    if (loc.getChunk().isForceLoaded()) {
                        chunks.add(loc.getChunk());
                    }
                }
            }
        }
        return chunks;
    }
}


