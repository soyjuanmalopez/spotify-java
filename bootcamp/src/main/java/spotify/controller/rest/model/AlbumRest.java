package spotify.controller.rest.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spotify.controller.rest.model.restAlbums.ArtistRestAlbum;
import spotify.controller.rest.model.restAlbums.SongRestAlbum;

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
    private Double duration;

    @JsonProperty("year_relase")
    private Integer yearRelease;

    @JsonProperty("songs")
    private List<SongRestAlbum> songs;

    @JsonProperty("artists")
    private List<ArtistRestAlbum> artists;

}
