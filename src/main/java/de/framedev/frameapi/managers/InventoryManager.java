package de.framedev.frameapi.managers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class InventoryManager {
    private Inventory inventory;
    private int inventorysize;
    private String inventoryname;

    public InventoryManager setSize(int size) {
        this.inventorysize = size;
        return this;
    }

    public InventoryManager setName(String name) {
        this.inventoryname = name;
        return this;
    }

    public InventoryManager build() {
        try {
            this.inventory = Bukkit.getServer().createInventory(null, 9 * this.inventorysize, this.inventoryname);
            setInventory(this.inventory);
        } catch (NullPointerException ex) {
            Bukkit.getConsoleSender().sendMessage("§cThis Inventory Can not Created! " + ex.getMessage());
        }
        return this;
    }

    public InventoryManager setItem(ItemStack item, int position) {
        this.inventory.setItem(position, item);
        return this;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public InventoryManager setInventory(Inventory inventory) {
        this.inventory = inventory;
        return this;
    }

    public int getInventorysize() {
        return this.inventorysize;
    }

    public String getInventoryname() {
        return this.inventoryname;
    }

    public InventoryManager showInv(Player player) {
        player.openInventory(this.inventory);
        return this;
    }

    public InventoryManager FillNull() {
        int size = 9 * this.inventorysize;
        for (int i = 0; i < size; i++) {
            if (this.inventory.getItem(i) == null) {
                this.inventory.setItem(i, (new ItemBuilder(Material.BLACK_STAINED_GLASS)).setDisplayName(" ").build());
            }
        }

        return this;
    }

    public InventoryManager updateInventory(Player player) {
        player.updateInventory();
        return this;
    }
}


