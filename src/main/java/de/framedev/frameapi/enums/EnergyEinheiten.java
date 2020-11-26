package de.framedev.frameapi.enums;

public enum EnergyEinheiten {
    VOLT("Volt", 140),
    AMPERE("Ampere", 540),
    MILIAMPERE("Miliampere", 100),
    WATT("Watt", 10),
    OHM("Ohm", 120);

    String name;
    int amount;

    EnergyEinheiten(String name, int amount) {
        this.amount = amount;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}


