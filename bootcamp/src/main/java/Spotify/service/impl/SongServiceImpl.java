package Spotify.service.impl;

import Spotify.controller.rest.model.SongRest;
import Spotify.exception.SpotifyException;
import Spotify.exception.SpotifyNotFoundException;
import Spotify.exception.error.ErrorDto;
import Spotify.mapper.SongMapper;
import Spotify.persistence.entity.SongEntity;

import Spotify.persistence.repository.SongRepository;
import Spotify.service.SongService;

import Spotify.util.constant.ExceptionConstantsUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final SongMapper songMapper;

    @Override
    public Page<SongRest> getAllSongs(final Pageable pageable) throws SpotifyException {
		return songRepository.findAll(pageable).map(song -> songMapper.mapToRest(song));
    }

    @Override
    public SongRest createSong(final SongEntity song) throws SpotifyException {
		songRepository.save(song);
		return songMapper.mapToRest(song);
	
    }

    @Override
    public SongRest getSongById(final Long id) throws SpotifyException {
	SongEntity song = songRepository.findById(id)
		.orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
		return songMapper.mapToRest(song);
    }

    @Override
    public SongRest updateSong(final SongEntity songEntity) throws SpotifyException {
	SongEntity song = songRepository.findById(songEntity.getId())
		.orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
		songRepository.save(song);
		return songMapper.mapToRest(song);
    }

    @Override
    public void deleteSong(final Long id) throws SpotifyException {
	songRepository.deleteById(id);
    }
}
