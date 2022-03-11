package Spotify.mapper;

import Spotify.controller.rest.model.AlbumRest;
import Spotify.controller.rest.model.restAlbums.AlbumRestPost;
import Spotify.persistence.entity.AlbumEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AlbumPostMapper {

    /*AlbumPostMapper INSTANCE = Mappers.getMapper(AlbumPostMapper.class);*/

    AlbumEntity mapToEntity(AlbumRestPost rest);
    AlbumRestPost mapToRest(AlbumEntity entity);
}
