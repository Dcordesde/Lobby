package de.korzhorz.lobby;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class EVT_WorldInteract implements Listener {
	private main plugin;
	public EVT_WorldInteract(main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if(!(plugin.Build.contains(e.getPlayer()))) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if(!(plugin.Build.contains(e.getPlayer()))) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onArmorStandChange(PlayerArmorStandManipulateEvent e) {
		if(!(plugin.Build.contains(e.getPlayer()))) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(!(plugin.Build.contains(p))) {
				e.setCancelled(true);
			}
		}
	}
}
