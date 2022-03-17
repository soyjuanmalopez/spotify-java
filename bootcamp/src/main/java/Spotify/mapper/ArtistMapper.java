package Spotify.mapper;

import Spotify.controller.rest.model.ArtistRest;
import Spotify.controller.rest.model.restArtists.PostArtistRest;
import Spotify.persistence.entity.ArtistEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")

public interface ArtistMapper {

    ArtistEntity mapToEntity(ArtistRest rest);

    ArtistRest mapToRest(ArtistEntity entity);

    ArtistEntity mapToEntity(PostArtistRest rest);

    PostArtistRest mapToRestPost(ArtistEntity entity);
}
