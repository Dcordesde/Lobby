package de.korzhorz.lobby;

import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.md_5.bungee.api.ChatColor;

public class CMD_setlobby implements CommandExecutor {
	private main plugin;
	public CMD_setlobby(main plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			if(cmd.getName().equalsIgnoreCase("SetLobby")) {
				if(p.hasPermission("lobby.setlobby")) {
					if(args.length == 0) {
						Location Lobby = p.getLocation();
						
						main.lob.set("Lobby.X", Lobby.getX());
						main.lob.set("Lobby.Y", Lobby.getY());
						main.lob.set("Lobby.Z", Lobby.getZ());
						main.lob.set("Lobby.Yaw", Lobby.getYaw());
						main.lob.set("Lobby.Pitch", Lobby.getPitch());
						main.lob.set("Lobby.World", Lobby.getWorld().getName());
  						try {
  							main.lob.save(main.lobby);
  							p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getConfig().getString("Messages.SetLobby")));
  						} catch (IOException e) {
  							e.printStackTrace();
  						}
					} else {
						String UsageMessage = plugin.getConfig().getString("Errors.Usage");
						UsageMessage = UsageMessage.replaceAll("%command%", "/setlobby");
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + UsageMessage));
					}
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getConfig().getString("Errors.NoPermission")));
				}
			}
		} else {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getConfig().getString("Errors.NoPlayers")));
		}
		return true;
	}
}
