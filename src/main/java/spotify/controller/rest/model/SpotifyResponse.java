package spotify.controller.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class SpotifyResponse<T> implements Serializable {

    private static final long serialVersionUID = -1462076719007656405L;

    private String status;
    private String code;
    private String message;
    private T data;

    public SpotifyResponse(final String status, final String code, final String message) {
        super();
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
