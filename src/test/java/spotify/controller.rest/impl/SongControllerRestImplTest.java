package spotify.controller.rest.impl;


import org.assertj.core.api.Assertions;
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
import spotify.controller.rest.model.D4iPageRest;
import spotify.controller.rest.model.SongRest;
import spotify.controller.rest.model.SpotifyResponse;
import spotify.controller.rest.model.restSongs.PostSongRest;
import spotify.exception.SpotifyException;
import spotify.persistence.entity.ArtistEntity;
import spotify.persistence.entity.SongEntity;
import spotify.persistence.repository.ArtistRepository;
import spotify.persistence.repository.SongRepository;
import spotify.service.SongService;
import spotify.util.constant.RestConstantsUtils;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SongControllerRestImplTest {

    static final Long ID = 1L;
    static final SongEntity SONG_ENTITY = new SongEntity();
    static final Set<SongEntity> SONG_ENTITY_SET = new HashSet<>();
    static final List<ArtistEntity> ARTIST_ENTITY_LIST = new ArrayList<>();
    static final SongRest SONG_REST = new SongRest();
    static final PostSongRest POST_SONG_REST = new PostSongRest();
    static final AlbumRest ALBUM_REST = new AlbumRest();
    static final ArtistEntity ARTIST_ENTITY = new ArtistEntity();


    @Mock
    private SongRepository songRepository;

    @Mock
    private SongService songService;

    @Mock
    private ArtistRepository artistRepository;

    @InjectMocks
    private SongControllerRestImpl songControllerRestImpl;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        ARTIST_ENTITY_LIST.add(ARTIST_ENTITY);
        SONG_ENTITY_SET.add(SONG_ENTITY);
        ARTIST_ENTITY.setSongs(SONG_ENTITY_SET);
        SONG_ENTITY.setArtists(ARTIST_ENTITY_LIST);
        ALBUM_REST.setId(ID);
        SONG_ENTITY.setId(ID);
        SONG_REST.setId(ID);
        ARTIST_ENTITY.setId(ID);

        Mockito.when(songRepository.findById(anyLong())).thenReturn(Optional.of(SONG_ENTITY));
    }

    @Test
    public void getAllSongsTest() throws SpotifyException {
        int page = 0;
        int size = 2;

        Pageable pageable = PageRequest.of(0, 1);
        Page<SongEntity> songPage = new PageImpl<>(List.of(SONG_ENTITY), pageable, 0);
        Page<SongRest> songRestPage = new PageImpl<>(List.of(SONG_REST), pageable, 0);

        Mockito.when(songService.getAllSongs(any(Pageable.class))).thenReturn(songRestPage);
        SpotifyResponse<D4iPageRest<SongRest>> response = songControllerRestImpl.getAllSongs(0L, 2L, pageable);

        assertNotNull(response);
        assertEquals(RestConstantsUtils.OK, response.getMessage());
        assertNotNull(response.getData().getContent());

    }

    @Test
    public void getSongByIdTest() throws SpotifyException {

        Mockito.when(songService.getSongById(anyLong())).thenReturn(SONG_REST);
        SpotifyResponse<SongRest> response = songControllerRestImpl.getSongById(ID);

        assertNotNull(response);
        assertEquals(String.valueOf(HttpStatus.OK), response.getStatus());
        assertEquals(RestConstantsUtils.OK, response.getMessage());
        assertEquals(SONG_REST, response.getData());
    }

    @Test
    public void getAlbumBySongIdTest() throws SpotifyException {

        Mockito.when(songService.getAlbumBySongId(anyLong())).thenReturn(ALBUM_REST);
        SpotifyResponse<AlbumRest> response = songControllerRestImpl.getAlbumBySongId(ID);

        assertNotNull(response);
        assertEquals(String.valueOf(HttpStatus.OK), response.getStatus());
        assertEquals(RestConstantsUtils.OK, response.getMessage());
        assertEquals(ALBUM_REST, response.getData());
    }

    @Test
    public void createSongTest() throws SpotifyException {

        Mockito.when(songService.createSong(any(PostSongRest.class))).thenReturn(POST_SONG_REST);
        SpotifyResponse<PostSongRest> response = songControllerRestImpl.createSong(POST_SONG_REST);

        assertNotNull(response);
        assertEquals(String.valueOf(HttpStatus.OK), response.getStatus());
        assertEquals("200", response.getCode());
        assertEquals(RestConstantsUtils.OK, response.getMessage());
        Assertions.assertThat(response.getData()).isEqualTo(POST_SONG_REST);
    }

    @Test
    public void deleteSongTest() throws SpotifyException {
        songControllerRestImpl.deleteSong(ID);
        Mockito.verify(songService, Mockito.times(1)).deleteSong(Mockito.anyLong());
    }

    @Test
    public void deleteArtistFromSongById() throws SpotifyException {
        songControllerRestImpl.deleteArtistFromSongById(ID, ID);
        verify(songService, times(1)).deleteArtistFromSongById(ID, ID);
    }

    @Test
    public void updateSongTest() throws SpotifyException {


        SpotifyResponse<PostSongRest> response = songControllerRestImpl.updateSong(POST_SONG_REST);

        assertNotNull(response);
        assertEquals(String.valueOf(HttpStatus.OK), response.getStatus());
        assertEquals("200", response.getCode());
        assertEquals(RestConstantsUtils.OK, response.getMessage());
    }

    @Test
    public void updateArtistBySongIdTest() throws SpotifyException {

        SpotifyResponse<SongRest> response = songControllerRestImpl.updateArtistBySongId(ID, ID);
        assertNotNull(response);
        assertEquals(String.valueOf(HttpStatus.OK), response.getStatus());
        assertEquals("200", response.getCode());
        assertEquals(RestConstantsUtils.OK, response.getMessage());
    }
}
