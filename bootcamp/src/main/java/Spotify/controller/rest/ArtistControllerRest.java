package Spotify.controller.rest;

import Spotify.controller.rest.model.AlbumRest;
import Spotify.controller.rest.model.ArtistRest;
import Spotify.controller.rest.model.D4iPageRest;
import Spotify.controller.rest.model.SpotifyResponse;
import Spotify.controller.rest.model.restArtists.PostArtistRest;
import Spotify.exception.SpotifyException;
import org.springframework.data.domain.Pageable;

public interface ArtistControllerRest {
    SpotifyResponse<D4iPageRest<ArtistRest>> getAllArtists(int page, int size, Pageable pageable) throws SpotifyException;
    SpotifyResponse<ArtistRest> getArtistById(Long id) throws SpotifyException;
    SpotifyResponse<PostArtistRest> createArtist(PostArtistRest artist) throws SpotifyException;
    SpotifyResponse<PostArtistRest> updateArtist(PostArtistRest artist) throws SpotifyException;
    void deleteArtist(Long id) throws SpotifyException;
    SpotifyResponse<D4iPageRest<AlbumRest>> getAlbumsOfArtist(int page, int size, Pageable pageable, Long id) throws SpotifyException;
}
