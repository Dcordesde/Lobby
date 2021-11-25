package de.korzhorz.lobby;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EVT_PlayerJoinEvent implements Listener {
    private main plugin;
    public EVT_PlayerJoinEvent(main plugin) {
    	this.plugin = plugin;
    }
    
    @SuppressWarnings({ "static-access", "unlikely-arg-type" })
	@EventHandler
    public void onJoin(PlayerJoinEvent e) {
    	Player p = e.getPlayer();
    	
    	if(main.lob.contains("Lobby.World")) {
    		World world = plugin.getServer().getWorld((String) main.lob.get("Lobby.World"));
    		double X = main.lob.getDouble("Lobby.X");
    		double Y = main.lob.getDouble("Lobby.Y");
    		double Z = main.lob.getDouble("Lobby.Z");
    		float Yaw = (float) main.lob.getDouble("Lobby.Yaw");
    		float Pitch = (float) main.lob.getDouble("Lobby.Pitch");
    		
    		Location LobbyLocation = new Location(world, X, Y, Z, Yaw, Pitch);
    		
    		p.getInventory().clear();
    		p.getInventory().setArmorContents(null);
    		
    		ItemStack Teleporter = new ItemStack(Material.COMPASS);
    		ItemMeta TeleporterMeta = Teleporter.getItemMeta();
    		TeleporterMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Items.Navigator")));
    		Teleporter.setItemMeta(TeleporterMeta);
    		
    		ItemStack HidePlayers = new ItemStack(Material.BLAZE_ROD);
    		ItemMeta HidePlayersMeta = HidePlayers.getItemMeta();
    		HidePlayersMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Items.HidePlayers")));
    		HidePlayers.setItemMeta(HidePlayersMeta);
    		
    		ItemStack Gadgets = new ItemStack(Material.CHEST);
    		ItemMeta GadgetsMeta = Gadgets.getItemMeta();
    		GadgetsMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Items.Inventory")));
    		Gadgets.setItemMeta(GadgetsMeta);
    		
    		ItemStack NotSelected = new ItemStack(Material.GLOWSTONE_DUST);
    		ItemMeta NotSelectedMeta = NotSelected.getItemMeta();
    		NotSelectedMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Items.GadgetNotSelected")));
    		NotSelected.setItemMeta(NotSelectedMeta);
    		
    		ItemStack Lobbys = new ItemStack(Material.NETHER_STAR);
    		ItemMeta LobbysMeta = Lobbys.getItemMeta();
    		LobbysMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Items.LobbySwitch")));
    		Lobbys.setItemMeta(LobbysMeta);
    		
    		p.getInventory().setItem(0, Teleporter);
    		p.getInventory().setItem(1, HidePlayers);
    		p.getInventory().setItem(4, Lobbys);
    		p.getInventory().setItem(7, NotSelected);
    		p.getInventory().setItem(8, Gadgets);
    		
    		p.setGameMode(GameMode.SURVIVAL);
    		
    		e.setJoinMessage("");
    		
    		for(Player hideplayers : Bukkit.getOnlinePlayers()) {
        		if(plugin.HideShow.contains(hideplayers.getName())) {
        			hideplayers.hidePlayer(p);    			
        		}
        	}
    		
    		p.getInventory().setHeldItemSlot(4);    		
    		
    		Date nowdate = new Date();
    		SimpleDateFormat format = new SimpleDateFormat("yyyy");
    		
    		int year = Integer.parseInt(format.format(nowdate));
    		
    		p.setLevel(year);
    		p.setExp(1);
    		
    		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				@Override
				public void run() {
					p.teleport(LobbyLocation);
				}
    		}, 3);
    	} else {
    		p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&cDie Lobby wurde noch nicht gesetzt."));
    		p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&cSetze die Lobby mit &e/setlobby"));
    	}
    }
}
