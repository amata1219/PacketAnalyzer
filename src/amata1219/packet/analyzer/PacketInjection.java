package amata1219.packet.analyzer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.entity.Player;

import amata1219.packet.analyzer.monad.Maybe;
import amata1219.packet.analyzer.object.EntityPlayer;
import amata1219.packet.analyzer.object.NetworkManager;
import io.netty.channel.Channel;

import static amata1219.packet.analyzer.Reflection.*;

public class PacketInjection {
	
	private static final String IDENTIFIER = "PacketAnalyzer";
	private static final Method getHandle = getMethod(getOBCClass("CraftPlayer"), "getHandle");
	private static final Field playerConnection = getField(getNMSClass("EntityPlayer"), "playerConnection");
	private static final Class<?> PlayerConnection = getNMSClass("PlayerConnection");
	private static final Field networkManager = getField(PlayerConnection, "networkManager");
	private static final Field channel = getField(getNMSClass("NetworkManager"), "channel");
	//private static final Field m = getField(NetworkManager, "m");

	public static void injectTo(Channel channel, PacketHandler handler){
		Maybe.unit(channel)
		.map(Channel::pipeline)
		.filter(p -> p.get(IDENTIFIER) != null)
		.apply(p -> p.addBefore("packet_handler", IDENTIFIER, handler));
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
