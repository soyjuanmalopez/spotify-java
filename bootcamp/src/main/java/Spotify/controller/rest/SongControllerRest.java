package Spotify.controller.rest;

import Spotify.controller.rest.model.*;
import org.springframework.data.domain.Pageable;

import Spotify.exception.SpotifyException;

public interface SongControllerRest {

    SpotifyResponse<D4iPageRest<SongRest>> getAllSongs(int page, int size, Pageable pageable) throws SpotifyException;
    SpotifyResponse<SongRest> getSongById(int id) throws SpotifyException;
    SpotifyResponse<AlbumRest> getAlbumBySongId(int songId) throws SpotifyException;
    SpotifyResponse<PostSongRest> createSong(PostSongRest song) throws SpotifyException;
    SpotifyResponse<SongRest> updateSong(SongRest song) throws SpotifyException;
    void deleteSong(int id) throws SpotifyException;
    void deleteArtistFromSongById(int songId,int artistId) throws SpotifyException;
}
