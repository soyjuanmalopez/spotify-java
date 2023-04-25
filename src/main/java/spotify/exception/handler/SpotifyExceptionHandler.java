package spotify.exception.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import spotify.controller.rest.model.SpotifyResponse;
import spotify.exception.SpotifyException;
import spotify.exception.SpotifyRuntimeException;
import spotify.exception.error.ErrorMessageService;
import spotify.exception.error.ErrorRest;
import spotify.util.constant.ExceptionConstantsUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@SuppressWarnings({"rawtypes", "unchecked"})
@Log4j2
@RequiredArgsConstructor
public class SpotifyExceptionHandler {

    private final ErrorMessageService errorMessageService;

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public SpotifyResponse unhandledErrors(final HttpServletRequest req, final Exception ex) {
        logException(ex);
        return new SpotifyResponse(ExceptionConstantsUtils.ERROR,
                Long.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage());
    }

    @ExceptionHandler({SpotifyException.class})
    @ResponseBody
    public SpotifyResponse handleException(final HttpServletRequest request, final HttpServletResponse response,
                                           final SpotifyException ex) {
        logException(ex);
        response.setStatus(ex.getCode());

        final ErrorRest[] errorRestArray = ex.getErrorDtoCollection().stream().map(
                        errorDto -> new ErrorRest(errorDto.getCode(), errorMessageService.getCodes().get(errorDto.getCode())))
                .toArray(ErrorRest[]::new);

        return new SpotifyResponse(ExceptionConstantsUtils.ERROR, Long.toString(ex.getCode()), ex.getMessage(),
                errorRestArray);
    }

    @ExceptionHandler({SpotifyRuntimeException.class})
    @ResponseBody
    public SpotifyResponse handleException(final HttpServletRequest request, final HttpServletResponse response,
                                           final SpotifyRuntimeException ex) {
        return handleException(request, response, ex.getSpotifyException());
    }

    private void logException(final Exception exception) {
        log.error(ExceptionUtils.getStackTrace(exception));
    }

}
