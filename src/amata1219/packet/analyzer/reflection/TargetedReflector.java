package amata1219.packet.analyzer.reflection;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TargetedReflector<T> {
	
	private final T instance;
	
	public TargetedReflector(T instance){
		this.instance = instance;
	}
	
	public <U> U fieldValue(String name){
		return Reflector.fieldValue(Reflector.field(instance.getClass(), name), instance);
	}
	
	@SuppressWarnings("rawtypes")
	public <U> U invokeMethod(String name, Object... parameters){
		Class[] parameterTypes = Arrays.stream(parameters).map(Object::getClass).toArray(Class[]::new);
		Method method = Reflector.method(instance.getClass(), name, parameterTypes);
		return Reflector.invokeMethod(method, name, parameters);
	}
	
}
