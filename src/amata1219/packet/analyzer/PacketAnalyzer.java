package amata1219.packet.analyzer;

import org.bukkit.plugin.java.JavaPlugin;

public class PacketAnalyzer extends JavaPlugin {
	
	private static PacketAnalyzer plugin;
	
	@Override
	public void onEnable(){
		plugin = this;
	}
	
	@Override
	public void onDisable(){
		
	}
	
	public static PacketAnalyzer plugin(){
		return plugin;
	}

}
