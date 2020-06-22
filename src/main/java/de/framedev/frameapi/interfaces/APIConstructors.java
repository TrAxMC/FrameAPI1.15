package de.framedev.frameapi.interfaces;/*
 * =============================================
 * = This Plugin was Created by FrameDev       =
 * = Copyrighted by FrameDev                   =
 * = Please don't Change anything              =
 * =============================================
 * This Class was made at 17.05.2020, 21:54
 */

import de.framedev.frameapi.api.API;
import de.framedev.frameapi.api.energy.Energy;
import de.framedev.frameapi.api.energy.EnergyWithEnergyUnits;
import de.framedev.frameapi.api.money.Money;

public interface APIConstructors {

    Energy energy = new Energy();
    EnergyWithEnergyUnits energyUnits = new EnergyWithEnergyUnits();
    Money money = new Money();
    API api = new API();
}
