package de.framedev.frameapi.api.energy;

import de.framedev.frameapi.enums.EnergyEinheiten;
import de.framedev.frameapi.interfaces.EnergyInterfaceWithEnergyEinheiten;
import org.bukkit.OfflinePlayer;


public class EnergyWithEnergyUnits
        implements EnergyInterfaceWithEnergyEinheiten {
    public void addEnergy(OfflinePlayer player, int amount, EnergyEinheiten energyEinheiten) {
        int energy = (new Energy()).getEnergy(player);
        amount += energyEinheiten.getAmount();
        energy += amount;
        (new Energy()).setEnergy(player, energy);
    }


    public void removeEnergy(OfflinePlayer player, int amount, EnergyEinheiten energyEinheiten) {
        int energy = (new Energy()).getEnergy(player);
        amount += energyEinheiten.getAmount();
        energy -= amount;
        (new Energy()).setEnergy(player, energy);
    }
}


