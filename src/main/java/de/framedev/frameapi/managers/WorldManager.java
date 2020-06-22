package de.framedev.frameapi.managers;

import de.framedev.frameapi.interfaces.Constructors;
import org.bukkit.Chunk;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;


public class WorldManager
        implements Constructors {
    public Chunk getChunk(Player player) {
        return player.getWorld().getChunkAt(player.getLocation());
    }

    public String getWorldName(Player player) {
        return player.getWorld().getName();
    }

    public boolean isNorth(Player player) {
        return player.getFacing() == BlockFace.NORTH;
    }


    public boolean isSouth(Player player) {
        return player.getFacing() == BlockFace.SOUTH;
    }


    public boolean isEast(Player player) {
        return player.getFacing() == BlockFace.EAST;
    }


    public boolean isWest(Player player) {
        return player.getFacing() == BlockFace.WEST;
    }


    public double getWorldBorderSize(Player player) {
        return player.getWorld().getWorldBorder().getSize();
    }
}


