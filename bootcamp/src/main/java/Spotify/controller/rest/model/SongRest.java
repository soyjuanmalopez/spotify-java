package Spotify.controller.rest.model;

import java.io.Serializable;
import java.util.Set;

import Spotify.controller.rest.model.restSongs.AlbumRestSong;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

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

    @JsonProperty("album_ref")
    private AlbumRestSong album_ref;

    @JsonProperty("duration")
    private Double duration;

    @JsonProperty("artists")
    private Set<ArtistRest> artists;

    @JsonProperty("genres")
    private Set<GenreRest> genres;



}
