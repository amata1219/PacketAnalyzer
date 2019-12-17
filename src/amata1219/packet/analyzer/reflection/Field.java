package amata1219.packet.analyzer.reflection;

import amata1219.packet.analyzer.monad.Either;
import amata1219.packet.analyzer.monad.Maybe;

public class Field<T, U> {
	
	public static <T, U> Either<String, Field<T, U>> of(Class<T> clazz, Class<U> type, String name){
		try {
			return Either.unit(clazz.getDeclaredField(name)).map(Field::new);
		} catch (NoSuchFieldException | SecurityException e) {
			return Either.Failure(e.getMessage());
		}
	}
	
	private final java.lang.reflect.Field field;
	
	private Field(java.lang.reflect.Field field){
		this.field = field;
		this.field.setAccessible(true);
	}
	
	@SuppressWarnings("unchecked")
	public Maybe<U> get(T instance){
		try {
			return Maybe.unit((U) field.get(instance));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			return Maybe.None();
		}
	}
	
	public void set(T instance, U value){
		try {
			field.set(instance, value);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
}
