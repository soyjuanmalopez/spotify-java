package Spotify.mapper;

import Spotify.controller.rest.model.AlbumRest;
import Spotify.controller.rest.model.restAlbums.SongRestAlbum;
import Spotify.mapper.AlbumMapper;
import Spotify.mapper.AlbumMapperImpl;
import Spotify.persistence.entity.AlbumEntity;
import Spotify.persistence.entity.SongEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AlbumEntityMapperTest {

    private final static AlbumEntity ALBUM_ENTITY = new AlbumEntity();

    private final static List<SongEntity> SONG_ENTITY_LIST = new ArrayList<>();
    private final static SongEntity SONG_ENTITY = new SongEntity();

    private final static List<SongRestAlbum> SONG_REST_ALBUMS_LIST = new ArrayList<>();
    private final static SongRestAlbum SONG_REST_ALBUM = new SongRestAlbum();

    private final static List<AlbumEntity> ALBUM_ENTITY_LIST = new ArrayList<>();
    private final static List<AlbumRest> ALBUM_REST_LIST = new ArrayList<>();

    @InjectMocks
    private AlbumMapperImpl albumMapper = new AlbumMapperImpl();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    public void setUp(){
        SONG_ENTITY_LIST.clear();

        SONG_ENTITY.setId(1L);
        SONG_ENTITY.setTitle("TestingSong");
        SONG_ENTITY.setDuration(1.4);
        SONG_ENTITY.setAlbum(ALBUM_ENTITY);

        SONG_REST_ALBUM.setId(SONG_ENTITY.getId());
        SONG_REST_ALBUM.setTitle(SONG_ENTITY.getTitle());
        SONG_REST_ALBUM.setDuration(SONG_ENTITY.getDuration());

        SONG_ENTITY_LIST.add(SONG_ENTITY);
        SONG_REST_ALBUMS_LIST.add(SONG_REST_ALBUM);

        ALBUM_ENTITY.setSongs(SONG_ENTITY_LIST);
        ALBUM_ENTITY_LIST.add(ALBUM_ENTITY);

    }

    @Test
    public void testMapEntity(){
        Long id = 1L;
        String title= "TestingAlbum";
        double duration= 1.1;
        int yearRelease = 2021;


        AlbumRest album = new AlbumRest();

        album.setId(id);
        album.setTitle(title);
        album.setDuration(duration);
        album.setYearRelease(yearRelease);
        album.setSongs(SONG_REST_ALBUMS_LIST);


        AlbumEntity result = albumMapper.mapToEntity(album);

        assertEquals(id, result.getId());
        assertEquals(title, result.getTitle());
        assertEquals(duration, result.getDuration());
        assertEquals(yearRelease, result.getYearRelease());
        assertEquals(SONG_ENTITY_LIST, result.getSongs());

    }

    @Test
    public void testMapEntityRestNull(){
        AlbumRest album = null;
        AlbumEntity result = albumMapper.mapToEntity(album);
        assertNull(result);
    }

    @Test
    public void testMapRest(){
        Long id = 1L;
        String title= "TestingAlbum";
        double duration= 1.1;
        int yearRelease = 2021;

        AlbumEntity album = new AlbumEntity();

        album.setId(id);
        album.setTitle(title);
        album.setDuration(duration);
        album.setYearRelease(yearRelease);
        album.setSongs(SONG_ENTITY_LIST);

        AlbumRest result = albumMapper.mapToRest(album);

        assertEquals(id, result.getId());
        assertEquals(title, result.getTitle());
        assertEquals(duration, result.getDuration());
        assertEquals(yearRelease, result.getYearRelease());
        assertEquals(SONG_REST_ALBUMS_LIST, result.getSongs());

    }

    @Test
    public void testMapRestEntityNull(){
        AlbumEntity album = null;
        AlbumRest result = albumMapper.mapToRest(album);
        assertNull(result);
    }

}
