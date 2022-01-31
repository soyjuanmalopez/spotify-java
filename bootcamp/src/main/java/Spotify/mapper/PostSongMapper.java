package Spotify.mapper;

import Spotify.controller.rest.model.PostSongRest;
import Spotify.controller.rest.model.SongRest;
import Spotify.persistence.entity.SongEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.io.Serializable;

@Mapper(componentModel = "spring")
public interface PostSongMapper {

    PostSongMapper INSTANCE = Mappers.getMapper(PostSongMapper.class);

    SongEntity mapToEntity(PostSongRest rest);

    PostSongRest mapToRest(SongEntity entity);
}
