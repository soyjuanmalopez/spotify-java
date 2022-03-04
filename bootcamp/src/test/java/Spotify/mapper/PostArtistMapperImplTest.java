package Spotify.mapper;

import Spotify.controller.rest.model.restArtists.PostArtistRest;
import Spotify.persistence.entity.ArtistEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

public class PostArtistMapperImplTest {

    Long id = 1L;
    String name= "name";
    String description = "description";

    @Mock
    PostArtistMapper postArtistMapper;

    @InjectMocks
    PostArtistMapperImpl postArtistMapperImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void mapToEntity() {
        PostArtistRest artist = Mockito.mock(PostArtistRest.class);

        when(artist.getId()).thenReturn(id);
        when(artist.getName()).thenReturn(name);
        when(artist.getDescription()).thenReturn(description);


        ArtistEntity response = postArtistMapperImpl.mapToEntity(artist);

        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getName()).isEqualTo(name);
        assertThat(response.getDescription()).isEqualTo(description);
    }

    @Test
    public  void mapToEntityArtistNull(){
        ArtistEntity response = postArtistMapperImpl.mapToEntity(null);
        assertThat(response).isEqualTo(null);
    }

    @Test
    public void mapToRest() {
        ArtistEntity artist = Mockito.mock(ArtistEntity.class);

        when(artist.getId()).thenReturn(id);
        when(artist.getName()).thenReturn(name);
        when(artist.getDescription()).thenReturn(description);


        PostArtistRest response = postArtistMapperImpl.mapToRest(artist);

        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getName()).isEqualTo(name);
        assertThat(response.getDescription()).isEqualTo(description);
    }
    @Test
    public  void mapToRestArtistNull(){
        PostArtistRest response = postArtistMapperImpl.mapToRest(null);
        assertThat(response).isEqualTo(null);
    }
}