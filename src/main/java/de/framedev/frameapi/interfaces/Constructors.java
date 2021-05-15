package de.framedev.frameapi.interfaces;

import de.framedev.frameapi.api.API;
import de.framedev.frameapi.api.energy.Energy;
import de.framedev.frameapi.api.energy.EnergyWithEnergyUnits;
import de.framedev.frameapi.api.money.Money;
import de.framedev.frameapi.kits.KitManager;

import java.util.ArrayList;

public interface Constructors {

    Energy energy = new Energy();
    EnergyWithEnergyUnits energyeinheiten = new EnergyWithEnergyUnits();
    API api = new API();
    KitManager kit = new KitManager();
    Money eco = new Money();
    ArrayList<String> pays = new ArrayList<>();
    ArrayList<Integer> lol = new ArrayList<>();

}
