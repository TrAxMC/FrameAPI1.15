package de.framedev.frameapi.interfaces;

import de.framedev.frameapi.api.API;
import de.framedev.frameapi.api.energy.Energy;
import de.framedev.frameapi.api.energy.EnergyWithEnergyUnits;
import de.framedev.frameapi.api.money.Money;
import de.framedev.frameapi.kits.KitManager;
import de.framedev.frameapi.managers.WorldManager;
import de.framedev.frameapi.pets.Pets;
import java.util.ArrayList;

public interface Constructors {

    Energy energy = new Energy();
    EnergyWithEnergyUnits energyeinheiten = new EnergyWithEnergyUnits();
    API api = new API();
    WorldManager worldmanager = new WorldManager();
    KitManager kit = new KitManager();
    Money eco = new Money();
    Pets pet = new Pets();
    ArrayList<String> pays = new ArrayList<>();
    ArrayList<Integer> lol = new ArrayList<>();

}
