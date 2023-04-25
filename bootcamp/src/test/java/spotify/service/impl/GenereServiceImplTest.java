package spotify.service.impl;

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
import spotify.controller.rest.model.GenereRest;
import spotify.controller.rest.model.SongRest;
import spotify.exception.SpotifyException;
import spotify.exception.SpotifyNotFoundException;
import spotify.mapper.GenereMapper;
import spotify.mapper.SongMapper;
import spotify.persistence.entity.GenereEntity;
import spotify.persistence.entity.SongEntity;
import spotify.persistence.repository.GenereRepository;
import spotify.persistence.repository.SongRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

public class GenereServiceImplTest {

    Long SONG_ID = 2L;
    SongRest SONG_REST = new SongRest();
    SongEntity SONG_ENTITY = new SongEntity();
    Set<SongEntity> SONG_HASHSET = new HashSet<>();
    Long GENRE_ID = 1L;
    String NAME = "Test name";
    GenereRest GENRE_REST = new GenereRest(GENRE_ID, NAME);
    GenereEntity GENRE_ENTITY;

    @Before
    public void onBefore() {
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

    @Mock
    GenereRepository genereRepository;

    @Mock
    GenereMapper genereMapper;

    @Mock
    SongMapper songMapper;

    @Mock
    SongRepository songRepository;


    @InjectMocks
    GenereServiceImpl genereService;

    @Test
    public void getAllGeneres() throws SpotifyException {
        Pageable pageable = PageRequest.of(0, 1);
        Page<GenereEntity> genereEntityPage = new PageImpl<>(List.of(GENRE_ENTITY));
        Page<GenereRest> genereRestPage = new PageImpl<>(List.of(GENRE_REST));


        when(genereRepository.findAll(any(Pageable.class))).thenReturn(genereEntityPage);

        Page<GenereRest> response = genereService.getAllGeneres(pageable);

        Assertions.assertThat(response).isEqualTo(genereRestPage);

    }

    @Test
    public void getGenereById() throws SpotifyException {

        GenereRest response = genereService.getGenereById(anyLong());

        Assertions.assertThat(response).isEqualTo(GENRE_REST);

    }

    @Test(expected = SpotifyNotFoundException.class)
    public void getGenereByIdException() throws SpotifyException {

        when(genereRepository.findById(anyLong())).thenReturn(Optional.empty());
        genereService.getGenereById(anyLong());

    }

    @Test
    public void getSongsByGenereId() throws SpotifyException {
        when(songMapper.mapToRest(any(SongEntity.class))).thenReturn(SONG_REST);

        List<SongRest> response = genereService.getSongsByGenereId(anyLong());

        Assertions.assertThat(response).isEqualTo(List.of(SONG_REST));

    }

    @Test
    public void createGenere() throws SpotifyException {

        genereService.createGenere(any(GenereRest.class));
        verify(genereRepository).save(any());

    }

    @Test
    public void updateGenere() throws SpotifyException {

        genereService.updateGenere(GENRE_REST);
        verify(genereRepository).save(GENRE_ENTITY);
    }

    @Test
    public void updateSongByGenereId() throws SpotifyException {
        when(songRepository.findById(anyLong())).thenReturn(Optional.of(SONG_ENTITY));

        genereService.updateSongByGenereId(GENRE_ID, SONG_ID);
        verify(genereRepository, times(1)).save(any(GenereEntity.class));
        verify(songRepository, times(1)).save(any(SongEntity.class));

    }

    @Test(expected = SpotifyNotFoundException.class)
    public void updateSongByGenereIdGenereException() throws SpotifyException {

        when(genereRepository.findById(anyLong())).thenReturn(Optional.empty());
        genereService.updateSongByGenereId(GENRE_ID, SONG_ID);

    }

    @Test
    public void deleteGenere() throws SpotifyException {

        genereService.deleteGenere(anyLong());
        Mockito.verify(genereRepository, Mockito.times(1)).deleteById(anyLong());

    }

    @Test
    public void deleteSongFromGenereById() throws SpotifyException {

        genereService.deleteSongFromGenereById(GENRE_ID, SONG_ID);
        verify(genereRepository, times(1)).save(any(GenereEntity.class));
        verify(songRepository, times(1)).save(any(SongEntity.class));


    }

    @Test(expected = SpotifyNotFoundException.class)
    public void deleteSongFromGenereByIdException() throws SpotifyException {
        when(genereRepository.findById(anyLong())).thenReturn(Optional.empty());
        genereService.deleteSongFromGenereById(GENRE_ID, SONG_ID);
    }

}