package com.doublejump.bluenatural;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class DoubleJump extends JavaPlugin {
	public static Plugin plugin;
	public static Server server;
	public static String pluginName;
	public static String pluginVersion;
	public static List<UUID> prevent = new ArrayList<>();
	public static List<UUID> falling = new ArrayList<>();
	String cslprefix = "[DoubleJump] ";
	
	@Override
    public void onLoad()
    {
	    plugin = this;
        server = plugin.getServer();
        NLog.setPluginLogger(plugin.getLogger());
        NLog.setPluginLogger(plugin.getLogger());
        pluginName = plugin.getDescription().getName();
        pluginVersion = plugin.getDescription().getVersion();
        this.saveDefaultConfig();
    }
	public void loadingConfiguration(){
		String prefix = "prefix";
		plugin.getConfig().addDefault(prefix, "&7&l[&aDouble&eJump&7&l] ");
		
		String soundjump = "sound-jumping";
		plugin.getConfig().addDefault(soundjump, "ENTITY_WITHER_SHOOT");
		
		String titlejump = "title-jumping";
		plugin.getConfig().addDefault(titlejump, "&aJUMP !!");
		
		String msgjumping = "msg.jumping";
		plugin.getConfig().addDefault(msgjumping, "&aJump !!");
		String msgpreventing = "msg.preventing";
		plugin.getConfig().addDefault(msgpreventing, "&aPrevent {player} can not double-jump complete !");
		
		String particlejump = "particle-jumping";
		plugin.getConfig().addDefault(particlejump, "FIREWORKS_SPARK");
		
	    String reload = "reload";
	    plugin.getConfig().addDefault(reload, "&aReload Successfully !");
	    
	    String noperm = "no-perm";
	    plugin.getConfig().addDefault(noperm, "&cHey you I can not do your asking because you are not owner or admintrastor to do this !");
	    	    
	    getConfig().options().copyDefaults(true);
	    saveDefaultConfig();
	}
	@Override
	public void onEnable(){
		if(ServerVersion.isMC19() || ServerVersion.isMC110() || ServerVersion.isMC111() || ServerVersion.isMC112() || ServerVersion.isMC113()){	
			if(setupTitles()){
			plugin = this;
			DoubleJumpEvent.plugin = this;
			DoubleJumpCommand.plugin = this;
			NLog.info(this.cslprefix + "Found TitleBarAPI,Will start plugin in a few seconds");
			loadingConfiguration();
			loadCommandsAndEvents();
			NLog.info(this.cslprefix + "The plugin started");
			NLog.info("Please contact to me if this plugin has errors " + getDescription().getWebsite());
			NLog.info("Version " + getDescription().getVersion());
			}else{
				plugin = this;
				DoubleJumpEvent.plugin = this;
				DoubleJumpCommand.plugin = this;
				NLog.info(this.cslprefix + "Not found TitleBarAPI,activate sending messages");
				
				loadingConfiguration();
				loadCommandsAndEvents();
				NLog.info(this.cslprefix + "The plugin started");
				NLog.info("Please contact to me if this plugin has errors" + getDescription().getWebsite());
				NLog.info("Version:" + getDescription().getVersion());	
			}
		}else{
		NLog.warning("You choose incorrect version when loading plugin");
		NLog.warning("Please report it for author to get help !");
		NLog.warning("You can get link here to get help:" + getDescription().getWebsite());
		Bukkit.getServer().getPluginManager().disablePlugin(this);
		}
	}
	public boolean setupTitles() {
		if(getServer().getPluginManager().getPlugin("TitleBarAPI") == null){
			return false;
		}
		return true;
	}
	@Override
	public void onDisable(){
		
	}
	public void loadCommandsAndEvents() {
		plugin.getServer().getPluginManager().registerEvents(new DoubleJumpEvent(this), this);
		getCommand("doublejump").setExecutor(new DoubleJumpCommand(this));
	}

	
}
