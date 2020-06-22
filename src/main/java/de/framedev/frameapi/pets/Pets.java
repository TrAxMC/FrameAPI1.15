package de.framedev.frameapi.pets;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftCreature;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import de.framedev.frameapi.main.Main;

public class Pets implements Listener {
	public static HashMap<String, Entity> Pet = new HashMap<>();

	private static double speed;

	public void removePet(Player player) {
		if (Pet.containsKey(player.getName())) {
			((Entity) Pet.get(player.getName())).remove();
		}
	}

	public void createPet(Player player, EntityType type, String name, double speed, boolean cangetBabies) {
		Entity entity = player.getWorld().spawnEntity(player.getLocation(), type);
		entity.setCustomName(name);
		entity.setCustomNameVisible(true);
		Pet.put(player.getName(), entity);
		Pets.speed = speed;
		getBabies(player, cangetBabies);
	}

	public void walktoLocation(Creature creature, Player player, double speed) {
		Location location = player.getLocation();
		Pets.speed = speed;
		Random rnd = new Random();
		int zufall = rnd.nextInt(5);
		switch (zufall) {
		case 0:
			location.add(1.0D, 0.0D, 1.0D);
			break;
		case 1:
			location.add(0.0D, 0.0D, 1.0D);
			break;
		case 2:
			location.add(1.0D, 0.0D, 0.0D);
			break;
		case 3:
			location.subtract(1.0D, 0.0D, 1.0D);
			break;
		case 4:
			location.subtract(0.0D, 0.0D, 1.0D);
			break;
		}
		if (location.distanceSquared(creature.getLocation()) > 100.0D) {
			if (!player.isOnGround()) {
				return;
			}
			creature.teleport(location);
		} else {
			((CraftCreature) creature).getHandle().getNavigation().a(location.getX(), location.getY(), location.getZ(),
					speed);
		}
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		if (Pet.containsKey(player.getName())) {
			((Entity) Pet.get(player.getName())).remove();
		}
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (Pet.containsKey(e.getPlayer().getName())) {

			double speed = Pets.speed;
			walktoLocation((Creature) Pet.get(e.getPlayer().getName()), e.getPlayer(), speed);
		}
	}

	@EventHandler
	public void EntityDamageEvent(EntityDamageByEntityEvent e) {
		Entity entity = e.getEntity();
		if (Pet.containsValue(entity)) {
			e.setCancelled(true);
		}
	}

	public void getBabies(final Player player, Boolean baby) {
		if (baby)
			(new BukkitRunnable() {
				public void run() {
					World world = player.getWorld();
					world.spawnEntity(player.getLocation(), ((Entity) Pets.Pet.get(player.getName())).getType());
					player.sendMessage("§aYou get a Baby");
				}
			}).runTaskTimer((Plugin) Main.getInstance(), 0L, 1200L);
	}
}
