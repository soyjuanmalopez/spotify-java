package Spotify.controller.rest;

import Spotify.controller.rest.model.D4iPageRest;
import Spotify.controller.rest.model.PostSongRest;
import org.springframework.data.domain.Pageable;

import Spotify.controller.rest.model.SpotifyResponse;
import Spotify.controller.rest.model.SongRest;
import Spotify.exception.SpotifyException;

public interface SongControllerRest {

    SpotifyResponse<D4iPageRest<SongRest>> getAllSongs(int page, int size, Pageable pageable) throws SpotifyException;
    SpotifyResponse<SongRest> getSongById(int id) throws SpotifyException;
    SpotifyResponse<PostSongRest> createSong(PostSongRest song) throws SpotifyException;
    SpotifyResponse<SongRest> updateSong(SongRest song) throws SpotifyException;
    void deleteSong(int id) throws SpotifyException;
}
