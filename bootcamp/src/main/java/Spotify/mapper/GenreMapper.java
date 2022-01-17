package Spotify.mapper;

import Spotify.controller.rest.model.GenreRest;
import Spotify.persistence.entity.GenreEntity;

public interface GenreMapper {

    public GenreEntity mapToEntity(GenreRest rest);
    public GenreRest mapToRest(GenreEntity entity);
}
