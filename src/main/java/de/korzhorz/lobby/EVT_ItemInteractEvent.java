package de.korzhorz.lobby;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.util.Vector;

import net.md_5.bungee.api.ChatColor;

public class EVT_ItemInteractEvent implements Listener {
	private main plugin;
	public EVT_ItemInteractEvent(main plugin) {
		this.plugin = plugin;
	}
	
	public HashMap<String, Location> locationHashMap = new HashMap<>();
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(e.getItem() !=null) {
			if(e.getMaterial() == Material.COMPASS) {
				Inventory inv = Bukkit.createInventory(null, 5*9, ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.Navigator.DisplayName")));
				
				List<String> list = getWarps();
				
				for(String s : list) {
					try {
						String WarpName = s.split(":") [0];
						Material material = Material.valueOf(s.split(":") [1].toUpperCase());
						int Slot = Integer.parseInt(s.split(":") [2]);
						String DisplayName = s.split(":") [9];
						Location WarpLocation = new Location(Bukkit.getWorld(s.split(":") [3]), Double.valueOf(s.split(":") [4]), Double.valueOf(s.split(":") [5]), Double.valueOf(s.split(":") [6]), Float.valueOf(s.split(":") [7]), Float.valueOf(s.split(":") [8]));
						
						locationHashMap.put(ChatColor.translateAlternateColorCodes('&', DisplayName), WarpLocation);
						inv.setItem(Slot, new ItemAPI(WarpName, material, (byte) 0, 1, DisplayName).build());
					} catch (Exception e1) {
						
					}
				}
				e.getPlayer().openInventory(inv);
			} else {
			if(e.getMaterial() == Material.BLAZE_ROD) {
				for(Player all : Bukkit.getOnlinePlayers()) {
					if(!plugin.HideShow.contains(e.getPlayer())) {
						plugin.HideShow.add(e.getPlayer());

						ItemStack ShowPlayers = new ItemStack(Material.STICK);
						ItemMeta ShowPlayersMeta = ShowPlayers.getItemMeta();
						ShowPlayersMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Items.ShowPlayers")));
						ShowPlayers.setItemMeta(ShowPlayersMeta);

						e.getPlayer().getInventory().setItem(1, ShowPlayers);

						e.getPlayer().hidePlayer(plugin, all);
						//e.getPlayer().hidePlayer(all);

						e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					} else {

						e.getPlayer().hidePlayer(plugin, all);
						//e.getPlayer().hidePlayer(all);

						ItemStack ShowPlayers = new ItemStack(Material.STICK);
						ItemMeta ShowPlayersMeta = ShowPlayers.getItemMeta();
						ShowPlayersMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Items.ShowPlayers")));
						ShowPlayers.setItemMeta(ShowPlayersMeta);

						e.getPlayer().getInventory().setItem(1, ShowPlayers);

						e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				}
			} else {
			if(e.getMaterial() == Material.STICK) {
					for(Player all : Bukkit.getOnlinePlayers()) {
						if(!plugin.HideShow.contains(e.getPlayer())) {
							plugin.HideShow.remove(e.getPlayer());

							ItemStack HidePlayers = new ItemStack(Material.BLAZE_ROD);
							ItemMeta HidePlayersMeta = HidePlayers.getItemMeta();
							HidePlayersMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Items.HidePlayers")));
							HidePlayers.setItemMeta(HidePlayersMeta);

							e.getPlayer().getInventory().setItem(1, HidePlayers);

							e.getPlayer().showPlayer(all);

							e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
						} else {
							ItemStack HidePlayers = new ItemStack(Material.BLAZE_ROD);
							ItemMeta HidePlayersMeta = HidePlayers.getItemMeta();
							HidePlayersMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Items.HidePlayers")));
							HidePlayers.setItemMeta(HidePlayersMeta);

							e.getPlayer().getInventory().setItem(1, HidePlayers);

							e.getPlayer().showPlayer(all);

							e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
						}
					}
				} else
			if(e.getMaterial() == Material.NETHER_STAR) {
					Inventory inv = Bukkit.createInventory(null, 4*9, ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.LobbySwitch.DisplayName")));

					ItemStack PremiumLobby;
					if(plugin.getConfig().getBoolean("LobbyServers.PremiumLobby") == true) {
						PremiumLobby = new ItemStack(Material.GLOWSTONE_DUST);
						ItemMeta PremiumLobbyMeta = PremiumLobby.getItemMeta();
						PremiumLobbyMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.LobbySwitch.PremiumLobby")));
						PremiumLobby.setItemMeta(PremiumLobbyMeta);
					} else {
						PremiumLobby = new ItemStack(Material.BARRIER);
						ItemMeta PremiumLobbyMeta = PremiumLobby.getItemMeta();
						PremiumLobbyMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.LobbySwitch.NotEnabled")));
						PremiumLobby.setItemMeta(PremiumLobbyMeta);
					}

					ItemStack Lobby1;
					if(plugin.getConfig().getBoolean("LobbyServers.Lobby1") == true) {
						Lobby1 = new ItemStack(Material.SUGAR);
						ItemMeta Lobby1Meta = Lobby1.getItemMeta();
						Lobby1Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.LobbySwitch.Lobby1")));
						Lobby1.setItemMeta(Lobby1Meta);
					} else {
						Lobby1 = new ItemStack(Material.BARRIER);
						ItemMeta Lobby1Meta = Lobby1.getItemMeta();
						Lobby1Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.LobbySwitch.NotEnabled")));
						Lobby1.setItemMeta(Lobby1Meta);
					}

					ItemStack Lobby2;
					if(plugin.getConfig().getBoolean("LobbyServers.Lobby2") == true) {
						Lobby2 = new ItemStack(Material.SUGAR);
						ItemMeta Lobby2Meta = Lobby2.getItemMeta();
						Lobby2Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.LobbySwitch.Lobby2")));
						Lobby2.setItemMeta(Lobby2Meta);
					} else {
						Lobby2 = new ItemStack(Material.BARRIER);
						ItemMeta Lobby2Meta = Lobby2.getItemMeta();
						Lobby2Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.LobbySwitch.NotEnabled")));
						Lobby2.setItemMeta(Lobby2Meta);
					}

					ItemStack Lobby3;
					if(plugin.getConfig().getBoolean("LobbyServers.Lobby3") == true) {
						Lobby3 = new ItemStack(Material.SUGAR);
						ItemMeta Lobby3Meta = Lobby3.getItemMeta();
						Lobby3Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.LobbySwitch.Lobby3")));
						Lobby3.setItemMeta(Lobby3Meta);
					} else {
						Lobby3 = new ItemStack(Material.BARRIER);
						ItemMeta Lobby3Meta = Lobby3.getItemMeta();
						Lobby3Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.LobbySwitch.NotEnabled")));
						Lobby3.setItemMeta(Lobby3Meta);
					}

					inv.setItem(13, PremiumLobby);
					inv.setItem(21, Lobby1);
					inv.setItem(22, Lobby2);
					inv.setItem(23, Lobby3);

					e.getPlayer().openInventory(inv);
				} else
			if(e.getMaterial() == Material.CHEST) {
						Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.Inventory.DisplayName")));
						
						ItemStack Boots = new ItemStack(Material.LEATHER_BOOTS);
						Boots.addEnchantment(Enchantment.PROTECTION_FALL, 1);
						LeatherArmorMeta BootsMeta = (LeatherArmorMeta) Boots.getItemMeta();
						BootsMeta.setColor(Color.fromBGR(0, 150, 255));
						BootsMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.Inventory.Boots")));
						BootsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
						BootsMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
						Boots.setItemMeta(BootsMeta);
						
						ItemStack Skulls = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
						Skulls.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
						SkullMeta SkullsMeta = (SkullMeta) Skulls.getItemMeta();
						SkullsMeta.setOwner(plugin.getConfig().getString("Inventorys.Inventory.Skulls.SkullOwner"));
						SkullsMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.Inventory.Skulls.DisplayName")));
						SkullsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
						SkullsMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
						Skulls.setItemMeta(SkullsMeta);
						
						ItemStack Gadgets = new ItemStack(Material.ENDER_CHEST);
						Gadgets.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
						ItemMeta GadgetsMeta = Gadgets.getItemMeta();
						GadgetsMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.Inventory.Gadgets")));
						GadgetsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
						GadgetsMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
						Gadgets.setItemMeta(GadgetsMeta);
						
						inv.setItem(0, Boots);
						inv.setItem(2, Skulls);
						inv.setItem(4, Gadgets);
						
						e.getPlayer().openInventory(inv);
					} else
			if(e.getMaterial() == Material.FLINT_AND_STEEL) {
						Vector v = e.getPlayer().getLocation().getDirection().multiply(0.0D).setY(2D);
	    				
						e.getPlayer().setVelocity(v);
						e.getPlayer().setAllowFlight(true);
	    				
	    				Bukkit.getScheduler().runTaskLater(this.plugin, new Runnable() {
							@Override
							public void run() {
								e.getPlayer().setAllowFlight(false);	
							}
	    				}, 30);
					}
				}
			}
		}
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_BLOCK) {
			if(!(plugin.Build.contains(e.getPlayer()))) {
				if(e.getClickedBlock().getType() == Material.BEACON || e.getClickedBlock().getType() == Material.DISPENSER || e.getClickedBlock().getType() == Material.CHEST || e.getClickedBlock().getType() == Material.JUKEBOX || e.getClickedBlock().getType() == Material.BREWING_STAND || e.getClickedBlock().getType() == Material.FURNACE || e.getClickedBlock().getType() == Material.DROPPER || e.getClickedBlock().getType() == Material.HOPPER || e.getClickedBlock().getType() == Material.NOTE_BLOCK || e.getClickedBlock().getType() == Material.ANVIL || e.getClickedBlock().getType() == Material.ENDER_CHEST || e.getClickedBlock().getType() == Material.ENCHANTING_TABLE || e.getClickedBlock().getType() == Material.TRAPPED_CHEST) {
					e.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if(!(plugin.Build.contains(e.getWhoClicked()))) {
			if(e.getClickedInventory() != null) {
				if(e.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.Navigator.DisplayName")))) {
					if(e.getCurrentItem() != null) {
						if(e.getCurrentItem().getType() != Material.AIR) {
							if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
								String displayname = e.getCurrentItem().getItemMeta().getDisplayName();
								if (locationHashMap.containsKey(ChatColor.translateAlternateColorCodes('&', displayname))) {
									e.getWhoClicked().closeInventory();
									e.getWhoClicked().teleport(locationHashMap.get(ChatColor.translateAlternateColorCodes('&', displayname)));
									if (e.getWhoClicked() instanceof Player) {
										Player p = (Player) e.getWhoClicked();
										p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
									}
								}
							}
						}
						e.setCancelled(true);
					}
					e.setCancelled(true);
				} else if(e.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.LobbySwitch.DisplayName")))) {
					e.setCancelled(true);
					if(e.getCurrentItem() != null) {
						if (e.getCurrentItem().getType() != Material.AIR) {
							if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
								String displayname = e.getCurrentItem().getItemMeta().getDisplayName();
								if (displayname.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.LobbySwitch.PremiumLobby")))) {
									if (e.getWhoClicked().hasPermission("lobby.premiumlobby")) {
										String premiumlobbyname = plugin.getConfig().getString("ServerNames.PremiumLobby");
										e.getWhoClicked().closeInventory();
										plugin.connectToServer((Player) e.getWhoClicked(), premiumlobbyname);
										if (e.getWhoClicked() instanceof Player) {
											Player p = (Player) e.getWhoClicked();
											p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
										}
									} else {
										e.getWhoClicked().closeInventory();
										e.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getConfig().getString("Errors.NoPremium")));
									}
								}

								if (displayname.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.LobbySwitch.Lobby1")))) {
									String lobbyname = plugin.getConfig().getString("ServerNames.Lobby1");
									e.getWhoClicked().closeInventory();
									plugin.connectToServer((Player) e.getWhoClicked(), lobbyname);
									if (e.getWhoClicked() instanceof Player) {
										Player p = (Player) e.getWhoClicked();
										p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
									}
								}

								if (displayname.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.LobbySwitch.Lobby2")))) {
									String lobbyname = plugin.getConfig().getString("ServerNames.Lobby2");
									e.getWhoClicked().closeInventory();
									plugin.connectToServer((Player) e.getWhoClicked(), lobbyname);
									System.out.println(lobbyname);
									if (e.getWhoClicked() instanceof Player) {
										Player p = (Player) e.getWhoClicked();
										p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
									}
								}

								if (displayname.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.LobbySwitch.Lobby3")))) {
									String lobbyname = plugin.getConfig().getString("ServerNames.Lobby3");
									e.getWhoClicked().closeInventory();
									plugin.connectToServer((Player) e.getWhoClicked(), lobbyname);
									System.out.println(lobbyname);
									if (e.getWhoClicked() instanceof Player) {
										Player p = (Player) e.getWhoClicked();
										p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
									}
								}
							}
						}
					}
				} else if(e.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.Inventory.DisplayName")))) {
					if(e.getCurrentItem() != null) {
						if(e.getCurrentItem().getType() != Material.AIR) {
							if(e.getCurrentItem().getItemMeta().hasDisplayName()) {
								String displayname = e.getCurrentItem().getItemMeta().getDisplayName();
								if(displayname.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.Inventory.Boots")))) {
									Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.Inventory.Boots")));
									
									ItemStack Boots1 = new ItemStack(Material.LEATHER_BOOTS);
									LeatherArmorMeta Boots1Meta = (LeatherArmorMeta) Boots1.getItemMeta();
									Boots1Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Items.Boots.1.DisplayName")));
									Boots1.setItemMeta(Boots1Meta);
									
									ItemStack Boots2 = new ItemStack(Material.LEATHER_BOOTS);
									Boots2.addEnchantment(Enchantment.PROTECTION_FALL, 1);
									LeatherArmorMeta Boots2Meta = (LeatherArmorMeta) Boots2.getItemMeta();
									Boots2Meta.setColor(Color.fromBGR(255, 100, 255));
									Boots2Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Items.Boots.2.DisplayName")));
									Boots2Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
									Boots2.setItemMeta(Boots2Meta);
									
									ItemStack Boots3 = new ItemStack(Material.LEATHER_BOOTS);
									Boots3.addEnchantment(Enchantment.PROTECTION_FALL, 1);
									LeatherArmorMeta Boots3Meta = (LeatherArmorMeta) Boots3.getItemMeta();
									Boots3Meta.setColor(Color.fromBGR(0, 150, 255));
									Boots3Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Items.Boots.3.DisplayName")));
									Boots3Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
									Boots3Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
									Boots3.setItemMeta(Boots3Meta);
									
									ItemStack Boots4 = new ItemStack(Material.LEATHER_BOOTS);
									Boots4.addEnchantment(Enchantment.PROTECTION_FALL, 1);
									LeatherArmorMeta Boots4Meta = (LeatherArmorMeta) Boots4.getItemMeta();
									Boots4Meta.setColor(Color.fromBGR(160, 0, 160));
									Boots4Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Items.Boots.4.DisplayName")));
									Boots4Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
									Boots4Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
									Boots4.setItemMeta(Boots4Meta);
									
									ItemStack Boots5 = new ItemStack(Material.LEATHER_BOOTS);
									Boots5.addEnchantment(Enchantment.PROTECTION_FALL, 1);
									LeatherArmorMeta Boots5Meta = (LeatherArmorMeta) Boots5.getItemMeta();
									Boots5Meta.setColor(Color.fromBGR(30, 30, 190));
									Boots5Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Items.Boots.5.DisplayName")));
									Boots5Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
									Boots5Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
									Boots5.setItemMeta(Boots5Meta);
									
									ItemStack NoPermissionBoots = new ItemStack(Material.LEATHER_BOOTS);
									LeatherArmorMeta NoPermissionBootsMeta = (LeatherArmorMeta) NoPermissionBoots.getItemMeta();
									NoPermissionBootsMeta.setColor(Color.fromBGR(0, 0, 255));
									NoPermissionBootsMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Items.Boots.NoPermission.DisplayName")));
									Boots3Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
									Boots3Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
									NoPermissionBoots.setItemMeta(NoPermissionBootsMeta);
									
									if(e.getWhoClicked().hasPermission("lobby.boots1")) {
										inv.setItem(0, Boots1);
									} else {
										inv.setItem(0, NoPermissionBoots);
									}
									if(e.getWhoClicked().hasPermission("lobby.boots2")) {
										inv.setItem(1, Boots2);
									} else {
										inv.setItem(1, NoPermissionBoots);
									}
									if(e.getWhoClicked().hasPermission("lobby.boots3")) {
										inv.setItem(2, Boots3);
									} else {
										inv.setItem(2, NoPermissionBoots);
									}
									if(e.getWhoClicked().hasPermission("lobby.boots4")) {
										inv.setItem(3, Boots4);
									} else {
										inv.setItem(3, NoPermissionBoots);
									}
									if(e.getWhoClicked().hasPermission("lobby.boots5")) {
										inv.setItem(4, Boots5);
									} else {
										inv.setItem(4, NoPermissionBoots);
									}
									
									e.getWhoClicked().openInventory(inv);
									if(e.getWhoClicked() instanceof Player) {Player p = (Player) e.getWhoClicked(); p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);}
									e.setCancelled(true);
								} else if(displayname.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.Inventory.Skulls.DisplayName")))) {
									Inventory inv = Bukkit.createInventory(null, 5*9, ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.Inventory.Skulls.DisplayName")));

									String Skull1Owner = plugin.getConfig().getString("Items.Skulls.1.SkullOwner");
									String Skull2Owner = plugin.getConfig().getString("Items.Skulls.2.SkullOwner");
									String Skull3Owner = plugin.getConfig().getString("Items.Skulls.3.SkullOwner");
									String Skull4Owner = plugin.getConfig().getString("Items.Skulls.4.SkullOwner");
									String Skull5Owner = plugin.getConfig().getString("Items.Skulls.5.SkullOwner");
									String Skull6Owner = plugin.getConfig().getString("Items.Skulls.6.SkullOwner");
									String Skull7Owner = plugin.getConfig().getString("Items.Skulls.7.SkullOwner");
									String Skull8Owner = plugin.getConfig().getString("Items.Skulls.8.SkullOwner");
									OfflinePlayer Skull9Owner = Bukkit.getOfflinePlayer(Objects.requireNonNull(plugin.getConfig().getString("Items.Skulls.9.SkullOwner")));
									//String Skull9Owner = plugin.getConfig().getString("Items.Skulls.9.SkullOwner");
									String Skull10Owner = plugin.getConfig().getString("Items.Skulls.10.SkullOwner");
									String Skull11Owner = plugin.getConfig().getString("Items.Skulls.11.SkullOwner");
									String Skull12Owner = plugin.getConfig().getString("Items.Skulls.12.SkullOwner");
									String Skull13Owner = plugin.getConfig().getString("Items.Skulls.13.SkullOwner");
									String Skull14Owner = plugin.getConfig().getString("Items.Skulls.14.SkullOwner");
									
									String Skull1DisplayName = plugin.getConfig().getString("Items.Skulls.1.DisplayName");
									String Skull2DisplayName = plugin.getConfig().getString("Items.Skulls.2.DisplayName");
									String Skull3DisplayName = plugin.getConfig().getString("Items.Skulls.3.DisplayName");
									String Skull4DisplayName = plugin.getConfig().getString("Items.Skulls.4.DisplayName");
									String Skull5DisplayName = plugin.getConfig().getString("Items.Skulls.5.DisplayName");
									String Skull6DisplayName = plugin.getConfig().getString("Items.Skulls.6.DisplayName");
									String Skull7DisplayName = plugin.getConfig().getString("Items.Skulls.7.DisplayName");
									String Skull8DisplayName = plugin.getConfig().getString("Items.Skulls.8.DisplayName");
									String Skull9DisplayName = plugin.getConfig().getString("Items.Skulls.9.DisplayName");
									String Skull10DisplayName = plugin.getConfig().getString("Items.Skulls.10.DisplayName");
									String Skull11DisplayName = plugin.getConfig().getString("Items.Skulls.11.DisplayName");
									String Skull12DisplayName = plugin.getConfig().getString("Items.Skulls.12.DisplayName");
									String Skull13DisplayName = plugin.getConfig().getString("Items.Skulls.13.DisplayName");
									String Skull14DisplayName = plugin.getConfig().getString("Items.Skulls.14.DisplayName");
									
									String NoPermissionsSkullDisplayName = plugin.getConfig().getString("Items.Skulls.NoPermission.DisplayName");
									
									ItemStack Skull1 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
									SkullMeta Skull1Meta = (SkullMeta) Skull1.getItemMeta();
									Skull1Meta.setOwner(Skull1Owner);
									Skull1Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Skull1DisplayName));
									Skull1.setItemMeta(Skull1Meta);
									
									ItemStack Skull2 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
									SkullMeta Skull2Meta = (SkullMeta) Skull2.getItemMeta();
									Skull2Meta.setOwner(Skull2Owner);
									Skull2Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Skull2DisplayName));
									Skull2.setItemMeta(Skull2Meta);
									
									ItemStack Skull3 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
									SkullMeta Skull3Meta = (SkullMeta) Skull3.getItemMeta();
									Skull3Meta.setOwner(Skull3Owner);
									Skull3Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Skull3DisplayName));
									Skull3.setItemMeta(Skull3Meta);
									
									ItemStack Skull4 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
									SkullMeta Skull4Meta = (SkullMeta) Skull4.getItemMeta();
									Skull4Meta.setOwner(Skull4Owner);
									Skull4Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Skull4DisplayName));
									Skull4.setItemMeta(Skull4Meta);
									
									ItemStack Skull5 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
									SkullMeta Skull5Meta = (SkullMeta) Skull5.getItemMeta();
									Skull5Meta.setOwner(Skull5Owner);
									Skull5Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Skull5DisplayName));
									Skull5.setItemMeta(Skull5Meta);
									
									ItemStack Skull6 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
									SkullMeta Skull6Meta = (SkullMeta) Skull6.getItemMeta();
									Skull6Meta.setOwner(Skull6Owner);
									Skull6Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Skull6DisplayName));
									Skull6.setItemMeta(Skull6Meta);
									
									ItemStack Skull7 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
									SkullMeta Skull7Meta = (SkullMeta) Skull7.getItemMeta();
									Skull7Meta.setOwner(Skull7Owner);
									Skull7Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Skull7DisplayName));
									Skull7.setItemMeta(Skull7Meta);
									
									ItemStack Skull8 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
									SkullMeta Skull8Meta = (SkullMeta) Skull8.getItemMeta();
									Skull8Meta.setOwner(Skull8Owner);
									Skull8Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Skull8DisplayName));
									Skull8.setItemMeta(Skull8Meta);
									
									ItemStack Skull9 = new ItemStack(Material.PLAYER_HEAD, 1);
									SkullMeta Skull9Meta = (SkullMeta) Skull9.getItemMeta();
									Skull9Meta.setOwningPlayer(Skull9Owner);
									Skull9Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Skull9DisplayName));
									Skull9.setItemMeta(Skull9Meta);
									
									ItemStack Skull10 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
									SkullMeta Skull10Meta = (SkullMeta) Skull10.getItemMeta();
									Skull10Meta.setOwner(Skull10Owner);
									Skull10Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Skull10DisplayName));
									Skull10.setItemMeta(Skull10Meta);
									
									ItemStack Skull11 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
									SkullMeta Skull11Meta = (SkullMeta) Skull11.getItemMeta();
									Skull11Meta.setOwner(Skull11Owner);
									Skull11Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Skull11DisplayName));
									Skull11.setItemMeta(Skull11Meta);
									
									ItemStack Skull12 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
									SkullMeta Skull12Meta = (SkullMeta) Skull12.getItemMeta();
									Skull12Meta.setOwner(Skull12Owner);
									Skull12Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Skull12DisplayName));
									Skull12.setItemMeta(Skull12Meta);
									
									ItemStack Skull13 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
									SkullMeta Skull13Meta = (SkullMeta) Skull13.getItemMeta();
									Skull13Meta.setOwner(Skull13Owner);
									Skull13Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Skull13DisplayName));
									Skull13.setItemMeta(Skull13Meta);
									
									ItemStack Skull14 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
									SkullMeta Skull14Meta = (SkullMeta) Skull14.getItemMeta();
									Skull14Meta.setOwner(Skull14Owner);
									Skull14Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Skull14DisplayName));
									Skull14.setItemMeta(Skull14Meta);
									
									ItemStack NoPermissionsSkull = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 1);
									SkullMeta NoPermissionsSkullMeta = (SkullMeta) NoPermissionsSkull.getItemMeta();
									NoPermissionsSkullMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', NoPermissionsSkullDisplayName));
									NoPermissionsSkull.setItemMeta(NoPermissionsSkullMeta);
									
									if(e.getWhoClicked().hasPermission("lobby.skull1")) {
										inv.setItem(10, Skull1);
									} else {
										inv.setItem(10, NoPermissionsSkull);
									}
									
									if(e.getWhoClicked().hasPermission("lobby.skull2")) {
										inv.setItem(11, Skull2);
									} else {
										inv.setItem(11, NoPermissionsSkull);
									}
									
									if(e.getWhoClicked().hasPermission("lobby.skull3")) {
										inv.setItem(12, Skull3);
									} else {
										inv.setItem(12, NoPermissionsSkull);
									}
									
									if(e.getWhoClicked().hasPermission("lobby.skull4")) {
										inv.setItem(13, Skull4);
									} else {
										inv.setItem(13, NoPermissionsSkull);
									}
									
									if(e.getWhoClicked().hasPermission("lobby.skull5")) {
										inv.setItem(14, Skull5);
									} else {
										inv.setItem(14, NoPermissionsSkull);
									}
									
									if(e.getWhoClicked().hasPermission("lobby.skull6")) {
										inv.setItem(15, Skull6);
									} else {
										inv.setItem(15, NoPermissionsSkull);
									}
									
									if(e.getWhoClicked().hasPermission("lobby.skull7")) {
										inv.setItem(16, Skull7);
									} else {
										inv.setItem(16, NoPermissionsSkull);
									}
									
									if(e.getWhoClicked().hasPermission("lobby.skull8")) {
										inv.setItem(28, Skull8);
									} else {
										inv.setItem(28, NoPermissionsSkull);
									}
									
									if(e.getWhoClicked().hasPermission("lobby.skull9")) {
										inv.setItem(29, Skull9);
									} else {
										inv.setItem(29, NoPermissionsSkull);
									}
									
									if(e.getWhoClicked().hasPermission("lobby.skull10")) {
										inv.setItem(30, Skull10);
									} else {
										inv.setItem(30, NoPermissionsSkull);
									}
									
									if(e.getWhoClicked().hasPermission("lobby.skull11")) {
										inv.setItem(31, Skull11);
									} else {
										inv.setItem(31, NoPermissionsSkull);
									}
									
									if(e.getWhoClicked().hasPermission("lobby.skull12")) {
										inv.setItem(32, Skull12);
									} else {
										inv.setItem(32, NoPermissionsSkull);
									}
									
									if(e.getWhoClicked().hasPermission("lobby.skull13")) {
										inv.setItem(33, Skull13);
									} else {
										inv.setItem(33, NoPermissionsSkull);
									}
									
									if(e.getWhoClicked().hasPermission("lobby.skull14")) {
										inv.setItem(34, Skull14);
									} else {
										inv.setItem(34, NoPermissionsSkull);
									}
									
									e.getWhoClicked().openInventory(inv);
									if(e.getWhoClicked() instanceof Player) {Player p = (Player) e.getWhoClicked(); p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);}
									e.setCancelled(true);
								} else if(displayname.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.Inventory.Gadgets")))) {
									Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.Inventory.Gadgets")));
									
									ItemStack EnderPearl = new ItemStack(Material.ENDER_PEARL);
									ItemMeta EnderPearlMeta = EnderPearl.getItemMeta();
									EnderPearlMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.Gadgets.Enderpearl")));
									EnderPearl.setItemMeta(EnderPearlMeta);
									
									ItemStack Explosion = new ItemStack(Material.EGG);
									ItemMeta ExplosionMeta = Explosion.getItemMeta();
									ExplosionMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.Gadgets.Explosion")));
									Explosion.setItemMeta(ExplosionMeta);
									
									ItemStack Jetpack = new ItemStack(Material.FLINT_AND_STEEL);
									ItemMeta JetpackMeta = Jetpack.getItemMeta();
									JetpackMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.Gadgets.Jetpack")));
									Jetpack.setItemMeta(JetpackMeta);
									
									ItemStack NoPermissions = new ItemStack(Material.FIREWORK_ROCKET);
									ItemMeta NoPermissionsMeta = NoPermissions.getItemMeta();
									NoPermissionsMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.Gadgets.NoPermission")));
									NoPermissions.setItemMeta(NoPermissionsMeta);
									
									if(e.getWhoClicked().hasPermission("lobby.enderpearl")) {
										inv.setItem(0, EnderPearl);
									} else {
										inv.setItem(0, NoPermissions);
									}
									
									if(e.getWhoClicked().hasPermission("lobby.explosion")) {
										inv.setItem(2, Explosion);
									} else {
										inv.setItem(2, NoPermissions);
									}
									
									if(e.getWhoClicked().hasPermission("lobby.jetpack")) {
										inv.setItem(4, Jetpack);
									} else {
										inv.setItem(4, NoPermissions);
									}
										
									e.getWhoClicked().openInventory(inv);
									if(e.getWhoClicked() instanceof Player) {Player p = (Player) e.getWhoClicked(); p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);}
									e.setCancelled(true);
								}
							}
						}
					}
				} else if(e.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.Inventory.Boots")))) {
					if(e.getCurrentItem() != null) {
						if(e.getCurrentItem().getItemMeta().hasDisplayName()) {
							String Boots1DisplayName = plugin.getConfig().getString("Items.Boots.1.DisplayName");
							String Boots2DisplayName = plugin.getConfig().getString("Items.Boots.2.DisplayName");
							String Boots3DisplayName = plugin.getConfig().getString("Items.Boots.3.DisplayName");
							String Boots4DisplayName = plugin.getConfig().getString("Items.Boots.4.DisplayName");
							String Boots5DisplayName = plugin.getConfig().getString("Items.Boots.5.DisplayName");
							
							if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Boots1DisplayName))) {
								if(e.getWhoClicked().hasPermission("lobby.boots1")) {
									ItemStack Boots1 = new ItemStack(Material.LEATHER_BOOTS);
									LeatherArmorMeta Boots1Meta = (LeatherArmorMeta) Boots1.getItemMeta();
									Boots1Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Boots1DisplayName));
									Boots1.setItemMeta(Boots1Meta);
									
									e.getWhoClicked().getInventory().setBoots(Boots1);
									e.getWhoClicked().closeInventory();
									if(e.getWhoClicked() instanceof Player) {Player p = (Player) e.getWhoClicked(); p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);}
								}
							} else {
								if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Boots2DisplayName))) {
									if(e.getWhoClicked().hasPermission("lobby.boots2")) {
										ItemStack Boots2 = new ItemStack(Material.LEATHER_BOOTS);
										Boots2.addEnchantment(Enchantment.PROTECTION_FALL, 1);
										LeatherArmorMeta Boots2Meta = (LeatherArmorMeta) Boots2.getItemMeta();
										Boots2Meta.setColor(Color.fromBGR(255, 100, 255));
										Boots2Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Boots2DisplayName));
										Boots2Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
										Boots2.setItemMeta(Boots2Meta);
										
										e.getWhoClicked().getInventory().setBoots(Boots2);
										e.getWhoClicked().closeInventory();
										if(e.getWhoClicked() instanceof Player) {Player p = (Player) e.getWhoClicked(); p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);}
									}
								} else {
									if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Boots3DisplayName))) {
										if(e.getWhoClicked().hasPermission("lobby.boots3")) {
											ItemStack Boots3 = new ItemStack(Material.LEATHER_BOOTS);
											Boots3.addEnchantment(Enchantment.PROTECTION_FALL, 1);
											LeatherArmorMeta Boots3Meta = (LeatherArmorMeta) Boots3.getItemMeta();
											Boots3Meta.setColor(Color.fromBGR(0, 150, 255));
											Boots3Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Boots3DisplayName));
											Boots3Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
											Boots3Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
											Boots3.setItemMeta(Boots3Meta);
											
											e.getWhoClicked().getInventory().setBoots(Boots3);
											e.getWhoClicked().closeInventory();
											if(e.getWhoClicked() instanceof Player) {Player p = (Player) e.getWhoClicked(); p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);}
										}
									} else {
										if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Boots4DisplayName) )) {
											if(e.getWhoClicked().hasPermission("lobby.boots4")) {
												ItemStack Boots4 = new ItemStack(Material.LEATHER_BOOTS);
												Boots4.addEnchantment(Enchantment.PROTECTION_FALL, 1);
												LeatherArmorMeta Boots4Meta = (LeatherArmorMeta) Boots4.getItemMeta();
												Boots4Meta.setColor(Color.fromBGR(160, 0, 160));
												Boots4Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Boots4DisplayName));
												Boots4Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
												Boots4Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
												Boots4.setItemMeta(Boots4Meta);
												
												e.getWhoClicked().getInventory().setBoots(Boots4);
												e.getWhoClicked().closeInventory();
												if(e.getWhoClicked() instanceof Player) {Player p = (Player) e.getWhoClicked(); p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);}
											}
										} else {
											if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Boots5DisplayName))) {
												if(e.getWhoClicked().hasPermission("lobby.boots5")) {
													ItemStack Boots5 = new ItemStack(Material.LEATHER_BOOTS);
													Boots5.addEnchantment(Enchantment.PROTECTION_FALL, 1);
													LeatherArmorMeta Boots5Meta = (LeatherArmorMeta) Boots5.getItemMeta();
													Boots5Meta.setColor(Color.fromBGR(30, 30, 190));
													Boots5Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Boots5DisplayName));
													Boots5Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
													Boots5Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
													Boots5.setItemMeta(Boots5Meta);

													ItemStack Plate5 = new ItemStack(Material.DIAMOND_CHESTPLATE);
													//Plate5.addEnchantment(Enchantment., 1);
													ItemMeta Plate5Meta = Plate5.getItemMeta();
													Plate5Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Boots5DisplayName));
													Plate5Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
													Plate5Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
													Plate5.setItemMeta(Plate5Meta);


													e.getWhoClicked().getInventory().setBoots(Boots5);
													e.getWhoClicked().getInventory().setChestplate(Plate5);
													e.getWhoClicked().closeInventory();
													if(e.getWhoClicked() instanceof Player) {Player p = (Player) e.getWhoClicked(); p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);}
												}
											}
										}
									}
								}
							}					
						}
					}
					e.setCancelled(true);
				} else if(e.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.Inventory.Skulls.DisplayName")))) {
					if(e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName()) {
						String Skull1Owner = plugin.getConfig().getString("Items.Skulls.1.SkullOwner");
						String Skull2Owner = plugin.getConfig().getString("Items.Skulls.2.SkullOwner");
						String Skull3Owner = plugin.getConfig().getString("Items.Skulls.3.SkullOwner");
						String Skull4Owner = plugin.getConfig().getString("Items.Skulls.4.SkullOwner");
						String Skull5Owner = plugin.getConfig().getString("Items.Skulls.5.SkullOwner");
						String Skull6Owner = plugin.getConfig().getString("Items.Skulls.6.SkullOwner");
						String Skull7Owner = plugin.getConfig().getString("Items.Skulls.7.SkullOwner");
						String Skull8Owner = plugin.getConfig().getString("Items.Skulls.8.SkullOwner");
						String Skull9Owner = plugin.getConfig().getString("Items.Skulls.9.SkullOwner");
						String Skull10Owner = plugin.getConfig().getString("Items.Skulls.10.SkullOwner");
						String Skull11Owner = plugin.getConfig().getString("Items.Skulls.11.SkullOwner");
						String Skull12Owner = plugin.getConfig().getString("Items.Skulls.12.SkullOwner");
						String Skull13Owner = plugin.getConfig().getString("Items.Skulls.13.SkullOwner");
						String Skull14Owner = plugin.getConfig().getString("Items.Skulls.14.SkullOwner");
						
						String Skull1DisplayName = plugin.getConfig().getString("Items.Skulls.1.DisplayName");
						String Skull2DisplayName = plugin.getConfig().getString("Items.Skulls.2.DisplayName");
						String Skull3DisplayName = plugin.getConfig().getString("Items.Skulls.3.DisplayName");
						String Skull4DisplayName = plugin.getConfig().getString("Items.Skulls.4.DisplayName");
						String Skull5DisplayName = plugin.getConfig().getString("Items.Skulls.5.DisplayName");
						String Skull6DisplayName = plugin.getConfig().getString("Items.Skulls.6.DisplayName");
						String Skull7DisplayName = plugin.getConfig().getString("Items.Skulls.7.DisplayName");
						String Skull8DisplayName = plugin.getConfig().getString("Items.Skulls.8.DisplayName");
						String Skull9DisplayName = plugin.getConfig().getString("Items.Skulls.9.DisplayName");
						String Skull10DisplayName = plugin.getConfig().getString("Items.Skulls.10.DisplayName");
						String Skull11DisplayName = plugin.getConfig().getString("Items.Skulls.11.DisplayName");
						String Skull12DisplayName = plugin.getConfig().getString("Items.Skulls.12.DisplayName");
						String Skull13DisplayName = plugin.getConfig().getString("Items.Skulls.13.DisplayName");
						String Skull14DisplayName = plugin.getConfig().getString("Items.Skulls.14.DisplayName");
						
						ItemStack Skull1 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
						SkullMeta Skull1Meta = (SkullMeta) Skull1.getItemMeta();
						Skull1Meta.setOwner(Skull1Owner);
						Skull1Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Skull1DisplayName));
						Skull1.setItemMeta(Skull1Meta);
						
						ItemStack Skull2 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
						SkullMeta Skull2Meta = (SkullMeta) Skull2.getItemMeta();
						Skull2Meta.setOwner(Skull2Owner);
						Skull2Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Skull2DisplayName));
						Skull2.setItemMeta(Skull2Meta);
						
						ItemStack Skull3 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
						SkullMeta Skull3Meta = (SkullMeta) Skull3.getItemMeta();
						Skull3Meta.setOwner(Skull3Owner);
						Skull3Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Skull3DisplayName));
						Skull3.setItemMeta(Skull3Meta);
						
						ItemStack Skull4 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
						SkullMeta Skull4Meta = (SkullMeta) Skull4.getItemMeta();
						Skull4Meta.setOwner(Skull4Owner);
						Skull4Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Skull4DisplayName));
						Skull4.setItemMeta(Skull4Meta);
						
						ItemStack Skull5 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
						SkullMeta Skull5Meta = (SkullMeta) Skull5.getItemMeta();
						Skull5Meta.setOwner(Skull5Owner);
						Skull5Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Skull5DisplayName));
						Skull5.setItemMeta(Skull5Meta);
						
						ItemStack Skull6 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
						SkullMeta Skull6Meta = (SkullMeta) Skull6.getItemMeta();
						Skull6Meta.setOwner(Skull6Owner);
						Skull6Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Skull6DisplayName));
						Skull6.setItemMeta(Skull6Meta);
						
						ItemStack Skull7 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
						SkullMeta Skull7Meta = (SkullMeta) Skull7.getItemMeta();
						Skull7Meta.setOwner(Skull7Owner);
						Skull7Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Skull7DisplayName));
						Skull7.setItemMeta(Skull7Meta);
						
						ItemStack Skull8 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
						SkullMeta Skull8Meta = (SkullMeta) Skull8.getItemMeta();
						Skull8Meta.setOwner(Skull8Owner);
						Skull8Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Skull8DisplayName));
						Skull8.setItemMeta(Skull8Meta);
						
						ItemStack Skull9 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
						SkullMeta Skull9Meta = (SkullMeta) Skull9.getItemMeta();
						Skull9Meta.setOwner(Skull9Owner);
						Skull9Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Skull9DisplayName));
						Skull9.setItemMeta(Skull9Meta);
						
						ItemStack Skull10 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
						SkullMeta Skull10Meta = (SkullMeta) Skull10.getItemMeta();
						Skull10Meta.setOwner(Skull10Owner);
						Skull10Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Skull10DisplayName));
						Skull10.setItemMeta(Skull10Meta);
						
						ItemStack Skull11 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
						SkullMeta Skull11Meta = (SkullMeta) Skull11.getItemMeta();
						Skull11Meta.setOwner(Skull11Owner);
						Skull11Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Skull11DisplayName));
						Skull11.setItemMeta(Skull11Meta);
						
						ItemStack Skull12 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
						SkullMeta Skull12Meta = (SkullMeta) Skull12.getItemMeta();
						Skull12Meta.setOwner(Skull12Owner);
						Skull12Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Skull12DisplayName));
						Skull12.setItemMeta(Skull12Meta);
						
						ItemStack Skull13 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
						SkullMeta Skull13Meta = (SkullMeta) Skull13.getItemMeta();
						Skull13Meta.setOwner(Skull13Owner);
						Skull13Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Skull13DisplayName));
						Skull13.setItemMeta(Skull13Meta);
						
						ItemStack Skull14 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
						SkullMeta Skull14Meta = (SkullMeta) Skull14.getItemMeta();
						Skull14Meta.setOwner(Skull14Owner);
						Skull14Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Skull14DisplayName));
						Skull14.setItemMeta(Skull14Meta);
						
						if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Skull1DisplayName))) {
							if(e.getWhoClicked().hasPermission("lobby.skull1")) {
								e.getWhoClicked().getInventory().setHelmet(Skull1);
								e.getWhoClicked().closeInventory();
								if(e.getWhoClicked() instanceof Player) {Player p = (Player) e.getWhoClicked(); p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);}
							}
						}
						
						if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Skull2DisplayName))) {
							if(e.getWhoClicked().hasPermission("lobby.skull2")) {
								e.getWhoClicked().getInventory().setHelmet(Skull2);
								e.getWhoClicked().closeInventory();
								if(e.getWhoClicked() instanceof Player) {Player p = (Player) e.getWhoClicked(); p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);}
							}
						}
						
						if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Skull3DisplayName))) {
							if(e.getWhoClicked().hasPermission("lobby.skull3")) {
								e.getWhoClicked().getInventory().setHelmet(Skull3);
								e.getWhoClicked().closeInventory();
								if(e.getWhoClicked() instanceof Player) {Player p = (Player) e.getWhoClicked(); p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);}
							}
						}
						
						if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Skull4DisplayName))) {
							if(e.getWhoClicked().hasPermission("lobby.skull4")) {
								e.getWhoClicked().getInventory().setHelmet(Skull4);
								e.getWhoClicked().closeInventory();
								if(e.getWhoClicked() instanceof Player) {Player p = (Player) e.getWhoClicked(); p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);}
							}
						}
						
						if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Skull5DisplayName))) {
							if(e.getWhoClicked().hasPermission("lobby.skull5")) {
								e.getWhoClicked().getInventory().setHelmet(Skull5);
								e.getWhoClicked().closeInventory();
								if(e.getWhoClicked() instanceof Player) {Player p = (Player) e.getWhoClicked(); p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);}
							}
						}
						
						if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Skull6DisplayName))) {
							if(e.getWhoClicked().hasPermission("lobby.skull6")) {
								e.getWhoClicked().getInventory().setHelmet(Skull6);
								e.getWhoClicked().closeInventory();
								if(e.getWhoClicked() instanceof Player) {Player p = (Player) e.getWhoClicked(); p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);}
							}
						}
						
						if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Skull7DisplayName))) {
							if(e.getWhoClicked().hasPermission("lobby.skull7")) {
								e.getWhoClicked().getInventory().setHelmet(Skull7);
								e.getWhoClicked().closeInventory();
								if(e.getWhoClicked() instanceof Player) {Player p = (Player) e.getWhoClicked(); p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);}
							}
						}
						
						if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Skull8DisplayName))) {
							if(e.getWhoClicked().hasPermission("lobby.skull8")) {
								e.getWhoClicked().getInventory().setHelmet(Skull8);
								e.getWhoClicked().closeInventory();
								if(e.getWhoClicked() instanceof Player) {Player p = (Player) e.getWhoClicked(); p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);}
							}
						}
						
						if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Skull9DisplayName))) {
							if(e.getWhoClicked().hasPermission("lobby.skull9")) {
								e.getWhoClicked().getInventory().setHelmet(Skull9);
								e.getWhoClicked().closeInventory();
								if(e.getWhoClicked() instanceof Player) {Player p = (Player) e.getWhoClicked(); p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);}
							}
						}
						
						if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Skull10DisplayName))) {
							if(e.getWhoClicked().hasPermission("lobby.skull10")) {
								e.getWhoClicked().getInventory().setHelmet(Skull10);
								e.getWhoClicked().closeInventory();
								if(e.getWhoClicked() instanceof Player) {Player p = (Player) e.getWhoClicked(); p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);}
							}
						}
						
						if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Skull11DisplayName))) {
							if(e.getWhoClicked().hasPermission("lobby.skull11")) {
								e.getWhoClicked().getInventory().setHelmet(Skull11);
								e.getWhoClicked().closeInventory();
								if(e.getWhoClicked() instanceof Player) {Player p = (Player) e.getWhoClicked(); p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);}
							}
						}
						
						if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Skull12DisplayName))) {
							if(e.getWhoClicked().hasPermission("lobby.skull12")) {
								e.getWhoClicked().getInventory().setHelmet(Skull12);
								e.getWhoClicked().closeInventory();
								if(e.getWhoClicked() instanceof Player) {Player p = (Player) e.getWhoClicked(); p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);}
							}
						}
						
						if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Skull13DisplayName))) {
							if(e.getWhoClicked().hasPermission("lobby.skull13")) {
								e.getWhoClicked().getInventory().setHelmet(Skull13);
								e.getWhoClicked().closeInventory();
								if(e.getWhoClicked() instanceof Player) {Player p = (Player) e.getWhoClicked(); p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);}
							}
						}
						
						if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Skull14DisplayName))) {
							if(e.getWhoClicked().hasPermission("lobby.skull14")) {
								e.getWhoClicked().getInventory().setHelmet(Skull14);
								e.getWhoClicked().closeInventory();
								if(e.getWhoClicked() instanceof Player) {Player p = (Player) e.getWhoClicked(); p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);}
							}
						}
					}
					
					e.setCancelled(true);
				} else if(e.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.Inventory.Gadgets")))) {
					if(e.getCurrentItem() != null) {
						if(e.getCurrentItem() != null || e.getCurrentItem().getType() != Material.AIR) {
							if(e.getCurrentItem().getType() == Material.ENDER_PEARL) {
								ItemStack EnderPearl = new ItemStack(Material.ENDER_PEARL);
								ItemMeta EnderPearlMeta = EnderPearl.getItemMeta();
								EnderPearlMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.Gadgets.Enderpearl")));
								EnderPearl.setItemMeta(EnderPearlMeta);
								
								if(e.getWhoClicked().getInventory().getItem(7).getType() != Material.FIREWORK_ROCKET) {
									e.getWhoClicked().getInventory().setItem(7, EnderPearl);
									e.getWhoClicked().closeInventory();
									if(e.getWhoClicked() instanceof Player) {Player p = (Player) e.getWhoClicked(); p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);}
								}
							} else if(e.getCurrentItem().getType() == Material.EGG) {
								ItemStack Explosion = new ItemStack(Material.EGG);
								ItemMeta ExplosionMeta = Explosion.getItemMeta();
								ExplosionMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.Gadgets.Explosion")));
								Explosion.setItemMeta(ExplosionMeta);
								
								if(e.getWhoClicked().getInventory().getItem(7).getType() != Material.FIREWORK_ROCKET) {
									e.getWhoClicked().getInventory().setItem(7, Explosion);
									e.getWhoClicked().closeInventory();
									if(e.getWhoClicked() instanceof Player) {Player p = (Player) e.getWhoClicked(); p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);}
								}
							} else if(e.getCurrentItem().getType() == Material.FLINT_AND_STEEL) {
								ItemStack Jetpack = new ItemStack(Material.FLINT_AND_STEEL);
								ItemMeta JetpackMeta = Jetpack.getItemMeta();
								JetpackMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Inventorys.Gadgets.Jetpack")));
								Jetpack.setItemMeta(JetpackMeta);
								
								if(e.getWhoClicked().getInventory().getItem(7).getType() != Material.FIREWORK_ROCKET) {
									e.getWhoClicked().getInventory().setItem(7, Jetpack);
									e.getWhoClicked().closeInventory();
									if(e.getWhoClicked() instanceof Player) {Player p = (Player) e.getWhoClicked(); p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);}
								}
							}
						}
					}
					e.setCancelled(true);
				}
			}
			e.setCancelled(true);
		}
	}
	
	public List<String> getWarps() {
		try {
			return TeleportUtils.getCfg().getStringList("Warps");
		} catch (Exception e) {
			
		}
		
		return new ArrayList<>();
	}
}
