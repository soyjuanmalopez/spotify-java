package Spotify.mapper;

import Spotify.controller.rest.model.GenreRest;
import Spotify.controller.rest.model.GenreSongRest;
import Spotify.persistence.entity.GenreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GenreSongMapper {

    GenreSongMapper INSTANCE = Mappers.getMapper(GenreSongMapper.class);

    public GenreEntity mapToEntity(GenreSongRest rest);
    public GenreSongRest mapToRest(GenreEntity entity);
}
