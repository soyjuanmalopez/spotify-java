package Spotify.mapper;

import Spotify.controller.rest.model.*;
import Spotify.persistence.entity.AlbumEntity;
import Spotify.persistence.entity.GenreEntity;
import Spotify.persistence.entity.SongEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GenreSongMapperTest {

    Long id = 1L;
    String name = "Test name";
    SongEntity songEntity = new SongEntity();
    PostSongRest songRest = new PostSongRest();
    AlbumRest albumRest = new AlbumRest();
    AlbumEntity albumEntity = new AlbumEntity();
    Set<SongEntity> songEntitySet = new HashSet<>();
    Set<PostSongRest> postSongRestSet = new HashSet<>();



    @Mock
    GenreSongMapper genreSongMapper;

    @Mock
    PostSongMapper songMapper;

    @Mock
    AlbumMapper albumMapper;

    @InjectMocks
    GenreSongMapperImpl genreSongMapperImpl;



    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        songEntity.setAlbum_ref(albumEntity);
        songRest.setAlbum_ref(albumRest);
        songEntitySet.add(songEntity);
        postSongRestSet.add(songRest);
        postSongRestSet.add(null);
        songEntitySet.add(null);

    }

    @Test
    public void mapToRest() {
        GenreEntity genre = Mockito.mock(GenreEntity.class);

        when(genre.getId()).thenReturn(id);
        when(genre.getName()).thenReturn(name);
        when(genre.getSongs()).thenReturn(songEntitySet);
        GenreSongRest response = genreSongMapperImpl.mapToRest(genre);

        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getName()).isEqualTo(name);
        assertThat(response.getSongs()).usingRecursiveComparison().isEqualTo(postSongRestSet);
    }

    @Test
    public void mapToRestSongSetNull() {
        GenreEntity genre = Mockito.mock(GenreEntity.class);
        when(genre.getSongs()).thenReturn(null);
        GenreSongRest response = genreSongMapperImpl.mapToRest(genre);
        assertThat(response.getSongs()).isEqualTo(null);
    }

    @Test
    public void mapToRestNull() {
        GenreSongRest response = genreSongMapperImpl.mapToRest(null);
        assertThat(response).isEqualTo(null);
    }

    @Test
    public void mapToEntity() {
        GenreSongRest genre = Mockito.mock(GenreSongRest.class);

        when(genre.getId()).thenReturn(id);
        when(genre.getName()).thenReturn(name);
        when(genre.getSongs()).thenReturn(postSongRestSet);
        GenreEntity response = genreSongMapperImpl.mapToEntity(genre);

        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getName()).isEqualTo(name);
        assertThat(response.getSongs()).usingRecursiveComparison().isEqualTo(songEntitySet);

    }

    @Test
    public void mapToEntitySongSetNull() {
        GenreSongRest genre = Mockito.mock(GenreSongRest.class);
        when(genre.getSongs()).thenReturn(null);
        GenreEntity response = genreSongMapperImpl.mapToEntity(genre);
        assertThat(response.getSongs()).isEqualTo(null);

    }

    @Test
    public void mapToEntityNull() {

        GenreEntity response = genreSongMapperImpl.mapToEntity(null);
        assertThat(response).isEqualTo(null);

    }
}