package de.framedev.frameapi.managers;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.inventory.ItemStack;


public class CropsManager {
    public void setWheat(World world, int maxX, int minX, int maxY, int minY, int maxZ, int minZ) {
        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                for (int y = minY - maxY; y <= maxY; y++) {
                    if (world.getBlockAt(x, y, z).getType() == Material.FARMLAND) {
                        Block block = world.getBlockAt(x, y + 1, z);
                        block.setType(Material.WHEAT);
                        if (block.getBlockData() instanceof Ageable) {
                            Ageable ageable = (Ageable) block.getBlockData();
                            ageable.setAge(1);
                        }
                    }
                }
            }
        }
    }

    public void setBeetRoot(World world, int maxX, int minX, int maxY, int minY, int maxZ, int minZ) {
        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                for (int y = minY - maxY; y <= maxY; y++) {
                    if (world.getBlockAt(x, y, z).getType() == Material.FARMLAND) {
                        Block block = world.getBlockAt(x, y + 1, z);
                        block.setType(Material.BEETROOT);
                        if (block.getBlockData() instanceof Ageable) {
                            Ageable ageable = (Ageable) block.getBlockData();
                            ageable.setAge(1);
                        }
                    }
                }
            }
        }
    }

    public void getBeetRoot(World world, int maxX, int minX, int maxY, int minY, int maxZ, int minZ) {
        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                for (int y = minY - maxY; y <= maxY; y++) {
                    if (world.getBlockAt(x, y + 1, z).getType() == Material.BEETROOT) {
                        Block block = world.getBlockAt(x, y + 1, z);
                        if (block.getBlockData() instanceof Ageable) {
                            Ageable ageable = (Ageable) block.getBlockData();
                            if (ageable.getAge() == 7) {
                                block.setType(Material.AIR);
                                world.dropItem(block.getLocation(), new ItemStack(Material.BEETROOT, 2));
                            }
                        }
                    }
                }
            }
        }
    }

    public void getWheat(World world, int maxX, int minX, int maxY, int minY, int maxZ, int minZ) {
        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                for (int y = minY - maxY; y <= maxY; y++) {
                    if (world.getBlockAt(x, y + 1, z).getType() == Material.WHEAT) {
                        Block block = world.getBlockAt(x, y + 1, z);
                        if (block.getBlockData() instanceof Ageable) {
                            Ageable ageable = (Ageable) block.getBlockData();
                            if (ageable.getAge() == 7) {
                                block.setType(Material.AIR);
                                world.dropItem(block.getLocation(), new ItemStack(Material.WHEAT, 1));
                                world.dropItem(block.getLocation(), new ItemStack(Material.WHEAT_SEEDS, 2));
                            }
                        }
                    }
                }
            }
        }
    }

    public void setCarrot(World world, int maxX, int minX, int maxY, int minY, int maxZ, int minZ) {
        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                for (int y = minY - maxY; y <= maxY; y++) {
                    if (world.getBlockAt(x, y, z).getType() == Material.FARMLAND) {
                        Block block = world.getBlockAt(x, y + 1, z);
                        block.setType(Material.CARROTS);
                        if (block.getBlockData() instanceof Ageable) {
                            Ageable ageable = (Ageable) block.getBlockData();
                            ageable.setAge(1);
                        }
                    }
                }
            }
        }
    }

    public void getCarrot(World world, int maxX, int minX, int maxY, int minY, int maxZ, int minZ) {
        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                for (int y = minY - maxY; y <= maxY; y++) {
                    if (world.getBlockAt(x, y + 1, z).getType() == Material.CARROTS) {
                        Block block = world.getBlockAt(x, y + 1, z);
                        if (block.getBlockData() instanceof Ageable) {
                            Ageable ageable = (Ageable) block.getBlockData();
                            if (ageable.getAge() == ageable.getMaximumAge()) {
                                block.setType(Material.AIR);
                                world.dropItem(block.getLocation(), new ItemStack(Material.CARROT, 3));
                            }
                        }
                    }
                }
            }
        }
    }

    public void setPotato(World world, int maxX, int minX, int maxY, int minY, int maxZ, int minZ) {
        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                for (int y = minY - maxY; y <= maxY; y++) {
                    if (world.getBlockAt(x, y, z).getType() == Material.FARMLAND) {
                        Block block = world.getBlockAt(x, y + 1, z);
                        block.setType(Material.POTATOES);
                        if (block.getBlockData() instanceof Ageable) {
                            Ageable ageable = (Ageable) block.getBlockData();
                            ageable.setAge(1);
                        }
                    }
                }
            }
        }
    }

    public void getPotato(World world, int maxX, int minX, int maxY, int minY, int maxZ, int minZ) {
        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                for (int y = minY - maxY; y <= maxY; y++) {
                    if (world.getBlockAt(x, y + 1, z).getType() == Material.POTATOES) {
                        Block block = world.getBlockAt(x, y + 1, z);
                        if (block.getBlockData() instanceof Ageable) {
                            Ageable ageable = (Ageable) block.getBlockData();
                            if (ageable.getAge() == ageable.getMaximumAge()) {
                                block.setType(Material.AIR);
                                world.dropItem(block.getLocation(), new ItemStack(Material.POTATO, 3));
                            }
                        }
                    }
                }
            }
        }
    }

    public void setSugarCane(World world, int maxX, int minX, int maxY, int minY, int maxZ, int minZ) {
        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                for (int y = minY - maxY; y <= maxY; y++) {
                    if (world.getBlockAt(x, y, z).getType() != Material.FARMLAND &&
                            world.getBlockAt(x, y, z).getType() != Material.WATER) {
                        if (world.getBlockAt(x - 1, y, z).getType() == Material.WATER) {
                            if (world.getBlockAt(x, y, z).getType() == Material.DIRT || world.getBlockAt(x, y, z).getType() == Material.GRASS_BLOCK || world.getBlockAt(x, y, z).getType() == Material.SAND) {
                                Block block = world.getBlockAt(x, y + 1, z);
                                block.setType(Material.SUGAR_CANE);
                            }
                        } else if (world.getBlockAt(x + 1, y, z).getType() == Material.WATER) {
                            if (world.getBlockAt(x, y, z).getType() == Material.DIRT || world.getBlockAt(x, y, z).getType() == Material.GRASS_BLOCK || world.getBlockAt(x, y, z).getType() == Material.SAND) {
                                Block block = world.getBlockAt(x, y + 1, z);
                                block.setType(Material.SUGAR_CANE);
                            }
                        } else if (world.getBlockAt(x, y, z + 1).getType() == Material.WATER) {
                            if (world.getBlockAt(x, y, z).getType() == Material.DIRT || world.getBlockAt(x, y, z).getType() == Material.GRASS_BLOCK || world.getBlockAt(x, y, z).getType() == Material.SAND) {
                                Block block = world.getBlockAt(x, y + 1, z);
                                block.setType(Material.SUGAR_CANE);
                            }
                        } else if (world.getBlockAt(x, y, z - 1).getType() == Material.WATER) {
                            if (world.getBlockAt(x, y, z).getType() == Material.DIRT || world.getBlockAt(x, y, z).getType() == Material.GRASS_BLOCK || world.getBlockAt(x, y, z).getType() == Material.SAND) {
                                Block block = world.getBlockAt(x, y + 1, z);
                                block.setType(Material.SUGAR_CANE);
                            }
                        }
                    }
                }
            }
        }
    }

    public void getSugarCane(World world, int maxX, int minX, int maxY, int minY, int maxZ, int minZ) {
        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                for (int y = minY - maxY; y <= maxY; y++) {
                    if (world.getBlockAt(x, y + 1, z).getType() == Material.SUGAR_CANE &&
                            world.getBlockAt(x, y + 2, z).getType() == Material.SUGAR_CANE &&
                            world.getBlockAt(x, y + 3, z).getType() == Material.SUGAR_CANE) {
                        world.getBlockAt(x, y + 1, z).setType(Material.AIR);
                        world.getBlockAt(x, y + 2, z).setType(Material.AIR);
                        world.getBlockAt(x, y + 3, z).setType(Material.AIR);
                        Block block = world.getBlockAt(x, y + 1, z);
                        world.dropItem(block.getLocation(), new ItemStack(Material.SUGAR_CANE, 3));
                    } else if (world.getBlockAt(x, y + 1, z).getType() == Material.SUGAR_CANE &&
                            world.getBlockAt(x, y + 2, z).getType() == Material.SUGAR_CANE) {
                        world.getBlockAt(x, y + 1, z).setType(Material.AIR);
                        world.getBlockAt(x, y + 2, z).setType(Material.AIR);
                    } else if (world.getBlockAt(x, y + 1, z).getType() == Material.SUGAR_CANE) {
                        world.getBlockAt(x, y + 1, z).setType(Material.AIR);
                    }
                }
            }
        }
    }
}


