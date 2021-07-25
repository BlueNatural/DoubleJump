package com.doublejump.bluenatural;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class DoubleJumpCommand implements CommandExecutor {
	public DoubleJump plugin;

	public DoubleJumpCommand(DoubleJump plugin){
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			plugin.getLogger().sendMessage("Double Jump" + ChatColor.RED + "This command only use in game");
			return true;
		} else {
			Player p = (Player) sender;
		    if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
		    	if (p.hasPermission("dj.help")) {
		    		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&m---------------------------------------"));
		    		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/dj help &7- show all commands of DoubleJump"));
		    		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/dj prevent <player> &7- prevent player can not double jump"));
		    		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/dj add &7- add player can double-jump"));
		    		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/dj reload &7- reload plugin"));
		    		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&m---------------------------------------"));
		    	} else {
		    		p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"))+
		    				ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-perm")));
		    	}
		    } else if(args.length == 2 && args[0].equalsIgnoreCase("prevent")){
		    	if (p.hasPermission("dj.prevent")) {
		    		Player pl = Bukkit.getPlayerExact(args[1]);
		    		plugin.prevent.add(pl.getUniqueId());
		    		p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"))+
		    			ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("msg.preventing")).replace("{player}", pl.getName()));
		   		} else {
		    		p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"))+
	    				ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-perm")));
		    }
			} else if(args.length == 2 && args[0].equalsIgnoreCase("add")) {
				if (p.hasPermission("dj.add")) {
					Player pl = Bukkit.getPlayerExact(args[1]);
					if (!plugin.prevent.contains(p.getUniqueId())) {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"))+
							ChatColor.translateAlternateColorCodes('&', "&aCan not add player because of available player"));
					} else {
						plugin.prevent.remove(pl.getUniqueId());
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"))+
							ChatColor.translateAlternateColorCodes('&', "&aAdd player to list of double-jumping"));
					}
	    		} else {
	    			p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"))+
    					ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-perm")));
	    		}
			} else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
				if (p.hasPermission("dj.reload")) {
					plugin.reloadConfig();
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"))+
						ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("reload")));
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"))+
	    				ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-perm")));
				}
			}
		}
		return true;
	}
}
