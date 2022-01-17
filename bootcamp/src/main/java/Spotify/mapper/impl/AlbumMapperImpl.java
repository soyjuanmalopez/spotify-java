package Spotify.mapper.impl;

import Spotify.controller.rest.model.AlbumRest;
import Spotify.mapper.AlbumMapper;
import Spotify.mapper.ArtistMapper;
import Spotify.mapper.SongMapper;
import Spotify.persistence.entity.AlbumEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AlbumMapperImpl implements AlbumMapper {

    @Override
    public AlbumEntity mapToEntity(AlbumRest rest) {

        AlbumEntity entity = new AlbumEntity();
        ArtistMapper artistMapper = new ArtistMapperImpl();
        SongMapper songMapper= new SongMapperImpl();
        entity.setId(rest.getId());
        entity.setTitle(rest.getTitle());
        entity.setDuration(rest.getDuration());
        entity.setYearRelease(rest.getYearRelease());
        entity.setArtists(rest.getArtists().stream().map(artistMapper::mapToEntity).collect(Collectors.toList()));
        entity.setSongs(rest.getSongs().stream().map(songMapper::mapToEntity).collect(Collectors.toList()));
        return entity;
    }

    @Override
    public AlbumRest mapToRest(AlbumEntity entity) {
        AlbumRest rest = new AlbumRest();
        ArtistMapper artistMapper = new ArtistMapperImpl();
        SongMapper songMapper= new SongMapperImpl();
        rest.setId(entity.getId());
        rest.setTitle(entity.getTitle());
        rest.setDuration(entity.getDuration());
        rest.setYearRelease(entity.getYearRelease());
        rest.setArtists(entity.getArtists().stream().map(artistMapper::mapToRest).collect(Collectors.toList()));
        rest.setSongs(entity.getSongs().stream().map(songMapper::mapToRest).collect(Collectors.toList()));
        return rest;
    }
}
