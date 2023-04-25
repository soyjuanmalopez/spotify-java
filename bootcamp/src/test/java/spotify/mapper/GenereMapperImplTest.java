package spotify.mapper;


import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import spotify.controller.rest.model.GenereRest;
import spotify.persistence.entity.GenereEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GenereMapperImplTest {

    @InjectMocks
    GenereMapperImpl genereMapperImpl;

    Long id = 1L;
    String name = "Test name";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void mapToRest() {

        GenereEntity genere = new GenereEntity();
        genere.setId(id);
        genere.setName(name);

        GenereRest response = genereMapperImpl.mapToRest(genere);

        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getName()).isEqualTo(name);
    }

    @Test
    public void mapToRestNull() {
        GenereRest response = genereMapperImpl.mapToRest(null);
        assertThat(response).isNull();
    }

    @Test
    public void mapToEntity() {

        GenereRest genere = new GenereRest();
        genere.setId(id);
        genere.setName(name);

        GenereEntity response = genereMapperImpl.mapToEntity(genere);

        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getName()).isEqualTo(name);
    }

    @Test
    public void mapToEntityNull() {
        GenereEntity response = genereMapperImpl.mapToEntity((GenereRest) null);
        assertThat(response).isNull();
    }

}