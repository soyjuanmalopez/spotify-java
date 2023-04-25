package spotify.controller.rest;

import org.springframework.data.domain.Pageable;
import spotify.controller.rest.model.D4iPageRest;
import spotify.controller.rest.model.GenereRest;
import spotify.controller.rest.model.SongRest;
import spotify.controller.rest.model.SpotifyResponse;
import spotify.controller.rest.model.restSongs.GenereSongRest;
import spotify.exception.SpotifyException;

import java.util.List;


public interface GenereControllerRest {

    SpotifyResponse<D4iPageRest<GenereRest>> getAllGeneres(Long page, Long size, Pageable pageable) throws SpotifyException;

    SpotifyResponse<GenereRest> getGenereById(Long id) throws SpotifyException;

    SpotifyResponse<List<SongRest>> getSongByGenereId(Long genereId) throws SpotifyException;

    SpotifyResponse<GenereRest> createGenere(GenereRest genere) throws SpotifyException;

    SpotifyResponse<GenereRest> updateGenere(GenereRest genere) throws SpotifyException;

    SpotifyResponse<GenereSongRest> updateSongByGenereId(Long genereId, Long songId) throws SpotifyException;

    void deleteGenere(Long id) throws SpotifyException;

    void deleteSongFromGenereById(Long genereId, Long songId) throws SpotifyException;
}
