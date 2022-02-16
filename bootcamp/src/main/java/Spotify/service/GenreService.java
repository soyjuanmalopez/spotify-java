package Spotify.service;

import Spotify.controller.rest.model.*;
import Spotify.exception.SpotifyException;
import Spotify.persistence.entity.GenreEntity;
import Spotify.persistence.entity.SongEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenreService {

    Page<GenreRest> getAllGenres(Pageable pageable) throws SpotifyException;

    GenreRest getGenreById(Long id) throws SpotifyException;

    List<SongRest> getSongsByGenreId(Long genreId) throws SpotifyException;

    GenreRest createGenre(GenreRest genre) throws SpotifyException;

    GenreRest updateGenre(GenreEntity genre) throws SpotifyException;

    GenreSongRest updateSongByGenreId(Long genreId, Long songId) throws SpotifyException;

    void deleteGenre(Long id) throws SpotifyException;

    void deleteSongFromGenreById(Long genreId, Long songId) throws SpotifyException;

}
