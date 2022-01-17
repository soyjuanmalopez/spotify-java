package Spotify.mapper;

import Spotify.controller.rest.model.AlbumRest;
import Spotify.controller.rest.model.ArtistRest;
import Spotify.persistence.entity.ArtistEntity;

public interface ArtistMapper {

    public ArtistEntity mapToEntity(ArtistRest rest);

    public ArtistRest mapToRest(ArtistEntity entity);
}
