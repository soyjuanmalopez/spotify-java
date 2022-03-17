package Spotify.mapper;

import Spotify.controller.rest.model.GenreRest;
import Spotify.controller.rest.model.restSongs.GenreSongRest;
import Spotify.persistence.entity.GenreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreEntity mapToEntity(GenreRest rest);

    GenreRest mapToRest(GenreEntity entity);

    GenreEntity mapToEntity(GenreSongRest rest);

    GenreSongRest mapToGenreSongRest(GenreEntity entity);
}
