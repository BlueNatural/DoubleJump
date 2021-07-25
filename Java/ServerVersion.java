package com.doublejump.bluenatural;

import org.bukkit.Bukkit;

public class ServerVersion {
	
	public static boolean isMC19(){
		return Bukkit.getBukkitVersion().contains("1.9");
	}
	public static boolean isMC110(){
		return Bukkit.getBukkitVersion().contains("1.10");
	}
	public static boolean isMC111(){
		return Bukkit.getBukkitVersion().contains("1.11");
	}
	public static boolean isMC112(){
		return Bukkit.getBukkitVersion().contains("1.12");
	}
	public static boolean isMC113(){
		return Bukkit.getBukkitVersion().contains("1.13");
	}
    
}
