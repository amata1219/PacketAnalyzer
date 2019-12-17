package amata1219.packet.analyzer;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEffect;
import net.minecraft.server.v1_8_R3.PacketPlayOutRemoveEntityEffect;

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
			ByteBuf buffer = Unpooled.buffer();
			buffer.writeInt(PacketInjector.extractEntityPlayer(player).getId());
			buffer.writeByte(14);
			buffer.writeByte(0);
			buffer.writeInt(1728000);
			buffer.writeByte(2);
			PacketDataSerializer data = new PacketDataSerializer(buffer);
			PacketPlayOutEntityEffect packet = new PacketPlayOutEntityEffect();
			try {
				packet.a(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			for(Player other : getServer().getOnlinePlayers()) if(!other.equals(player))
				PacketInjector.extractEntityPlayer(other).playerConnection.sendPacket(packet);
			
			player.sendMessage("他プレイヤーを透明化しました。");
			break;
		} case "unapply": {
			ByteBuf buffer = Unpooled.buffer();
			buffer.writeInt(PacketInjector.extractEntityPlayer(player).getId());
			buffer.writeByte(14);
			PacketDataSerializer data = new PacketDataSerializer(buffer);
			PacketPlayOutRemoveEntityEffect packet = new PacketPlayOutRemoveEntityEffect();
			try {
				packet.a(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			for(Player other : getServer().getOnlinePlayers()) if(!other.equals(player))
				PacketInjector.extractEntityPlayer(other).playerConnection.sendPacket(packet);
			
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
