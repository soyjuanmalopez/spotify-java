package Spotify.controller.rest.impl;

import Spotify.controller.rest.model.*;
import Spotify.controller.rest.model.restSongs.GenreSongRest;
import Spotify.exception.SpotifyException;
import Spotify.mapper.GenreMapper;
import Spotify.mapper.GenreSongMapper;
import Spotify.mapper.SongMapper;
import Spotify.persistence.entity.GenreEntity;
import Spotify.persistence.entity.SongEntity;
import Spotify.persistence.repository.GenreRepository;
import Spotify.persistence.repository.SongRepository;
import Spotify.service.impl.GenreServiceImpl;
import Spotify.util.constant.RestConstantsUtils;
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

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class GenreControllerRestImplTest {

    Long SONG_ID = 2L;
    SongRest SONG_REST = new SongRest();
    SongEntity SONG_ENTITY = new SongEntity();
    Set<SongEntity> SONG_HASHSET = new HashSet<>();
    Long GENRE_ID = 1L;
    String NAME = "Test name";
    GenreRest GENRE_REST = new GenreRest(GENRE_ID, NAME);
    GenreEntity GENRE_ENTITY;


    @Mock
    GenreRepository genreRepository;

    @Mock
    GenreMapper genreMapper;

    @Mock
    SongMapper songMapper;

    @Mock
    SongRepository songRepository;

    @Mock
    GenreSongMapper genreSongMapper;

    @Mock
    GenreServiceImpl genreService;

    @InjectMocks
    GenreControllerRestImpl genreControllerRest;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        SONG_HASHSET.add(SONG_ENTITY);
        GENRE_ENTITY= new GenreEntity(GENRE_ID, NAME, SONG_HASHSET);
        SONG_ENTITY.setId(SONG_ID);
        SONG_REST.setId(SONG_ID);
        Set<GenreEntity> GENREENTITY_HASHSET = new HashSet<>();
        GENREENTITY_HASHSET.add(GENRE_ENTITY);
        Set<GenreRest> GENREREST_HASHSET = new HashSet<>();
        GENREREST_HASHSET.add(GENRE_REST);
        SONG_ENTITY.setGenres(GENREENTITY_HASHSET);
        SONG_REST.setGenres(GENREREST_HASHSET);

        when(genreRepository.findById(anyLong())).thenReturn(Optional.of(GENRE_ENTITY));
        when(genreMapper.mapToEntity(any(GenreRest.class))).thenReturn(GENRE_ENTITY);
        when(genreMapper.mapToRest(any(GenreEntity.class))).thenReturn(GENRE_REST);


    }

    @Test
    public void getAllGenresTest() throws SpotifyException {
        // given
        int page = 0;
        int size = 2;

        Pageable pageable = PageRequest.of(0, 1);
        Page<GenreRest> genreRestPage = new PageImpl<>(List.of(GENRE_REST));
        // when
        Mockito.when(genreService.getAllGenres(any(Pageable.class))).thenReturn(genreRestPage);
        SpotifyResponse<D4iPageRest<GenreRest>> response = genreControllerRest.getAllGenres(0L, 2L, pageable);

        // then
        assertNotNull(response);
        assertEquals(RestConstantsUtils.OK, response.getMessage());
        assertNotNull(response.getData().getContent());

    }

    @Test
    public void getGenreByIdTest() throws SpotifyException {

        when(genreService.getGenreById(anyLong())).thenReturn(GENRE_REST);
        SpotifyResponse<GenreRest> response = genreControllerRest.getGenreById(GENRE_ID);

        assertNotNull(response);
        assertEquals(String.valueOf(HttpStatus.OK), response.getStatus());
        assertEquals(RestConstantsUtils.OK, response.getMessage());
        assertEquals(GENRE_REST, response.getData());
    }
    @Test
    public void getSongByGenreIdTest() throws SpotifyException {

        Mockito.when(genreService.getSongsByGenreId(anyLong())).thenReturn(List.of(SONG_REST));
        SpotifyResponse<List<SongRest>> response = genreControllerRest.getSongByGenreId(GENRE_ID);

        assertNotNull(response);
        assertEquals(String.valueOf(HttpStatus.OK), response.getStatus());
        assertEquals(RestConstantsUtils.OK, response.getMessage());
        assertEquals(List.of(SONG_REST), response.getData());
    }

    @Test
    public void createGenreTest() throws SpotifyException {

        Mockito.when(genreService.createGenre(any(GenreRest.class))).thenReturn(GENRE_REST);
        SpotifyResponse<GenreRest> response = genreControllerRest.createGenre(GENRE_REST);

        assertNotNull(response);
        assertEquals(String.valueOf(HttpStatus.OK), response.getStatus());
        assertEquals("200", response.getCode());
        assertEquals(RestConstantsUtils.OK, response.getMessage());
        Assertions.assertThat(response.getData()).isEqualTo(GENRE_REST);
    }

    @Test
    public void deleteSongTest() throws SpotifyException {
        genreControllerRest.deleteGenre(GENRE_ID);
        Mockito.verify(genreService, Mockito.times(1)).deleteGenre(Mockito.anyLong());
    }
    @Test
    public void deleteArtistFromSongById() throws SpotifyException{
        genreControllerRest.deleteSongFromGenreById(GENRE_ID,SONG_ID);
        verify(genreService,times(1)).deleteSongFromGenreById(GENRE_ID,SONG_ID);
    }

    @Test
    public void updateSongTest() throws SpotifyException {


        SpotifyResponse<GenreRest> response = genreControllerRest.updateGenre(GENRE_REST);

        assertNotNull(response);
        assertEquals(String.valueOf(HttpStatus.OK), response.getStatus());
        assertEquals("200", response.getCode());
        assertEquals(RestConstantsUtils.OK, response.getMessage());
    }
    @Test
    public void updateArtistBySongIdTest() throws SpotifyException {

        SpotifyResponse<GenreSongRest> response = genreControllerRest.updateSongByGenreId(GENRE_ID,SONG_ID);
        assertNotNull(response);
        assertEquals(String.valueOf(HttpStatus.OK), response.getStatus());
        assertEquals("200", response.getCode());
        assertEquals(RestConstantsUtils.OK, response.getMessage());
    }
}
