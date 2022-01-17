package Spotify.mapper.impl;

import Spotify.controller.rest.model.GenreRest;
import Spotify.mapper.GenreMapper;
import Spotify.mapper.SongMapper;
import Spotify.persistence.entity.GenreEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class GenreMapperImpl implements GenreMapper {


    @Override
    public GenreEntity mapToEntity(GenreRest rest) {
        GenreEntity entity = new GenreEntity();
        SongMapper songMapper= new SongMapperImpl();
        entity.setId(rest.getId());
        entity.setName(rest.getName());
        entity.setSongs(rest.getSongs().stream().map(songMapper::mapToEntity).collect(Collectors.toList()));
        return entity;

    }

    @Override
    public GenreRest mapToRest(GenreEntity entity) {
        GenreRest rest = new GenreRest();
        SongMapper songMapper= new SongMapperImpl();
        rest.setId(entity.getId());
        rest.setName(entity.getName());
        rest.setSongs(entity.getSongs().stream().map(songMapper::mapToRest).collect(Collectors.toList()));
        return rest;
    }
}
