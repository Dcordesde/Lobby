package de.korzhorz.lobby;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class EVT_ItemDropEvent implements Listener {
	private main plugin;
	public EVT_ItemDropEvent(main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		
		if(!(plugin.Build.contains(p))) {
			e.setCancelled(true);
		}
	}
}
