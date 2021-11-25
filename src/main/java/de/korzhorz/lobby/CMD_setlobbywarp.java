package de.korzhorz.lobby;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_setlobbywarp implements CommandExecutor {
    private main plugin;
    public CMD_setlobbywarp(main plugin) {
    	this.plugin = plugin;
    }
    
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			if(cmd.getName().equalsIgnoreCase("setwarp")) {
				if(p.hasPermission("lobby.setwarp")) {
					if(args.length == 3) {
						String WarpName = args[0];
						String DisplayName = args[2];
						
						Material mat = null;
						if(!(p.getInventory().getItemInMainHand().getType() == Material.AIR)) {
							mat = p.getItemInHand().getType();
							
							int Slot;
							
							try {
								Slot = Integer.parseInt(args[1]);
								
								plugin.getTeleportUtils().save(WarpName, mat, Slot, p.getLocation(), DisplayName);
								
								String message = plugin.getConfig().getString("Messages.SetWarp.Message");
								message = message.replaceAll("%name%", WarpName);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + message));
							} catch (Exception e) {
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getConfig().getString("Messages.SetWarp.Errors.NoSlot")));
							}
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getConfig().getString("Messages.SetWarp.Errors.NoItem")));
						}
					} else {
						String UsageMessage = plugin.getConfig().getString("Errors.Usage");
						UsageMessage = UsageMessage.replaceAll("%command%", "/setwarp <Warp-Name> <Slot> <Display-Name>");
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + UsageMessage));
					}
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getConfig().getString("Errors.NoPermission")));
				}
			}
		} else {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getConfig().getString("Errors.NoPlayer")));
		}
		return false;
	}
}
