package de.framedev.frameapi.managers;

import de.framedev.frameapi.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.UUID;

public class ItemBuilder {
    ItemStack itemStack;
    ItemMeta itemMeta;

    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder setDisplayName(String displayName) {
        this.itemMeta.setDisplayName(displayName);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder addItemFlag(ItemFlag itemFlag) {
        this.itemMeta.addItemFlags(itemFlag);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level, boolean ignorelevel) {
        this.itemMeta.addEnchant(enchantment, level, ignorelevel);
        return this;
    }

    public ItemBuilder addAttribute(Attribute attribute, int amount, AttributeModifier.Operation operation, EquipmentSlot equipmentSlot) {
        AttributeModifier attributeModifier = new AttributeModifier(UUID.randomUUID(), attribute.name(), amount, operation, equipmentSlot);
        this.itemMeta.addAttributeModifier(attribute, attributeModifier);
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        this.itemMeta.setLore(Arrays.asList(lore));
        return this;
    }

    public ItemBuilder setUnbreakable(boolean isUnbreakable) {
        this.itemMeta.setUnbreakable(isUnbreakable);
        return this;
    }

    public ItemBuilder hideEnchantments() {
        this.itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    public ItemStack build() {
        try {
            if (this.itemStack != null) {
                if (this.itemMeta != null) {
                    this.itemStack.setItemMeta(this.itemMeta);
                    return this.itemStack;
                }
                return this.itemStack;
            }

            return null;
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            Bukkit.getConsoleSender().sendMessage(Main.Variables.getPrefix() + "Â§cItemStack oder ItemMeta ist Â§6null");
            return null;
        }
    }
}


