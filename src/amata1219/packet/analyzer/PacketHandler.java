package amata1219.packet.analyzer;

import java.util.HashMap;

import org.bukkit.entity.Player;

import amata1219.packet.analyzer.monad.Maybe;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketListenerPlayIn;
import net.minecraft.server.v1_8_R3.PacketListenerPlayOut;

public abstract class PacketHandler extends ChannelDuplexHandler {

	@SuppressWarnings("rawtypes")
	private final HashMap<Class<?>, Analyzer> outAnalyzers = new HashMap<>();
	
	@SuppressWarnings("rawtypes")
	private final HashMap<Class<?>, Analyzer> inAnalyzers = new HashMap<>();
	
	protected final Player player;
	
	public PacketHandler(Player player){
		this.player = player;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void write(ChannelHandlerContext context, Object packet, ChannelPromise promise) throws Exception {
		Maybe.unit(outAnalyzers.get(packet))
		.apply(analyzer -> analyzer.analyze(packet));
		super.write(context, packet, promise);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void channelRead(ChannelHandlerContext context, Object packet) throws Exception {
		Maybe.unit(inAnalyzers.get(packet))
		.apply(analyzer -> analyzer.analyze(packet));
		super.channelRead(context, packet);
	}
	
	@SafeVarargs
	protected final void registerOutAnalyzers(Analyzer<? extends Packet<PacketListenerPlayOut>>... analyzers){
		for(Analyzer<?> analyzer : analyzers) this.outAnalyzers.put(analyzer.target, analyzer);
	}
	
	@SafeVarargs
	protected final void registerInAnalyzers(Analyzer<? extends Packet<PacketListenerPlayIn>>... analyzers){
		for(Analyzer<?> analyzer : analyzers) this.outAnalyzers.put(analyzer.target, analyzer);
	}
	
}
