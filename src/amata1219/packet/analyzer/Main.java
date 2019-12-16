package amata1219.packet.analyzer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
		
		getCommand("analyze").setExecutor(this);
		
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable(){
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(!(command instanceof Player)) return true;
		
		Player player = (Player) sender;
		if(PacketInjector.isInjectedTo(player)) PacketInjector.unapplyTo(player);
		else PacketInjector.applyTo(player, PacketHandler.class);
		
		return true;
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
