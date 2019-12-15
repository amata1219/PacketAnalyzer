package amata1219.packet.analyzer;

import java.util.HashMap;

import org.bukkit.entity.Player;

import amata1219.packet.analyzer.monad.Maybe;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

public abstract class PacketHandler extends ChannelDuplexHandler {

	private final HashMap<Class<?>, Analyzer<?>> analyzers = new HashMap<>();
	protected final Player player;
	
	public PacketHandler(Player player){
		this.player = player;
	}
	
	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		Maybe.unit(analyzers.get(msg))
		.apply(a -> a.analyze(msg));
		super.write(ctx, msg, promise);
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		super.channelRead(ctx, msg);
	}
	
	protected void registerAnalyzers(Analyzer<?>... analyzers){
		for(Analyzer<?> analyzer : analyzers) this.analyzers.put(analyzer.target, analyzer);
	}
	
}
