package com.doublejump.bluenatural;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class DoubleJumpCommand implements CommandExecutor {
	public static DoubleJump plugin;
	@SuppressWarnings("static-access")
	public DoubleJumpCommand(DoubleJump plugin){
		this.plugin = plugin;
	}

	@SuppressWarnings("static-access")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage(plugin.cslprefix + ChatColor.RED + "This command only use in game");
			return true;
		}else{
			Player p = (Player) sender;
		    if(args.length < 1 || args.length == 1 && args[0].equalsIgnoreCase("help")){
		    	if(p.hasPermission("dj.help")){
		    		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&m---------------------------------------"));
		    		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/dj help &7- show all commands of DoubleJump"));
		    		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/dj prevent <player> &7- prevent player can not double jump"));
		    		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/dj add &7- add player can double-jump"));
		    		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/dj reload &7- reload plugin"));
		    		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&m---------------------------------------"));
		    		return true;
		    	}else{
		    		p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"))+
		    				ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-perm")));
		    		return true;
		    	}
		    	
		    }else if(args.length == 2 && args[0].equalsIgnoreCase("prevent")){
		    	if(p.hasPermission("dj.prevent")){
		    	Player pl = Bukkit.getPlayerExact(args[1]);
		    	plugin.prevent.add(pl.getUniqueId());
		    	p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"))+
		    			ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("msg.preventing")).replace("{player}", pl.getName()));
		    	return true;
		    }else{
		    	p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"))+
	    				ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-perm")));
	    		return true;
		    
		    }
		}else if(args.length == 2 && args[0].equalsIgnoreCase("add")){
			if(p.hasPermission("dj.add")){
			Player pl = Bukkit.getPlayerExact(args[1]);
			if(!plugin.prevent.contains(p.getUniqueId())){
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"))+
						ChatColor.translateAlternateColorCodes('&', "&aCan not add player because of available player"));
				return true;
			}else{
				plugin.prevent.remove(pl.getUniqueId());
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"))+
						ChatColor.translateAlternateColorCodes('&', "&aAdd player to list of double-jumping"));
				return true;
			}
	    }else{
	    	p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"))+
    				ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-perm")));
    		return true;
	    }
		}else if(args.length == 1 && args[0].equalsIgnoreCase("reload")){
			if(p.hasPermission("dj.reload")){
				ReloadPlugin();
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"))+
						ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("reload")));
				return true;
			}else{
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"))+
	    				ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-perm")));
	    		return true;
			}
		}
		}
		return true;
		
	}

	private void ReloadPlugin() {
		plugin.reloadConfig();
		plugin.saveConfig();
		
	}

}
