package Spotify.controller.rest;

import Spotify.controller.rest.model.D4iPageRest;
import org.springframework.data.domain.Pageable;

import Spotify.controller.rest.model.SpotifyResponse;
import Spotify.controller.rest.model.SongRest;
import Spotify.exception.SpotifyException;

public interface SongControllerRest {

    SpotifyResponse<D4iPageRest<SongRest>> getAllSongs(int page, int size, Pageable pageable) throws SpotifyException;

    /*SpotifyResponse<SongRest> createSong(SongRest song) throws SpotifyException;

    SpotifyResponse<SongRest> getSongById(Long id) throws SpotifyException;

    SpotifyResponse<SongRest> updateSong(SongRest song) throws SpotifyException;

    void deleteSong(Long id) throws SpotifyException;*/
}
