package Spotify.mapper;

import Spotify.controller.rest.model.AlbumRest;
import Spotify.controller.rest.model.ArtistRest;
import Spotify.persistence.entity.AlbumEntity;
import Spotify.persistence.entity.ArtistEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ArtistMapperImplTest {

    @InjectMocks
    private ArtistMapperImpl artistMapperImpl;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void mapToEntityTest() {
        Long id = 1L;
        String name = "Pink";
        String description = "Is an American singer and songwriter. She was originally a member of the girl group Choice.";
        List<AlbumRest> albumRestList = new ArrayList<>();
        List<AlbumEntity> albumEntityList = new ArrayList<>();
        ArtistRest artistRest = Mockito.mock(ArtistRest.class);
        ArtistRest artistRest2 = null;

        Mockito.when(artistRest.getId()).thenReturn(id);
        Mockito.when(artistRest.getName()).thenReturn(name);
        Mockito.when(artistRest.getDescription()).thenReturn(description);
        Mockito.when(artistRest.getAlbums()).thenReturn(albumRestList);

        ArtistEntity artistEntityResult = artistMapperImpl.mapToEntity(artistRest);
        ArtistEntity artistEntityResultNull = artistMapperImpl.mapToEntity(artistRest2);

        assertNull(artistEntityResultNull);
        assertEquals(id, artistEntityResult.getId());
        assertEquals(name, artistEntityResult.getName());
        assertEquals(description, artistEntityResult.getDescription());
        assertEquals(albumEntityList, artistEntityResult.getAlbums());
    }

    @Test
    public void mapToRestTest() {
        Long id = 345L;
        String name = "Alton Ellis";
        String description = "Was a Jamaican singer-songwriter. One of the innovators of rocksteady, he was given the informal title \"Godfather of Rocksteady\".";
        List<AlbumRest> albumRestList = new ArrayList<>();
        List<AlbumEntity> albumEntityList = new ArrayList<>();
        ArtistEntity artistEntity = Mockito.mock(ArtistEntity.class);
        ArtistEntity artistEntity2 = null;

        Mockito.when(artistEntity.getId()).thenReturn(id);
        Mockito.when(artistEntity.getName()).thenReturn(name);
        Mockito.when(artistEntity.getDescription()).thenReturn(description);
        Mockito.when(artistEntity.getAlbums()).thenReturn(albumEntityList);

        ArtistRest artistRestResult = artistMapperImpl.mapToRest(artistEntity);
        ArtistRest artistRestResultNull = artistMapperImpl.mapToRest(artistEntity2);

        assertNull(artistRestResultNull);
        assertEquals(id, artistRestResult.getId());
        assertEquals(name, artistRestResult.getName());
        assertEquals(description, artistRestResult.getDescription());
        assertEquals(albumRestList, artistRestResult.getAlbums());
    }
}