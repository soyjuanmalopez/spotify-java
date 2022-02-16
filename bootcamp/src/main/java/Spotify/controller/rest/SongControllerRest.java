package Spotify.controller.rest;

import Spotify.controller.rest.model.*;
import org.springframework.data.domain.Pageable;

import Spotify.exception.SpotifyException;

public interface SongControllerRest {

    SpotifyResponse<D4iPageRest<SongRest>> getAllSongs(Long page, Long size, Pageable pageable) throws SpotifyException;
    SpotifyResponse<SongRest> getSongById(Long id) throws SpotifyException;
    SpotifyResponse<AlbumRest> getAlbumBySongId(Long songId) throws SpotifyException;
    SpotifyResponse<PostSongRest> createSong(PostSongRest song) throws SpotifyException;
    SpotifyResponse<PostSongRest> updateSong(PostSongRest song) throws SpotifyException;
    SpotifyResponse<SongRest> updateArtistBySongId(Long songId, Long artistId) throws SpotifyException;
    void deleteSong(Long id) throws SpotifyException;
    void deleteArtistFromSongById(Long songId,Long artistId) throws SpotifyException;
}
