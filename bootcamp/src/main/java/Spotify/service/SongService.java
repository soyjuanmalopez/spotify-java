package Spotify.service;

import Spotify.controller.rest.model.AlbumRest;
import Spotify.controller.rest.model.restSongs.PostSongRest;
import Spotify.controller.rest.model.SongRest;
import Spotify.exception.SpotifyException;
import Spotify.persistence.entity.SongEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SongService {

    Page<SongRest> getAllSongs(Pageable pageable) throws SpotifyException;

    SongRest getSongById(Long id) throws SpotifyException;

    AlbumRest getAlbumBySongId(Long songId) throws SpotifyException;

    PostSongRest createSong(PostSongRest Song) throws SpotifyException;

    PostSongRest updateSong(PostSongRest postSongRest) throws SpotifyException;

    SongRest updateArtistBySongId(Long songId, Long artistId) throws SpotifyException;

    void deleteSong(Long id) throws SpotifyException;

    void deleteArtistFromSongById(Long songId, Long artistId) throws SpotifyException;



}
