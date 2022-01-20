package Spotify.service;

import Spotify.controller.rest.model.SongRest;
import Spotify.exception.SpotifyException;
import Spotify.persistence.entity.SongEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SongService {

    Page<SongRest> getAllSongs(Pageable pageable)
	    throws SpotifyException;

    SongRest createSong(SongEntity Song) throws SpotifyException;

    SongRest getSongById(Long id) throws SpotifyException;

    SongRest updateSong(SongEntity SongDetails) throws SpotifyException;

    void deleteSong(Long id) throws SpotifyException;

}
