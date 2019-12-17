package amata1219.packet.analyzer;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import amata1219.packet.analyzer.reflection.Reflector;
import net.minecraft.server.v1_8_R3.DataWatcher.WatchableObject;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;

public class Main extends JavaPlugin implements Listener {
	
	private static Main plugin;
	
	public static Main plugin(){
		return plugin;
	}
	
	@Override
	public void onEnable(){
		plugin = this;
		
		getCommand("packet").setExecutor(this);
		
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable(){
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(!(sender instanceof Player) || args.length == 0) return true;
		
		Player player = (Player) sender;
		
		switch(args[0]){
		  case "analyze": {
			if(PacketInjector.isInjectedTo(player)) PacketInjector.unapplyTo(player);
			else PacketInjector.applyTo(player, PacketAnalyzer.class);
			player.sendMessage(PacketInjector.isInjectedTo(player) ? "パケットの解析を開始しました。" : "パケットの解析を終了しました。");
			break;
		} case "apply": {
			for(Player other : getServer().getOnlinePlayers()) if(!other.equals(player)) {
				PacketPlayOutEntityMetadata packet = new PacketPlayOutEntityMetadata();
				Field f_a = Reflector.field(packet.getClass(), "a");
				Field f_b = Reflector.field(packet.getClass(), "b");
				Reflector.setFieldValue(f_a, packet, PacketInjector.extractEntityPlayer(other).getId());
				WatchableObject wo = new WatchableObject(0, 0, 32);
				wo.a(false);
				List<WatchableObject> b = Arrays.asList(wo);
				Reflector.setFieldValue(f_b, packet, b);
				PacketInjector.extractEntityPlayer(player).playerConnection.sendPacket(packet);
			}
			player.sendMessage("他プレイヤーを透明化しました。");
			break;
		} case "unapply": {
			for(Player other : getServer().getOnlinePlayers()) if(!other.equals(player)){
				PacketPlayOutEntityMetadata packet = new PacketPlayOutEntityMetadata();
				Field f_a = Reflector.field(packet.getClass(), "a");
				Field f_b = Reflector.field(packet.getClass(), "b");
				Reflector.setFieldValue(f_a, packet, PacketInjector.extractEntityPlayer(other).getId());
				WatchableObject wo = new WatchableObject(0, 0, 0);
				wo.a(false);
				List<WatchableObject> b = Arrays.asList(wo);
				Reflector.setFieldValue(f_b, packet, b);
				PacketInjector.extractEntityPlayer(player).playerConnection.sendPacket(packet);
			}
			player.sendMessage("他プレイヤーの透明化を解除しました。");
			break;
		} default:
			break;
		}
		
		return true;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		//PacketInjector.applyTo(event.getPlayer(), PacketAnalyzer.class);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event){
		PacketInjector.unapplyTo(event.getPlayer());
	}
	
}
