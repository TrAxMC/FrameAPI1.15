package de.framedev.frameapi.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.framedev.frameapi.main.Main;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class UUIDManager {
    List<UUID> uuids = new ArrayList<>();
    private File file = new File(Main.getInstance().getDataFolder(), "uuids.yml");
    private FileConfiguration cfg = YamlConfiguration.loadConfiguration(this.file);

    UUID uuid;

    public UUIDManager(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID generateRandomUUID() {
        UUID uuid = UUID.randomUUID();
        System.out.println("Random UUID was created!");
        return uuid;
    }

    protected void saveCfg() {
        try {
            this.cfg.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void loadCfg() {
        try {
            this.cfg.load(this.file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    protected void createFile() {
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void saveUUIDInFile(UUID uuid) {
        this.uuids = (List<UUID>) this.cfg.getList("UUIDS");
        if (!this.uuids.contains(uuid)) {
            this.uuids.add(uuid);
        }
        this.cfg.set("UUIDS", this.uuids);
        saveCfg();
        createFile();
        loadCfg();
    }

    public List<UUID> getUUIDS() throws NullPointerException {
        return (List<UUID>) this.cfg.getList("UUIDS");
    }
}

