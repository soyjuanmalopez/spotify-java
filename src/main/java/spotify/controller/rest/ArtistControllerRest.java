package spotify.controller.rest;

import org.springframework.data.domain.Pageable;
import spotify.controller.rest.model.AlbumRest;
import spotify.controller.rest.model.ArtistRest;
import spotify.controller.rest.model.D4iPageRest;
import spotify.controller.rest.model.SpotifyResponse;
import spotify.controller.rest.model.restArtists.PostArtistRest;
import spotify.exception.SpotifyException;

public interface ArtistControllerRest {

    SpotifyResponse<D4iPageRest<ArtistRest>> getAllArtists(int page, int size, Pageable pageable) throws SpotifyException;

    SpotifyResponse<ArtistRest> getArtistById(Long id) throws SpotifyException;

    SpotifyResponse<PostArtistRest> createArtist(PostArtistRest artist) throws SpotifyException;

    SpotifyResponse<PostArtistRest> updateArtist(PostArtistRest artist) throws SpotifyException;

    void deleteArtist(Long id) throws SpotifyException;

    SpotifyResponse<D4iPageRest<AlbumRest>> getAlbumsOfArtist(int page, int size, Pageable pageable, Long id) throws SpotifyException;
}
