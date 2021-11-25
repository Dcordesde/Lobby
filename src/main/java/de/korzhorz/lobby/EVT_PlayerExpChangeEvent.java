package de.korzhorz.lobby;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;;

public class EVT_PlayerExpChangeEvent implements Listener {
	@EventHandler
	public void onChange(PlayerExpChangeEvent e) {
		Player p = e.getPlayer();
		
		Date nowdate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		
		int year = Integer.parseInt(format.format(nowdate));
		
		p.setLevel(year);
		p.setExp(1);
	}
	
	@EventHandler
	public void onChange2(PlayerLevelChangeEvent e) {
		Player p = e.getPlayer();
		
		Date nowdate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		
		int year = Integer.parseInt(format.format(nowdate));
		
		p.setLevel(year);
		p.setExp(1);
	}
}
