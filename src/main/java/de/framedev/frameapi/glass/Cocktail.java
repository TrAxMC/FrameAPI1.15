package de.framedev.frameapi.glass;

import de.framedev.frameapi.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;


public class Cocktail
        extends Glas {
    private boolean empty;
    private int filled;
    private int size;

    public Cocktail(int size, int filled, boolean limete) {
        super(size, filled);
        this.itemstack = new ItemStack(Material.POTION);
        this.itemMeta = (PotionMeta) this.itemstack.getItemMeta();
        PotionData data = new PotionData(PotionType.WATER);
        this.itemMeta.setBasePotionData(data);
        this.itemMeta.setUnbreakable(true);
        this.itemMeta.setColor(Color.fromRGB(2, 208, 152));
        this.filled = filled;
        this.size = size;
        this.empty = (filled == 0);
        this.limete = limete;
        this.drinked = false;
    }

    private ItemStack itemstack;
    private boolean limete;
    private PotionMeta itemMeta;
    private boolean drinked;

    public static void register() {
        Bukkit.getPluginManager().registerEvents(new event(), (Plugin) Main.getInstance());
    }

    public boolean isDrinked() {
        return this.drinked;
    }

    public void setDrinked(boolean drinked) {
        this.drinked = drinked;
    }

    public boolean isLimete() {
        return this.limete;
    }

    public void setLimete(boolean limete) {
        this.limete = limete;
    }

    public boolean isEmpty() {
        return this.empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public void onDrink() {
        this.filled = 0;
        this.empty = true;
        this.limete = false;
        this.drinked = true;
    }

    public void fill() {
        this.filled = this.size;
        this.drinked = false;
    }

    public int getFilled() {
        return this.filled;
    }

    public void setFilled(int filled) {
        this.filled = filled;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ItemStack getItemstack() {
        return this.itemstack;
    }

    public void setItemstack(ItemStack itemstack) {
        this.itemstack = itemstack;
    }

    public PotionMeta getItemMeta() {
        return this.itemMeta;
    }

    public void setItemMeta(PotionMeta itemMeta) {
        this.itemMeta = itemMeta;
    }

    public ItemStack build() {
        if (this.itemMeta != null) {
            this.itemMeta.setDisplayName("§aCocktail");
            this.itemstack.setItemMeta((ItemMeta) this.itemMeta);
        }
        return this.itemstack;
    }

    public static class event
            implements Listener {
        @EventHandler
        public void onInteractCocktail(PlayerItemConsumeEvent e) {
            if (e.getItem().getType() == Material.POTION && e
                    .getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aCocktail")) {
                Cocktail tail = new Cocktail(100, 100, true);
                tail.onDrink();
                e.getPlayer().sendMessage("§a§lCocktail §r§bgetrunken");
                if (tail.isEmpty()) {
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
                    e.getPlayer().playEffect(e.getPlayer().getLocation(), Effect.ENDER_SIGNAL, 1000);
                    e.getPlayer().setFoodLevel(e.getPlayer().getFoodLevel() + 4);
                }
            }
        }
    }
}


