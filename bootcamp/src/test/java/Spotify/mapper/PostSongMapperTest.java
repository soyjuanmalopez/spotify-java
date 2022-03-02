package Spotify.mapper;

import Spotify.controller.rest.model.AlbumRest;
import Spotify.controller.rest.model.PostSongRest;
import Spotify.persistence.entity.AlbumEntity;
import Spotify.persistence.entity.SongEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class PostSongMapperTest {
    Long id = 1L;
    String title= "titulo";
    Integer reproductions= 300;
    Double duration= 3.90;
    AlbumRest albumRest = new AlbumRest();
    AlbumEntity albumEntity = new AlbumEntity();


    @Mock
    PostSongMapper postSongMapper;

    @InjectMocks
    PostSongMapperImpl postSongMapperImpl;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void mapToEntity() {
        PostSongRest song = Mockito.mock(PostSongRest.class);

        when(song.getId()).thenReturn(id);
        when(song.getTitle()).thenReturn(title);
        when(song.getReproductions()).thenReturn(reproductions);
        when(song.getDuration()).thenReturn(duration);
        when(song.getAlbum_ref()).thenReturn(albumRest);


        SongEntity response = postSongMapperImpl.mapToEntity(song);

        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getTitle()).isEqualTo(title);
        assertThat(response.getDuration()).isEqualTo(duration);
        assertThat(response.getReproductions()).isEqualTo(reproductions);
        assertThat(response.getAlbum_ref()).usingRecursiveComparison().isEqualTo(albumEntity);
    }
    @Test
    public void mapToEntityAlbumNull() {
        PostSongRest song = Mockito.mock(PostSongRest.class);
        when(song.getAlbum_ref()).thenReturn(null);
        SongEntity response = postSongMapperImpl.mapToEntity(song);
        assertThat(response.getAlbum_ref()).isEqualTo(null);
    }

    @Test
    public void mapToEntityNull() {
        SongEntity response = postSongMapperImpl.mapToEntity(null);
        assertThat(response).isEqualTo(null);
    }

    @Test
    public void mapToRest() {
        SongEntity song = Mockito.mock(SongEntity.class);

        when(song.getId()).thenReturn(id);
        when(song.getTitle()).thenReturn(title);
        when(song.getReproductions()).thenReturn(reproductions);
        when(song.getDuration()).thenReturn(duration);
        when(song.getAlbum_ref()).thenReturn(albumEntity);

        PostSongRest response = postSongMapperImpl.mapToRest(song);

        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getTitle()).isEqualTo(title);
        assertThat(response.getDuration()).isEqualTo(duration);
        assertThat(response.getReproductions()).isEqualTo(reproductions);
        assertThat(response.getAlbum_ref()).usingRecursiveComparison().isEqualTo(albumRest);
    }

    @Test
    public void mapToRestAlbumNull() {
        SongEntity song = Mockito.mock(SongEntity.class);
        when(song.getAlbum_ref()).thenReturn(null);
        PostSongRest response = postSongMapperImpl.mapToRest(song);
        assertThat(response.getAlbum_ref()).isEqualTo(null);
    }
    @Test
    public void mapToRestNull() {
        PostSongRest response = postSongMapperImpl.mapToRest(null);
        assertThat(response).isEqualTo(null);
    }
}