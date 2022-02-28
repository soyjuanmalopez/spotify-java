package Spotify.mapper;



import Spotify.controller.rest.model.GenreRest;
import Spotify.persistence.entity.GenreEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

public class GenreMapperImplTest {
    @Mock
    GenreMapper genreMapper;

    @InjectMocks
    GenreMapperImpl genreMapperImpl;

    Long id = 1L;
    String name = "Test name";


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void mapToRest() {

        GenreEntity genre = Mockito.mock(GenreEntity.class);

        when(genre.getId()).thenReturn(id);
        when(genre.getName()).thenReturn(name);
        GenreRest response = genreMapperImpl.mapToRest(genre);

        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getName()).isEqualTo(name);


    }
    @Test
    public void mapToRestNull() {
        GenreRest response = genreMapperImpl.mapToRest(null);
        assertThat(response).isEqualTo(null);
    }

    @Test
    public void mapToEntity() {

        GenreRest genre = Mockito.mock(GenreRest.class);

        when(genre.getId()).thenReturn(id);
        when(genre.getName()).thenReturn(name);
        GenreEntity response = genreMapperImpl.mapToEntity(genre);

        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getName()).isEqualTo(name);

    }

    @Test
    public void mapToEntityNull() {

        GenreEntity response = genreMapperImpl.mapToEntity(null);
        assertThat(response).isEqualTo(null);

    }

}