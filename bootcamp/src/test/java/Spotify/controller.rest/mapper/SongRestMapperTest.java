package Spotify.controller.rest.mapper;

import Spotify.controller.rest.model.SongRest;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

public class SongRestMapperTest {

  /*  @InjectMocks
    private SongRestMapper songRestMapper;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMapToRest() {
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

        SongRest result = songRestMapper.mapToRest(songDto);

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


        SongRest songRest = Mockito.mock(SongRest.class);

        Mockito.when(songRest.getId()).thenReturn(id);
        Mockito.when(songRest.getAlbum_ref()).thenReturn(albumRef);
        Mockito.when(songRest.getDuration()).thenReturn(duration);
        Mockito.when(songRest.getReproductions()).thenReturn(reproductions);
        Mockito.when(songRest.getTitle()).thenReturn(title);

        SongDto result = songRestMapper.mapToDto(songRest);

        assertEquals(id, result.getId());
        assertEquals(albumRef, result.getAlbum_ref());
        Assertions.assertThat(duration).isEqualTo(result.getDuration());
        assertEquals(title, result.getTitle());
        assertEquals(reproductions, result.getReproductions());
    }
*/
}