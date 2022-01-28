package Spotify.service.impl;

import Spotify.controller.rest.model.AlbumRest;
import Spotify.controller.rest.model.ArtistRest;
import Spotify.controller.rest.model.restAlbums.SongRestAlbum;
import Spotify.exception.SpotifyException;
import Spotify.exception.SpotifyNotFoundException;
import Spotify.exception.error.ErrorDto;
import Spotify.mapper.AlbumMapper;
import Spotify.mapper.SongMapper;
import Spotify.persistence.entity.AlbumEntity;
import Spotify.persistence.repository.AlbumRepository;
import Spotify.service.AlbumService;
import Spotify.util.constant.ExceptionConstantsUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private final AlbumRepository albumRepository;

    @Autowired
    private final AlbumMapper albumMapper;

    @Autowired
    private final SongMapper songMapper;


    @Override
    public Page<AlbumRest> getAllAlbums(Pageable pageable) throws SpotifyException {
        Page<AlbumEntity> page = albumRepository.findAll(pageable);
        return page.map(albumMapper::mapToRest);
    }

    @Override
    public AlbumRest getAlbumById(int id) throws SpotifyException {
        AlbumEntity album = albumRepository.findById(id)
                .orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        return albumMapper.mapToRest(album);
    }

    @Override
    public Page<SongRestAlbum> getSongsOfAlbum(Pageable pageable, int id) throws SpotifyException {
        AlbumEntity album = albumRepository.findById(id)
                .orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        List<SongRestAlbum> songRest = albumMapper.mapToRest(album).getSongs();
        return new PageImpl(songRest, pageable, songRest.size());
    }

    @Override
    public Page<ArtistRest> getArtistsOfAlbum(Pageable pageable, int id) throws SpotifyException {
        return null;
    }


    @Override
    public AlbumRest createAlbum(AlbumEntity album) throws SpotifyException {
        albumRepository.save(album);
        return albumMapper.mapToRest(album);
    }

    @Override
    public AlbumRest updateAlbum(AlbumEntity album, int id) throws SpotifyException {
        return null;
    }

    @Override
    public void deleteAlbum(int id) throws SpotifyException {

    }
}
