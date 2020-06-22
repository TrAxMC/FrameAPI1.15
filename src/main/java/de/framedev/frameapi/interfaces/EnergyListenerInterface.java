package de.framedev.frameapi.interfaces;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public interface EnergyListenerInterface {
  void run(Player paramPlayer, Block paramBlock);
  
  void updateWorld(World paramWorld, Player paramPlayer);
}


