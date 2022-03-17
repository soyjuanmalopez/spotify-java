package Spotify.service;

import Spotify.controller.rest.model.AlbumRest;
import Spotify.controller.rest.model.ArtistRest;
import Spotify.controller.rest.model.restArtists.PostArtistRest;
import Spotify.exception.SpotifyException;
import Spotify.persistence.entity.ArtistEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArtistService {

    Page<ArtistRest> getAllArtists(Pageable pageable) throws SpotifyException;

    ArtistRest getArtistById(Long id) throws SpotifyException;

    PostArtistRest createArtist(PostArtistRest artist) throws SpotifyException;

    PostArtistRest updateArtist(PostArtistRest artist) throws SpotifyException;

    void deleteArtist(Long id) throws SpotifyException;

    Page<AlbumRest> getAlbumsOfArtist(Pageable pageable, Long id) throws SpotifyException;

}
