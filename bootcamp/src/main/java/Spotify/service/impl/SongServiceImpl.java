package Spotify.service.impl;

import Spotify.controller.rest.model.PostSongRest;
import Spotify.controller.rest.model.SongRest;
import Spotify.exception.SpotifyException;
import Spotify.exception.SpotifyNotFoundException;
import Spotify.exception.error.ErrorDto;
import Spotify.mapper.PostSongMapper;
import Spotify.mapper.SongMapper;
import Spotify.persistence.entity.SongEntity;

import Spotify.persistence.repository.SongRepository;
import Spotify.service.SongService;

import Spotify.util.constant.ExceptionConstantsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    @Autowired
    private final SongRepository songRepository;

    @Autowired
    private final SongMapper songMapper;

    @Autowired
    private final PostSongMapper postSongMapper;

    @Transactional(readOnly = true)
    @Override
    public Page<SongRest> getAllSongs(final Pageable pageable) throws SpotifyException {
		return songRepository.findAll(pageable).map(song -> songMapper.mapToRest(song));
    }

    @Transactional(readOnly = false)
    @Override
    public PostSongRest createSong(final PostSongRest song) throws SpotifyException {
     SongEntity songEntity=  postSongMapper.mapToEntity(song);
		songRepository.save( songEntity );
		return song;
	
    }
    @Transactional(readOnly = true)
    @Override
    public SongRest getSongById(final int id) throws SpotifyException {
	SongEntity song = songRepository.findById(id)
		.orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
		return songMapper.mapToRest(song);
    }
    @Transactional(readOnly = false)
    @Override
    public SongRest updateSong(final SongEntity songEntity) throws SpotifyException {
	SongEntity song = songRepository.findById(songEntity.getId())
		.orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
		songRepository.save(song);
		return songMapper.mapToRest(song);
    }
    @Transactional(readOnly = false)
    @Override
    public void deleteSong(final int id) throws SpotifyException {
	songRepository.deleteById(id);
    }
}
