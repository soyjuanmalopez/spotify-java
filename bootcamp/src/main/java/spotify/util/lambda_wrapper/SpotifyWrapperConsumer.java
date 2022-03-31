package spotify.util.lambda_wrapper;

import spotify.exception.SpotifyException;
import spotify.exception.SpotifyRuntimeException;

import java.util.function.Consumer;

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
