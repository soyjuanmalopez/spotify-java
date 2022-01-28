package Spotify.service;

import Spotify.controller.rest.model.AlbumRest;
import Spotify.controller.rest.model.ArtistRest;
import Spotify.controller.rest.model.restAlbums.SongRestAlbum;
import Spotify.exception.SpotifyException;
import Spotify.persistence.entity.AlbumEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlbumService {

    Page<AlbumRest> getAllAlbums(Pageable pageable) throws SpotifyException;

    AlbumRest getAlbumById(int id) throws SpotifyException;

    Page<SongRestAlbum> getSongsOfAlbum(Pageable pageable, int id) throws SpotifyException;

    Page<ArtistRest> getArtistsOfAlbum(Pageable pageable, int id) throws SpotifyException;

    AlbumRest createAlbum(AlbumEntity album) throws SpotifyException;

    AlbumRest updateAlbum(AlbumEntity album, int id) throws SpotifyException;

    void deleteAlbum(int id) throws SpotifyException;


}
