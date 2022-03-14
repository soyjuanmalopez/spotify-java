package Spotify.mapper;

import Spotify.controller.rest.model.restSongs.PostSongRest;
import Spotify.persistence.entity.SongEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostSongMapper {

    SongEntity mapToEntity(PostSongRest rest);

    PostSongRest mapToRest(SongEntity entity);
}
