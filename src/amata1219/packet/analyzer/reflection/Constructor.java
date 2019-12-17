package amata1219.packet.analyzer.reflection;

import java.lang.reflect.InvocationTargetException;

import amata1219.packet.analyzer.monad.Either;

public class Constructor<T> {
	
	@SuppressWarnings("unchecked")
	public static <T> Either<String, Constructor<T>> of(Class<T> clazz, Class<?>... parameterTypes){
		try {
			return Either.unit(clazz.getConstructor(parameterTypes)).map(Constructor::new);
		} catch (NoSuchMethodException | SecurityException e) {
			return Either.Failure(e.getMessage());
		}
	}
	
	private final java.lang.reflect.Constructor<T> constructor;
	
	private Constructor(java.lang.reflect.Constructor<T> constructor){
		this.constructor = constructor;
	}
	
	public Either<String, T> newInstance(Object... initargs){
		try {
			return Either.unit(constructor.newInstance(initargs));
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			return Either.Failure(e.getMessage());
		}
	}
	
}
