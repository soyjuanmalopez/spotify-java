package spotify.mapper;

import org.mapstruct.Mapper;
import spotify.controller.rest.model.ArtistRest;
import spotify.controller.rest.model.restArtists.PostArtistRest;
import spotify.persistence.entity.ArtistEntity;

@Mapper(componentModel = "spring")
public interface ArtistMapper {

    ArtistEntity mapToEntity(ArtistRest rest);

    ArtistRest mapToRest(ArtistEntity entity);

    ArtistEntity mapToEntity(PostArtistRest rest);

    PostArtistRest mapToRestPost(ArtistEntity entity);
}
