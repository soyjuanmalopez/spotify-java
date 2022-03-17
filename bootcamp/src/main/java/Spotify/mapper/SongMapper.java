package Spotify.mapper;

import Spotify.controller.rest.model.SongRest;
import Spotify.controller.rest.model.restSongs.PostSongRest;
import Spotify.persistence.entity.SongEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.io.Serializable;

@Mapper(componentModel = "spring")
public interface SongMapper {

    SongEntity mapToEntity(SongRest rest);

    SongRest mapToRest(SongEntity entity);

    SongEntity mapToEntity(PostSongRest rest);

    PostSongRest mapToRestPost(SongEntity entity);
}
