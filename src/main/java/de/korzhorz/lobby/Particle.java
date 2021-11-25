package de.korzhorz.lobby;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import org.bukkit.entity.Player;



public class Particle {
	org.bukkit.Particle particletype;
	Location location;
	boolean longdistance;
	float offsetx;
	float offsety;
	float offsetz;
	float speed;
	int amount;
	
	public Particle(org.bukkit.Particle particletype, Location location, boolean longdistance, float offsetx, float offsety, float offsetz, float speed, int amount) {
		this.particletype = particletype;
		this.location = location;
		this.longdistance = longdistance;
		this.offsetx = offsetx;
		this.offsety = offsety;
		this.offsetz = offsetz;
		this.speed = speed;
		this.amount = amount;
		
	}

	public void sendAll() {
		for(Player p : Bukkit.getOnlinePlayers()){
			p.spawnParticle(particletype, location, amount, offsetx, offsety, offsetz);
		}

		/*org.bukkit.Particle packet = new Particle(this.particletype, this.longdistance, (float) this.location.getX(), (float) this.location.getY(), (float) this.location.getZ(), this.offsetx, this.offsety, this.offsetz, this.speed, this.amount, 0);

		for(Player p : Bukkit.getOnlinePlayers()) {
			((Player) p).getHandle().playerConnection.sendPacket(packet);
		}*/
	}
	
	public void sendPlayer(Player player) {
		player.spawnParticle(particletype, location, amount, offsetx, offsety, offsetz);
	}
}
