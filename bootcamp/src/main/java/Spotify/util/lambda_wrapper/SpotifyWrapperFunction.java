package Spotify.util.lambda_wrapper;

import java.util.function.Function;

import Spotify.exception.SpotifyException;
import Spotify.exception.SpotifyRuntimeException;

@FunctionalInterface
public interface SpotifyWrapperFunction<T, R, E extends SpotifyException> {

	R apply(T item) throws E;

	static <T, R, E extends SpotifyException> Function<T, R> wrap(final SpotifyWrapperFunction<T, R, E> wrapper) {
		return item -> {
			try {
				return wrapper.apply(item);
			} catch (final SpotifyException exception) {
				throw new SpotifyRuntimeException(exception);
			}
		};
	}

}
