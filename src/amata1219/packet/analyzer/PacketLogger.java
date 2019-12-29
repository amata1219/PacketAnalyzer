package amata1219.packet.analyzer;

import java.util.Arrays;
import java.util.HashSet;

import org.bukkit.entity.Player;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraft.server.v1_14_R1.PacketPlayInFlying;
import net.minecraft.server.v1_14_R1.PacketPlayInFlying.PacketPlayInLook;
import net.minecraft.server.v1_14_R1.PacketPlayInFlying.PacketPlayInPosition;
import net.minecraft.server.v1_14_R1.PacketPlayInFlying.PacketPlayInPositionLook;
import net.minecraft.server.v1_14_R1.PacketPlayInKeepAlive;
import net.minecraft.server.v1_14_R1.PacketPlayOutBlockChange;
import net.minecraft.server.v1_14_R1.PacketPlayOutEntity.PacketPlayOutRelEntityMove;
import net.minecraft.server.v1_14_R1.PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook;
import net.minecraft.server.v1_14_R1.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_14_R1.PacketPlayOutEntityEquipment;
import net.minecraft.server.v1_14_R1.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.v1_14_R1.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_14_R1.PacketPlayOutEntityTeleport;
import net.minecraft.server.v1_14_R1.PacketPlayOutEntityVelocity;
import net.minecraft.server.v1_14_R1.PacketPlayOutKeepAlive;
import net.minecraft.server.v1_14_R1.PacketPlayOutLightUpdate;
import net.minecraft.server.v1_14_R1.PacketPlayOutMapChunk;
import net.minecraft.server.v1_14_R1.PacketPlayOutPosition;
import net.minecraft.server.v1_14_R1.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_14_R1.PacketPlayOutUpdateAttributes;
import net.minecraft.server.v1_14_R1.PacketPlayOutUpdateTime;

public class PacketLogger extends PacketHandler {
	
	private final HashSet<Class<?>> ignores = new HashSet<>(Arrays.asList(
		PacketPlayOutRelEntityMove.class,
		PacketPlayOutBlockChange.class,
		PacketPlayOutPosition.class,
		PacketPlayOutKeepAlive.class,
		PacketPlayOutUpdateTime.class,
		PacketPlayOutMapChunk.class,
		PacketPlayOutLightUpdate.class,
		PacketPlayOutEntityHeadRotation.class,
		PacketPlayOutRelEntityMoveLook.class,
		PacketPlayOutEntityTeleport.class,
		PacketPlayOutEntityVelocity.class,
		PacketPlayOutUpdateAttributes.class,
		PacketPlayOutEntityMetadata.class,
		PacketPlayOutSpawnEntityLiving.class,
		PacketPlayOutEntityEquipment.class,
		PacketPlayOutEntityDestroy.class,
		PacketPlayInFlying.class,
		PacketPlayInPositionLook.class,
		PacketPlayInPosition.class,
		PacketPlayInLook.class,
		PacketPlayInKeepAlive.class
	));

	public PacketLogger(Player player) {
		super(player);
	}
	
	@Override
	public void write(ChannelHandlerContext context, Object packet, ChannelPromise promise) throws Exception {
		logging(packet);
		super.write(context, packet, promise);
	}
	
	@Override
	public void channelRead(ChannelHandlerContext context, Object packet) throws Exception {
		logging(packet);
		super.channelRead(context, packet);
	}
	
	private void logging(Object packet){
		if(ignores.contains(packet.getClass())) return;
		String name = packet.getClass().getSimpleName();
		boolean isOut = name.startsWith("PacketPlayOut");
		System.out.println((isOut ? "Out" : "In") + " -> " + name);
	}

}
