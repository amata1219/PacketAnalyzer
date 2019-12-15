package amata1219.packet.analyzer;

import org.bukkit.entity.Player;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

public abstract class PacketHandler extends ChannelDuplexHandler {
	
	protected final Player player;
	
	public PacketHandler(Player player){
		this.player = player;
	}
	
	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		super.write(ctx, msg, promise);
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		super.channelRead(ctx, msg);
	}
	
	/*
	 * setTarget(Packet.class).analyze(p -> {
	 * 
	 * })
	 */
	
}
