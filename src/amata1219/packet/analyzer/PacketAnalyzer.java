package amata1219.packet.analyzer;

import org.bukkit.entity.Player;

import static amata1219.packet.analyzer.reflection.Reflector.*;

import java.lang.reflect.Field;

import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEffect;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityStatus;

public class PacketAnalyzer extends PacketHandler {
	
	private static final Field f_a = field(PacketPlayOutEntityMetadata.class, "a");
	private static final Field f_b = field(PacketPlayOutEntityMetadata.class, "b");
	
	public PacketAnalyzer(Player player) {
		super(player);
		
		registerOutAnalyzers(
			Analyzer.of(PacketPlayOutEntityMetadata.class, packet -> {
				int a = fieldValue(f_a, packet);
			}),
			Analyzer.of(PacketPlayOutEntityStatus.class, packet -> {
				
			}),
			Analyzer.of(PacketPlayOutEntityEffect.class, packet -> {
	
			}),
			Analyzer.of(PacketPlayOutEntityEffect.class, packet -> {
	
			})
		);
	}
	
}
