package amata1219.packet.analyzer;

import static amata1219.packet.analyzer.reflection.Reflection.*;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import amata1219.packet.analyzer.monad.Maybe;
import io.netty.channel.Channel;
import net.minecraft.server.v1_8_R3.EntityPlayer;

public class PacketInjector {
	
	private static final String IDENTIFIER = "PacketInjector";
	
	public static void applyTo(Player player, Class<? extends PacketHandler> handler){
		injectTo(extractEntityPlayer(player).playerConnection.networkManager.channel, (PacketHandler) newInstance(constructor(handler, Player.class), player));
	}

	public static void injectTo(Channel channel, PacketHandler handler){
		Maybe.unit(channel)
		.map(Channel::pipeline)
		.filter(p -> p.get(IDENTIFIER) != null)
		.apply(p -> p.addBefore("packet_handler", IDENTIFIER, handler));
	}
	
	public static void unapplyTo(Player player){
		rejectFrom(extractEntityPlayer(player).playerConnection.networkManager.channel);
	}
	
	public static void rejectFrom(Channel channel){
		Maybe.unit(channel)
		.map(Channel::pipeline)
		.filter(p -> p.get(IDENTIFIER) != null)
		.apply(p -> p.remove(IDENTIFIER));
	}
	
	public static EntityPlayer extractEntityPlayer(Player player){
		return ((CraftPlayer) player).getHandle();
	}
	
}
