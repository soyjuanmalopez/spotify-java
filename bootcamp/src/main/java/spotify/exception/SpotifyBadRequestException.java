package spotify.exception;

import org.springframework.http.HttpStatus;
import spotify.exception.error.ErrorDto;

import java.util.Collection;


public class SpotifyBadRequestException extends SpotifyException {

    private static final long serialVersionUID = 105837498733124083L;

    public SpotifyBadRequestException(final Collection<ErrorDto> errorDtoCollection) {
        super(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), errorDtoCollection);
    }

    public SpotifyBadRequestException(final ErrorDto errorDto) {
        super(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), errorDto);
    }

}
