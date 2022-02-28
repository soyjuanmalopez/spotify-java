package Spotify.service.impl;

import Spotify.controller.rest.model.GenreRest;
import Spotify.controller.rest.model.SongRest;
import Spotify.exception.SpotifyException;
import Spotify.exception.SpotifyNotFoundException;
import Spotify.mapper.GenreMapper;
import Spotify.mapper.GenreSongMapper;
import Spotify.mapper.SongMapper;
import Spotify.persistence.entity.GenreEntity;
import Spotify.persistence.entity.SongEntity;
import Spotify.persistence.repository.GenreRepository;
import Spotify.persistence.repository.SongRepository;
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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

public class GenreServiceImplTest {

    Long SONG_ID = 2L;
    SongRest SONG_REST = new SongRest();
    SongEntity SONG_ENTITY = new SongEntity();
    Set<SongEntity> SONG_HASHSET = new HashSet<>();
    Long GENRE_ID = 1L;
    String NAME = "Test name";
    GenreRest GENRE_REST = new GenreRest(GENRE_ID, NAME);
    GenreEntity GENRE_ENTITY;

    @Before
    public void onBefore() {
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

    @InjectMocks
    GenreServiceImpl genreService;

    @Test
    public void getAllGenres() throws SpotifyException {
        Pageable pageable = PageRequest.of(0, 1);
        Page<GenreEntity> genreEntityPage = new PageImpl<>(List.of(GENRE_ENTITY));
        Page<GenreRest> genreRestPage = new PageImpl<>(List.of(GENRE_REST));


        when(genreRepository.findAll(any(Pageable.class))).thenReturn(genreEntityPage);

        Page<GenreRest> response = genreService.getAllGenres(pageable);

        Assertions.assertThat(response).isEqualTo(genreRestPage);

    }

    @Test
    public void getGenreById() throws SpotifyException {

        GenreRest response = genreService.getGenreById(anyLong());

        Assertions.assertThat(response).isEqualTo(GENRE_REST);

    }

    @Test(expected = SpotifyNotFoundException.class)
    public void getGenreByIdException() throws SpotifyException {

        when(genreRepository.findById(anyLong())).thenReturn(Optional.empty());
        genreService.getGenreById(anyLong());

    }

    @Test
    public void getSongsByGenreId() throws SpotifyException {
        when(songMapper.mapToRest(any(SongEntity.class))).thenReturn(SONG_REST);

        List<SongRest> response = genreService.getSongsByGenreId(anyLong());

        Assertions.assertThat(response).isEqualTo(List.of(SONG_REST));

    }

    @Test
    public void createGenre() throws SpotifyException {

        genreService.createGenre(any(GenreRest.class));
        verify(genreRepository).save(any());

    }

    @Test
    public void updateGenre() throws SpotifyException {

        genreService.updateGenre(GENRE_ENTITY);
        verify(genreRepository).save(GENRE_ENTITY);
    }

    @Test
    public void updateSongByGenreId() throws SpotifyException {
        when(songRepository.findById(anyLong())).thenReturn(Optional.of(SONG_ENTITY));

        genreService.updateSongByGenreId(GENRE_ID,SONG_ID);
        verify(genreRepository, times(1)).save(any(GenreEntity.class));
        verify(songRepository, times(1)).save(any(SongEntity.class));

    }
    @Test(expected = SpotifyNotFoundException.class)
    public void updateSongByGenreIdGenreException() throws SpotifyException {

        when(genreRepository.findById(anyLong())).thenReturn(Optional.empty());
        genreService.updateSongByGenreId(GENRE_ID,SONG_ID);

    }
    /*@Test(expected = SpotifyNotFoundException.class)
    public void updateSongByGenreIdSongException() throws SpotifyException {

        when(songRepository.findById(anyLong())).thenReturn(Optional.empty());
        genreService.deleteSongFromGenreById(GENRE_ID,SONG_ID);

    }*/
    @Test
    public void deleteGenre() throws SpotifyException {

        genreService.deleteGenre(anyLong());
        Mockito.verify(genreRepository, Mockito.times(1)).deleteById(anyLong());

    }

    @Test
    public void deleteSongFromGenreById() throws SpotifyException {

        genreService.deleteSongFromGenreById(GENRE_ID,SONG_ID);
        verify(genreRepository, times(1)).save(any(GenreEntity.class));
        verify(songRepository, times(1)).save(any(SongEntity.class));


    }
    @Test(expected = SpotifyNotFoundException.class)
    public void deleteSongFromGenreByIdException() throws SpotifyException {
        when(genreRepository.findById(anyLong())).thenReturn(Optional.empty());
        genreService.deleteSongFromGenreById(GENRE_ID,SONG_ID);
    }

}