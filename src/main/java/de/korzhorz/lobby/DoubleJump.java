package de.korzhorz.lobby;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

public class DoubleJump implements Listener {
	
	private main plugin;
	public DoubleJump(main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		p.setAllowFlight(true);
		p.setFlying(false);
	}
	
	@EventHandler
	public void onFly(PlayerToggleFlightEvent e) {
		Player p = e.getPlayer();
		if(p.getGameMode() == GameMode.SURVIVAL) {
			if(p.isFlying() == false) {
				if(!(plugin.DoubleJump.contains(p))) {
					e.setCancelled(true);
					p.setAllowFlight(false);
					p.setFlying(false);
					Vector v = p.getLocation().getDirection().multiply(2).add(new Vector(0, 1.2, 0));
					p.setVelocity(v);
					
					plugin.DoubleJump.add(p);
					
					p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 5, 5);
				}
			}
		}
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		
		Location loc = p.getLocation().subtract(0, 1, 0);
		
		if(p.getGameMode() == GameMode.SURVIVAL) {
			if(loc.getBlock().getType() != null) {
				p.setAllowFlight(true);
				p.setFlying(false);
			}
		}
	}
}
