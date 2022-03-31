package spotify.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spotify.controller.rest.model.GenreRest;
import spotify.controller.rest.model.SongRest;
import spotify.controller.rest.model.restSongs.GenreSongRest;
import spotify.exception.SpotifyException;

import java.util.List;

public interface GenreService {

    Page<GenreRest> getAllGenres(Pageable pageable) throws SpotifyException;

    GenreRest getGenreById(Long id) throws SpotifyException;

    List<SongRest> getSongsByGenreId(Long genreId) throws SpotifyException;

    GenreRest createGenre(GenreRest genre) throws SpotifyException;

    GenreRest updateGenre(GenreRest genre) throws SpotifyException;

    GenreSongRest updateSongByGenreId(Long genreId, Long songId) throws SpotifyException;

    void deleteGenre(Long id) throws SpotifyException;

    void deleteSongFromGenreById(Long genreId, Long songId) throws SpotifyException;

}
