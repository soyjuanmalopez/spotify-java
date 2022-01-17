package Spotify.controller.rest.model;


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

    @JsonProperty("year_relase")
    private int yearRelease;

    @JsonProperty("artists")
    private List<ArtistRest> artists;

    @JsonProperty("songs")
    private List<SongRest> songs;

}
