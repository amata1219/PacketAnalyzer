package amata1219.packet.analyzer;

import org.bukkit.entity.Player;

import net.minecraft.server.v1_14_R1.PacketDataSerializer;
import net.minecraft.server.v1_14_R1.PacketPlayInCustomPayload;

public class PacketAnalyzer extends PacketHandler {
	
	public PacketAnalyzer(Player player) {
		super(player);
		
		registerInAnalyzers(
			Analyzer.of(PacketPlayInCustomPayload.class, packet -> {
				println(packet.tag.toString());
				println(packet.tag.getKey());
				println(packet.tag.getNamespace());
				//println(packet.data.toString());
				PacketDataSerializer data = packet.data;
				try{
					println(new String(data.a(), "UTF-8"));
				}catch(Throwable t){
					println("finish reading");
				}
			})
		);
	}
	
}
