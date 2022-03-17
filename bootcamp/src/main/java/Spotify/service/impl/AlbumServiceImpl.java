package Spotify.service.impl;

import Spotify.controller.rest.model.AlbumRest;
import Spotify.controller.rest.model.restAlbums.AlbumRestPost;
import Spotify.controller.rest.model.restAlbums.ArtistRestAlbum;
import Spotify.controller.rest.model.restAlbums.SongRestAlbum;
import Spotify.exception.SpotifyException;
import Spotify.exception.SpotifyNotFoundException;
import Spotify.exception.error.ErrorDto;
import Spotify.mapper.AlbumMapper;
import Spotify.mapper.SongMapper;
import Spotify.persistence.entity.AlbumEntity;
import Spotify.persistence.entity.ArtistEntity;
import Spotify.persistence.entity.SongEntity;
import Spotify.persistence.repository.AlbumRepository;
import Spotify.persistence.repository.ArtistRepository;
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
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    private final SongRepository songRepository;

    private final ArtistRepository artistRepository;

    private final AlbumMapper albumMapper;


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
        spotifyBadRequestSongsOrArtistEmpty(album);        
        return albumMapper.mapToRest(album);
    }

    public void spotifyBadRequestSongsOrArtistEmpty(AlbumEntity album) throws SpotifyException {
        if (album.getArtists().isEmpty() && album.getSongs().isEmpty()) {
            throw new SpotifyException(ExceptionConstantsUtils.BAD_REQUEST_INT,"Songs and Artist list are empty",new ErrorDto(ExceptionConstantsUtils.BAD_REQUEST_GENERIC, "Songs and Artist list are empty"));
        }
        if (album.getArtists().isEmpty()) {
            throw new SpotifyException(ExceptionConstantsUtils.BAD_REQUEST_INT,"Artist list is empty",new ErrorDto(ExceptionConstantsUtils.BAD_REQUEST_GENERIC));
        }
        if (album.getSongs().isEmpty()) {
            throw new SpotifyException(ExceptionConstantsUtils.BAD_REQUEST_INT,"Songs list is empty",new ErrorDto(ExceptionConstantsUtils.BAD_REQUEST_GENERIC));
        }
    }

    @Override
    public Page<SongRestAlbum> getSongsOfAlbum(Pageable pageable, Long id) throws SpotifyException {
        AlbumEntity album = albumRepository.findById(id)
                .orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        spotifyBadRequestSongsOrArtistEmpty(album);        
        List<SongRestAlbum> songRest = albumMapper.mapToRest(album).getSongs();
        return new PageImpl(songRest, pageable, songRest.size());
    }

    @Override
    public Page<ArtistRestAlbum> getArtistsOfAlbum(Pageable pageable, Long id) throws SpotifyException {
        AlbumEntity album = albumRepository.findById(id)
                .orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        spotifyBadRequestSongsOrArtistEmpty(album);        
        List<ArtistRestAlbum> artistRest = albumMapper.mapToRest(album).getArtists();
        return new PageImpl(artistRest, pageable, artistRest.size());
    }


    @Override
    public AlbumRestPost createAlbum(AlbumRestPost album) throws SpotifyException {
        AlbumEntity albumEntity = albumMapper.mapToEntity(album);
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
        return albumMapper.mapToRestPost(albumEntity);
    }

    @Override
    public void deleteAlbum(Long id) throws SpotifyException {
        if (!albumRepository.existsById(id)) {
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
        songEntity.setAlbum(album);
        albumRepository.save(album);
        songRepository.save(songEntity);
        return albumMapper.mapToRest(album);
    }

    @Override
    public AlbumRest addArtistToAlbum(Long albumId, Long artistId) throws SpotifyException {
        AlbumEntity album = albumRepository.findById(albumId)
                .orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        ArtistEntity artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        album.getArtists().add(artist);
        artist.getAlbums().add(album);
        albumRepository.save(album);
        artistRepository.save(artist);
        return albumMapper.mapToRest(album);
    }

    @Override
    public AlbumRest deleteSongOfAlbum(Long albumId, Long songId) throws SpotifyException {
        AlbumEntity album = albumRepository.findById(albumId)
                .orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        SongEntity songEntity = songRepository.findById(songId)
                .orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        for (int i = 0; i < album.getSongs().size(); i++) {
            if (album.getSongs().get(i).getId() == songId) {
                album.getSongs().remove(i);
            }
        }
        songEntity.setAlbum(null);
        albumRepository.save(album);
        songRepository.save(songEntity);
        return albumMapper.mapToRest(album);
    }

    @Override
    public AlbumRest deleteArtistOfAlbum(Long albumId, Long artistId) throws SpotifyException {
        AlbumEntity album = albumRepository.findById(albumId)
                .orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        ArtistEntity artistEntity = artistRepository.findById(artistId)
                .orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        for (int i = 0; i < album.getArtists().size(); i++) {
            if (album.getArtists().get(i).getId() == artistId) {
                album.getArtists().remove(i);
            }
        }
        for (int i = 0; i < artistEntity.getAlbums().size(); i++) {
            if (artistEntity.getAlbums().get(i).getId() == albumId) {
                artistEntity.getAlbums().remove(i);
            }
        }
        albumRepository.save(album);
        artistRepository.save(artistEntity);
        return albumMapper.mapToRest(album);
    }
}
