package amata1219.packet.analyzer.monad;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public interface Maybe<T> {
	
	public static <T> Maybe<T> unit(T value){
		return value != null ? Some(value) : None();
	}
	
	public static <T> Maybe<T> Some(T value){
		return new Some<>(Objects.requireNonNull(value));
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Maybe<T> None(){
		return (Maybe<T>) None.NONE;
	}
	
	<U> Maybe<U> flatMap(Function<T, Maybe<U>> mapper);
	
	@SuppressWarnings("unchecked")
	default <U> Maybe<U> map(Function<T, U> mapper){
		return (Maybe<U>) flatMap(mapper.andThen(Maybe::Some));
	}
	
	Maybe<T> filter(Predicate<T> filter);
	
	<X extends Throwable> T or(Supplier<X> x) throws X;
	
	void apply(Consumer<T> action);
	
	public class Some<T> implements Maybe<T> {
		
		private final T value;
		
		private Some(T value){
			this.value = value;
		}

		@Override
		public <U> Maybe<U> flatMap(Function<T, Maybe<U>> mapper) {
			return mapper.apply(value);
		}
		
		@Override
		public Maybe<T> filter(Predicate<T> filter) {
			return filter.test(value) ? this : None();
		}
		
		@Override
		public <X extends Throwable> T or(Supplier<X> x) throws X {
			return value;
		}
		
		@Override
		public void apply(Consumer<T> action) {
			action.accept(value);
		}

	}
	
	public class None<T> implements Maybe<T> {
		
		private static final None<?> NONE = new None<>();
		
		private None(){
			
		}

		@Override
		public <U> Maybe<U> flatMap(Function<T, Maybe<U>> mapper) {
			return None();
		}
		
		@Override
		public Maybe<T> filter(Predicate<T> filter) {
			return this;
		}
		
		@Override
		public <X extends Throwable> T or(Supplier<X> x) throws X {
			throw x.get();
		}
		
		@Override
		public void apply(Consumer<T> action) {
			
		}

	}

}
