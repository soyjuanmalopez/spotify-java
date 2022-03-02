package Spotify.service;

import Spotify.controller.rest.model.AlbumRest;
import Spotify.controller.rest.model.ArtistRest;
import Spotify.controller.rest.model.SpotifyResponse;
import Spotify.controller.rest.model.restAlbums.SongRestAlbum;
import Spotify.exception.SpotifyException;
import Spotify.persistence.entity.AlbumEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlbumService {

    Page<AlbumRest> getAllAlbums(Pageable pageable) throws SpotifyException;

    AlbumRest getAlbumById(Long id) throws SpotifyException;

    Page<SongRestAlbum> getSongsOfAlbum(Pageable pageable, Long id) throws SpotifyException;

    Page<ArtistRest> getArtistsOfAlbum(Pageable pageable, Long id) throws SpotifyException;

    AlbumRest createAlbum(AlbumEntity album) throws SpotifyException;

    AlbumRest updateAlbum(AlbumEntity album, Long id) throws SpotifyException;

    void deleteAlbum(Long id) throws SpotifyException;

    AlbumRest deleteSongOfAlbum(Long albumId, int songId) throws SpotifyException;

    AlbumRest deleteArtistOfAlbum(Long albumId, Long artistId) throws SpotifyException;

    AlbumRest addSongOfAlbum(Long albumId, int songId) throws SpotifyException;

    AlbumRest addArtistToAlbum(Long albumId, Long artistId) throws SpotifyException;


}
