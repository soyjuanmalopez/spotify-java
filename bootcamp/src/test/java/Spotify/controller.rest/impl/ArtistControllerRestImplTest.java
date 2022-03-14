package Spotify.controller.rest.impl;

import Spotify.controller.rest.model.AlbumRest;
import Spotify.controller.rest.model.ArtistRest;
import Spotify.controller.rest.model.D4iPageRest;
import Spotify.controller.rest.model.SpotifyResponse;
import Spotify.controller.rest.model.restArtists.PostArtistRest;
import Spotify.exception.SpotifyException;
import Spotify.mapper.ArtistMapper;
import Spotify.mapper.PostArtistMapper;
import Spotify.persistence.entity.ArtistEntity;
import Spotify.service.ArtistService;
import Spotify.util.constant.RestConstantsUtils;
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

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.any;

public class ArtistControllerRestImplTest {
    static final Long ID = 1L;
    static final ArtistEntity ARTIST_ENTITY = new ArtistEntity();
    static final ArtistRest ARTIST_REST = new ArtistRest();
    static final PostArtistRest POST_ARTIST_REST = new PostArtistRest();

    @Mock
    private ArtistService artistService;

    @Mock
    private ArtistMapper artistMapper;

    @Mock
    private PostArtistMapper postartistMapper;

    @InjectMocks
    private ArtistControllerRestImpl artistControllerRest;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        POST_ARTIST_REST.setId(1L);
        POST_ARTIST_REST.setDescription("desc");
        POST_ARTIST_REST.setName("name");
    }

    @Test
    public void getAllArtistsTest() throws SpotifyException {
        //given
        int page = 0;
        int size = 1;
        Pageable pageable = PageRequest.of(page,size);
        Page<ArtistRest> artistsPagedModel = new PageImpl<>(Collections.singletonList(new ArtistRest()));
        Mockito.when(artistService.getAllArtists(any(Pageable.class))).thenReturn(artistsPagedModel);
        //when
        SpotifyResponse<D4iPageRest<ArtistRest>> response = artistControllerRest.getAllArtists(page,size,pageable);
        //then
        assertNotNull(response);
        assertEquals(RestConstantsUtils.OK, response.getMessage());
        assertNotNull(response.getData().getContent());
    }

    @Test
    public void getArtistByIdTest() throws SpotifyException {
        Mockito.when(artistService.getArtistById(anyLong())).thenReturn(ARTIST_REST);

        SpotifyResponse<ArtistRest> response = artistControllerRest.getArtistById(ID);

        assertNotNull(response);
        assertEquals(String.valueOf(HttpStatus.OK), response.getStatus());
        assertEquals(RestConstantsUtils.OK, response.getMessage());
        assertEquals(ARTIST_REST,response.getData());
    }

    @Test
    public void createArtistTest() throws SpotifyException{
        Mockito.when(artistService.createArtist(any(PostArtistRest.class))).thenReturn(POST_ARTIST_REST);
        Mockito.when(postartistMapper.mapToEntity(any(PostArtistRest.class))).thenReturn(ARTIST_ENTITY);

        SpotifyResponse<PostArtistRest> response = artistControllerRest.createArtist(POST_ARTIST_REST);

        assertNotNull(response);
        assertEquals(String.valueOf(HttpStatus.OK), response.getStatus());
        assertEquals("200", response.getCode());
        assertEquals(RestConstantsUtils.OK, response.getMessage());
        assertEquals(POST_ARTIST_REST, response.getData());
    }

    @Test
    public void updateArtistTest() throws SpotifyException{
        ArtistEntity artistEntity2 = new ArtistEntity();
        artistEntity2.setId(2L);

        Mockito.when(postartistMapper.mapToEntity(any(PostArtistRest.class))).thenReturn(artistEntity2);
        Mockito.when(artistService.updateArtist(any(PostArtistRest.class))).thenReturn(POST_ARTIST_REST);

        SpotifyResponse<PostArtistRest> response = artistControllerRest.updateArtist(POST_ARTIST_REST);

        assertNotNull(response);
        assertEquals(String.valueOf(HttpStatus.OK), response.getStatus());
        assertEquals("200", response.getCode());
        assertEquals(RestConstantsUtils.OK, response.getMessage());
        assertEquals(POST_ARTIST_REST, response.getData());
    }

    @Test
    public void deleteArtistTest() throws SpotifyException{
        artistControllerRest.deleteArtist(ID);

        Mockito.verify(artistService, Mockito.times(1)).deleteArtist(Mockito.anyLong());
    }

    @Test
    public void getAlbumsOfArtistTest() throws SpotifyException{
        //given
        int page = 0;
        int size = 1;
        Pageable pageable = PageRequest.of(page,size);
        Page<AlbumRest> albumsPagedModel = new PageImpl<>(Collections.singletonList(new AlbumRest()));
        Mockito.when(artistService.getAlbumsOfArtist(any(Pageable.class),anyLong())).thenReturn(albumsPagedModel);
        //When
        SpotifyResponse<D4iPageRest<AlbumRest>> response = artistControllerRest.getAlbumsOfArtist(page,size,pageable,ID);
        //Then
        assertNotNull(response);
        assertEquals(RestConstantsUtils.OK, response.getMessage());
        assertNotNull(response.getData().getContent());
    }
}
