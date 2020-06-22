package de.framedev.frameapi.managers;

import de.framedev.frameapi.main.Main;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;


public class MultiWorldManager {
    String worldname;
    File file = new File("plugins/FrameAPI/worlds.yml");
    FileConfiguration cfg = (FileConfiguration) YamlConfiguration.loadConfiguration(this.file);

    public MultiWorldManager(String worldname) {
        this.worldname = worldname;
    }


    public MultiWorldManager() {
    }


    public MultiWorldManager createWorld() {
        WorldCreator wc = new WorldCreator(this.worldname);
        wc.createWorld();
        return this;
    }

    public MultiWorldManager createWorld(World.Environment environment) {
        WorldCreator wc = new WorldCreator(this.worldname);
        wc.environment(environment);
        wc.createWorld();
        return this;
    }

    public MultiWorldManager createWorld(World.Environment environment, WorldType worldType) {
        WorldCreator wc = new WorldCreator(this.worldname);
        wc.environment(environment);
        wc.type(worldType);
        wc.createWorld();
        return this;
    }

    public MultiWorldManager createWorld(WorldType worldType) {
        WorldCreator wc = new WorldCreator(this.worldname);
        wc.type(worldType);
        wc.createWorld();
        return this;
    }

    public MultiWorldManager teleportToWorld(Player player) {
        World world = Bukkit.getWorld(this.worldname);
        player.teleport(world.getSpawnLocation());
        return this;
    }


    public void saveWorldinConfig() {
        ArrayList<String> worlds = (ArrayList<String>) Main.getInstance().getConfig().getStringList("Worlds");
        if (!worlds.contains(this.worldname)) {


            worlds.add(this.worldname);
            Main.getInstance().getConfig().set("Worlds", worlds);
            Main.getInstance().saveConfig();
        }
    }

    public void setDifficulty(Difficulty difficulty) {
        World world = Bukkit.getWorld(this.worldname);
        if (world != null) {
            world.setDifficulty(difficulty);
        } else {
            Bukkit.getConsoleSender().sendMessage(Main.FrameMainGet.getPrefix() + " §cDiese Welt existiert nicht!");
        }
    }


    public String toString() {
        return "MultiWorldManager{worldname='" + this.worldname + '\'' + ", file=" + this.file + ", cfg=" + this.cfg + '}';
    }


    public void createWorldifNotExists() {
        ArrayList<String> worlds = (ArrayList<String>) Main.getInstance().getConfig().getStringList("Worlds");
        for (String s : worlds) {
            WorldCreator wc = new WorldCreator(s);
            Bukkit.createWorld(wc);
        }
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultiWorldManager that = (MultiWorldManager) o;
        return (Objects.equals(this.worldname, that.worldname) &&
                Objects.equals(this.file, that.file) &&
                Objects.equals(this.cfg, that.cfg));
    }


    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    protected void finalize() throws Throwable {
        super.finalize();
    }


    public int hashCode() {
        return Objects.hash(new Object[]{this.worldname, this.file, this.cfg});
    }
}


