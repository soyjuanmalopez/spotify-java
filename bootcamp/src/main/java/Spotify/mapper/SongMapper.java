package Spotify.mapper;

import Spotify.controller.rest.model.SongRest;
import Spotify.persistence.entity.SongEntity;

import java.io.Serializable;

public interface SongMapper {

    public SongEntity mapToEntity(SongRest rest);

    SongRest mapToRest(SongEntity entity);
}
