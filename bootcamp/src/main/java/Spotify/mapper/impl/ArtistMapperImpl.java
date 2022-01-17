package Spotify.mapper.impl;

import Spotify.controller.rest.model.ArtistRest;
import Spotify.mapper.AlbumMapper;
import Spotify.mapper.ArtistMapper;
import Spotify.mapper.SongMapper;
import Spotify.persistence.entity.ArtistEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ArtistMapperImpl implements ArtistMapper {
    @Override
     public ArtistEntity mapToEntity(ArtistRest rest) {
        ArtistEntity entity = new ArtistEntity();
        SongMapper songMapper= new SongMapperImpl();
        AlbumMapper albumMapper = new AlbumMapperImpl();
        entity.setId(rest.getId());
        entity.setName(rest.getName());
        entity.setDescription(rest.getDescription());
        entity.setSongs(rest.getSongs().stream().map(songMapper::mapToEntity).collect(Collectors.toList()));
        entity.setAlbums(rest.getAlbums().stream().map(albumMapper::mapToEntity).collect(Collectors.toList()));
        return entity;
    }

    @Override
    public ArtistRest mapToRest(ArtistEntity entity) {
        ArtistRest rest = new ArtistRest();
        SongMapper songMapper= new SongMapperImpl();
        AlbumMapper albumMapper = new AlbumMapperImpl();
        rest.setId(entity.getId());
        rest.setName(entity.getName());
        rest.setDescription(entity.getDescription());
        rest.setSongs(entity.getSongs().stream().map(songMapper::mapToRest).collect(Collectors.toList()));
        rest.setAlbums(entity.getAlbums().stream().map(albumMapper::mapToRest).collect(Collectors.toList()));
        return rest;    }
}
