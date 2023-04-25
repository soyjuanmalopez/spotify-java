package spotify.controller.rest;

import org.springframework.data.domain.Pageable;
import spotify.controller.rest.model.AlbumRest;
import spotify.controller.rest.model.D4iPageRest;
import spotify.controller.rest.model.SpotifyResponse;
import spotify.controller.rest.model.restAlbums.AlbumRestPost;
import spotify.controller.rest.model.restAlbums.ArtistRestAlbum;
import spotify.controller.rest.model.restAlbums.SongRestAlbum;
import spotify.exception.SpotifyException;

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
