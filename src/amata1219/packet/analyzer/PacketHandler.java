package amata1219.packet.analyzer;

import java.util.HashMap;

import org.bukkit.entity.Player;

import amata1219.packet.analyzer.monad.Maybe;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraft.server.v1_14_R1.Packet;
import net.minecraft.server.v1_14_R1.PacketListenerPlayIn;
import net.minecraft.server.v1_14_R1.PacketListenerPlayOut;

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
		Class<?> clazz = packet.getClass();
		Maybe.unit(outAnalyzers.get(clazz))
		.apply(analyzer -> {
			println("Out > " + clazz.getSimpleName());
			analyzer.analyze(packet);
		});
		super.write(context, packet, promise);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void channelRead(ChannelHandlerContext context, Object packet) throws Exception {
		Class<?> clazz = packet.getClass();
		Maybe.unit(inAnalyzers.get(clazz))
		.apply(analyzer -> {
			println("In > " + clazz.getSimpleName());
			analyzer.analyze(packet);
		});
		super.channelRead(context, packet);
	}
	
	@SafeVarargs
	protected final void registerOutAnalyzers(Analyzer<? extends Packet<PacketListenerPlayOut>>... analyzers){
		for(Analyzer<?> analyzer : analyzers) this.outAnalyzers.put(analyzer.target, analyzer);
	}
	
	@SafeVarargs
	protected final void registerInAnalyzers(Analyzer<? extends Packet<PacketListenerPlayIn>>... analyzers){
		for(Analyzer<?> analyzer : analyzers) this.inAnalyzers.put(analyzer.target, analyzer);
	}
	
	protected void println(Object x){
		System.out.println(x);
	}
	
}
