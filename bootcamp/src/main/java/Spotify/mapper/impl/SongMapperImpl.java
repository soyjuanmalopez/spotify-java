package Spotify.mapper.impl;

import Spotify.controller.rest.model.SongRest;
import Spotify.mapper.ArtistMapper;
import Spotify.mapper.GenreMapper;
import Spotify.mapper.SongMapper;
import Spotify.persistence.entity.SongEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SongMapperImpl implements SongMapper {

    @Override
    public SongEntity mapToEntity(SongRest rest) {
        SongEntity entity= new SongEntity();
        ArtistMapper artistMapper = new ArtistMapperImpl();
        GenreMapper genreMapper= new GenreMapperImpl();
        entity.setId(rest.getId());
        entity.setDuration(rest.getDuration());
        entity.setAlbum_ref(rest.getAlbum_ref());
        entity.setReproductions(rest.getReproductions());
        entity.setTitle(rest.getTitle());
        entity.setArtists(rest.getArtists().stream().map(artistMapper::mapToEntity).collect(Collectors.toList()));
        entity.setGenres(rest.getGenres().stream().map(genreMapper::mapToEntity).collect(Collectors.toList()));
        return entity;
    }

    @Override
    public SongRest mapToRest(SongEntity entity) {
        SongRest rest= new SongRest();
        ArtistMapper artistMapper = new ArtistMapperImpl();
        GenreMapper genreMapper= new GenreMapperImpl();
        rest.setId(entity.getId());
        rest.setDuration(entity.getDuration());
        rest.setAlbum_ref(entity.getAlbum_ref());
        rest.setReproductions(entity.getReproductions());
        rest.setTitle(entity.getTitle());
        rest.setArtists(entity.getArtists().stream().map(artistMapper::mapToRest).collect(Collectors.toList()));
        rest.setGenres(entity.getGenres().stream().map(genreMapper::mapToRest).collect(Collectors.toList()));
        return rest;    }
}
