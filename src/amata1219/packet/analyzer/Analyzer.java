package amata1219.packet.analyzer;

import net.minecraft.server.v1_8_R3.Packet;

public class Analyzer {
	
	private final Class<? extends Packet<?>> clazz;
	
	public Analyzer(Class<? extends Packet<?>> clazz){
		this.clazz = clazz;
	}
	

}
