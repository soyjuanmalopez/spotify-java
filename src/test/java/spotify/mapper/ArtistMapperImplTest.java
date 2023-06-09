package spotify.mapper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import spotify.controller.rest.model.AlbumRest;
import spotify.controller.rest.model.ArtistRest;
import spotify.persistence.entity.AlbumEntity;
import spotify.persistence.entity.ArtistEntity;

import java.util.ArrayList;
import java.util.List;

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

        ArtistRest artistRest = new ArtistRest();
        ArtistRest artistRest2 = null;
        artistRest.setId(id);
        artistRest.setName(name);
        artistRest.setDescription(description);
        artistRest.setAlbums(albumRestList);

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
        ArtistEntity artistEntity = new ArtistEntity();
        ArtistEntity artistEntity2 = null;
        artistEntity.setId(id);
        artistEntity.setName(name);
        artistEntity.setDescription(description);
        artistEntity.setAlbums(albumEntityList);

        ArtistRest artistRestResult = artistMapperImpl.mapToRest(artistEntity);
        ArtistRest artistRestResultNull = artistMapperImpl.mapToRest(artistEntity2);

        assertNull(artistRestResultNull);
        assertEquals(id, artistRestResult.getId());
        assertEquals(name, artistRestResult.getName());
        assertEquals(description, artistRestResult.getDescription());
        assertEquals(albumRestList, artistRestResult.getAlbums());
    }
}