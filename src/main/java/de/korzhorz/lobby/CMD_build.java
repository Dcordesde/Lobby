package de.korzhorz.lobby;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CMD_build implements CommandExecutor {
	private main plugin;
	public CMD_build(main plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			if(cmd.getName().equalsIgnoreCase("Build")) {
				if(p.hasPermission("lobby.build")) {
					if(plugin.Build.contains(p)) {
						plugin.Build.remove(p);
						
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
			    		
			    		ItemStack ShowPlayers = new ItemStack(Material.STICK);
			    		ItemMeta ShowPlayersMeta = HidePlayers.getItemMeta();
			    		ShowPlayersMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Items.ShowPlayers")));
			    		ShowPlayers.setItemMeta(ShowPlayersMeta);
			    		
			    		p.getInventory().setItem(0, Teleporter);
			    		if(plugin.HideShow.contains(p)) {
			    			p.getInventory().setItem(1, ShowPlayers);
			    		} else {
			    			p.getInventory().setItem(1, HidePlayers);
			    		}
			    		p.getInventory().setItem(4, Lobbys);
			    		p.getInventory().setItem(7, NotSelected);
			    		p.getInventory().setItem(8, Gadgets);
						
						p.setGameMode(GameMode.SURVIVAL);
						
						p.setWalkSpeed((float) 0.2);
						p.setFlySpeed((float) 0.2);
						
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getConfig().getString("Messages.Build.Leave")));
					} else {
						if(!(plugin.Build.contains(p))) {
							plugin.Build.add(p);
							
							p.getInventory().clear();
							p.getInventory().setHelmet(null);
							p.getInventory().setChestplate(null);
							p.getInventory().setLeggings(null);
							p.getInventory().setBoots(null);
							p.setGameMode(GameMode.CREATIVE);
							
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getConfig().getString("Messages.Build.Join")));
							
						}
					}
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getConfig().getString("Errors.NoPermission")));
				}
			}
		} else {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getConfig().getString("Errors.NoPlayer")));
		}
		return true;
	}
}
