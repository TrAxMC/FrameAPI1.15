package de.framedev.frameapi.enums;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;


public enum MaterialInID {
    AIR("AIR", Material.AIR, 0, 0),
    STONE("STONE", Material.STONE, 1, 0),
    GRASS("GRASS", Material.GRASS_BLOCK, 2, 0),
    DIRT("DIRT", Material.DIRT, 3, 0),
    COBBLESTONE("COBBLESTONE", Material.COBBLESTONE, 4, 0),
    OAK_WOOD_PLANKS("OAK_PLANKS", Material.OAK_PLANKS, 5, 0),
    OAK_SAPLING("OAK_SAPLING", Material.OAK_SAPLING, 6, 0),
    BEDROCK("BEDROCK", Material.BEDROCK, 7, 0),
    FLOWING_WATER("FLOWING_WATER", Material.WATER, 8, 0),
    WATER("WATER", Material.WATER, 9, 0),
    FLOWING_LAVA("FLOWING_LAVA", Material.LAVA, 10, 0),
    LAVA("LAVA", Material.LAVA, 11, 0),
    SAND("SAND", Material.SAND, 12, 0),
    GRAVEL("GRAVEL", Material.GRAVEL, 13, 0),
    GOLD_ORE("GOLD_ORE", Material.GOLD_ORE, 14, 0),
    IRON_ORE("IRON_ORE", Material.IRON_ORE, 15, 0),
    COAL_ORE("COAL_ORE", Material.COAL_ORE, 16, 0),
    OAK_WOOD("OAK_WOOD", Material.OAK_WOOD, 17, 0),
    OAK_LEAVES("OAK_LEAVES", Material.OAK_LEAVES, 18, 0),
    SPONGE("SPONGE", Material.SPONGE, 19, 0),
    GLASS("GLASS", Material.GLASS, 20, 0),
    LAPIS_ORE("LAPIS_ORE", Material.LAPIS_ORE, 21, 0),
    LAPIS_BLOCK("LAPIS_BLOCK", Material.LAPIS_BLOCK, 22, 0),
    DISPENSER("DISPENSER", Material.DISPENSER, 23, 0),
    SANDSTONE("SANDSTONE", Material.SANDSTONE, 24, 0),
    NOTE_BLOCK("NOTE_BLOCK", Material.NOTE_BLOCK, 25, 0),
    BED("BED", Material.WHITE_BED, 26, 0),
    POWERED_RAIL("POWERED_RAIL", Material.POWERED_RAIL, 27, 0),
    DETECTOR_RAIL("DETECTOR_RAIL", Material.DETECTOR_RAIL, 28, 0),
    STICKY_PISTON("STICKY_PISTON", Material.STICKY_PISTON, 29, 0),
    COBWEB("COBWEB", Material.COBWEB, 30, 0),
    DEAD_SHRUB("DEAD_SHRUB", Material.DEAD_BUSH, 31, 0),
    PISTON("PISTON", Material.PISTON, 33, 0),
    PISTON_HEAD("PISTON_HEAD", Material.PISTON_HEAD, 34, 0),
    WHITE_WOOL("WHITE_WOOL", Material.WHITE_WOOL, 35, 0),
    DANDELION("DANDELION", Material.DANDELION, 37, 0),
    POPPY("POPPY", Material.POPPY, 38, 0),
    BROWN_MUSHROOM("BROWN_MUSHROOM", Material.BROWN_MUSHROOM, 39, 0),
    RED_MUSHROOM("RED_MUSHROOM", Material.RED_MUSHROOM, 40, 0),
    GOLD_BLOCK("GOLD_BLOCK", Material.GOLD_BLOCK, 41, 0),
    IRON_BLOCK("IRON_BLOCK", Material.IRON_BLOCK, 42, 0),
    BRICKS("BRICKS", Material.BRICKS, 45, 0),
    TNT("TNT", Material.TNT, 46, 0),
    BOOKSHELF("BOOKSHELF", Material.BOOKSHELF, 47, 0),
    MOSS_STONE("MOSS_STONE", Material.MOSSY_COBBLESTONE, 48, 0),
    OBSIDIAN("OBSIDIAN", Material.OBSIDIAN, 49, 0),
    TORCH("TORCH", Material.TORCH, 50, 0),
    FIRE("FIRE", Material.FIRE, 51, 0),
    MOB_SPAWNER("MOB_SPAWNER", Material.SPAWNER, 52, 0),
    OAK_WOOD_STAIRS("OAK_WOOD_STAIRS", Material.OAK_STAIRS, 53, 0),
    CHEST("CHEST", Material.CHEST, 54, 0),
    REDSTONE_WIRE("REDSTONE_WIRE", Material.REDSTONE_WIRE, 55, 0),
    DIAMOND_ORE("DIAMOND_ORE", Material.DIAMOND_ORE, 56, 0),
    DIAMOND_BLOCK("DIAMOND_BLOCK", Material.DIAMOND_BLOCK, 57, 0),
    CRAFTING_TABLE("CRAFTING_TABLE", Material.CRAFTING_TABLE, 58, 0),
    WHEAT_CROPS("WHEAT_CROPS", Material.WHEAT, 59, 0),
    FARMLAND("FARMLAND", Material.FARMLAND, 60, 0),
    FURNACE("FURNACE", Material.FURNACE, 61, 0),
    BURNING_FURNACE("BURNING_FURNACE", Material.LEGACY_BURNING_FURNACE, 62, 0),

    STANDING_SIGN("STANDING_SIGN", Material.OAK_SIGN, 63, 0),
    OAK_DOOR("OAK_DOOR", Material.OAK_DOOR, 64, 0),
    LADDER("LADDER", Material.LADDER, 65, 0),
    RAIL("RAIL", Material.RAIL, 66, 0),
    COBBLESTONE_STAIRS("COBBLESTONE_STAIRS", Material.COBBLESTONE_STAIRS, 67, 0),
    WALL_SIGN("WALL_SIGN", Material.OAK_WALL_SIGN, 68, 0),
    LEVER("LEVER", Material.LEVER, 69, 0),
    STONE_PRESSURE_PLATE("STONE_PRESSURE_PLATE", Material.STONE_PRESSURE_PLATE, 70, 0),
    IRON_DOOR("IRON_DOOR", Material.IRON_DOOR, 71, 0),
    OAK_PRESSURE_PLATE("OAK_PRESSURE_PLATE", Material.OAK_PRESSURE_PLATE, 72, 0),
    REDSTONE_ORE("REDSTONE_ORE", Material.REDSTONE_ORE, 73, 0),
    GLOWING_REDSTONE_ORE("GLOWING_REDSTONE_ORE", Material.LEGACY_GLOWING_REDSTONE_ORE, 74, 0),

    REDSTONE_TORCH_OFF("REDSTONE_TORCH_OFF", Material.LEGACY_REDSTONE_TORCH_OFF, 75, 0),

    REDSTONE_TORCH("REDSTONE_TORCH", Material.REDSTONE_TORCH, 76, 0),
    STONE_BUTTON("STONE_BUTTON", Material.STONE_BUTTON, 77, 0),
    SNOW_LAYER("SNOW_LAYER", Material.SNOW, 78, 0),
    ICE("ICE", Material.ICE, 79, 0),
    SNOW_BLOCK("SNOW_BLOCK", Material.SNOW_BLOCK, 80, 0),
    CACTUS("CACTUS", Material.CACTUS, 81, 0),
    CLAY("CLAY", Material.CLAY, 82, 0),
    SUGAR_CANES("SUGAR_CANES", Material.SUGAR_CANE, 83, 0),
    JUKEBOX("JUKEBOX", Material.JUKEBOX, 84, 0),
    OAK_FENCE("OAK_FENCE", Material.OAK_FENCE, 85, 0),
    PUMPKIN("PUMPKIN", Material.PUMPKIN, 86, 0),
    NETHERRACK("NETHERRACK", Material.NETHERRACK, 87, 0),
    SOUL_SAND("SOUL_SAND", Material.SOUL_SAND, 88, 0),
    GLOWSTONE("GLOWSTONE", Material.GLOWSTONE, 89, 0),
    NETHER_PORTAL("NETHER_PORTAL", Material.NETHER_PORTAL, 90, 0),
    JACK_O_LANTERN("JACK_O_LANTERN", Material.JACK_O_LANTERN, 91, 0),
    CAKE_BLOCK("CAKE_BLOCK", Material.CAKE, 92, 0),
    WHITE_STAINED_GLASS("WHITE_STAINED_GLASS", Material.WHITE_STAINED_GLASS, 93, 0),
    OAK_TRAPDOOR("OAK_TRAPDOOR", Material.OAK_TRAPDOOR, 96, 0),
    STONE_MONSTER_EGG("STONE_MONSTER_EGG", Material.LEGACY_MONSTER_EGG, 97, 0),

    STONEBRICK("STONEBRICK", Material.STONE_BRICKS, 98, 0),
    BROWN_MUSHROOM_BLOCK("BROWN_MUSHROOM_BLOCK", Material.BROWN_MUSHROOM_BLOCK, 99, 0),
    RED_MUSHROOM_BLOCK("RED_MUSHROOM_BLOCK", Material.RED_MUSHROOM_BLOCK, 100, 0),
    IRON_BARS("IRON_BARS", Material.IRON_BARS, 101, 0),
    GLASS_PANE("GLASS_PANE", Material.GLASS_PANE, 102, 0),
    MELON_BLOCK("MELON_BLOCK", Material.LEGACY_MELON_BLOCK, 103, 0),

    PUMPKIN_STEM("PUMPKIN_STEM", Material.PUMPKIN_STEM, 104, 0),
    MELON_STEM("MELON_STEM", Material.MELON_STEM, 105, 0),
    VINES("MELON_STEM", Material.VINE, 106, 0),
    OAK_FENCE_GATE("OAK_FENCE_GATE", Material.OAK_FENCE_GATE, 107, 0),
    BRICK_STAIRS("BRICK_STAIRS", Material.BRICK_STAIRS, 108, 0),
    STONE_BRICK_STAIRS("STONE_BRICK_STAIRS", Material.STONE_BRICK_STAIRS, 109, 0),
    MYCELIUM("MYCELIUM", Material.MYCELIUM, 110, 0),
    LILY_PAD("LILYPAD", Material.LILY_PAD, 111, 0),
    NETHER_BRICK("NETHER_BRICK", Material.NETHER_BRICK, 112, 0),
    NETHER_BRICK_FENCE("NETHER_BRICK_FENCE", Material.NETHER_BRICK_FENCE, 113, 0),
    NETHER_BRICK_STAIRS("NETHER_BRICK_STAIRS", Material.NETHER_BRICK_STAIRS, 114, 0),
    NETHER_WART("NETHER_WART", Material.NETHER_WART, 115, 0),
    ENCHANTING_TABLE("ENCHANTMENT_TABLE", Material.ENCHANTING_TABLE, 116, 0),
    BREWING_STAND("BREWING_STAND", Material.BREWING_STAND, 117, 0),
    CAULDRON("CAULDRON", Material.CAULDRON, 118, 0),
    END_PORTAL("END_PORTAL", Material.END_PORTAL, 119, 0),
    END_PORTAL_FRAME("END_PORTAL_FRAME", Material.END_PORTAL_FRAME, 120, 0),
    END_STONE("END_STONE", Material.END_STONE, 121, 0),
    DRAGON_EGG("DRAGON_EGG", Material.DRAGON_EGG, 122, 0),
    REDSTONE_LAMP("REDSTONE_LAMP", Material.REDSTONE_LAMP, 123, 0),
    REDSTONE_LAMP_ON("REDSTONE_LAMP_ON", Material.LEGACY_REDSTONE_LAMP_ON, 124, 0),

    DOUBLE_OAK_WOOD_SLAB("DOUBLE_OAK_WOOD_SLAB", Material.OAK_SLAB, 125, 0),
    OAK_WOOD_SLAB("OAK_WOOD_SLAB", Material.OAK_SLAB, 126, 0),
    COCOA("COCOA", Material.COCOA, 127, 0),
    SANDSTONE_STAIRS("SANDSTONE_STAIRS", Material.SANDSTONE_STAIRS, 128, 0),
    EMERALD_ORE("EMERALD_ORE", Material.EMERALD_ORE, 129, 0),
    ENDER_CHEST("ENDER_CHEST", Material.ENDER_CHEST, 130, 0),
    TRIPWIRE_HOOK("TRIPWIRE_HOOK", Material.TRIPWIRE_HOOK, 131, 0),
    TRIPWIRE("TRIPWIRE", Material.TRIPWIRE, 132, 0),
    EMERALD_BLOCK("EMERALD_BLOCK", Material.EMERALD_BLOCK, 133, 0),
    SPRUCE_WOOD_STAIRS("SPRUCE_WOOD_STAIRS", Material.LEGACY_SPRUCE_WOOD_STAIRS, 134, 0),

    BIRCH_WOOD_STAIRS("BIRCH_WOOD_STAIRS", Material.LEGACY_BIRCH_WOOD_STAIRS, 135, 0),

    JUNGLE_WOOD_STAIRS("JUNGLE_WOOD_STAIRS", Material.LEGACY_JUNGLE_WOOD_STAIRS, 136, 0),

    COMMAND_BLOCK("COMMAND_BLOCK", Material.COMMAND_BLOCK, 137, 0),
    BEACON("BEACON", Material.BEACON, 138, 0),
    COBBLESTONE_WALL("COBBLESTONE_WALL", Material.COBBLESTONE_WALL, 139, 0),
    FLOWER_POT("FLOWER_POT", Material.FLOWER_POT, 140, 0),
    CARROTS("CARROTS", Material.CARROTS, 141, 0),
    POTATOES("POTATOES", Material.POTATOES, 142, 0),
    WOODEN_BUTTON("WOODEN_BUTTON", Material.OAK_BUTTON, 143, 0),
    MOB_HEAD("MOB_HEAD", Material.LEGACY_SKULL_ITEM, 144, 0),

    ANVIL("ANVIL", Material.ANVIL, 145, 0),
    TRAPPED_CHEST("TRAPPED_CHEST", Material.TRAPPED_CHEST, 146, 0),
    LIGHT_WEIGHTED_PRESSURE_PLATE("LIGHT_WEIGHTED_PRESSURE_PLATE", Material.LIGHT_WEIGHTED_PRESSURE_PLATE, 147, 0),
    HEAVY_WEIGHTED_PRESSURE_PLATE("HEAVY_WEIGHTED_PRESSURE_PLATE", Material.HEAVY_WEIGHTED_PRESSURE_PLATE, 148, 0),
    UNPOWERED_COMPARATOR("UNPOWERED_COMPARATOR", Material.COMPARATOR, 149, 0),
    COMPARATOR("UNPOWERED_COMPARATOR", Material.COMPARATOR, 150, 0),
    DAYLIGHT_DETECTOR("DAYLIGHT_SENSOR", Material.DAYLIGHT_DETECTOR, 151, 0),
    REDSTONE_BLOCK("REDSTONE_BLOCK", Material.REDSTONE_BLOCK, 152, 0),
    NETHER_QUARTZ_ORE("NETHER_QUARTZ_ORE", Material.NETHER_QUARTZ_ORE, 153, 0),
    HOPPER("HOPPER", Material.HOPPER, 154, 0),
    QUARTZ_BLOCK("QUARTZ_BLOCK", Material.QUARTZ_BLOCK, 155, 0),
    QUARTZ_STAIRS("QUARTZ_STAIRS", Material.QUARTZ_STAIRS, 156, 0),
    ACTIVATOR_RAIL("ACTIVATOR_RAIL", Material.ACTIVATOR_RAIL, 157, 0),
    DROPPER("DROPPER", Material.DROPPER, 158, 0),
    WHITE_HARDENED_CLAY("WHITE_HARDENED_CLAY", Material.LEGACY_HARD_CLAY, 159, 0),

    WHITE_STAINED_GLASS_PANE("WHITE_STAINED_GLASS_PANE", Material.WHITE_STAINED_GLASS_PANE, 160, 0),
    ACACIA_WOOD("ACACIA_WOOD", Material.ACACIA_WOOD, 162, 0),
    ACACIA_LEAVES("ACACIA_LEAVES", Material.ACACIA_LEAVES, 161, 0),
    ACACIA_STAIRS("ACACIA_STAIRS", Material.ACACIA_STAIRS, 163, 0),
    DARK_OAK_STAIRS("DARK_OAK_STAIRS", Material.DARK_OAK_STAIRS, 164, 0),
    SLIME_BLOCK("SLIME_BLOCK", Material.SLIME_BLOCK, 165, 0),
    BARRIER("BARRIER", Material.BARRIER, 166, 0),
    IRON_TRAPDOOR("IRON_TRAPDOOR", Material.IRON_TRAPDOOR, 167, 0),
    PRISMARINE("PRISMARINE", Material.PRISMARINE, 168, 0),
    SEA_LANTERN("SEA_LANTERN", Material.SEA_LANTERN, 169, 0),
    HAY_BLOCK("HAY_BLOCK", Material.HAY_BLOCK, 170, 0),
    WHITE_CARPET("WHITE_CARPET", Material.WHITE_CARPET, 171, 0),
    HARDENED_CLAY("HARDENED_CLAY", Material.LEGACY_HARD_CLAY, 172, 0),

    COAL_BLOCK("COAL_BLOCK", Material.COAL_BLOCK, 173, 0),
    PACKED_ICE("PACKED_ICE", Material.PACKED_ICE, 174, 0),
    SUNFLOWER("SUNFLOWER", Material.SUNFLOWER, 175, 0),
    STANDING_BANNER("STANDING_BANNER", Material.LEGACY_STANDING_BANNER, 176, 0),

    WALL_BANNER("WALL_BANNER", Material.LEGACY_WALL_BANNER, 177, 0),

    INVERTED_DAYLIGHT_SENSOR("INVERTED_DAYLIGHT_SENSOR", Material.DAYLIGHT_DETECTOR, 178, 0),
    RED_SANDSTONE("RED_SANDSTONE", Material.RED_SANDSTONE, 179, 0),
    RED_SANDSTONE_STAIRS("RED_SANDSTONE_STAIRS", Material.RED_SANDSTONE_STAIRS, 180, 0),
    RED_SANDSTONE_SLAB("RED_SANDSTONE_SLAB", Material.RED_SANDSTONE_SLAB, 182, 0),
    SPRUCE_FENCE_GATE("SPRUCE_FENCE_GATE", Material.SPRUCE_FENCE_GATE, 183, 0),
    BIRCH_FENCE_GATE("BIRCH_FENCE_GATE", Material.BIRCH_FENCE_GATE, 184, 0),
    JUNGLE_FENCE_GATE("JUNGLE_FENCE_GATE", Material.JUNGLE_FENCE_GATE, 185, 0),
    DARK_OAK_FENCE_GATE("DARK_OAK_FENCE_GATE", Material.DARK_OAK_FENCE_GATE, 186, 0),
    ACACIA_FENCE_GATE("ACACIA_FENCE_GATE", Material.ACACIA_FENCE_GATE, 187, 0),
    SPRUCE_FENCE("SPRUCE_FENCE", Material.SPRUCE_FENCE, 188, 0),
    BIRCH_FENCE("BIRCH_FENCE", Material.BIRCH_FENCE, 189, 0),
    JUNGLE_FENCE("JUNGLE_FENCE", Material.JUNGLE_FENCE, 190, 0),
    DARK_OAK_FENCE("DARK_OAK_FENCE", Material.DARK_OAK_FENCE, 191, 0),
    ACACIA_FENCE("ACACIA_FENCE", Material.ACACIA_FENCE, 192, 0),
    SPRUCE_DOOR("SPRUCE_DOOR", Material.SPRUCE_DOOR, 193, 0),
    BIRCH_DOOR("BIRCH_DOOR", Material.BIRCH_DOOR, 194, 0),
    JUNGLE_DOOR("JUNGLE_DOOR", Material.JUNGLE_DOOR, 195, 0),
    ACACIA_DOOR("ACACIA_DOOR", Material.ACACIA_DOOR, 196, 0),
    DARK_OAK_DOOR("DARK_OAK_DOOR", Material.DARK_OAK_DOOR, 197, 0),
    END_ROD("END_ROD", Material.END_ROD, 198, 0),
    CHORUS_PLANT("CHORUS_PLANT", Material.CHORUS_PLANT, 199, 0),
    CHORUS_FLOWER("CHORUS_FLOWER", Material.CHORUS_FLOWER, 200, 0),
    PURPUR_BLOCK("PURPUR_BLOCK", Material.PURPUR_BLOCK, 201, 0),
    PURPUR_PILLAR("PURPUR_PILLAR", Material.PURPUR_PILLAR, 202, 0),
    PURPUR_STAIRS("PURPUR_STAIRS", Material.PURPUR_STAIRS, 203, 0),
    PURPUR_SLAB("PURPUR_SLAB", Material.PURPUR_SLAB, 205, 0),
    END_STONE_BRICKS("END_BRICKS", Material.END_STONE_BRICKS, 206, 0),
    BEETROOT("BEETROOT", Material.BEETROOT, 207, 0),
    GRASS_PATH("GRASS_PATH", Material.GRASS_PATH, 208, 0),
    END_GATEWAY("END_GATEWAY", Material.END_GATEWAY, 209, 0),
    REPEATING_COMMAND_BLOCK("REPEATING_COMMAND_BLOCK", Material.REPEATING_COMMAND_BLOCK, 210, 0),
    CHAIN_COMMAND_BLOCK("CHAIN_COMMAND_BLOCK", Material.CHAIN_COMMAND_BLOCK, 211, 0),
    FROSTED_ICE("FROSTED_ICE", Material.FROSTED_ICE, 212, 0),
    MAGMA_BLOCK("MAGMA_BLOCK", Material.MAGMA_BLOCK, 213, 0),
    NETHER_WART_BLOCK("NETHER_WART_BLOCK", Material.NETHER_WART_BLOCK, 214, 0),
    RED_NETHER_BRICK("RED_NETHER_BRICK", Material.RED_NETHER_BRICKS, 215, 0),
    BONE_BLOCK("BONE_BLOCK", Material.BONE_BLOCK, 216, 0),
    STRUCTURE_VOID("STRUCTURE_VOID", Material.STRUCTURE_VOID, 217, 0),
    OBSERVER("OBSERVER", Material.OBSERVER, 218, 0),
    WHITE_SHULKER_BOX("WHITE_SHULKER_BOX", Material.WHITE_SHULKER_BOX, 219, 0),
    ORANGE_SHULKER_BOX("ORANGE_SHULKER_BOX", Material.ORANGE_SHULKER_BOX, 220, 0),
    MAGENTA_SHULKER_BOX("MAGENTA_SHULKER_BOX", Material.MAGENTA_SHULKER_BOX, 221, 0),
    LIGHT_BLUE_SHULKER_BOX("LIGHT_BLUE_SHULKER_BOX", Material.LIGHT_BLUE_SHULKER_BOX, 222, 0),
    YELLOW_SHULKER_BOX("YELLOW_SHULKER_BOX", Material.YELLOW_SHULKER_BOX, 223, 0),
    LIME_SHULKER_BOX("LIME_SHULKER_BOX", Material.LIME_SHULKER_BOX, 224, 0),
    PINK_SHULKER_BOX("PINK_SHULKER_BOX", Material.PINK_SHULKER_BOX, 225, 0),
    GRAY_SHULKER_BOX("GRAY_SHULKER_BOX", Material.GRAY_SHULKER_BOX, 226, 0),
    LIGHT_GRAY_SHULKER_BOX("LIGHT_GRAY_SHULKER_BOX", Material.LIGHT_GRAY_SHULKER_BOX, 227, 0),
    CYAN_SHULKER_BOX("CYAN_SHULKER_BOX", Material.CYAN_SHULKER_BOX, 228, 0),
    PURPLE_SHULKER_BOX("PURPLE_SHULKER_BOX", Material.PURPLE_SHULKER_BOX, 229, 0),
    BLUE_SHULKER_BOX("BLUE_SHULKER_BOX", Material.BLUE_SHULKER_BOX, 230, 0),
    BROWN_SHULKER_BOX("BROWN_SHULKER_BOX", Material.BROWN_SHULKER_BOX, 231, 0),
    GREEN_SHULKER_BOX("GREEN_SHULKER_BOX", Material.GREEN_SHULKER_BOX, 232, 0),
    RED_SHULKER_BOX("RED_SHULKER_BOX", Material.RED_SHULKER_BOX, 233, 0),
    BLACK_SHULKER_BOX("BLACK_SHULKER_BOX", Material.BLACK_SHULKER_BOX, 234, 0),
    WHITE_GLAZED_TERRACOTTA("WHITE_GLAZED_TERRACOTTA", Material.WHITE_GLAZED_TERRACOTTA, 235, 0),
    ORANGE_GLAZED_TERRACOTTA("ORANGE_GLAZED_TERRACOTTA", Material.ORANGE_GLAZED_TERRACOTTA, 236, 0),
    MAGENTA_GLAZED_TERRACOTTA("MAGENTA_GLAZED_TERRACOTTA", Material.MAGENTA_GLAZED_TERRACOTTA, 237, 0),
    LIGHT_BLUE_GLAZED_TERRACOTTA("LIGHT_BLUE_GLAZED_TERRACOTTA", Material.LIGHT_BLUE_GLAZED_TERRACOTTA, 238, 0),
    YELLOW_GLAZED_TERRACOTTA("YELLOW_BLUE_GLAZED_TERRACOTTA", Material.YELLOW_GLAZED_TERRACOTTA, 239, 0),
    LIME_GLAZED_TERRACOTTA("LIME_BLUE_GLAZED_TERRACOTTA", Material.LIME_GLAZED_TERRACOTTA, 240, 0),
    PINK_GLAZED_TERRACOTTA("PINK_GLAZED_TERRACOTTA", Material.PINK_GLAZED_TERRACOTTA, 241, 0),
    GRAY_GLAZED_TERRACOTTA("GRAY_GLAZED_TERRACOTTA", Material.GRAY_GLAZED_TERRACOTTA, 242, 0),
    LIGHT_GRAY_GLAZED_TERRACOTTA("LIGHT_GRAY_GLAZED_TERRACOTTA", Material.LIGHT_GRAY_GLAZED_TERRACOTTA, 243, 0),
    CYAN_GLAZED_TERRACOTTA("CYAN_GLAZED_TERRACOTTA", Material.CYAN_GLAZED_TERRACOTTA, 244, 0),
    PURPLE_GLAZED_TERRACOTTA("PURPLE_GLAZED_TERRACOTTA", Material.PURPLE_GLAZED_TERRACOTTA, 245, 0),
    BLUE_GLAZED_TERRACOTTA("BLUE_GLAZED_TERRACOTTA", Material.BLUE_GLAZED_TERRACOTTA, 246, 0),
    BROWN_GLAZED_TERRACOTTA("BROWN_GLAZED_TERRACOTTA", Material.BROWN_GLAZED_TERRACOTTA, 247, 0),
    GREEN_GLAZED_TERRACOTTA("GREEN_GLAZED_TERRACOTTA", Material.GREEN_GLAZED_TERRACOTTA, 248, 0),
    RED_GLAZED_TERRACOTTA("RED_GLAZED_TERRACOTTA", Material.RED_GLAZED_TERRACOTTA, 249, 0),
    BLACK_GLAZED_TERRACOTTA("BLACK_GLAZED_TERRACOTTA", Material.BLACK_GLAZED_TERRACOTTA, 250, 0);

    String name;

    int subid;
    Material item;
    int id;

    MaterialInID(String name, Material item, int id, int subid) {
        this.id = id;
        this.name = name;
        this.item = item;
        this.subid = subid;
    }

    public static ItemStack ItemsinID(int id) {
        if (id == 0) {
            Material x = AIR.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 1) {
            Material x = STONE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 2) {
            Material x = GRASS.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 3) {
            Material x = DIRT.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 4) {
            Material x = COBBLESTONE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 5) {
            Material x = OAK_WOOD_PLANKS.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 6) {
            Material x = OAK_SAPLING.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 7) {
            Material x = BEDROCK.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 8) {
            Material x = FLOWING_WATER.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 9) {
            Material x = WATER.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 10) {
            Material x = FLOWING_LAVA.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 11) {
            Material x = LAVA.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 12) {
            Material x = SAND.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 13) {
            Material x = GRAVEL.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 14) {
            Material x = GOLD_ORE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 15) {
            Material x = IRON_ORE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 16) {
            Material x = COAL_ORE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 17) {
            Material x = OAK_WOOD.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 18) {
            Material x = OAK_LEAVES.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 19) {
            Material x = SPONGE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 20) {
            Material x = GLASS.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 21) {
            Material x = LAPIS_ORE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 22) {
            Material x = LAPIS_BLOCK.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 23) {
            Material x = DISPENSER.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 24) {
            Material x = SANDSTONE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 25) {
            Material x = NOTE_BLOCK.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 26) {
            Material x = BED.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 27) {
            Material x = POWERED_RAIL.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 28) {
            Material x = DETECTOR_RAIL.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 29) {
            Material x = STICKY_PISTON.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 30) {
            Material x = COBWEB.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 31) {
            Material x = DEAD_SHRUB.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 33) {
            Material x = PISTON.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 34) {
            Material x = PISTON_HEAD.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 35) {
            Material x = WHITE_WOOL.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 37) {
            Material x = DANDELION.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 38) {
            Material x = POPPY.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 39) {
            Material x = BROWN_MUSHROOM.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 40) {
            Material x = RED_MUSHROOM.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 41) {
            Material x = GOLD_BLOCK.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 42) {
            Material x = IRON_BLOCK.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 45) {
            Material x = BRICKS.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 46) {
            Material x = TNT.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 47) {
            Material x = BOOKSHELF.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 48) {
            Material x = MOSS_STONE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 49) {
            Material x = OBSIDIAN.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 50) {
            Material x = TORCH.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 51) {
            Material x = FIRE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 52) {
            Material x = MOB_SPAWNER.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 53) {
            Material x = OAK_WOOD_STAIRS.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 54) {
            Material x = CHEST.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 55) {
            Material x = REDSTONE_WIRE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 56) {
            Material x = DIAMOND_ORE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 57) {
            Material x = DIAMOND_BLOCK.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 58) {
            Material x = CRAFTING_TABLE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 59) {
            Material x = WHEAT_CROPS.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 60) {
            Material x = FARMLAND.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 61) {
            Material x = FURNACE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 62) {
            Material x = BURNING_FURNACE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 63) {
            Material x = STANDING_SIGN.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 64) {
            Material x = OAK_DOOR.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 65) {
            Material x = LADDER.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 66) {
            Material x = RAIL.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 67) {
            Material x = COBBLESTONE_STAIRS.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 68) {
            Material x = WALL_SIGN.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 69) {
            Material x = LEVER.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 70) {
            Material x = STONE_PRESSURE_PLATE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 71) {
            Material x = IRON_DOOR.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 72) {
            Material x = OAK_PRESSURE_PLATE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 73) {
            Material x = REDSTONE_ORE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 74) {
            Material x = GLOWING_REDSTONE_ORE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 75) {
            Material x = REDSTONE_TORCH_OFF.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 76) {
            Material x = REDSTONE_TORCH.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 77) {
            Material x = STONE_BUTTON.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 78) {
            Material x = SNOW_LAYER.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 79) {
            Material x = ICE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 80) {
            Material x = SNOW_BLOCK.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 81) {
            Material x = CACTUS.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 82) {
            Material x = CLAY.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 83) {
            Material x = SUGAR_CANES.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 84) {
            Material x = JUKEBOX.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 85) {
            Material x = OAK_FENCE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 86) {
            Material x = PUMPKIN.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 87) {
            Material x = NETHERRACK.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 88) {
            Material x = SOUL_SAND.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 89) {
            Material x = GLOWSTONE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 90) {
            Material x = NETHER_PORTAL.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 91) {
            Material x = JACK_O_LANTERN.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 92) {
            Material x = CAKE_BLOCK.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 93) {
            Material x = WHITE_STAINED_GLASS.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 96) {
            Material x = OAK_TRAPDOOR.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 97) {
            Material x = STONE_MONSTER_EGG.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 98) {
            Material x = STONEBRICK.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 99) {
            Material x = BROWN_MUSHROOM_BLOCK.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 100) {
            Material x = RED_MUSHROOM_BLOCK.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 101) {
            Material x = IRON_BARS.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 102) {
            Material x = GLASS_PANE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 103) {
            Material x = MELON_BLOCK.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 104) {
            Material x = PUMPKIN_STEM.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 105) {
            Material x = MELON_STEM.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 106) {
            Material x = VINES.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 107) {
            Material x = OAK_FENCE_GATE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 108) {
            Material x = BRICK_STAIRS.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 109) {
            Material x = STONE_BRICK_STAIRS.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 110) {
            Material x = MYCELIUM.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 111) {
            Material x = LILY_PAD.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 112) {
            Material x = NETHER_BRICK.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 113) {
            Material x = NETHER_BRICK_FENCE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 114) {
            Material x = NETHER_BRICK_STAIRS.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 115) {
            Material x = NETHER_WART.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 116) {
            Material x = ENCHANTING_TABLE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 117) {
            Material x = BREWING_STAND.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 118) {
            Material x = CAULDRON.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 119) {
            Material x = END_PORTAL.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 120) {
            Material x = END_PORTAL_FRAME.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 121) {
            Material x = END_STONE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 122) {
            Material x = DRAGON_EGG.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 123) {
            Material x = REDSTONE_LAMP.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 124) {
            Material x = REDSTONE_LAMP_ON.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 125) {
            Material x = DOUBLE_OAK_WOOD_SLAB.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 126) {
            Material x = OAK_WOOD_SLAB.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 127) {
            Material x = COCOA.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 128) {
            Material x = SANDSTONE_STAIRS.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 129) {
            Material x = EMERALD_ORE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 130) {
            Material x = ENDER_CHEST.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 131) {
            Material x = TRIPWIRE_HOOK.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 132) {
            Material x = TRIPWIRE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 133) {
            Material x = EMERALD_BLOCK.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 134) {
            Material x = SPRUCE_WOOD_STAIRS.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 135) {
            Material x = BIRCH_WOOD_STAIRS.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 136) {
            Material x = JUNGLE_WOOD_STAIRS.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 137) {
            Material x = COMMAND_BLOCK.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 138) {
            Material x = BEACON.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 139) {
            Material x = COBBLESTONE_WALL.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 140) {
            Material x = FLOWER_POT.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 141) {
            Material x = CARROTS.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 142) {
            Material x = POTATOES.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 143) {
            Material x = WOODEN_BUTTON.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 144) {
            Material x = MOB_HEAD.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 145) {
            Material x = ANVIL.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 146) {
            Material x = TRAPPED_CHEST.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 147) {
            Material x = LIGHT_WEIGHTED_PRESSURE_PLATE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 148) {
            Material x = HEAVY_WEIGHTED_PRESSURE_PLATE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 149) {
            Material x = UNPOWERED_COMPARATOR.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 150) {
            Material x = COMPARATOR.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 151) {
            Material x = DAYLIGHT_DETECTOR.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 152) {
            Material x = REDSTONE_BLOCK.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 153) {
            Material x = NETHER_QUARTZ_ORE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 154) {
            Material x = HOPPER.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 155) {
            Material x = QUARTZ_BLOCK.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 156) {
            Material x = QUARTZ_STAIRS.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 157) {
            Material x = ACTIVATOR_RAIL.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 158) {
            Material x = DROPPER.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 159) {
            Material x = WHITE_HARDENED_CLAY.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 160) {
            Material x = WHITE_STAINED_GLASS_PANE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 161) {
            Material x = ACACIA_LEAVES.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 162) {
            Material x = ACACIA_WOOD.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 163) {
            Material x = ACACIA_STAIRS.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 164) {
            Material x = DARK_OAK_STAIRS.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 165) {
            Material x = SLIME_BLOCK.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 166) {
            Material x = BARRIER.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 167) {
            Material x = IRON_TRAPDOOR.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 168) {
            Material x = PRISMARINE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 169) {
            Material x = SEA_LANTERN.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 170) {
            Material x = HAY_BLOCK.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 171) {
            Material x = WHITE_CARPET.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 172) {
            Material x = HARDENED_CLAY.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 173) {
            Material x = COAL_BLOCK.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 174) {
            Material x = PACKED_ICE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 175) {
            Material x = SUNFLOWER.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 176) {
            Material x = STANDING_BANNER.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 177) {
            Material x = WALL_BANNER.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 178) {
            Material x = INVERTED_DAYLIGHT_SENSOR.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 179) {
            Material x = RED_SANDSTONE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 180) {
            Material x = RED_SANDSTONE_STAIRS.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 182) {
            Material x = RED_SANDSTONE_SLAB.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 183) {
            Material x = SPRUCE_FENCE_GATE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 184) {
            Material x = BIRCH_FENCE_GATE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 185) {
            Material x = JUNGLE_FENCE_GATE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 186) {
            Material x = DARK_OAK_FENCE_GATE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 187) {
            Material x = ACACIA_FENCE_GATE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 188) {
            Material x = SPRUCE_FENCE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 189) {
            Material x = BIRCH_FENCE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 190) {
            Material x = JUNGLE_FENCE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 191) {
            Material x = DARK_OAK_FENCE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 192) {
            Material x = ACACIA_FENCE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 193) {
            Material x = SPRUCE_DOOR.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 194) {
            Material x = BIRCH_DOOR.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 195) {
            Material x = JUNGLE_DOOR.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 196) {
            Material x = ACACIA_DOOR.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 197) {
            Material x = DARK_OAK_DOOR.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 198) {
            Material x = END_ROD.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 199) {
            Material x = CHORUS_PLANT.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 200) {
            Material x = CHORUS_FLOWER.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 201) {
            Material x = PURPUR_BLOCK.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 202) {
            Material x = PURPUR_PILLAR.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 203) {
            Material x = PURPUR_STAIRS.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 205) {
            Material x = PURPUR_SLAB.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 206) {
            Material x = END_STONE_BRICKS.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 207) {
            Material x = BEETROOT.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 208) {
            Material x = GRASS_PATH.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 209) {
            Material x = END_GATEWAY.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 210) {
            Material x = REPEATING_COMMAND_BLOCK.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 211) {
            Material x = CHAIN_COMMAND_BLOCK.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 212) {
            Material x = FROSTED_ICE.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 213) {
            Material x = MAGMA_BLOCK.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 214) {
            Material x = NETHER_WART_BLOCK.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 215) {
            Material x = RED_NETHER_BRICK.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 216) {
            Material x = BONE_BLOCK.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 217) {
            Material x = STRUCTURE_VOID.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 218) {
            Material x = OBSERVER.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 219) {
            Material x = WHITE_SHULKER_BOX.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 220) {
            Material x = ORANGE_SHULKER_BOX.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 221) {
            Material x = MAGENTA_SHULKER_BOX.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 222) {
            Material x = LIGHT_BLUE_SHULKER_BOX.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 223) {
            Material x = YELLOW_SHULKER_BOX.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 224) {
            Material x = LIME_SHULKER_BOX.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 225) {
            Material x = PINK_SHULKER_BOX.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 226) {
            Material x = GRAY_SHULKER_BOX.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 227) {
            Material x = LIGHT_GRAY_SHULKER_BOX.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 228) {
            Material x = CYAN_SHULKER_BOX.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 229) {
            Material x = PURPLE_SHULKER_BOX.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 230) {
            Material x = BLUE_SHULKER_BOX.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 231) {
            Material x = BROWN_SHULKER_BOX.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 232) {
            Material x = GREEN_SHULKER_BOX.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 233) {
            Material x = RED_SHULKER_BOX.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 234) {
            Material x = BLACK_SHULKER_BOX.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 235) {
            Material x = WHITE_GLAZED_TERRACOTTA.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 236) {
            Material x = ORANGE_GLAZED_TERRACOTTA.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 237) {
            Material x = MAGENTA_GLAZED_TERRACOTTA.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 238) {
            Material x = LIGHT_BLUE_GLAZED_TERRACOTTA.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 239) {
            Material x = YELLOW_GLAZED_TERRACOTTA.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 240) {
            Material x = LIME_GLAZED_TERRACOTTA.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 241) {
            Material x = PINK_GLAZED_TERRACOTTA.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 242) {
            Material x = GRAY_GLAZED_TERRACOTTA.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 243) {
            Material x = LIGHT_GRAY_GLAZED_TERRACOTTA.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 244) {
            Material x = CYAN_GLAZED_TERRACOTTA.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 245) {
            Material x = PURPLE_GLAZED_TERRACOTTA.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 246) {
            Material x = BLUE_GLAZED_TERRACOTTA.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 247) {
            Material x = BROWN_GLAZED_TERRACOTTA.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 248) {
            Material x = GREEN_GLAZED_TERRACOTTA.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 249) {
            Material x = RED_GLAZED_TERRACOTTA.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 250) {
            Material x = BLACK_GLAZED_TERRACOTTA.getItem();
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 251) {
            Material x = Material.WHITE_CONCRETE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 252) {
            Material x = Material.WHITE_CONCRETE_POWDER;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 255) {
            Material x = Material.STRUCTURE_BLOCK;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 256) {
            Material x = Material.IRON_SHOVEL;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 257) {
            Material x = Material.IRON_PICKAXE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 258) {
            Material x = Material.IRON_AXE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 259) {
            Material x = Material.FLINT_AND_STEEL;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 260) {
            Material x = Material.APPLE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 261) {
            Material x = Material.BOW;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 262) {
            Material x = Material.ARROW;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 263) {
            Material x = Material.COAL;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 264) {
            Material x = Material.DIAMOND;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 265) {
            Material x = Material.IRON_INGOT;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 266) {
            Material x = Material.GOLD_INGOT;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 267) {
            Material x = Material.IRON_SWORD;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 268) {
            Material x = Material.WOODEN_SWORD;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 269) {
            Material x = Material.WOODEN_SHOVEL;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 270) {
            Material x = Material.WOODEN_PICKAXE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 271) {
            Material x = Material.WOODEN_AXE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 272) {
            Material x = Material.STONE_SWORD;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 273) {
            Material x = Material.STONE_SHOVEL;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 274) {
            Material x = Material.STONE_PICKAXE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 275) {
            Material x = Material.STONE_AXE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 276) {
            Material x = Material.DIAMOND_SWORD;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 277) {
            Material x = Material.DIAMOND_SHOVEL;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 278) {
            Material x = Material.DIAMOND_PICKAXE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 279) {
            Material x = Material.DIAMOND_AXE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 280) {
            Material x = Material.STICK;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 281) {
            Material x = Material.BOWL;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 282) {
            Material x = Material.MUSHROOM_STEW;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 283) {
            Material x = Material.GOLDEN_SWORD;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 284) {
            Material x = Material.GOLDEN_SHOVEL;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 285) {
            Material x = Material.GOLDEN_PICKAXE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 286) {
            Material x = Material.GOLDEN_AXE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 287) {
            Material x = Material.STRING;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 288) {
            Material x = Material.FEATHER;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 289) {
            Material x = Material.GUNPOWDER;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 290) {
            Material x = Material.WOODEN_HOE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 291) {
            Material x = Material.STONE_HOE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 292) {
            Material x = Material.IRON_HOE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 293) {
            Material x = Material.DIAMOND_HOE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 294) {
            Material x = Material.GOLDEN_HOE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 295) {
            Material x = Material.WHEAT_SEEDS;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 296) {
            Material x = Material.WHEAT;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 297) {
            Material x = Material.BREAD;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 298) {
            Material x = Material.LEATHER_HELMET;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 299) {
            Material x = Material.LEATHER_CHESTPLATE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 300) {
            Material x = Material.LEATHER_LEGGINGS;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 301) {
            Material x = Material.LEATHER_BOOTS;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 302) {
            Material x = Material.CHAINMAIL_HELMET;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 303) {
            Material x = Material.CHAINMAIL_CHESTPLATE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 304) {
            Material x = Material.CHAINMAIL_LEGGINGS;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 305) {
            Material x = Material.CHAINMAIL_BOOTS;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 306) {
            Material x = Material.IRON_HELMET;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 307) {
            Material x = Material.IRON_CHESTPLATE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 308) {
            Material x = Material.IRON_LEGGINGS;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 309) {
            Material x = Material.IRON_BOOTS;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 310) {
            Material x = Material.DIAMOND_HELMET;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 311) {
            Material x = Material.DIAMOND_CHESTPLATE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 312) {
            Material x = Material.DIAMOND_LEGGINGS;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 313) {
            Material x = Material.DIAMOND_BOOTS;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 314) {
            Material x = Material.GOLDEN_HELMET;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 315) {
            Material x = Material.GOLDEN_CHESTPLATE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 316) {
            Material x = Material.GOLDEN_LEGGINGS;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 317) {
            Material x = Material.GOLDEN_BOOTS;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 318) {
            Material x = Material.FLINT;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 319) {
            Material x = Material.PORKCHOP;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 320) {
            Material x = Material.COOKED_PORKCHOP;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 321) {
            Material x = Material.PAINTING;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 322) {
            Material x = Material.GOLDEN_APPLE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 323) {
            Material x = Material.OAK_SIGN;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 324) {
            Material x = Material.OAK_DOOR;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 325) {
            Material x = Material.BUCKET;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 326) {
            Material x = Material.WATER_BUCKET;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 327) {
            Material x = Material.LAVA_BUCKET;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 328) {
            Material x = Material.MINECART;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 329) {
            Material x = Material.SADDLE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 330) {
            Material x = Material.IRON_DOOR;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 331) {
            Material x = Material.REDSTONE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 332) {
            Material x = Material.SNOWBALL;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 333) {
            Material x = Material.OAK_BOAT;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 334) {
            Material x = Material.LEATHER;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 335) {
            Material x = Material.MILK_BUCKET;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 336) {
            Material x = Material.BRICK;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 337) {
            Material x = Material.CLAY_BALL;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 338) {
            Material x = Material.SUGAR_CANE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 339) {
            Material x = Material.PAPER;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 340) {
            Material x = Material.BOOK;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 341) {
            Material x = Material.SLIME_BALL;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 342) {
            Material x = Material.CHEST_MINECART;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 343) {
            Material x = Material.FURNACE_MINECART;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 344) {
            Material x = Material.EGG;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 345) {
            Material x = Material.COMPASS;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 346) {
            Material x = Material.FISHING_ROD;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 347) {
            Material x = Material.CLOCK;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 348) {
            Material x = Material.GLOWSTONE_DUST;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 349) {

            Material x = Material.LEGACY_RAW_FISH;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 350) {

            Material x = Material.LEGACY_COOKED_FISH;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 351) {
            Material x = Material.INK_SAC;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 352) {
            Material x = Material.BONE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 353) {
            Material x = Material.SUGAR;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 354) {
            Material x = Material.CAKE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 355) {
            Material x = Material.RED_BED;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 356) {
            Material x = Material.REPEATER;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 357) {
            Material x = Material.COOKIE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 358) {
            Material x = Material.FILLED_MAP;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 359) {
            Material x = Material.SHEARS;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 360) {
            Material x = Material.MELON;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 361) {
            Material x = Material.PUMPKIN_SEEDS;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 362) {
            Material x = Material.MELON_SEEDS;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 363) {
            Material x = Material.BEEF;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 364) {

            Material x = Material.LEGACY_COOKED_BEEF;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 365) {
            Material x = Material.CHICKEN;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 366) {
            Material x = Material.COOKED_CHICKEN;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 367) {
            Material x = Material.ROTTEN_FLESH;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 368) {
            Material x = Material.ENDER_PEARL;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 369) {
            Material x = Material.BLAZE_ROD;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 370) {
            Material x = Material.GHAST_TEAR;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 371) {
            Material x = Material.GOLD_NUGGET;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 372) {
            Material x = Material.NETHER_WART;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 373) {
            Material x = Material.POTION;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 374) {
            Material x = Material.GLASS_BOTTLE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 375) {
            Material x = Material.SPIDER_EYE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 376) {
            Material x = Material.FERMENTED_SPIDER_EYE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 377) {
            Material x = Material.BLAZE_POWDER;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 378) {
            Material x = Material.MAGMA_CREAM;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 379) {
            Material x = Material.BREWING_STAND;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 380) {
            Material x = Material.CAULDRON;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 381) {
            Material x = Material.ENDER_EYE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 382) {

            Material x = Material.LEGACY_SPECKLED_MELON;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 384) {
            Material x = Material.EXPERIENCE_BOTTLE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 385) {
            Material x = Material.FIRE_CHARGE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 386) {
            Material x = Material.WRITABLE_BOOK;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 387) {
            Material x = Material.WRITTEN_BOOK;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 388) {
            Material x = Material.EMERALD;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 389) {
            Material x = Material.ITEM_FRAME;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 390) {
            Material x = Material.FLOWER_POT;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 391) {
            Material x = Material.CARROT;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 392) {
            Material x = Material.POTATO;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 393) {
            Material x = Material.BAKED_POTATO;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 394) {
            Material x = Material.POISONOUS_POTATO;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 395) {
            Material x = Material.MAP;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 396) {
            Material x = Material.GOLDEN_CARROT;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 397) {

            Material x = Material.LEGACY_SKULL;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 398) {
            Material x = Material.CARROT_ON_A_STICK;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 399) {
            Material x = Material.NETHER_STAR;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 400) {
            Material x = Material.PUMPKIN_PIE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 401) {
            Material x = Material.FIREWORK_ROCKET;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 402) {
            Material x = Material.FIREWORK_STAR;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 403) {
            Material x = Material.ENCHANTED_BOOK;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 404) {
            Material x = Material.COMPARATOR;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 405) {
            Material x = Material.NETHER_BRICK;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 406) {
            Material x = Material.QUARTZ;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 407) {
            Material x = Material.TNT_MINECART;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 408) {
            Material x = Material.HOPPER_MINECART;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 409) {
            Material x = Material.PRISMARINE_SHARD;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 410) {
            Material x = Material.PRISMARINE_CRYSTALS;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 411) {
            Material x = Material.RABBIT;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 412) {
            Material x = Material.COOKED_RABBIT;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 413) {
            Material x = Material.RABBIT_STEW;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 414) {
            Material x = Material.RABBIT_FOOT;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 415) {
            Material x = Material.RABBIT_HIDE;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 416) {
            Material x = Material.ARMOR_STAND;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 417) {
            Material x = Material.IRON_HORSE_ARMOR;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 418) {
            Material x = Material.GOLDEN_HORSE_ARMOR;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 419) {
            Material x = Material.DIAMOND_HORSE_ARMOR;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 420) {
            Material x = Material.LEAD;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 421) {
            Material x = Material.NAME_TAG;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 422) {
            Material x = Material.COMMAND_BLOCK_MINECART;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 423) {
            Material x = Material.MUTTON;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 424) {
            Material x = Material.COOKED_MUTTON;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 425) {
            Material x = Material.WHITE_BANNER;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 426) {
            Material x = Material.END_CRYSTAL;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 427) {
            Material x = Material.SPRUCE_DOOR;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 428) {
            Material x = Material.BIRCH_DOOR;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 429) {
            Material x = Material.JUNGLE_DOOR;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 430) {
            Material x = Material.ACACIA_DOOR;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 431) {
            Material x = Material.DARK_OAK_DOOR;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 432) {
            Material x = Material.CHORUS_FRUIT;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 433) {
            Material x = Material.POPPED_CHORUS_FRUIT;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 434) {
            Material x = Material.BEETROOT;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 435) {
            Material x = Material.BEETROOT_SEEDS;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 436) {
            Material x = Material.BEETROOT_SOUP;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 437) {
            Material x = Material.DRAGON_BREATH;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 438) {
            Material x = Material.SPLASH_POTION;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 439) {
            Material x = Material.SPECTRAL_ARROW;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 440) {
            Material x = Material.TIPPED_ARROW;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 441) {
            Material x = Material.LINGERING_POTION;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 442) {
            Material x = Material.SHIELD;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 443) {
            Material x = Material.ELYTRA;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 444) {
            Material x = Material.SPRUCE_BOAT;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 445) {
            Material x = Material.BIRCH_BOAT;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 446) {
            Material x = Material.JUNGLE_BOAT;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 447) {
            Material x = Material.ACACIA_BOAT;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 448) {
            Material x = Material.DARK_OAK_BOAT;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 449) {
            Material x = Material.TOTEM_OF_UNDYING;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 450) {
            Material x = Material.SHULKER_SHELL;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 452) {
            Material x = Material.IRON_NUGGET;
            ItemStack item = new ItemStack(x);
            return item;
        }
        if (id == 453) {
            Material x = Material.KNOWLEDGE_BOOK;
            ItemStack item = new ItemStack(x);
            return item;
        }
        return null;
    }


    public int getSubid() {
        return this.subid;
    }


    public void setSubid(int subid) {
        this.subid = subid;
    }

    public ItemStack build() {
        ItemStack item = new ItemStack(ItemsinID(this.id));
        if (this.subid == 0) ;


        item.setDurability((short) this.subid);
        return item;
    }


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Material getItem() {
        return this.item;
    }

    public ItemStack getItemStack() {
        return new ItemStack(item);
    }

    public void setItem(Material item) {
        this.item = item;
    }


    public int getId() {
        return this.id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public ItemStack getItemStackSubID(int id, int subid) {
        for (int i = 0; i <= id; i++) {
            ItemStack item = new ItemStack(ItemsinID(id));
            if (item != null) {
                item.setDurability((short) subid);
                return item;
            }
        }
        return null;
    }


    public ItemStack getItemStackinID(int id) {
        int i = 0;
        if (i <= id) {
            ItemStack item = new ItemStack(ItemsinID(id));
            if (item != null) {
                return item;
            }
            return item;
        }
        return null;
    }
}


