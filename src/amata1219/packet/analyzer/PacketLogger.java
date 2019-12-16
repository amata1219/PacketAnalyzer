package amata1219.packet.analyzer;

import java.util.Arrays;
import java.util.HashSet;

import org.bukkit.entity.Player;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraft.server.v1_8_R3.PacketPlayOutMapChunkBulk;
import net.minecraft.server.v1_8_R3.PacketPlayOutPosition;
import net.minecraft.server.v1_8_R3.PacketPlayOutUpdateTime;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying.PacketPlayInPosition;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying.PacketPlayInPositionLook;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import net.minecraft.server.v1_8_R3.PacketPlayOutBlockChange;
import net.minecraft.server.v1_8_R3.PacketPlayOutKeepAlive;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntity.PacketPlayOutRelEntityMove;

public class PacketLogger extends PacketHandler {
	
	/*
	 * metadata? → status → effect → metadata?
	 * 
	 * ポーションを飲み始める(右クリック押下)のタイミングでplace
	 * 途中で止めるか飲み終えるかに拘わらず右クリックを止めるとdig
	 *
	 * 
	 * 余計なパケット削除済み(chat, keepalive)
	 * 
	 * ------------------
	 * 
[03:04:12 INFO]: In -> PacketPlayInBlockPlace
[03:04:12 INFO]: 	Out -> PacketPlayOutEntityMetadata  0
[03:04:13 INFO]: 	Out -> PacketPlayOutEntityStatus    1
[03:04:13 INFO]: In -> PacketPlayInBlockPlace
[03:04:13 INFO]: 	Out -> PacketPlayOutEntityEffect    2
[03:04:13 INFO]: 	Out -> PacketPlayOutEntityMetadata  0
[03:04:13 INFO]: 	Out -> PacketPlayOutEntityMetadata  0
[03:04:14 INFO]: In -> PacketPlayInBlockDig
[03:04:14 INFO]: 	Out -> PacketPlayOutEntityMetadata  0
		------------------------
[03:04:21 INFO]: In -> PacketPlayInBlockPlace
[03:04:21 INFO]: 	Out -> PacketPlayOutEntityMetadata  0
[03:04:23 INFO]: 	Out -> PacketPlayOutEntityStatus    1
[03:04:23 INFO]: 	Out -> PacketPlayOutEntityEffect    2
[03:04:23 INFO]: 	Out -> PacketPlayOutEntityMetadata  0
[03:04:23 INFO]: In -> PacketPlayInBlockPlace
[03:04:23 INFO]: 	Out -> PacketPlayOutEntityMetadata  0
[03:04:23 INFO]: In -> PacketPlayInBlockDig
[03:04:23 INFO]: 	Out -> PacketPlayOutEntityMetadata  0
		-----------------------
[03:05:39 INFO]: In -> PacketPlayInBlockPlace
[03:05:39 INFO]: 	Out -> PacketPlayOutEntityMetadata  0
[03:05:40 INFO]: 	Out -> PacketPlayOutEntityStatus    1
[03:05:40 INFO]: 	Out -> PacketPlayOutEntityEffect    2
[03:05:40 INFO]: 	Out -> PacketPlayOutSetSlot
[03:05:40 INFO]: 	Out -> PacketPlayOutEntityMetadata  0
		-----------------------

remove:
[03:05:52 INFO]: 	Out -> PacketPlayOutEntityStatus
[03:05:52 INFO]: 	Out -> PacketPlayOutRemoveEntityEffect
[03:05:52 INFO]: 	Out -> PacketPlayOutEntityMetadata
[03:05:52 INFO]: In -> PacketPlayInBlockPlace
[03:05:52 INFO]: 	Out -> PacketPlayOutEntityMetadata
[03:05:52 INFO]: In -> PacketPlayInBlockDig
[03:05:52 INFO]: 	Out -> PacketPlayOutEntityMetadata

	 */
	
	private final HashSet<Class<?>> ignores = new HashSet<>(Arrays.asList(
		PacketPlayOutRelEntityMove.class,
		PacketPlayOutMapChunkBulk.class,
		PacketPlayOutBlockChange.class,
		PacketPlayOutPosition.class,
		PacketPlayOutKeepAlive.class,
		PacketPlayOutUpdateTime.class,
		
		PacketPlayInFlying.class,
		PacketPlayInPositionLook.class,
		PacketPlayInPosition.class
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
