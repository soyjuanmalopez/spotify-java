package Spotify.mapper;

import Spotify.controller.rest.model.restSongs.GenreSongRest;
import Spotify.persistence.entity.GenreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GenreSongMapper {

    GenreSongMapper INSTANCE = Mappers.getMapper(GenreSongMapper.class);

    GenreEntity mapToEntity(GenreSongRest rest);
    GenreSongRest mapToRest(GenreEntity entity);
}
