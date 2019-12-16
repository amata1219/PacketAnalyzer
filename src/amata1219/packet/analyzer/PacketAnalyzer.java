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
	
	/*
	 * PacketPlayOutEntityStatus
	 * 
	 * 
	 * 
	 * https://wiki.vg/Protocol#Entity_Effect
	 * 
	 * PacletPlayOutEntityEffect
	 * a: entity-id
	 * b: effect-id
	 * c: amplifier
	 * d: duration
	 * e: ?
	 * 
	 * 
	 */
	
	/*
	 * invisibility duration: 3:00=360000 id:14 particle-color: #7F8392 (light gray)
	 * 
	 * [20:33:02 INFO]: Out > PacketPlayOutEntityMetadata
[20:33:02 INFO]: a -> 174
[20:33:02 INFO]: b.size() -> 1
[20:33:02 INFO]: Member > WatchableObject
[20:33:02 INFO]: a -> 0
[20:33:02 INFO]: b -> 0
[20:33:02 INFO]: c -> 16
[20:33:02 INFO]: d -> false

[20:33:03 INFO]: Out > PacketPlayOutEntityStatus
[20:33:03 INFO]: a -> 174
[20:33:03 INFO]: b -> 9
[20:33:03 INFO]: Out > PacketPlayOutEntityEffect
[20:33:03 INFO]: a -> 174
[20:33:03 INFO]: b -> 14
[20:33:03 INFO]: c -> 0
[20:33:03 INFO]: d -> 3600
[20:33:03 INFO]: e -> 1
[20:33:03 INFO]: Out > PacketPlayOutEntityMetadata
[20:33:03 INFO]: a -> 174
[20:33:03 INFO]: b.size() -> 2
[20:33:03 INFO]: Member > WatchableObject
[20:33:03 INFO]: a -> 2
[20:33:03 INFO]: b -> 7
[20:33:03 INFO]: c -> 8356754
[20:33:03 INFO]: d -> false
[20:33:03 INFO]: a -> 0
[20:33:03 INFO]: b -> 0
[20:33:03 INFO]: c -> 32
[20:33:03 INFO]: d -> false
[20:33:03 INFO]: Out > PacketPlayOutEntityMetadata
[20:33:03 INFO]: a -> 174
[20:33:03 INFO]: b.size() -> 1
[20:33:03 INFO]: Member > WatchableObject
[20:33:03 INFO]: a -> 0
[20:33:03 INFO]: b -> 0
[20:33:03 INFO]: c -> 48
[20:33:03 INFO]: d -> false
[20:33:04 INFO]: Out > PacketPlayOutEntityMetadata
[20:33:04 INFO]: a -> 174
[20:33:04 INFO]: b.size() -> 1
[20:33:04 INFO]: Member > WatchableObject
[20:33:04 INFO]: a -> 0
[20:33:04 INFO]: b -> 0
[20:33:04 INFO]: c -> 32
[20:33:04 INFO]: d -> false
	 */
	
	/*
	 * [20:57:00 INFO]: amata1219 issued server command: /effect amata1219 invisibility 180
[20:57:00 INFO]: Out > PacketPlayOutEntityEffect
[20:57:00 INFO]: a -> 259
[20:57:00 INFO]: [amata1219: Given Invisibility (ID 14) * 0 to amata1219 for 180 seconds]
[20:57:00 INFO]: b -> 14
[20:57:00 INFO]: c -> 0
[20:57:00 INFO]: d -> 3600
[20:57:00 INFO]: e -> 1
[20:57:00 INFO]: Out > PacketPlayOutEntityMetadata
[20:57:00 INFO]: a -> 259
[20:57:00 INFO]: b.size() -> 2
[20:57:00 INFO]: Member > WatchableObject
[20:57:00 INFO]: a -> 2
[20:57:00 INFO]: b -> 7
[20:57:00 INFO]: c -> 8356754
[20:57:00 INFO]: d -> false
[20:57:00 INFO]: a -> 0
[20:57:00 INFO]: b -> 0
[20:57:00 INFO]: c -> 32
[20:57:00 INFO]: d -> false
	 */
	
	/*
	 * regeneration duration: 0:45=900 id:10 particle-color: #CD5CAB (pink)
	 * 
	 * [20:34:50 INFO]: Out > PacketPlayOutEntityMetadata
[20:34:50 INFO]: a -> 174
[20:34:50 INFO]: b.size() -> 1
[20:34:50 INFO]: Member > WatchableObject
[20:34:50 INFO]: a -> 0
[20:34:50 INFO]: b -> 0
[20:34:50 INFO]: c -> 16
[20:34:50 INFO]: d -> false

[20:34:51 INFO]: Out > PacketPlayOutEntityStatus
[20:34:51 INFO]: a -> 174
[20:34:51 INFO]: b -> 9
[20:34:51 INFO]: Out > PacketPlayOutEntityEffect
[20:34:51 INFO]: a -> 174
[20:34:51 INFO]: b -> 10
[20:34:51 INFO]: c -> 0
[20:34:51 INFO]: d -> 900
[20:34:51 INFO]: e -> 1
[20:34:51 INFO]: Out > PacketPlayOutEntityMetadata
[20:34:51 INFO]: a -> 174
[20:34:51 INFO]: b.size() -> 2
[20:34:51 INFO]: Member > WatchableObject
[20:34:51 INFO]: a -> 2
[20:34:51 INFO]: b -> 7
[20:34:51 INFO]: c -> 13458603
[20:34:51 INFO]: d -> false
[20:34:51 INFO]: a -> 0
[20:34:51 INFO]: b -> 0
[20:34:51 INFO]: c -> 0
[20:34:51 INFO]: d -> false
[20:34:51 INFO]: Out > PacketPlayOutEntityMetadata
[20:34:51 INFO]: a -> 174
[20:34:51 INFO]: b.size() -> 1
[20:34:51 INFO]: Member > WatchableObject
[20:34:51 INFO]: a -> 0
[20:34:51 INFO]: b -> 0
[20:34:51 INFO]: c -> 16
[20:34:51 INFO]: d -> false
[20:34:52 INFO]: Out > PacketPlayOutEntityMetadata
[20:34:52 INFO]: a -> 174
[20:34:52 INFO]: b.size() -> 1
[20:34:52 INFO]: Member > WatchableObject
[20:34:52 INFO]: a -> 0
[20:34:52 INFO]: b -> 0
[20:34:52 INFO]: c -> 0
[20:34:52 INFO]: d -> false
	 */
	
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
