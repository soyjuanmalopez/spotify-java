package Spotify.service;

import Spotify.controller.rest.model.AlbumRest;
import Spotify.controller.rest.model.PostSongRest;
import Spotify.controller.rest.model.SongRest;
import Spotify.exception.SpotifyException;
import Spotify.persistence.entity.SongEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SongService {

    Page<SongRest> getAllSongs(Pageable pageable) throws SpotifyException;

    SongRest getSongById(int id) throws SpotifyException;

    PostSongRest createSong(PostSongRest Song) throws SpotifyException;

    SongRest updateSong(SongEntity SongDetails) throws SpotifyException;

    void deleteSong(int id) throws SpotifyException;

}
