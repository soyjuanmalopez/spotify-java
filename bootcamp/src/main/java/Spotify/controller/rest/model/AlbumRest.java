package Spotify.controller.rest.model;


import Spotify.controller.rest.model.restAlbums.ArtistRestAlbum;
import Spotify.controller.rest.model.restAlbums.SongRestAlbum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlbumRest implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("duration")
    private double duration;

    @JsonProperty("year_release")
    private int yearRelease;

    @JsonProperty("songs")
    private List<SongRestAlbum> songs;

    /*@JsonProperty("artists")
    private List<ArtistRestAlbum> artists;*/

}
