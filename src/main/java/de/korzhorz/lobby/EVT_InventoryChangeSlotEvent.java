package de.korzhorz.lobby;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class EVT_InventoryChangeSlotEvent implements Listener {
	
	private main plugin;
	public EVT_InventoryChangeSlotEvent(main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onChange(PlayerItemHeldEvent e) {
		Player p = e.getPlayer();
		
		if(!(plugin.Build.contains(p))) {
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1, 1);
			// BLOCK_NOTE_BLOCK_HAT
		}
	}
	
}
