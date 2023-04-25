package spotify.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spotify.controller.rest.model.GenereRest;
import spotify.controller.rest.model.SongRest;
import spotify.controller.rest.model.restSongs.GenereSongRest;
import spotify.exception.SpotifyException;

import java.util.List;

public interface GenereService {

    Page<GenereRest> getAllGeneres(Pageable pageable) throws SpotifyException;

    GenereRest getGenereById(Long id) throws SpotifyException;

    List<SongRest> getSongsByGenereId(Long genereId) throws SpotifyException;

    GenereRest createGenere(GenereRest genere) throws SpotifyException;

    GenereRest updateGenere(GenereRest genere) throws SpotifyException;

    GenereSongRest updateSongByGenereId(Long genereId, Long songId) throws SpotifyException;

    void deleteGenere(Long id) throws SpotifyException;

    void deleteSongFromGenereById(Long genereId, Long songId) throws SpotifyException;

}
