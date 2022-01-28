package Spotify.controller.rest;

import Spotify.controller.rest.model.*;
import Spotify.controller.rest.model.restAlbums.SongRestAlbum;
import Spotify.exception.SpotifyException;
import Spotify.persistence.entity.AlbumEntity;
import org.springframework.data.domain.Pageable;

public interface AlbumControllerRest {

    SpotifyResponse<D4iPageRest<AlbumRest>> getAllAlbums(int page, int size, Pageable pageable) throws SpotifyException;

    SpotifyResponse<AlbumRest> getAlbumById(int id) throws SpotifyException;

    SpotifyResponse<D4iPageRest<SongRestAlbum>> getSongsOfAlbum(int page, int size, Pageable pageable, int id) throws SpotifyException;

    SpotifyResponse<D4iPageRest<ArtistRest>> getArtistsOfAlbum(int page, int size, Pageable pageable, int id) throws SpotifyException;

    SpotifyResponse<AlbumRest> createAlbum(AlbumEntity album) throws SpotifyException;

    SpotifyResponse<AlbumRest> updateAlbum(AlbumEntity album, int id) throws SpotifyException;

    void deleteAlbum(int id) throws SpotifyException;

}
