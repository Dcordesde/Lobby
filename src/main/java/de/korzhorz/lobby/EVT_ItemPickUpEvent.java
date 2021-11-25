package de.korzhorz.lobby;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class EVT_ItemPickUpEvent implements Listener {
	private main plugin;
	public EVT_ItemPickUpEvent(main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onDrop(PlayerPickupItemEvent e) {
		Player p = e.getPlayer();
		
		if(!(plugin.Build.contains(p))) {
			e.setCancelled(true);
		}
	}
}
