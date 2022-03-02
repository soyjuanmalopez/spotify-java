package Spotify.controller.rest.model;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

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

    @JsonProperty("album_ref")
    private AlbumRest album_ref;

    @JsonProperty("duration")
    private Double duration;




}
