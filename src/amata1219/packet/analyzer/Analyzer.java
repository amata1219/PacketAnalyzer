package amata1219.packet.analyzer;

import java.util.function.Consumer;

public class Analyzer<T> {
	
	public final Class<T> target;
	private final Consumer<T> action;
	
	public static <T> Analyzer<T> of(Class<T> target, Consumer<T> action){
		return new Analyzer<>(target, action);
	}
	
	public Analyzer(Class<T> target, Consumer<T> action){
		this.target = target;
		this.action = action;
	}
	
	public boolean isTargetedTo(Class<?> clazz){
		return target.equals(clazz);
	}
	
	public void analyze(T value){
		action.accept(value);
	}
	

}
