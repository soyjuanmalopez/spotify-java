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
import spotify.controller.rest.model.D4iPageRest;
import spotify.controller.rest.model.GenereRest;
import spotify.controller.rest.model.SongRest;
import spotify.controller.rest.model.SpotifyResponse;
import spotify.controller.rest.model.restSongs.GenereSongRest;
import spotify.exception.SpotifyException;
import spotify.mapper.GenereMapper;
import spotify.persistence.entity.GenereEntity;
import spotify.persistence.entity.SongEntity;
import spotify.persistence.repository.GenereRepository;
import spotify.service.impl.GenereServiceImpl;
import spotify.util.constant.RestConstantsUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

public class GenereControllerRestImplTest {

    Long SONG_ID = 2L;
    SongRest SONG_REST = new SongRest();
    SongEntity SONG_ENTITY = new SongEntity();
    Set<SongEntity> SONG_HASHSET = new HashSet<>();
    Long GENRE_ID = 1L;
    String NAME = "Test name";
    GenereRest GENRE_REST = new GenereRest(GENRE_ID, NAME);
    GenereEntity GENRE_ENTITY;


    @Mock
    GenereRepository genereRepository;

    @Mock
    GenereMapper genereMapper;

    @Mock
    GenereServiceImpl genereService;

    @InjectMocks
    GenereControllerRestImpl genereControllerRest;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        SONG_HASHSET.add(SONG_ENTITY);
        GENRE_ENTITY = new GenereEntity(GENRE_ID, NAME, SONG_HASHSET);
        SONG_ENTITY.setId(SONG_ID);
        SONG_REST.setId(SONG_ID);
        Set<GenereEntity> GENREENTITY_HASHSET = new HashSet<>();
        GENREENTITY_HASHSET.add(GENRE_ENTITY);
        Set<GenereRest> GENREREST_HASHSET = new HashSet<>();
        GENREREST_HASHSET.add(GENRE_REST);
        SONG_ENTITY.setGeneres(GENREENTITY_HASHSET);
        SONG_REST.setGeneres(GENREREST_HASHSET);

        when(genereRepository.findById(anyLong())).thenReturn(Optional.of(GENRE_ENTITY));
        when(genereMapper.mapToEntity(any(GenereRest.class))).thenReturn(GENRE_ENTITY);
        when(genereMapper.mapToRest(any(GenereEntity.class))).thenReturn(GENRE_REST);
    }

    @Test
    public void getAllGeneresTest() throws SpotifyException {


        Pageable pageable = PageRequest.of(0, 1);
        Page<GenereRest> genereRestPage = new PageImpl<>(List.of(GENRE_REST));

        Mockito.when(genereService.getAllGeneres(any(Pageable.class))).thenReturn(genereRestPage);
        SpotifyResponse<D4iPageRest<GenereRest>> response = genereControllerRest.getAllGeneres(0L, 2L, pageable);


        assertNotNull(response);
        assertEquals(RestConstantsUtils.OK, response.getMessage());
        assertNotNull(response.getData().getContent());
    }

    @Test
    public void getGenereByIdTest() throws SpotifyException {

        when(genereService.getGenereById(anyLong())).thenReturn(GENRE_REST);
        SpotifyResponse<GenereRest> response = genereControllerRest.getGenereById(GENRE_ID);

        assertNotNull(response);
        assertEquals(String.valueOf(HttpStatus.OK), response.getStatus());
        assertEquals(RestConstantsUtils.OK, response.getMessage());
        assertEquals(GENRE_REST, response.getData());
    }

    @Test
    public void getSongByGenereIdTest() throws SpotifyException {

        Mockito.when(genereService.getSongsByGenereId(anyLong())).thenReturn(List.of(SONG_REST));
        SpotifyResponse<List<SongRest>> response = genereControllerRest.getSongByGenereId(GENRE_ID);

        assertNotNull(response);
        assertEquals(String.valueOf(HttpStatus.OK), response.getStatus());
        assertEquals(RestConstantsUtils.OK, response.getMessage());
        assertEquals(List.of(SONG_REST), response.getData());
    }

    @Test
    public void createGenereTest() throws SpotifyException {

        Mockito.when(genereService.createGenere(any(GenereRest.class))).thenReturn(GENRE_REST);
        SpotifyResponse<GenereRest> response = genereControllerRest.createGenere(GENRE_REST);

        assertNotNull(response);
        assertEquals(String.valueOf(HttpStatus.OK), response.getStatus());
        assertEquals("200", response.getCode());
        assertEquals(RestConstantsUtils.OK, response.getMessage());
        Assertions.assertThat(response.getData()).isEqualTo(GENRE_REST);
    }

    @Test
    public void deleteSongTest() throws SpotifyException {
        genereControllerRest.deleteGenere(GENRE_ID);
        Mockito.verify(genereService, Mockito.times(1)).deleteGenere(Mockito.anyLong());
    }

    @Test
    public void deleteArtistFromSongById() throws SpotifyException {
        genereControllerRest.deleteSongFromGenereById(GENRE_ID, SONG_ID);
        verify(genereService, times(1)).deleteSongFromGenereById(GENRE_ID, SONG_ID);
    }

    @Test
    public void updateSongTest() throws SpotifyException {


        SpotifyResponse<GenereRest> response = genereControllerRest.updateGenere(GENRE_REST);

        assertNotNull(response);
        assertEquals(String.valueOf(HttpStatus.OK), response.getStatus());
        assertEquals("200", response.getCode());
        assertEquals(RestConstantsUtils.OK, response.getMessage());
    }

    @Test
    public void updateArtistBySongIdTest() throws SpotifyException {

        SpotifyResponse<GenereSongRest> response = genereControllerRest.updateSongByGenereId(GENRE_ID, SONG_ID);
        assertNotNull(response);
        assertEquals(String.valueOf(HttpStatus.OK), response.getStatus());
        assertEquals("200", response.getCode());
        assertEquals(RestConstantsUtils.OK, response.getMessage());
    }
}
