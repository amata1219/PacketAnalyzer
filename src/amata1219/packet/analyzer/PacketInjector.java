package amata1219.packet.analyzer;

import static amata1219.packet.analyzer.reflection.Reflection.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.entity.Player;

import amata1219.packet.analyzer.monad.Maybe;
import amata1219.packet.analyzer.object.EntityPlayer;
import amata1219.packet.analyzer.object.NetworkManager;
import io.netty.channel.Channel;

public class PacketInjector {
	
	private static final String IDENTIFIER = "PacketAnalyzer";
	private static final Method getHandle = method(obcClass("CraftPlayer"), "getHandle");
	private static final Field playerConnection = field(nmsClass("EntityPlayer"), "playerConnection");
	private static final Field networkManager = field(nmsClass("PlayerConnection"), "networkManager");
	private static final Field channel = field(nmsClass("NetworkManager"), "channel");
	//private static final Field m = getField(NetworkManager, "m");
	
	public static void applyTo(Player player, Class<? extends PacketHandler> handler){
		EntityPlayer entityPlayer = extractEntityPlayer(player);
		NetworkManager networkManager = extractNetworkManager(entityPlayer);
		Channel channel = extractChannel(networkManager);
		injectTo(channel, (PacketHandler) newInstance(constructor(handler, Player.class), player));
	}

	public static void injectTo(Channel channel, PacketHandler handler){
		Maybe.unit(channel)
		.map(Channel::pipeline)
		.filter(p -> p.get(IDENTIFIER) != null)
		.apply(p -> p.addBefore("packet_handler", IDENTIFIER, handler));
	}
	
	public static void unapplyTo(Player player){
		EntityPlayer entityPlayer = extractEntityPlayer(player);
		NetworkManager networkManager = extractNetworkManager(entityPlayer);
		Channel channel = extractChannel(networkManager);
		rejectFrom(channel);
	}
	
	public static void rejectFrom(Channel channel){
		Maybe.unit(channel)
		.map(Channel::pipeline)
		.filter(p -> p.get(IDENTIFIER) != null)
		.apply(p -> p.remove(IDENTIFIER));
	}
	
	public static EntityPlayer extractEntityPlayer(Player player){
		return new EntityPlayer(invokeMethod(getHandle, player));
	}
	
	public static NetworkManager extractNetworkManager(EntityPlayer entityPlayer){
		Object connection = getFieldValue(playerConnection, entityPlayer.instance);
		return new NetworkManager(getFieldValue(networkManager, connection));
	}
	
	public static Channel extractChannel(NetworkManager networkManager){
		return (Channel) getFieldValue(channel, networkManager.instance);
	}
	
}
