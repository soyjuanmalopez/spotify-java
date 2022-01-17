package Spotify.service.impl;

import Spotify.exception.SpotifyException;
import Spotify.exception.SpotifyNotFoundException;
import Spotify.exception.error.ErrorDto;
import Spotify.persistence.entity.SongEntity;
import Spotify.persistence.mapper.SongEntityMapper2;
import Spotify.persistence.repository.SongRepository;
import Spotify.service.SongService;
import Spotify.service.model.SongDto;
import Spotify.util.constant.ExceptionConstantsUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final SongEntityMapper2 songEntityMapper;

    @Override
    public Page<SongDto> getAllSongs(final Pageable pageable) throws SpotifyException {
		return songRepository.findAll(pageable).map(songEntity -> songEntityMapper.mapToDto(songEntity));
    }

    @Override
    public SongDto createSong(final SongDto songDto) throws SpotifyException {
		SongEntity songEntity = songRepository.save(songEntityMapper.mapToEntity(songDto));
		return songEntityMapper.mapToDto(songEntity);
	
    }

    @Override
    public SongDto getSongById(final Long id) throws SpotifyException {
	SongEntity song = songRepository.findById(id)
		.orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
		return songEntityMapper.mapToDto(song);
    }

    @Override
    public SongDto updateSong(final SongDto songDto) throws SpotifyException {
	SongEntity song = songRepository.findById(songDto.getId())
		.orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
		SongEntity songEntity = songRepository.save(songEntityMapper.mapToEntity(songDto));
		return songEntityMapper.mapToDto(songEntity);
    }

    @Override
    public void deleteSong(final Long id) throws SpotifyException {
	songRepository.deleteById(id);
    }
}
