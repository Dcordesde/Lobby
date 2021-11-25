package de.korzhorz.lobby;

import org.bukkit.*;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;


public class EVT_EggThrowEvent implements Listener {
	private main plugin;
	public EVT_EggThrowEvent(main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onThrow(PlayerEggThrowEvent e) {
		Player p = e.getPlayer();
		Egg egg = e.getEgg();
		
		ItemStack CantUseNow = new ItemStack(Material.LEGACY_FIREWORK_CHARGE);
		ItemMeta CantUseNowMeta = CantUseNow.getItemMeta();
		CantUseNowMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Items.NotAvailable")));
		CantUseNow.setItemMeta(CantUseNowMeta);
		
		p.getInventory().setItem(7, CantUseNow);
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

			@Override
			public void run() {
				Particle particle = new Particle(org.bukkit.Particle.EXPLOSION_HUGE, egg.getLocation(), true, 5, 5, 5, 3, 60);
				particle.sendAll();
				for(Player all : Bukkit.getOnlinePlayers()) {
				all.playSound(egg.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, (float) 1, (float) 1);
				}
				
			}
			
		//}, 20*3);
		}, 10);
		e.setHatching(false);
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

			@Override
			public void run() {				
				ItemStack Explosion = new ItemStack(Material.EGG);
				ItemMeta ExplosionMeta = Explosion.getItemMeta();
				ExplosionMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.Gadgets.Explosion")));
				Explosion.setItemMeta(ExplosionMeta);
				
				p.getInventory().setItem(7, Explosion);
			}			
		//}, 20*30);
		}, 40);
	}
}
