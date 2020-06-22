package de.framedev.frameapi.interfaces;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public interface EnergyInterface {
  void addEnergy(OfflinePlayer paramOfflinePlayer, int paramInt);
  
  void removeEnergy(OfflinePlayer paramOfflinePlayer, int paramInt);
  
  int getEnergy(OfflinePlayer paramOfflinePlayer);
  
  void setEnergy(OfflinePlayer paramOfflinePlayer, int paramInt);
  
  void adminEnergy(Player paramPlayer);
}


