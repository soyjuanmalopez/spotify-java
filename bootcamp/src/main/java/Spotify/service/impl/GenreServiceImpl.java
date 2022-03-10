package Spotify.service.impl;

import Spotify.controller.rest.model.GenreRest;
import Spotify.controller.rest.model.restSongs.GenreSongRest;
import Spotify.controller.rest.model.SongRest;
import Spotify.exception.SpotifyException;
import Spotify.exception.SpotifyNotFoundException;
import Spotify.exception.error.ErrorDto;
import Spotify.mapper.GenreMapper;
import Spotify.mapper.GenreSongMapper;
import Spotify.mapper.SongMapper;
import Spotify.persistence.entity.GenreEntity;
import Spotify.persistence.entity.SongEntity;
import Spotify.persistence.repository.GenreRepository;
import Spotify.persistence.repository.SongRepository;
import Spotify.service.GenreService;
import Spotify.util.constant.ExceptionConstantsUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    private final GenreMapper genreMapper;

    private final SongMapper songMapper;

    private final SongRepository songRepository;

    private final GenreSongMapper genreSongMapper;

    @Transactional(readOnly = true)
    @Override
    public Page<GenreRest> getAllGenres(Pageable pageable) throws SpotifyException {
        return genreRepository.findAll(pageable).map(genre -> genreMapper.mapToRest(genre));
    }

    @Transactional(readOnly = true)
    @Override
    public GenreRest getGenreById(Long id) throws SpotifyException {
        GenreEntity genre = genreRepository.findById(id)
                .orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        return genreMapper.mapToRest(genre);
    }

    @Transactional(readOnly = true)
    @Override
    public List<SongRest> getSongsByGenreId(Long genreId) throws SpotifyException {
        GenreEntity genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
         return genre.getSongs().stream().map(songMapper::mapToRest).collect(Collectors.toList());
    }

    @Transactional(readOnly = false)
    @Override
    public GenreRest createGenre(GenreRest genre) throws SpotifyException {
        GenreEntity genreEntity=  genreMapper.mapToEntity(genre);
        genreRepository.save( genreEntity );
        return genre;
    }

    @Transactional(readOnly = false)
    @Override
    public GenreRest updateGenre(GenreEntity genre) throws SpotifyException {
        GenreEntity genreEntity = genreRepository.findById(genre.getId())
                .orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        genreEntity.setName(genre.getName());
        genreRepository.save(genreEntity);
        return genreMapper.mapToRest(genreEntity);
    }

    @Transactional(readOnly = false)
    @Override
    public GenreSongRest updateSongByGenreId(Long genreId, Long songId) throws SpotifyException {
        GenreEntity genre = genreRepository
                .findById(genreId).orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        SongEntity song = songRepository.findById(songId).orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        song.getGenres().add(genre);
        genre.getSongs().add(song);
        songRepository.save(song);
        genreRepository.save(genre);
        return genreSongMapper.mapToRest(genre);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteGenre(final Long id) throws SpotifyException {
    genreRepository.deleteById(id);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteSongFromGenreById(final Long genreId, final Long songId) throws SpotifyException {
    GenreEntity genreEntity = genreRepository.findById(genreId) .orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        List<SongEntity> songToDelete= new ArrayList<>();
        genreEntity.getSongs().forEach(song -> {
            if (song.getId() == songId) {
                songToDelete.add(song);
            }
        });
        genreEntity.getSongs().remove(songToDelete.get(0));
        songToDelete.get(0).getGenres().remove(genreEntity);
        genreRepository.save(genreEntity);
        songRepository.save(songToDelete.get(0));
    }
}
