package Spotify.controller.rest;

import Spotify.controller.rest.model.AlbumRest;
import Spotify.controller.rest.model.ArtistRest;
import Spotify.controller.rest.model.D4iPageRest;
import Spotify.controller.rest.model.SpotifyResponse;
import Spotify.exception.SpotifyException;
import org.springframework.data.domain.Pageable;

public interface ArtistControllerRest {
    SpotifyResponse<D4iPageRest<ArtistRest>> getAllArtists(int page, int size, Pageable pageable) throws SpotifyException;
    SpotifyResponse<ArtistRest> getArtistById(Long id) throws SpotifyException;
    SpotifyResponse<ArtistRest> createArtist(ArtistRest artist) throws SpotifyException;
    SpotifyResponse<ArtistRest> updateArtist(ArtistRest artist) throws SpotifyException;
    void deleteArtist(Long id) throws SpotifyException;
    SpotifyResponse<D4iPageRest<AlbumRest>> getAlbumsOfArtist(int page, int size, Pageable pageable, Long id) throws SpotifyException;
}
