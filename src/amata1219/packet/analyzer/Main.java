package amata1219.packet.analyzer;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	private static Main plugin;
	
	public static Main plugin(){
		return plugin;
	}
	
	@Override
	public void onEnable(){
		plugin = this;
		
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable(){
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		System.out.println("onJoin");
		PacketInjector.applyTo(event.getPlayer(), PacketAnalyzer.class);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event){
		PacketInjector.unapplyTo(event.getPlayer());
	}

}
