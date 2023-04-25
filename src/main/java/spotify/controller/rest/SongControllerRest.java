package spotify.controller.rest;

import org.springframework.data.domain.Pageable;
import spotify.controller.rest.model.AlbumRest;
import spotify.controller.rest.model.D4iPageRest;
import spotify.controller.rest.model.SongRest;
import spotify.controller.rest.model.SpotifyResponse;
import spotify.controller.rest.model.restSongs.PostSongRest;
import spotify.exception.SpotifyException;

public interface SongControllerRest {

    SpotifyResponse<D4iPageRest<SongRest>> getAllSongs(Long page, Long size, Pageable pageable) throws SpotifyException;

    SpotifyResponse<SongRest> getSongById(Long id) throws SpotifyException;

    SpotifyResponse<AlbumRest> getAlbumBySongId(Long songId) throws SpotifyException;

    SpotifyResponse<PostSongRest> createSong(PostSongRest song) throws SpotifyException;

    SpotifyResponse<PostSongRest> updateSong(PostSongRest song) throws SpotifyException;

    SpotifyResponse<SongRest> updateArtistBySongId(Long songId, Long artistId) throws SpotifyException;

    void deleteSong(Long id) throws SpotifyException;

    void deleteArtistFromSongById(Long songId, Long artistId) throws SpotifyException;
}
