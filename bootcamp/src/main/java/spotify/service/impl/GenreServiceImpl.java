package spotify.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spotify.controller.rest.model.GenreRest;
import spotify.controller.rest.model.SongRest;
import spotify.controller.rest.model.restSongs.GenreSongRest;
import spotify.exception.SpotifyException;
import spotify.exception.SpotifyNotFoundException;
import spotify.exception.error.ErrorDto;
import spotify.mapper.GenreMapper;
import spotify.mapper.SongMapper;
import spotify.persistence.entity.GenreEntity;
import spotify.persistence.entity.SongEntity;
import spotify.persistence.repository.GenreRepository;
import spotify.persistence.repository.SongRepository;
import spotify.service.GenreService;
import spotify.util.constant.ExceptionConstantsUtils;

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
        GenreEntity genreEntity = genreMapper.mapToEntity(genre);
        genreRepository.save(genreEntity);
        return genre;
    }

    @Transactional(readOnly = false)
    @Override
    public GenreRest updateGenre(GenreRest genre) throws SpotifyException {
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
        return genreMapper.mapToGenreSongRest(genre);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteGenre(final Long id) throws SpotifyException {
        genreRepository.deleteById(id);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteSongFromGenreById(final Long genreId, final Long songId) throws SpotifyException {
        GenreEntity genreEntity = genreRepository.findById(genreId).orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        List<SongEntity> songToDelete = new ArrayList<>();
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
