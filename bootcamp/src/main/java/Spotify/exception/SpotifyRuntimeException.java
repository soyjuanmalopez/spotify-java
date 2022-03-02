package Spotify.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SpotifyRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 475941979339214878L;

	private SpotifyException spotifyException;

}
