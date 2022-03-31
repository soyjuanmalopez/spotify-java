package spotify.controller.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spotify.controller.rest.model.restSongs.AlbumRestSong;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SongRest implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("reproductions")
    private Integer reproductions;

    @JsonProperty("album")
    private AlbumRestSong album;

    @JsonProperty("duration")
    private Double duration;

    @JsonProperty("artists")
    private Set<ArtistRest> artists;

    @JsonProperty("genres")
    private Set<GenreRest> genres;


}
