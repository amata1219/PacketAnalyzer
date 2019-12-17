package amata1219.packet.analyzer;

import java.util.Arrays;
import java.util.List;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import amata1219.packet.analyzer.monad.Either;
import amata1219.packet.analyzer.reflection.Field;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_8_R3.DataWatcher.WatchableObject;

public class Invisibility {
	
	/*
	 * https://wiki.vg/Entity_metadata#Entity
	 * 
	 * BitMask Invisible: 0x20(16進数) = 32(10進数)
	 * 
	 */
	
	private static final Either<String, Field<PacketPlayOutEntityMetadata, Integer>> a = Field.of(PacketPlayOutEntityMetadata.class, "a");
	private static final Either<String, Field<PacketPlayOutEntityMetadata, List<WatchableObject>>> b = Field.of(PacketPlayOutEntityMetadata.class, "b");
	
	public static void applyTo(Player player, Player target){
		updateMetadata(player, target, (byte) 0x20);
	}
	
	public static void unapplyTo(Player player, Player target){
		updateMetadata(player, target, (byte) 0x00);
	}
	
	private static void updateMetadata(Player player, Player target, byte mask){
		PacketPlayOutEntityMetadata packet = new PacketPlayOutEntityMetadata();
		
		a.onSuccess(f -> f.set(packet, player.getEntityId()))
		.onFailure(System.out::println);
		
		WatchableObject metadata = new WatchableObject(0, 0, mask);
		metadata.a(false);
		
		b.onSuccess(f -> f.set(packet, Arrays.asList(metadata)))
		.onFailure(System.out::println);
		
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}

}
