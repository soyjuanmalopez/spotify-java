package Spotify.mapper;

import Spotify.controller.rest.model.ArtistRest;
import Spotify.persistence.entity.ArtistEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")

public interface ArtistMapper {

    ArtistMapper INSTANCE = Mappers.getMapper(ArtistMapper.class);

    public ArtistEntity mapToEntity(ArtistRest rest);

    public ArtistRest mapToRest(ArtistEntity entity);
}
