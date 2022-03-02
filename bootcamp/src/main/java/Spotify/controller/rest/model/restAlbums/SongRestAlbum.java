package Spotify.controller.rest.model.restAlbums;

import Spotify.controller.rest.model.AlbumRest;
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
public class SongRestAlbum implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private int id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("reproductions")
    private int reproductions;

    @JsonProperty("duration")
    private double duration;

}
