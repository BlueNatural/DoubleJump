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
	public static DoubleJump plugin;
	@SuppressWarnings("static-access")
	public DoubleJumpEvent(DoubleJump plugin){
		this.plugin = plugin;
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMove(PlayerMoveEvent e){
		Player p = e.getPlayer();
		if(p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR){
			return;
		}
		if(p.isOnGround()){
			if(!p.getAllowFlight()){
				p.setAllowFlight(true);
			}
		}
	}
	@SuppressWarnings({ "static-access" })
	@EventHandler
	public void onJump(PlayerToggleFlightEvent e){
		Player p = e.getPlayer();
		Location loc = p.getLocation();
		
		if(p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR || p.hasPermission("dj.byass")){
			return;
		}else{
		if(!plugin.prevent.contains(p.getUniqueId())){
		e.setCancelled(true);
		p.setFlying(false);
		p.setAllowFlight(false);
		Vector v = p.getLocation().getDirection().multiply(1.2).setY(1.2);
		p.setVelocity(v);
	   TitleBarAPI api = (TitleBarAPI) plugin.getServer().getPluginManager().getPlugin("TitleBarAPI");
		if(plugin.getServer().getPluginManager().isPluginEnabled("TitleBarAPI")){		
			api.titles.sendTitle(p, ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("title-jumping")), "", 20, 40, 20);
			p.playSound(loc, Sound.valueOf(plugin.getConfig().getString("sound-jumping")), 4.0F, 1.0F);
			loc.getWorld().playEffect(loc, Effect.valueOf(plugin.getConfig().getString("particle-jumping")), 1, 5);
			loc.getWorld().playEffect(loc, Effect.valueOf(plugin.getConfig().getString("particle-jumping")), 1, 5);
			loc.getWorld().playEffect(loc, Effect.valueOf(plugin.getConfig().getString("particle-jumping")), 1, 5);
			loc.getWorld().playEffect(loc, Effect.valueOf(plugin.getConfig().getString("particle-jumping")), 1, 5);
			loc.getWorld().playEffect(loc, Effect.valueOf(plugin.getConfig().getString("particle-jumping")), 1, 5);
			loc.getWorld().playEffect(loc, Effect.valueOf(plugin.getConfig().getString("particle-jumping")), 1, 5);
			loc.getWorld().playEffect(loc, Effect.FLAME, 5, 5);
			loc.getWorld().playEffect(loc, Effect.FLAME, 5, 5);
			loc.getWorld().playEffect(loc, Effect.FLAME, 5, 5);
			loc.getWorld().playEffect(loc, Effect.FLAME, 5, 5);
			loc.getWorld().playEffect(loc, Effect.FLAME, 5, 5);
			loc.getWorld().playEffect(loc, Effect.FLAME, 5, 5);
			plugin.falling.add(p.getUniqueId());	
		}else{
			p.playSound(loc, Sound.valueOf(plugin.getConfig().getString("sound-jumping")), 4.0F, 1.0F);
			loc.getWorld().playEffect(loc, Effect.valueOf(plugin.getConfig().getString("particle-jumping")), 1, 5);
			loc.getWorld().playEffect(loc, Effect.valueOf(plugin.getConfig().getString("particle-jumping")), 1, 5);
			loc.getWorld().playEffect(loc, Effect.valueOf(plugin.getConfig().getString("particle-jumping")), 1, 5);
			loc.getWorld().playEffect(loc, Effect.valueOf(plugin.getConfig().getString("particle-jumping")), 1, 5);
			loc.getWorld().playEffect(loc, Effect.valueOf(plugin.getConfig().getString("particle-jumping")), 1, 5);
			loc.getWorld().playEffect(loc, Effect.FLAME, 5, 5);
			loc.getWorld().playEffect(loc, Effect.FLAME, 5, 5);
			loc.getWorld().playEffect(loc, Effect.FLAME, 5, 5);
			loc.getWorld().playEffect(loc, Effect.FLAME, 5, 5);
			loc.getWorld().playEffect(loc, Effect.FLAME, 5, 5);
			loc.getWorld().playEffect(loc, Effect.FLAME, 5, 5);
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"))+
					ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("msg.jumping")));
			plugin.falling.add(p.getUniqueId());
		}
		}else{
			e.setCancelled(true);
		}
		}
	}
	@SuppressWarnings("static-access")
	@EventHandler
	public void onFallDamage(EntityDamageEvent e){
		if(!(e.getEntity() instanceof Player)){
			return;
		}
		Player p = (Player) e.getEntity();
		if(plugin.falling.contains(p.getUniqueId()) && e.getCause().equals(DamageCause.FALL)){
			e.setCancelled(true);
		    plugin.falling.remove(p.getUniqueId());	
		
		}else{
			e.setCancelled(false);
		}
	}

}
