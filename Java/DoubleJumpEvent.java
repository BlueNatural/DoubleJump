package com.doublejump.bluenatural;

import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

import com.titlebarapi.bluenatural.TitleBarAPI;

import net.md_5.bungee.api.ChatColor;

public class DoubleJumpEvent implements Listener {
	public DoubleJump plugin;
	
	public DoubleJumpEvent(DoubleJump plugin){
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMove(PlayerMoveEvent e){
		Player p = e.getPlayer();
		if (p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR) {
			return;
		}

		if (p.isOnGround()) {
			if (!p.getAllowFlight()) {
				p.setAllowFlight(true);
			}
		}
	}

	@EventHandler
	public void onJump(PlayerToggleFlightEvent e){
		Player p = e.getPlayer();
		Location loc = p.getLocation();
		
		if (p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR || p.hasPermission("dj.byass"))
			return;
		e.setCancelled(true);

		if (plugin.prevent.contains(p.getUniqueId())) {
			TitleBarAPI api = (TitleBarAPI) plugin.getServer().getPluginManager().getPlugin("TitleBarAPI");
			Vector v = p.getLocation().getDirection().multiply(1.2).setY(1.2);
			p.setVelocity(v);
			p.setFlying(false);
			p.setAllowFlight(false);

			if (plugin.getServer().getPluginManager().isPluginEnabled("TitleBarAPI"))	
				api.titles.sendTitle(p, ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("title-jumping")), "", 20, 40, 20);
				
			p.playSound(loc, Sound.valueOf(plugin.getConfig().getString("sound-jumping")), 4.0F, 1.0F);

			loc.getWorld().playEffect(loc, Effect.valueOf(plugin.getConfig().getString("particle-jumping")), 1, 5);
			loc.getWorld().playEffect(loc, Effect.FLAME, 5, 5);

			plugin.falling.add(p.getUniqueId());	
		}
	}

	@EventHandler
	public void onFallDamage(EntityDamageEvent e){
		if(!(e.getEntity() instanceof Player))
			return;
		Player p = (Player) e.getEntity();

		if (plugin.falling.contains(p.getUniqueId()) && e.getCause().equals(DamageCause.FALL)){
			e.setCancelled(true);
		    plugin.falling.remove(p.getUniqueId());	
		}
	}
}
