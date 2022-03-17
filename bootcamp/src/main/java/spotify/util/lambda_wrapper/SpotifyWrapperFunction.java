package spotify.util.lambda_wrapper;

import spotify.exception.SpotifyException;
import spotify.exception.SpotifyRuntimeException;

import java.util.function.Function;

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
