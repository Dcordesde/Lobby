package de.korzhorz.lobby;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

public class TeleportUtils {
	private static File file;
	private static YamlConfiguration cfg;
	
	public TeleportUtils(main plugin) {
		file = new File(plugin.getDataFolder(), "warps.yml");
		try {
			cfg = YamlConfiguration.loadConfiguration(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(!(file.getParentFile().exists())) {
			file.getParentFile().mkdirs();
		}
		
		if(!(file.exists())) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.err.println("[Lobby] Mindestens eine Datei konnte nicht gespeichert werden.");
				e.printStackTrace();
			}
		}
	}
	
	public static void save(String name, Material material, int slot, Location location, String displayname) {
		String toSave = name + ":" + material.toString() + ":" + slot + ":" + location.getWorld().getName() + ":" + location.getX() + ":" + location.getY() + ":" + location.getZ() + ":" + location.getYaw() + ":" + location.getPitch() + ":" + displayname;
		
		List<String> list;
		try {
			list = cfg.getStringList("Warps");
		} catch (Exception e) {
			list = new ArrayList<>();
		}
		list.add(toSave);
		cfg.set("Warps", list);
		try {
			cfg.save(file);
		} catch (IOException e) {
			System.err.println("[Lobby] Mindestens eine Datei konnte nicht gespeichert werden.");
			e.printStackTrace();
		}
	}
	
	public static YamlConfiguration getCfg () {
		return cfg;
	}
}
