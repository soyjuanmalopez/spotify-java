package Spotify.controller.rest.impl;

import Spotify.controller.rest.model.ArtistRest;
import Spotify.controller.rest.model.D4iPageRest;
import Spotify.controller.rest.model.SpotifyResponse;
import Spotify.exception.SpotifyException;
import Spotify.mapper.ArtistMapper;
import Spotify.persistence.entity.ArtistEntity;
import Spotify.service.ArtistService;
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

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.any;

public class ArtistControllerRestImplTest {
    static final Long ID = 1L;
    static final ArtistEntity ARTIST_ENTITY = new ArtistEntity();
    static final ArtistRest ARTIST_REST = new ArtistRest();

    @Mock
    private ArtistService artistService;

    @Mock
    private ArtistMapper artistMapper;

    @InjectMocks
    private ArtistControllerRestImpl artistControllerRest;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
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
    }
}
