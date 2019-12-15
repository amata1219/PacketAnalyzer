package amata1219.packet.analyzer;

import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutKickDisconnect;

public class PacketAnalyzer extends PacketHandler {
	
	public PacketAnalyzer(Player player) {
		super(player);
		
		registerOutAnalyzers(
			Analyzer.of(PacketPlayOutChat.class, p -> {
				
			}),
			Analyzer.of(PacketPlayOutKickDisconnect.class, p -> {
				
			})
		);
	}
	
}
