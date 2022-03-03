package Spotify.controller.rest.impl;

import Spotify.controller.rest.model.AlbumRest;
import Spotify.controller.rest.model.SpotifyResponse;
import Spotify.controller.rest.model.restAlbums.SongRestAlbum;
import Spotify.exception.SpotifyException;
import Spotify.persistence.entity.AlbumEntity;
import Spotify.service.impl.AlbumServiceImpl;
import Spotify.util.constant.CommonConstantsUtils;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/*@RunWith(MockitoJUnitRunner.class)*/
public class AlbumControllerRestImplTest {

    @Mock
    private AlbumServiceImpl albumService;

    @InjectMocks
    private AlbumControllerRestImpl albumControllerRest;

    private final static AlbumRest ALBUM_REST = new AlbumRest();
    private final static AlbumEntity ALBUM_ENTITY = new AlbumEntity();
    private final static List<SongRestAlbum> SONG_REST_ALBUMS_LIST = new ArrayList<>();
    private final static SongRestAlbum SONG_REST_ALBUM = new SongRestAlbum();
    private final static List<AlbumRest> ALBUM_REST_LIST = new ArrayList<>();

    private static final Pageable pageable = PageRequest.of(1,8);

    private static Page<AlbumRest> ALBUM_REST_PAGE;
    private static Page<SongRestAlbum> SONG_REST_PAGE;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        SONG_REST_ALBUMS_LIST.clear();

        ALBUM_REST.setId(1L);
        ALBUM_REST.setTitle("TestingAlbum");
        ALBUM_REST.setDuration(1.1);
        ALBUM_REST.setYearRelease(2022);

        ALBUM_ENTITY.setId(1L);
        ALBUM_ENTITY.setTitle("TestingAlbum");
        ALBUM_ENTITY.setDuration(1.1);
        ALBUM_ENTITY.setYearRelease(2022);

        SONG_REST_ALBUM.setId(1L);
        SONG_REST_ALBUM.setTitle("TestingSong");
        SONG_REST_ALBUM.setDuration(1.4);

        SONG_REST_ALBUMS_LIST.add(SONG_REST_ALBUM);

        ALBUM_REST.setSongs(SONG_REST_ALBUMS_LIST);

        ALBUM_REST_LIST.add(ALBUM_REST);


        ALBUM_REST_PAGE = new PageImpl<>(ALBUM_REST_LIST);
        SONG_REST_PAGE = new PageImpl<>(SONG_REST_ALBUMS_LIST);


    }

    @Test
    public void getAllAlbums() throws SpotifyException {
        when(albumService.getAllAlbums(Mockito.any(Pageable.class))).thenReturn(ALBUM_REST_PAGE);
        assertEquals(ALBUM_REST_PAGE.getContent(), Arrays.stream(albumControllerRest.getAllAlbums(1, 8, pageable).getData().getContent()).collect(Collectors.toList()));
    }

    @Test
    public void getAlbumById() throws SpotifyException {
        when(albumService.getAlbumById(Mockito.anyLong())).thenReturn(ALBUM_REST);
        assertEquals(ALBUM_REST, albumControllerRest.getAlbumById(1L).getData());
    }

    @Test
    public void getSongsOfAlbum() throws SpotifyException {
        when(albumService.getSongsOfAlbum(Mockito.any(Pageable.class), Mockito.anyLong())).thenReturn(SONG_REST_PAGE);
        assertEquals(SONG_REST_ALBUMS_LIST, Arrays.stream(albumControllerRest.getSongsOfAlbum(1, 8, pageable, 1L).getData().getContent()).collect(Collectors.toList()));
    }

    @Test
    public void getArtistsOfAlbum() throws SpotifyException {
    }

    @Test
    public void createAlbum() throws SpotifyException {
        AlbumEntity album = new AlbumEntity(-1L, "CreateEntityTest", 1.2, 2022, new ArrayList<>());
        AlbumRest albumRest = new AlbumRest(-1L, "CreateEntityTest", 1.2, 2022, new ArrayList<>());
        when(albumService.createAlbum(Mockito.any(AlbumEntity.class))).thenReturn(albumRest);
        assertEquals(albumControllerRest.createAlbum(album).getData(), albumRest);
    }

    @Test
    public void updateAlbum() throws SpotifyException {
        when(albumService.updateAlbum(Mockito.any(AlbumEntity.class), Mockito.anyLong())).thenReturn(ALBUM_REST);
        assertEquals(ALBUM_REST, albumControllerRest.updateAlbum(ALBUM_ENTITY).getData());
    }

    @Test
    public void deleteAlbum() throws SpotifyException {
        SpotifyResponse<Object> spotifyResponse = new SpotifyResponse<>(HttpStatus.OK.toString(),
                String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK);
        assertEquals(spotifyResponse, albumControllerRest.deleteAlbum(1L));
    }

    @Test
    public void deleteSongOfAlbum() throws SpotifyException {
        when(albumService.deleteSongOfAlbum(Mockito.anyLong(), Mockito.anyLong())).thenReturn(ALBUM_REST);
        assertEquals(ALBUM_REST, albumControllerRest.deleteSongOfAlbum(1L, 1L).getData());
    }

    @Test
    public void addSongOfAlbum() throws SpotifyException {
        when(albumService.addSongOfAlbum(Mockito.anyLong(), Mockito.anyLong())).thenReturn(ALBUM_REST);
        assertEquals(ALBUM_REST, albumControllerRest.addSongOfAlbum(1L, 1L).getData());
    }
}