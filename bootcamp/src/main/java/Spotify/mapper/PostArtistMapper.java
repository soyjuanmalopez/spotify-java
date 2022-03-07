package Spotify.mapper;

import Spotify.controller.rest.model.restArtists.PostArtistRest;
import Spotify.persistence.entity.ArtistEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostArtistMapper {

    PostArtistMapper INSTANCE = Mappers.getMapper(PostArtistMapper.class);

    ArtistEntity mapToEntity(PostArtistRest rest);

    PostArtistRest mapToRest(ArtistEntity entity);
}
