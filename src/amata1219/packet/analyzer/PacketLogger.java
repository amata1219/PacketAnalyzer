package amata1219.packet.analyzer;

import org.bukkit.entity.Player;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

public class PacketLogger extends PacketHandler {

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
		String name = packet.getClass().getSimpleName();
		boolean isOut = name.startsWith("PacketPlayOut");
		System.out.println((isOut ? "Out" : "In") + " -> " + name);
	}

}
