package de.framedev.frameapi.interfaces;

import de.framedev.frameapi.enums.EnergyEinheiten;
import org.bukkit.OfflinePlayer;

public interface EnergyInterfaceWithEnergyEinheiten {
  void addEnergy(OfflinePlayer paramOfflinePlayer, int paramInt, EnergyEinheiten paramEnergyEinheiten);
  
  void removeEnergy(OfflinePlayer paramOfflinePlayer, int paramInt, EnergyEinheiten paramEnergyEinheiten);
}


