package de.korzhorz.lobby;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class ItemAPI {

	ItemStack itemStack;
	ItemMeta itemMeta;
	
	public ItemAPI(String name, Material material, byte subid, int amount, String displayname) {
		itemStack = new ItemStack(material, amount, subid);
		itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayname));
	}
	
	public ItemStack build() {
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
}
