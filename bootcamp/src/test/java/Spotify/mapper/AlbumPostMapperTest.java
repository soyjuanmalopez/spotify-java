package Spotify.mapper;

import Spotify.controller.rest.model.AlbumRest;
import Spotify.controller.rest.model.restAlbums.AlbumRestPost;
import Spotify.controller.rest.model.restAlbums.SongRestAlbum;
import Spotify.persistence.entity.AlbumEntity;
import Spotify.persistence.entity.SongEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlbumPostMapperTest {
    Long id = 1L;
    String title= "TestingAlbum";
    double duration= 1.1;
    int yearRelease = 2021;

    @Mock
    AlbumPostMapper albumPostMapper;

    @InjectMocks
    AlbumPostMapperImpl albumPostMapperImpl;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void mapToEntity() {
        AlbumRestPost album = Mockito.mock(AlbumRestPost.class);

        Mockito.when(album.getId()).thenReturn(id);
        Mockito.when(album.getTitle()).thenReturn(title);
        Mockito.when(album.getDuration()).thenReturn(duration);
        Mockito.when(album.getYearRelease()).thenReturn(yearRelease);


        AlbumEntity result = albumPostMapperImpl.mapToEntity(album);

        assertEquals(id, result.getId());
        assertEquals(title, result.getTitle());
        assertEquals(duration, result.getDuration());
        assertEquals(yearRelease, result.getYearRelease());
    }
    @Test
    public void mapToEntityNull() {
        AlbumEntity result = albumPostMapperImpl.mapToEntity(null);
        assertNull(result);

    }
    @Test
    public void mapToRest() {
        AlbumEntity album = Mockito.mock(AlbumEntity.class);

        Mockito.when(album.getId()).thenReturn(id);
        Mockito.when(album.getTitle()).thenReturn(title);
        Mockito.when(album.getDuration()).thenReturn(duration);
        Mockito.when(album.getYearRelease()).thenReturn(yearRelease);


        AlbumRestPost result = albumPostMapperImpl.mapToRest(album);

        assertEquals(id, result.getId());
        assertEquals(title, result.getTitle());
        assertEquals(duration, result.getDuration());
        assertEquals(yearRelease, result.getYearRelease());

    }
    @Test
    public void mapToRestNull() {
       AlbumRestPost result = albumPostMapperImpl.mapToRest(null);
        assertNull(result);

    }
}