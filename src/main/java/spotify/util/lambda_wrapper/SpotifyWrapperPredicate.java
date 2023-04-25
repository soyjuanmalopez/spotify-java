package spotify.util.lambda_wrapper;

import spotify.exception.SpotifyException;
import spotify.exception.SpotifyRuntimeException;

import java.util.function.Predicate;

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
