package spotify.util.lambda_wrapper;

import spotify.exception.SpotifyException;
import spotify.exception.SpotifyRuntimeException;

import java.util.function.Supplier;

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
