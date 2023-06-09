package spotify.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spotify.controller.rest.model.AlbumRest;
import spotify.controller.rest.model.restAlbums.AlbumRestPost;
import spotify.controller.rest.model.restAlbums.ArtistRestAlbum;
import spotify.controller.rest.model.restAlbums.SongRestAlbum;
import spotify.exception.SpotifyException;

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
