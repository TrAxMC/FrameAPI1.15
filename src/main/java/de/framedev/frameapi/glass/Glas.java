package de.framedev.frameapi.glass;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class Glas {
    private int size;
    private int filled;
    private boolean empty;
    private ItemStack itemStack;
    private ItemMeta potionMeta;

    public Glas(int size, int filled) {
        this.itemStack = new ItemStack(Material.GLASS_BOTTLE);
        this.potionMeta = this.itemStack.getItemMeta();
        this.potionMeta.setDisplayName("§aGlas");
        this.size = size;
        this.filled = filled;
        this.empty = (filled == 0);
    }

    public boolean isEmpty() {
        return this.empty;
    }

    public void fill() {
        this.filled = this.size;
        this.empty = false;
    }

    public void onDrink() {
        this.empty = true;
        this.filled = 0;
    }

    public ItemStack build() {
        if (this.potionMeta != null) {
            this.itemStack.setItemMeta(this.potionMeta);
        }
        return this.itemStack;
    }
}


