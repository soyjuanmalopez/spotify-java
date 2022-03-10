package Spotify.service.impl;

import Spotify.controller.rest.model.AlbumRest;
import Spotify.controller.rest.model.restSongs.PostSongRest;
import Spotify.controller.rest.model.SongRest;
import Spotify.exception.SpotifyException;
import Spotify.exception.SpotifyNotFoundException;
import Spotify.exception.error.ErrorDto;
import Spotify.mapper.AlbumMapper;
import Spotify.mapper.PostSongMapper;
import Spotify.mapper.SongMapper;
import Spotify.persistence.entity.ArtistEntity;
import Spotify.persistence.entity.SongEntity;

import Spotify.persistence.repository.ArtistRepository;
import Spotify.persistence.repository.SongRepository;
import Spotify.service.SongService;

import Spotify.util.constant.ExceptionConstantsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    private final ArtistRepository artistRepository;

    private final SongMapper songMapper;

    private final AlbumMapper albumMapper;

    private final PostSongMapper postSongMapper;

    @Transactional(readOnly = true)
    @Override
    public Page<SongRest> getAllSongs(final Pageable pageable) throws SpotifyException {
		return songRepository.findAll(pageable).map(song -> songMapper.mapToRest(song));
    }

    @Transactional(readOnly = false)
    @Override
    public PostSongRest createSong(final PostSongRest song) throws SpotifyException {
     SongEntity songEntity =  postSongMapper.mapToEntity(song);
		songRepository.save( songEntity );
		return song;
	
    }
    @Transactional(readOnly = true)
    @Override
    public SongRest getSongById(final Long id) throws SpotifyException {
	SongEntity song = songRepository.findById(id)
		.orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
		return songMapper.mapToRest(song);
    }
    @Transactional(readOnly = true)
    @Override
    public AlbumRest getAlbumBySongId(Long songId) throws SpotifyException {
        SongEntity song = songRepository.findById(songId)
                .orElseThrow(() ->
                        new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        return albumMapper.mapToRest(song.getAlbum_ref());
    }

    @Transactional(readOnly = false)
    @Override
    public PostSongRest updateSong(final SongEntity songEntity) throws SpotifyException {
        SongEntity song = songRepository.findById(songEntity.getId())
                .orElseThrow(() ->
                        new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));

        song.setTitle(songEntity.getTitle());
        song.setDuration(songEntity.getDuration());
        song.setReproductions(songEntity.getReproductions());
        song.setAlbum_ref(songEntity.getAlbum_ref());


		songRepository.save(song);
		return postSongMapper.mapToRest(song);
    }

    @Transactional(readOnly = false)
    @Override
    public SongRest updateArtistBySongId(Long songId, Long artistId) throws SpotifyException {
        SongEntity song = songRepository.findById(songId).orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        ArtistEntity artist = artistRepository.findById(artistId).orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        song.getArtists().add(artist);
        artist.getSongs().add(song);
        songRepository.save(song);
        artistRepository.save(artist);
        return songMapper.mapToRest(song);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteSong(final Long id) throws SpotifyException {
	songRepository.deleteById(id);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteArtistFromSongById(Long songId, Long artistId) throws SpotifyException {
        SongEntity song = songRepository.findById(songId).orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        List<ArtistEntity> artistToDelete= new ArrayList<>();
        song.getArtists().forEach(artist -> {
            if (artist.getId() == artistId) {
                artistToDelete.add(artist);
            }
        });
        song.getArtists().remove(artistToDelete.get(0));
        artistToDelete.get(0).getSongs().remove(song);
        artistRepository.save(artistToDelete.get(0));
        songRepository.save(song);

    }



}
