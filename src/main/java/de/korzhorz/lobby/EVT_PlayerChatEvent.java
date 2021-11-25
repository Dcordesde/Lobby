package de.korzhorz.lobby;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import net.md_5.bungee.api.ChatColor;

@SuppressWarnings("deprecation")
public class EVT_PlayerChatEvent implements Listener {
	private main plugin;
	public EVT_PlayerChatEvent(main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onChat(final PlayerChatEvent e) {
		Player p = e.getPlayer();
		
		if(plugin.getConfig().getBoolean("PremiumChat") == true) {
			if(!(p.hasPermission("lobby.chat"))) {
				e.setCancelled(true);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getConfig().getString("Errors.NoPremium")));
			}
		}
	}
}
