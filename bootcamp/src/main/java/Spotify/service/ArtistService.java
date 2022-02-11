package Spotify.service;

import Spotify.controller.rest.model.AlbumRest;
import Spotify.controller.rest.model.ArtistRest;
import Spotify.exception.SpotifyException;
import Spotify.persistence.entity.ArtistEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArtistService {

    Page<ArtistRest> getAllArtists(Pageable pageable)
        throws SpotifyException;

    ArtistRest getArtistById(Long id) throws SpotifyException;

    ArtistRest createArtist(ArtistEntity artist) throws SpotifyException;

    ArtistRest updateArtist(ArtistEntity artist) throws SpotifyException;

    void deleteArtist(Long id) throws SpotifyException;

    Page<AlbumRest> getAlbumsOfArtist (Pageable pageable, Long id) throws SpotifyException;

}
