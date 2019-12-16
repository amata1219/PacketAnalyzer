package amata1219.packet.analyzer;

import java.util.function.BiConsumer;

import amata1219.packet.analyzer.reflection.TargetedReflector;

public class Analyzer<T> {
	
	public final Class<T> target;
	private final BiConsumer<T, TargetedReflector<T>> action;
	
	public static <T> Analyzer<T> of(Class<T> target, BiConsumer<T, TargetedReflector<T>> action){
		return new Analyzer<>(target, action);
	}
	
	public Analyzer(Class<T> target, BiConsumer<T, TargetedReflector<T>> action){
		this.target = target;
		this.action = action;
	}
	
	public void analyze(T value){
		action.accept(value, new TargetedReflector<>(value));
	}

}
