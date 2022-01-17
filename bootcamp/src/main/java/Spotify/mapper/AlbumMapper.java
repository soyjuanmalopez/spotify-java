package Spotify.mapper;

import Spotify.controller.rest.model.AlbumRest;
import Spotify.persistence.entity.AlbumEntity;

public interface AlbumMapper {

    public AlbumEntity mapToEntity(AlbumRest rest);

    AlbumRest mapToRest(AlbumEntity entity);
}
