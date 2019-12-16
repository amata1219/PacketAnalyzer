package amata1219.packet.analyzer.reflection;

import java.util.Arrays;

public class TargetedReflector<T> {
	
	private final T instance;
	
	public TargetedReflector(T instance){
		this.instance = instance;
	}
	
	public <U> U fieldValue(String name){
		return Reflector.fieldValue(Reflector.field(instance.getClass(), name), instance);
	}
	
	public <U> U invokeMethod(String name, Object... parameters){
		return Reflector.invokeMethod(Reflector.method(instance.getClass(), name, Arrays.stream(parameters).map(Object::getClass).toArray(Class[]::new)), name, parameters);
	}

}
