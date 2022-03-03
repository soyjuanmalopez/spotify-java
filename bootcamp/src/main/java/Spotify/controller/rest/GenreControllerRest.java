package Spotify.controller.rest;

import Spotify.controller.rest.model.*;
import Spotify.controller.rest.model.restSongs.GenreSongRest;
import Spotify.exception.SpotifyException;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface GenreControllerRest {

    SpotifyResponse<D4iPageRest<GenreRest>> getAllGenres(Long page, Long size, Pageable pageable) throws SpotifyException;
    SpotifyResponse<GenreRest> getGenreById(Long id) throws SpotifyException;
    SpotifyResponse<List<SongRest>> getSongByGenreId(Long genreId) throws SpotifyException;
    SpotifyResponse<GenreRest> createGenre(GenreRest genre) throws SpotifyException;
    SpotifyResponse<GenreRest> updateGenre(GenreRest genre) throws SpotifyException;
    SpotifyResponse<GenreSongRest> updateSongByGenreId(Long genreId, Long songId) throws SpotifyException;
    void deleteGenre(Long id) throws SpotifyException;
    void deleteSongFromGenreById(Long genreId,Long songId) throws SpotifyException;
}
