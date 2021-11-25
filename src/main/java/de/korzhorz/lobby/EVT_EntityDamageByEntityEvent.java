package de.korzhorz.lobby;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EVT_EntityDamageByEntityEvent implements Listener {
	private main plugin;
	public EVT_EntityDamageByEntityEvent(main plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
		
			if(!(plugin.Build.contains(p))) {
				e.setCancelled(true);
			}		
		}
	}
}
