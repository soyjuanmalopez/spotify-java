package Spotify.service.impl;

import Spotify.controller.rest.model.AlbumRest;
import Spotify.controller.rest.model.ArtistRest;
import Spotify.controller.rest.model.restAlbums.AlbumRestPost;
import Spotify.controller.rest.model.restAlbums.SongRestAlbum;
import Spotify.exception.SpotifyException;
import Spotify.exception.SpotifyNotFoundException;
import Spotify.exception.error.ErrorDto;
import Spotify.mapper.AlbumMapper;
import Spotify.mapper.AlbumPostMapper;
import Spotify.mapper.SongMapper;
import Spotify.persistence.entity.AlbumEntity;
import Spotify.persistence.entity.SongEntity;
import Spotify.persistence.repository.AlbumRepository;
import Spotify.persistence.repository.SongRepository;
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
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private AlbumPostMapper albumPostMapper;

    @Autowired
    private SongMapper songMapper;


    @Override
    public Page<AlbumRest> getAllAlbums(Pageable pageable) throws SpotifyException {
        Page<AlbumEntity> page = albumRepository.findAll(pageable);
        Page<AlbumRest> albumRests = page.map(albumMapper::mapToRest);
        return albumRests;
    }

    @Override
    public AlbumRest getAlbumById(Long id) throws SpotifyException {
        AlbumEntity album = albumRepository.findById(id)
                .orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        return albumMapper.mapToRest(album);
    }

    @Override
    public Page<SongRestAlbum> getSongsOfAlbum(Pageable pageable, Long id) throws SpotifyException {
        AlbumEntity album = albumRepository.findById(id)
                .orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        List<SongRestAlbum> songRest = albumMapper.mapToRest(album).getSongs();
        return new PageImpl(songRest, pageable, songRest.size());
    }

    @Override
    public Page<ArtistRest> getArtistsOfAlbum(Pageable pageable, Long id) throws SpotifyException {
        return null;
    }


    @Override
    public AlbumRestPost createAlbum(AlbumRestPost album) throws SpotifyException {
        AlbumEntity albumEntity = albumPostMapper.mapToEntity(album);
        albumRepository.save(albumEntity);
        return album;
    }

    @Override
    public AlbumRestPost updateAlbum(AlbumRestPost album, Long id) throws SpotifyException {
        AlbumEntity albumEntity = albumRepository.findById(id)
                .orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        albumEntity.setTitle(album.getTitle());
        albumEntity.setDuration(album.getDuration());
        albumEntity.setYearRelease(album.getYearRelease());

        albumRepository.save(albumEntity);
        return albumPostMapper.mapToRest(albumEntity);
    }

    @Override
    public void deleteAlbum(Long id) throws SpotifyException {
        if (!albumRepository.existsById(id)){
            throw new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC));
        }
        albumRepository.deleteById(id);
    }

    @Override
    public AlbumRest addSongOfAlbum(Long albumId, Long songId) throws SpotifyException {
        AlbumEntity album = albumRepository.findById(albumId)
                .orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        SongEntity songEntity = songRepository.findById(songId)
                .orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        album.getSongs().add(songEntity);
        songEntity.setAlbum_ref(album);
        albumRepository.save(album);
        songRepository.save(songEntity);
        return albumMapper.mapToRest(album);
    }

    @Override
    public AlbumRest addArtistToAlbum(Long albumId, Long artistId) throws SpotifyException {
        return null;
    }

    @Override
    public AlbumRest deleteSongOfAlbum(Long albumId, Long songId) throws SpotifyException {
        AlbumEntity album = albumRepository.findById(albumId)
                .orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        SongEntity songEntity = songRepository.findById(songId)
                .orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        for (int i = 0; i < album.getSongs().size(); i++) {
            if (album.getSongs().get(i).getId() == songId){
                album.getSongs().remove(i);
            }
        }
        songEntity.setAlbum_ref(null);
        albumRepository.save(album);
        songRepository.save(songEntity);
        return albumMapper.mapToRest(album);
    }

    @Override
    public AlbumRest deleteArtistOfAlbum(Long albumId, Long artistId) throws SpotifyException {
        return null;
    }
}
