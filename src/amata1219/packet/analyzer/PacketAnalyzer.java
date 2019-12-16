package amata1219.packet.analyzer;

import java.lang.reflect.Field;
import java.util.List;

import org.bukkit.entity.Player;

import amata1219.packet.analyzer.reflection.Reflector;
import net.minecraft.server.v1_8_R3.DataWatcher.WatchableObject;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEffect;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityStatus;

public class PacketAnalyzer extends PacketHandler {
	
	public PacketAnalyzer(Player player) {
		super(player);
		
		registerOutAnalyzers(
			Analyzer.of(PacketPlayOutEntityMetadata.class, (i, r) -> {
				int a = r.fieldValue("a");
				println("a -> " + a);
				
				List<WatchableObject> b = r.fieldValue("b");
				println("b.size() -> " + b.size());
				
				Class<WatchableObject> clazz = WatchableObject.class;
				println("Member > WatchableObject");
				
				Field w_a = Reflector.field(clazz, "a");
				Field w_b = Reflector.field(clazz, "b");
				Field w_c = Reflector.field(clazz, "c");
				Field w_d = Reflector.field(clazz, "d");
				
				for(WatchableObject w : b){
					println("a -> " + Reflector.fieldValue(w_a, w));
					println("b -> " + Reflector.fieldValue(w_b, w));
					println("c -> " + Reflector.fieldValue(w_c, w));
					println("d -> " + Reflector.fieldValue(w_d, w));
				}
			}),
			Analyzer.of(PacketPlayOutEntityStatus.class, (i, r) -> {
				int a = r.fieldValue("a");
				println("a -> " + a);
				
				byte b = r.fieldValue("b");
				println("b -> " + b);
			}),
			Analyzer.of(PacketPlayOutEntityEffect.class, (i, r) -> {
				int a = r.fieldValue("a");
				println("a -> " + a);
				
				byte b = r.fieldValue("b");
				println("b -> " + b);
				
				byte c = r.fieldValue("c");
				println("c -> " + c);
				
				int d = r.fieldValue("d");
				println("d -> " + d);
				
				byte e = r.fieldValue("e");
				println("e -> " + e);
			})
		);
	}
	
}
