package de.korzhorz.lobby;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

public class main extends JavaPlugin implements PluginMessageListener {
	ArrayList<String> Warps = new ArrayList<String>();
	ArrayList<Player> HideShow = new ArrayList<Player>();
	ArrayList<Player> Build = new ArrayList<Player>();
	ArrayList<Player> DoubleJump = new ArrayList<Player>();
	
	public static File lobby;
	public static FileConfiguration lob;
	
	public static File items;
	public static FileConfiguration it;
	
	public static File warps;
	public static FileConfiguration war;
	
	public static File servers;
	public static FileConfiguration ser;
	
	public Inventory warpinv = null;
	public Inventory extrainv = null;
	
	private static TeleportUtils teleportUtils;
	
	public void onEnable() {
		this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6Lobby&7] &aLoading contents"));
		loadConfig();
        loadFiles();
		loadCommands();
		loadEvents();
		teleportUtils = new TeleportUtils(this);
		
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	    this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
		
		this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6Lobby&7] &aPlugin enabled - Version: &cv" + this.getDescription().getVersion()));
		this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6Lobby&7] &aDeveloped by &cKorzHorz"));
		this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6Lobby&7] &aUpdated by &cDavid C."));
		if(main.lob.contains("Lobby.World")) {
			Bukkit.getWorld(main.lob.getString("Lobby.World")).setGameRuleValue("doDaylightCycle", "false");
		}
	}
	
	public void onDisable() {		
		this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6Lobby&7] &aPlugin disabled"));
		this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6Lobby&7] &aDeveloped by &cKorzHorz"));
		this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6Lobby&7] &aUpdated by &cDavid C."));
	}
	
	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
	}
	
	public void loadFiles() {
		main.lobby = new File(this.getDataFolder(), "lobby.yml");
		main.lob = YamlConfiguration.loadConfiguration(main.lobby);
		
		main.items = new File(this.getDataFolder(), "items.yml");
		main.it = YamlConfiguration.loadConfiguration(main.items);
		
		main.warps = new File(this.getDataFolder(), "warps.yml");
		main.war = YamlConfiguration.loadConfiguration(main.warps);
	}
	
	public void loadCommands() {
		this.getCommand("SetLobby").setExecutor(new CMD_setlobby(this));
		this.getCommand("SetWarp").setExecutor(new CMD_setlobbywarp(this));
		this.getCommand("Build").setExecutor(new CMD_build(this));
	}
	
	public void loadEvents() {
		Bukkit.getPluginManager().registerEvents(new EVT_PlayerJoinEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new EVT_PlayerQuitEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new EVT_ItemInteractEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new EVT_NaturalDamageEvent(), this);
		Bukkit.getPluginManager().registerEvents(new EVT_EntityDamageByEntityEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new EVT_ItemDropEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new EVT_ItemPickUpEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new EVT_PlayerChatEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new EVT_PlayerMoveEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new EVT_InventoryChangeSlotEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new EVT_HungerLevelChangeEvent(), this);
		Bukkit.getPluginManager().registerEvents(new EVT_PlayerExpChangeEvent(), this);
		Bukkit.getPluginManager().registerEvents(new EVT_WeatherChangeEvent(), this);
		Bukkit.getPluginManager().registerEvents(new EVT_WorldInteract(this), this);
		Bukkit.getPluginManager().registerEvents(new EVT_EggThrowEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new EVT_EnderPearlThrowEvent(this), this);
		if(getConfig().getBoolean("DoubleJump") == true) {
			Bukkit.getPluginManager().registerEvents(new DoubleJump(this), this);
		}
	}
	
	public static TeleportUtils getTeleportUtils() {
		return teleportUtils;
	}
	
	public String prefix = getConfig().getString("Prefix") + "&r ";
	
	
	public void onPluginMessageReceived(String channel, Player p, byte[] message) {
		if(!(channel.equals("BungeeCord"))) {
			return;
		}
		
		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		String subchannel = in.readUTF();
		if(subchannel.equals("SomeSubChannel")) {
			
		}
	}
	
	
	public void connectToServer(Player p, String server) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("Connect");
			out.writeUTF(server);
		} catch (Exception e) {
			e.printStackTrace();
		}
		p.sendPluginMessage(this, "BungeeCord", b.toByteArray());
	}
}
