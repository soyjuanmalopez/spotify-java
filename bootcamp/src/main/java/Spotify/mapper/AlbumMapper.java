package Spotify.mapper;

import Spotify.controller.rest.model.AlbumRest;
import Spotify.persistence.entity.AlbumEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AlbumMapper {

    AlbumMapper INSTANCE = Mappers.getMapper(AlbumMapper.class);

    public AlbumEntity mapToEntity(AlbumRest rest);

    AlbumRest mapToRest(AlbumEntity entity);
}
