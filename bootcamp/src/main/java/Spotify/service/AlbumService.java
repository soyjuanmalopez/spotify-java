package Spotify.service;

import Spotify.controller.rest.model.AlbumRest;
import Spotify.controller.rest.model.restAlbums.AlbumRestPost;
import Spotify.controller.rest.model.restAlbums.ArtistRestAlbum;
import Spotify.controller.rest.model.restAlbums.SongRestAlbum;
import Spotify.exception.SpotifyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlbumService {

    Page<AlbumRest> getAllAlbums(Pageable pageable) throws SpotifyException;

    AlbumRest getAlbumById(Long id) throws SpotifyException;

    Page<SongRestAlbum> getSongsOfAlbum(Pageable pageable, Long id) throws SpotifyException;

    Page<ArtistRestAlbum> getArtistsOfAlbum(Pageable pageable, Long id) throws SpotifyException;

    AlbumRestPost createAlbum(AlbumRestPost album) throws SpotifyException;

    AlbumRestPost updateAlbum(AlbumRestPost album, Long id) throws SpotifyException;

    void deleteAlbum(Long id) throws SpotifyException;

    AlbumRest deleteSongOfAlbum(Long albumId, Long songId) throws SpotifyException;

    AlbumRest deleteArtistOfAlbum(Long albumId, Long artistId) throws SpotifyException;

    AlbumRest addSongOfAlbum(Long albumId, Long songId) throws SpotifyException;

    AlbumRest addArtistToAlbum(Long albumId, Long artistId) throws SpotifyException;


}
