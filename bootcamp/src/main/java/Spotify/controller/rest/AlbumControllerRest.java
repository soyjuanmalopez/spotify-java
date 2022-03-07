package Spotify.controller.rest;

import Spotify.controller.rest.model.*;
import Spotify.controller.rest.model.restAlbums.AlbumRestPost;
import Spotify.controller.rest.model.restAlbums.ArtistRestAlbum;
import Spotify.controller.rest.model.restAlbums.SongRestAlbum;
import Spotify.exception.SpotifyException;
import org.springframework.data.domain.Pageable;

public interface AlbumControllerRest {

    SpotifyResponse<D4iPageRest<AlbumRest>> getAllAlbums(int page, int size, Pageable pageable) throws SpotifyException;

    SpotifyResponse<AlbumRest> getAlbumById(Long id) throws SpotifyException;

    SpotifyResponse<D4iPageRest<SongRestAlbum>> getSongsOfAlbum(int page, int size, Pageable pageable, Long id) throws SpotifyException;

    SpotifyResponse<D4iPageRest<ArtistRestAlbum>> getArtistsOfAlbum(int page, int size, Pageable pageable, Long id) throws SpotifyException;

    SpotifyResponse<AlbumRestPost> createAlbum(AlbumRestPost album) throws SpotifyException;

    SpotifyResponse<AlbumRestPost> updateAlbum(AlbumRestPost album) throws SpotifyException;

    SpotifyResponse<Object> deleteAlbum(Long id) throws SpotifyException;

    SpotifyResponse<AlbumRest> deleteSongOfAlbum(Long albumId, Long songId) throws SpotifyException;

    SpotifyResponse<AlbumRest> deleteArtistOfAlbum(Long albumId, Long artistId) throws SpotifyException;

    SpotifyResponse<AlbumRest> addSongOfAlbum(Long albumId, Long songId) throws SpotifyException;

    SpotifyResponse<AlbumRest> addArtistToAlbum(Long albumId, Long artistId) throws SpotifyException;

}
