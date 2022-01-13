package Spotify.controller.rest.model;

import java.io.Serializable;
import java.util.List;

import Spotify.persistence.entity.ArtistEntity;
import Spotify.persistence.entity.GenreEntity;
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
    private int reproductions;

    @JsonProperty("album_ref")
    private int album_ref;

    @JsonProperty("duration")
    private double duration;

    @JsonProperty("artists")
    private List<ArtistEntity> artists;

    @JsonProperty("genres")
    private List<GenreEntity> genres;


}
