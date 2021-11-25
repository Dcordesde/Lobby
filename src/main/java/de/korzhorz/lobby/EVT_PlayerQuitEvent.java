package de.korzhorz.lobby;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class EVT_PlayerQuitEvent implements Listener {
    private main plugin;
    public EVT_PlayerQuitEvent(main plugin) {
    	this.plugin = plugin;
    }
    
    @SuppressWarnings({ "static-access", "unlikely-arg-type" })
	@EventHandler
    public void onJoin(PlayerQuitEvent e) {
    	Player p = e.getPlayer();
    	
    	if(main.lob.contains("Lobby.World")) {    		
    		e.setQuitMessage("");
    		plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&aDer Spieler &c" + p.getName() + " &amit der UUID &c" + p.getUniqueId() + " &ahat sich gerade ausgeloggt."));
    		
    		for(Player hideplayers : Bukkit.getOnlinePlayers()) {
        		if(plugin.HideShow.contains(hideplayers.getName())) {
        			hideplayers.showPlayer(p);    			
        		}
        	}
    	}
    }
}
