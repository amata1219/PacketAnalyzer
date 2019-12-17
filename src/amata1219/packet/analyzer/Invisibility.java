package amata1219.packet.analyzer;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Player;

import amata1219.packet.analyzer.reflection.Reflector;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_8_R3.DataWatcher.WatchableObject;

public class Invisibility {
	
	/*
	 * https://wiki.vg/Entity_metadata#Entity
	 * 
	 * BitMask Invisible: 0x20(16進数) = 32(10進数)
	 */
	
	/*
	 * case "apply": {
			for(Player other : getServer().getOnlinePlayers()) if(!other.equals(player)) {
				PacketPlayOutEntityMetadata packet = new PacketPlayOutEntityMetadata();
				Field f_a = Reflector.field(packet.getClass(), "a");
				Field f_b = Reflector.field(packet.getClass(), "b");
				Reflector.setFieldValue(f_a, packet, PacketInjector.extractEntityPlayer(other).getId());
				WatchableObject wo = new WatchableObject(0, 0, (byte) 32);
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
				WatchableObject wo = new WatchableObject(0, 0, (byte) 0);
				wo.a(false);
				List<WatchableObject> b = Arrays.asList(wo);
				Reflector.setFieldValue(f_b, packet, b);
				PacketInjector.extractEntityPlayer(player).playerConnection.sendPacket(packet);
			}
			player.sendMessage("他プレイヤーの透明化を解除しました。");
			break;
		}
	 */
	
	private static final Field A = Reflector.field(PacketPlayOutEntityMetadata.class, "a");
	private static final Field B = Reflector.field(PacketPlayOutEntityMetadata.class, "b");
	
	public static void applyTo(Player player, Player target){
		PacketPlayOutEntityMetadata packet = new PacketPlayOutEntityMetadata();
	}
	
	public static void unapplyTo(Player player, Player target){
	}

}
