package amata1219.packet.analyzer.reflection;

import java.lang.reflect.InvocationTargetException;

import amata1219.packet.analyzer.monad.Either;

public class Method<T, R> {
	
	public static <T, R> Either<String, Method<T, R>> of(Class<T> clazz, String name, Class<?>... parameterTypes){
		try {
			return Either.unit(clazz.getDeclaredMethod(name, parameterTypes)).map(Method::new);
		} catch (NoSuchMethodException | SecurityException e) {
			return Either.Failure(e.getMessage());
		}
	}
	
	public static <T> Either<String, Method<T, Void>> of_(Class<T> clazz, String name, Class<?>... parameterTypes){
		return of(clazz, name, parameterTypes);
	}
	
	private final java.lang.reflect.Method method;
	
	private Method(java.lang.reflect.Method method){
		this.method = method;
	}
	
	@SuppressWarnings("unchecked")
	public Either<String, R> invoke(T instance, Object... args){
		try {
			return Either.unit((R) method.invoke(instance, args));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			return Either.Failure(e.getMessage());
		}
	}

}
