package spotify.mapper;

import org.mapstruct.Mapper;
import spotify.controller.rest.model.AlbumRest;
import spotify.controller.rest.model.restAlbums.AlbumRestPost;
import spotify.persistence.entity.AlbumEntity;

@Mapper(componentModel = "spring")
public interface AlbumMapper {

    AlbumEntity mapToEntity(AlbumRest rest);

    AlbumRest mapToRest(AlbumEntity entity);

    AlbumEntity mapToEntity(AlbumRestPost rest);

    AlbumRestPost mapToRestPost(AlbumEntity entity);
}
