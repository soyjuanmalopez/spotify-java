package spotify.controller.rest.model.restSongs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostSongRest implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("tile")
    private String title;

    @JsonProperty("reproductions")
    private Integer reproductions;

    @JsonProperty("album")
    private AlbumRestSong album;

    @JsonProperty("duration")
    private Double duration;


}
