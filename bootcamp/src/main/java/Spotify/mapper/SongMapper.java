package Spotify.mapper;

import Spotify.controller.rest.model.SongRest;
import Spotify.persistence.entity.SongEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.io.Serializable;

@Mapper(componentModel = "spring")
public interface SongMapper {

    SongEntity mapToEntity(SongRest rest);

    SongRest mapToRest(SongEntity entity);
}
