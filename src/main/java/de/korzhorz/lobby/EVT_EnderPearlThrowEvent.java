package de.korzhorz.lobby;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class EVT_EnderPearlThrowEvent implements Listener {
	private main plugin;
	public EVT_EnderPearlThrowEvent(main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onThrow(PlayerTeleportEvent e) {
		Player p = e.getPlayer();
		if(e.getCause() == TeleportCause.ENDER_PEARL) {
			ItemStack CantUseNow = new ItemStack(Material.FIREWORK_ROCKET);
			ItemMeta CantUseNowMeta = CantUseNow.getItemMeta();
			CantUseNowMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Items.NotAvailable")));
			CantUseNow.setItemMeta(CantUseNowMeta);
			
			p.getInventory().setItem(7, CantUseNow);
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

				@Override
				public void run() {
					ItemStack EnderPearl = new ItemStack(Material.ENDER_PEARL);
					ItemMeta EnderPearlMeta = EnderPearl.getItemMeta();
					EnderPearlMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.Gadgets.Enderpearl")));
					EnderPearl.setItemMeta(EnderPearlMeta);
					
					p.getInventory().setItem(7, EnderPearl);					
				}
				
			}, 20*15);	
		}
	}
}
