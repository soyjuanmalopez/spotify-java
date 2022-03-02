package Spotify.util.lambda_wrapper;

import java.util.function.Consumer;

import Spotify.exception.SpotifyException;
import Spotify.exception.SpotifyRuntimeException;

@FunctionalInterface
public interface SpotifyWrapperConsumer<T, E extends SpotifyException> {

	void accept(T item) throws E;

	static <T, E extends SpotifyException> Consumer<T> wrap(final SpotifyWrapperConsumer<T, E> wrapper) {
		return item -> {
			try {
				wrapper.accept(item);
			} catch (final SpotifyException exception) {
				throw new SpotifyRuntimeException(exception);
			}
		};
	}

}
