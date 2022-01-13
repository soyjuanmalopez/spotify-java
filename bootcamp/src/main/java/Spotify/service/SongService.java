package Spotify.service;

import Spotify.exception.SpotifyException;
import Spotify.service.model.SongDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SongService {

    Page<SongDto> getAllSongs(Pageable pageable)
	    throws SpotifyException;

    SongDto createSong(SongDto Song) throws SpotifyException;

    SongDto getSongById(Long id) throws SpotifyException;

    SongDto updateSong(SongDto SongDetails) throws SpotifyException;

    void deleteSong(Long id) throws SpotifyException;

}
