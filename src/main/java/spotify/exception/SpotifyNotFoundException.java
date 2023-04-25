package spotify.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import spotify.exception.error.ErrorDto;

import java.util.Collection;

@Getter
public class SpotifyNotFoundException extends SpotifyException {

    private static final long serialVersionUID = 1419856382856533644L;

    public SpotifyNotFoundException(final ErrorDto errorDto) {
        super(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), errorDto);
    }

    public SpotifyNotFoundException(final Collection<ErrorDto> errorDtoCollection) {
        super(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), errorDtoCollection);
    }

}
