package spotify.controller.rest.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import spotify.controller.rest.model.AlbumRest;
import spotify.controller.rest.model.SpotifyResponse;
import spotify.controller.rest.model.restAlbums.AlbumRestPost;
import spotify.controller.rest.model.restAlbums.ArtistRestAlbum;
import spotify.controller.rest.model.restAlbums.SongRestAlbum;
import spotify.exception.SpotifyException;
import spotify.persistence.entity.AlbumEntity;
import spotify.service.impl.AlbumServiceImpl;
import spotify.util.constant.CommonConstantsUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AlbumControllerRestImplTest {

    private final static AlbumRest ALBUM_REST = new AlbumRest();
    private final static AlbumEntity ALBUM_ENTITY = new AlbumEntity();
    private final static List<SongRestAlbum> SONG_REST_ALBUMS_LIST = new ArrayList<>();
    private final static SongRestAlbum SONG_REST_ALBUM = new SongRestAlbum();
    private final static List<ArtistRestAlbum> ARTIST_REST_ALBUM_LIST = new ArrayList<>();
    private final static ArtistRestAlbum ARTIST_REST_ALBUM = new ArtistRestAlbum();
    private final static List<AlbumRest> ALBUM_REST_LIST = new ArrayList<>();
    private static final Pageable pageable = PageRequest.of(1, 8);
    private static Page<AlbumRest> ALBUM_REST_PAGE;
    private static Page<SongRestAlbum> SONG_REST_PAGE;
    private static Page<ArtistRestAlbum> ARTIST_REST_PAGE;

    @Mock
    private AlbumServiceImpl albumService;

    @InjectMocks
    private AlbumControllerRestImpl albumControllerRest;

    @Before
    public void init() {
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

        ARTIST_REST_ALBUM.setId(1L);
        ARTIST_REST_ALBUM.setName("TestArtist");
        ARTIST_REST_ALBUM.setDescription("TestDescription");

        SONG_REST_ALBUMS_LIST.add(SONG_REST_ALBUM);
        ARTIST_REST_ALBUM_LIST.add(ARTIST_REST_ALBUM);

        ALBUM_REST.setSongs(SONG_REST_ALBUMS_LIST);
        ALBUM_REST.setArtists(ARTIST_REST_ALBUM_LIST);

        ALBUM_REST_LIST.add(ALBUM_REST);

        ALBUM_REST_PAGE = new PageImpl<>(ALBUM_REST_LIST);
        SONG_REST_PAGE = new PageImpl<>(SONG_REST_ALBUMS_LIST);
        ARTIST_REST_PAGE = new PageImpl<>(ARTIST_REST_ALBUM_LIST);
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
        when(albumService.getArtistsOfAlbum(Mockito.any(Pageable.class), Mockito.anyLong())).thenReturn(ARTIST_REST_PAGE);
        assertEquals(ARTIST_REST_ALBUM_LIST, Arrays.stream(albumControllerRest.getArtistsOfAlbum(1, 8, pageable, 1L).getData().getContent()).collect(Collectors.toList()));
    }

    @Test
    public void createAlbum() throws SpotifyException {
        AlbumRestPost albumRest = new AlbumRestPost(-1L, "CreateEntityTest", 1.2, 2022);
        when(albumService.createAlbum(Mockito.any(AlbumRestPost.class))).thenReturn(albumRest);
        assertEquals(albumControllerRest.createAlbum(albumRest).getData(), albumRest);
    }

    @Test
    public void updateAlbum() throws SpotifyException {
        AlbumRestPost albumRest = new AlbumRestPost(-1L, "CreateEntityTest", 1.2, 2022);
        when(albumService.updateAlbum(Mockito.any(AlbumRestPost.class), Mockito.anyLong())).thenReturn(albumRest);
        assertEquals(albumRest, albumControllerRest.updateAlbum(albumRest).getData());
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

    @Test
    public void deleteArtistOfAlbum() throws SpotifyException {
        when(albumService.deleteArtistOfAlbum(Mockito.anyLong(), Mockito.anyLong())).thenReturn(ALBUM_REST);
        assertEquals(ALBUM_REST, albumControllerRest.deleteArtistOfAlbum(1L, 1L).getData());
    }

    @Test
    public void addArtistOfAlbum() throws SpotifyException {
        when(albumService.addArtistToAlbum(Mockito.anyLong(), Mockito.anyLong())).thenReturn(ALBUM_REST);
        assertEquals(ALBUM_REST, albumControllerRest.addArtistToAlbum(1L, 1L).getData());
    }

}