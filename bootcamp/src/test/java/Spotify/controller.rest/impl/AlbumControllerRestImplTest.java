package Spotify.controller.rest.impl;

import Spotify.controller.rest.model.AlbumRest;
import Spotify.controller.rest.model.restAlbums.SongRestAlbum;
import Spotify.exception.SpotifyException;
import Spotify.service.impl.AlbumServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlbumControllerRestImplTest {

    @Mock
    private AlbumServiceImpl albumService;

    @InjectMocks
    private AlbumControllerRestImpl albumControllerRest = new AlbumControllerRestImpl(albumService);

    private final static AlbumRest ALBUM_REST = new AlbumRest();

    private final static List<SongRestAlbum> SONG_REST_ALBUMS_LIST = new ArrayList<>();
    private final static SongRestAlbum SONG_REST_ALBUM = new SongRestAlbum();

    private final static List<AlbumRest> ALBUM_REST_LIST = new ArrayList<>();

    private static Page<AlbumRest> ALBUM_REST_PAGE;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        SONG_REST_ALBUMS_LIST.clear();

        ALBUM_REST.setId(1L);
        ALBUM_REST.setTitle("TestingAlbum");
        ALBUM_REST.setDuration(1.1);
        ALBUM_REST.setYearRelease(2021);

        SONG_REST_ALBUM.setId(1);
        SONG_REST_ALBUM.setTitle("TestingSong");
        SONG_REST_ALBUM.setDuration(1.4);

        SONG_REST_ALBUMS_LIST.add(SONG_REST_ALBUM);

        ALBUM_REST.setSongs(SONG_REST_ALBUMS_LIST);

        ALBUM_REST_LIST.add(ALBUM_REST);

        ALBUM_REST_PAGE = new PageImpl<>(ALBUM_REST_LIST);

    }

    @Test
    void getAllAlbums() throws SpotifyException {
    }

    @Test
    void getAlbumById() throws SpotifyException {
    }

    @Test
    void getSongsOfAlbum() throws SpotifyException {
    }

    @Test
    void getArtistsOfAlbum() throws SpotifyException {
    }

    @Test
    void createAlbum() throws SpotifyException {
    }

    @Test
    void updateAlbum() throws SpotifyException {
    }

    @Test
    void deleteAlbum() throws SpotifyException {
    }

    @Test
    void deleteSongOfAlbum() throws SpotifyException {
    }

    @Test
    void addSongOfAlbum() throws SpotifyException {
    }
}