package Spotify.controller.rest;

import Spotify.controller.rest.model.*;
import Spotify.controller.rest.model.restAlbums.SongRestAlbum;
import Spotify.exception.SpotifyException;
import Spotify.persistence.entity.AlbumEntity;
import org.springframework.data.domain.Pageable;

public interface AlbumControllerRest {

    SpotifyResponse<D4iPageRest<AlbumRest>> getAllAlbums(int page, int size, Pageable pageable) throws SpotifyException;

    SpotifyResponse<AlbumRest> getAlbumById(Long id) throws SpotifyException;

    SpotifyResponse<D4iPageRest<SongRestAlbum>> getSongsOfAlbum(int page, int size, Pageable pageable, Long id) throws SpotifyException;

    SpotifyResponse<D4iPageRest<ArtistRest>> getArtistsOfAlbum(int page, int size, Pageable pageable, Long id) throws SpotifyException;

    SpotifyResponse<AlbumRest> createAlbum(AlbumEntity album) throws SpotifyException;

    SpotifyResponse<AlbumRest> updateAlbum(AlbumEntity album) throws SpotifyException;

    void deleteAlbum(Long id) throws SpotifyException;

    SpotifyResponse<AlbumRest> deleteSongOfAlbum(Long albumId, int songId) throws SpotifyException;

    SpotifyResponse<AlbumRest> deleteArtistOfAlbum(Long albumId, Long artistId) throws SpotifyException;

    SpotifyResponse<AlbumRest> addSongOfAlbum(Long albumId, int songId) throws SpotifyException;

    SpotifyResponse<AlbumRest> addArtistToAlbum(Long albumId, Long artistId) throws SpotifyException;

}
