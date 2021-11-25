package de.korzhorz.lobby;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class EVT_HungerLevelChangeEvent implements Listener {
	@EventHandler
	public void onChange(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}
}
