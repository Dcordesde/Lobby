package de.korzhorz.lobby;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class EVT_PlayerMoveEvent implements Listener {
	private main plugin;
	public EVT_PlayerMoveEvent(main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		
		Location loc = p.getLocation();
		Vector v = loc.getDirection().multiply(3D).setY(1);
		
		if(loc.getBlock().getType() == Material.OAK_PRESSURE_PLATE) {
			if(plugin.getConfig().getBoolean("JumpPad.WoodenPlate") == true) {
				p.setVelocity(v);
				p.playSound(loc, Sound.ENTITY_ENDER_DRAGON_FLAP, 5, 5);
			}
		}
		
		if(loc.getBlock().getType() == Material.STONE_PRESSURE_PLATE) {
			if(plugin.getConfig().getBoolean("JumpPad.StonePlate") == true) {
				p.setVelocity(v);
				p.playSound(loc, Sound.ENTITY_ENDER_DRAGON_FLAP, 5, 5);
			}
		}
		
		if(loc.getBlock().getType() == Material.HEAVY_WEIGHTED_PRESSURE_PLATE) {
			if(plugin.getConfig().getBoolean("JumpPad.IronPlate") == true) {
				p.setVelocity(v);
				p.playSound(loc, Sound.ENTITY_ENDER_DRAGON_FLAP, 5, 5);
			}
		}
		
		if(loc.getBlock().getType() == Material.LIGHT_WEIGHTED_PRESSURE_PLATE) {
			if(plugin.getConfig().getBoolean("JumpPad.GoldenPlate") == true) {
				p.setVelocity(v);
				p.playSound(loc, Sound.ENTITY_ENDER_DRAGON_FLAP, 5, 5);
			}
		}
		
		if(p.isOnGround()) {
			plugin.DoubleJump.remove(p);
		}
	}
}
