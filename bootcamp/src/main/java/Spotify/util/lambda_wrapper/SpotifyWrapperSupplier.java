package Spotify.util.lambda_wrapper;

import java.util.function.Supplier;

import Spotify.exception.SpotifyException;
import Spotify.exception.SpotifyRuntimeException;

@FunctionalInterface
public interface SpotifyWrapperSupplier<T, E extends SpotifyException> {

	T get() throws E;

	static <T, E extends SpotifyException> Supplier<T> wrap(final SpotifyWrapperSupplier<T, E> wrapper) {
		return () -> {
			try {
				return wrapper.get();
			} catch (final SpotifyException exception) {
				throw new SpotifyRuntimeException(exception);
			}
		};
	}

}
