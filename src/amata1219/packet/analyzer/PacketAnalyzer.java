package amata1219.packet.analyzer;

import java.lang.reflect.Field;
import java.util.List;

import org.bukkit.entity.Player;

import amata1219.packet.analyzer.reflection.OldReflector;
import net.minecraft.server.v1_8_R3.DataWatcher.WatchableObject;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEffect;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityStatus;

public class PacketAnalyzer extends PacketHandler {
	
	/*
	 * 他プレイヤーがeffectコマンドを実行した時に受信したパケット
	 * 
	 * 透明化:
	 * [17:11:04 INFO]: Out > PacketPlayOutEntityMetadata
[17:11:04 INFO]: a -> 77 @ entity id
[17:11:04 INFO]: b.size() -> 2
[17:11:04 INFO]: Member > WatchableObject
[17:11:04 INFO]: a -> 2
[17:11:04 INFO]: b -> 7
[17:11:04 INFO]: c -> 8356754 @ particle color
[17:11:04 INFO]: d -> false
[17:11:04 INFO]: a -> 0
[17:11:04 INFO]: b -> 0
[17:11:04 INFO]: c -> 32
[17:11:04 INFO]: d -> false

[17:21:35 INFO]: Out > PacketPlayOutEntityMetadata
[17:21:35 INFO]: a -> 77
[17:21:35 INFO]: b.size() -> 2
[17:21:35 INFO]: Member > WatchableObject
[17:21:35 INFO]: a -> 2
[17:21:35 INFO]: b -> 7
[17:21:35 INFO]: c -> 8356754
[17:21:35 INFO]: d -> false
[17:21:35 INFO]: a -> 0
[17:21:35 INFO]: b -> 0
[17:21:35 INFO]: c -> 32
[17:21:35 INFO]: d -> false

		パーティクル非表示:
		[17:44:57 INFO]: Out > PacketPlayOutEntityMetadata
[17:44:57 INFO]: a -> 77
[17:44:57 INFO]: b.size() -> 1
[17:44:57 INFO]: Member > WatchableObject
[17:44:57 INFO]: a -> 0
[17:44:57 INFO]: b -> 0
[17:44:57 INFO]: c -> 32
[17:44:57 INFO]: c.class_name -> Byte
[17:44:57 INFO]: d -> false


		再生:
		[17:12:46 INFO]: Out > PacketPlayOutEntityMetadata
[17:12:46 INFO]: a -> 77 @ entity id
[17:12:46 INFO]: b.size() -> 1
[17:12:46 INFO]: Member > WatchableObject
[17:12:46 INFO]: a -> 2
[17:12:46 INFO]: b -> 7
[17:12:46 INFO]: c -> 13458603 @ particle color
[17:12:46 INFO]: d -> false

		盲目:
		[17:16:47 INFO]: Out > PacketPlayOutEntityMetadata
[17:16:47 INFO]: a -> 77
[17:16:47 INFO]: b.size() -> 1
[17:16:47 INFO]: Member > WatchableObject
[17:16:47 INFO]: a -> 2
[17:16:47 INFO]: b -> 7
[17:16:47 INFO]: c -> 2039587 @ particle color
[17:16:47 INFO]: d -> false

		速度上昇:
		[17:22:08 INFO]: Out > PacketPlayOutEntityMetadata
[17:22:08 INFO]: a -> 77
[17:22:08 INFO]: b.size() -> 1
[17:22:08 INFO]: Member > WatchableObject
[17:22:08 INFO]: a -> 2
[17:22:08 INFO]: b -> 7
[17:22:08 INFO]: c -> 8171462
[17:22:08 INFO]: d -> false


		effect clear
		透明化:
		[17:24:16 INFO]: Out > PacketPlayOutEntityMetadata
[17:24:16 INFO]: a -> 77
[17:24:16 INFO]: b.size() -> 2
[17:24:16 INFO]: Member > WatchableObject
[17:24:16 INFO]: a -> 2
[17:24:16 INFO]: b -> 7
[17:24:16 INFO]: c -> 0
[17:24:16 INFO]: d -> false
[17:24:16 INFO]: a -> 0
[17:24:16 INFO]: b -> 0
[17:24:16 INFO]: c -> 0
[17:24:16 INFO]: d -> false

		パーティクル非表示
		[17:45:08 INFO]: Out > PacketPlayOutEntityMetadata
[17:45:08 INFO]: a -> 77
[17:45:08 INFO]: b.size() -> 1
[17:45:08 INFO]: Member > WatchableObject
[17:45:08 INFO]: a -> 0
[17:45:08 INFO]: b -> 0
[17:45:08 INFO]: c -> 0
[17:45:08 INFO]: c.class_name -> Byte
[17:45:08 INFO]: d -> false

	 *
	 */
	
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
				
				Field f_a = OldReflector.field(clazz, "a");
				Field f_b = OldReflector.field(clazz, "b");
				Field f_c = OldReflector.field(clazz, "c");
				Field f_d = OldReflector.field(clazz, "d");
				
				for(WatchableObject w : b){
					int w_a = OldReflector.fieldValue(f_a, w);
					println("a -> " + w_a);
					
					int w_b = OldReflector.fieldValue(f_b, w);
					println("b -> " + w_b);
					
					Object w_c = OldReflector.fieldValue(f_c, w);
					println("c -> " + w_c);
					println("c.class_name -> " + w_c.getClass().getSimpleName());
					
					boolean w_d = OldReflector.fieldValue(f_d, w);
					println("d -> " + w_d);
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
