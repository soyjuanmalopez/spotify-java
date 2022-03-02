package Spotify.util.lambda_wrapper;

import java.util.function.Predicate;

import Spotify.exception.SpotifyException;
import Spotify.exception.SpotifyRuntimeException;

@FunctionalInterface
public interface SpotifyWrapperPredicate<T, E extends SpotifyException> {

	boolean test(T item) throws E;

	static <T, E extends SpotifyException> Predicate<T> wrap(final SpotifyWrapperPredicate<T, E> wrapper) {
		return item -> {
			try {
				return wrapper.test(item);
			} catch (final SpotifyException exception) {
				throw new SpotifyRuntimeException(exception);
			}
		};
	}

}
