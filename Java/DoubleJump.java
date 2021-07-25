package com.doublejump.bluenatural;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class DoubleJump extends JavaPlugin {
	public List<UUID> prevent = new ArrayList<>();
	public List<UUID> falling = new ArrayList<>();

	@Override
	public void onEnable() {
		if (ServerVersion.isMC19() || ServerVersion.isMC110() || ServerVersion.isMC111() || ServerVersion.isMC112() || ServerVersion.isMC113()){	
			if (getServer().getPluginManager().getPlugin("TitleBarAPI") != null) {
				getLogger().info("Found TitleBarAPI, Plugin will be started in a few seconds");
			}

			// Configuration

			// Useless code
			/*
			getConfig().addDefault("prefix", "&7&l[&aDouble&eJump&7&l] ");
			getConfig().addDefault("sound-jumping", "ENTITY_WITHER_SHOOT");
			getConfig().addDefault("title-jumping", "&aJUMP !!");
			getConfig().addDefault("msg.jumping", "&aJump !!");
			getConfig().addDefault("msg.preventing", "&aPrevent {player} can not double-jump complete !");
			getConfig().addDefault("particle-jumping", "FIREWORKS_SPARK");
	    	getConfig().addDefault("reload", "&aReload Successfully !");
	    	getConfig().addDefault("no-perm", "&cHey you I can not do your asking because you are not owner or admintrastor to do this !");
	    	    
	   	 	getConfig().options().copyDefaults(true);
			*/
	    	saveDefaultConfig();
			
			// Register command and event

			getServer().getPluginManager().registerEvents(new DoubleJumpEvent(this), this);
			getCommand("doublejump").setExecutor(new DoubleJumpCommand(this));

			getLogger().info("The plugin started");
			getLogger().info("Please contact to me if this plugin has errors " + getDescription().getWebsite());
			getLogger().info("Version " + getDescription().getVersion());

		} else {
			getLogger().warning("You choose incorrect version when loading plugin");
			getLogger().warning("Please report it for author to get help !");
			getLogger().warning("You can get link here to get help:" + getDescription().getWebsite());
			getServer().getPluginManager().disablePlugin(this);
		}
	}
}
