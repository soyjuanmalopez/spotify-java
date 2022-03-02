package Spotify.persistence.mapper;

import static org.junit.Assert.assertEquals;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import Spotify.persistence.entity.SongEntity;



public class SongEntityMapperTest {


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /*@Test
    public void testMapToEntity() {
        Long id = 1L;
        String title= "titulo";
        int reproductions= 300;
        int albumRef= 4;
        double duration= 3.90;


        SongDto songDto = Mockito.mock(SongDto.class);

        Mockito.when(songDto.getId()).thenReturn(id);
        Mockito.when(songDto.getAlbum_ref()).thenReturn(albumRef);
        Mockito.when(songDto.getDuration()).thenReturn(duration);
        Mockito.when(songDto.getReproductions()).thenReturn(reproductions);
        Mockito.when(songDto.getTitle()).thenReturn(title);

        SongEntity result = songEntityMapper.mapToEntity(songDto);

        assertEquals(id, result.getId());
        assertEquals(albumRef, result.getAlbum_ref());
        Assertions.assertThat(duration).isEqualTo(result.getDuration());
        assertEquals(title, result.getTitle());
        assertEquals(reproductions, result.getReproductions());
    }

    @Test
    public void testMapToDto() {
        Long id = 1L;
        String title= "titulo";
        int reproductions= 300;
        int albumRef= 4;
        double duration= 3.90;

        SongEntity songEntity = Mockito.mock(SongEntity.class);


        Mockito.when(songEntity.getId()).thenReturn(id);
        Mockito.when(songEntity.getAlbum_ref()).thenReturn(albumRef);
        Mockito.when(songEntity.getDuration()).thenReturn(duration);
        Mockito.when(songEntity.getReproductions()).thenReturn(reproductions);
        Mockito.when(songEntity.getTitle()).thenReturn(title);

        SongDto result = songEntityMapper.mapToDto(songEntity);

        assertEquals(id, result.getId());
        assertEquals(albumRef, result.getAlbum_ref());
        Assertions.assertThat(duration).isEqualTo(result.getDuration());
        assertEquals(title, result.getTitle());
        assertEquals(reproductions, result.getReproductions());
    }
*/
}