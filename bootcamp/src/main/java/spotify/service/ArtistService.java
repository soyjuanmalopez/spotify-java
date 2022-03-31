package spotify.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spotify.controller.rest.model.AlbumRest;
import spotify.controller.rest.model.ArtistRest;
import spotify.controller.rest.model.restArtists.PostArtistRest;
import spotify.exception.SpotifyException;

public interface ArtistService {

    Page<ArtistRest> getAllArtists(Pageable pageable) throws SpotifyException;

    ArtistRest getArtistById(Long id) throws SpotifyException;

    PostArtistRest createArtist(PostArtistRest artist) throws SpotifyException;

    PostArtistRest updateArtist(PostArtistRest artist) throws SpotifyException;

    void deleteArtist(Long id) throws SpotifyException;

    Page<AlbumRest> getAlbumsOfArtist(Pageable pageable, Long id) throws SpotifyException;

}
