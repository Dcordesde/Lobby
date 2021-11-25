package de.korzhorz.lobby;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EVT_NaturalDamageEvent implements Listener {
    
    @EventHandler
    public void onNaturalDamage(EntityDamageEvent e) {
    	if(e.getEntity() instanceof Player) {
    		Player p = (Player) e.getEntity();
    		
    		e.setCancelled(true);
    	}
    }
}
