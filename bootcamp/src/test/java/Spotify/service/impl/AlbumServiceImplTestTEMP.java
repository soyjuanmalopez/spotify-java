package Spotify.service.impl;

import Spotify.controller.rest.model.AlbumRest;
import Spotify.controller.rest.model.restAlbums.SongRestAlbum;
import Spotify.exception.SpotifyException;
import Spotify.mapper.AlbumMapper;
import Spotify.mapper.SongMapper;
import Spotify.persistence.entity.AlbumEntity;
import Spotify.persistence.entity.SongEntity;
import Spotify.persistence.repository.AlbumRepository;
import Spotify.persistence.repository.SongRepository;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
/*@SpringBootTest*/
public class AlbumServiceImplTestTEMP {

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private SongRepository songRepository;

    @Mock
    private AlbumMapper albumMapper;

    @Mock
    private SongMapper songMapper;

    @InjectMocks
    private AlbumServiceImpl albumService = new AlbumServiceImpl();


    private final static AlbumEntity ALBUM_ENTITY = new AlbumEntity();
    private final static AlbumRest ALBUM_REST = new AlbumRest();

    private final static List<SongEntity> SONG_ENTITY_LIST = new ArrayList<>();
    private final static SongEntity SONG_ENTITY = new SongEntity();

    private final static List<SongRestAlbum> SONG_REST_ALBUMS_LIST = new ArrayList<>();
    private final static SongRestAlbum SONG_REST_ALBUM = new SongRestAlbum();

    private final static List<AlbumEntity> ALBUM_ENTITY_LIST = new ArrayList<>();
    private final static List<AlbumRest> ALBUM_REST_LIST = new ArrayList<>();

    private static Page<AlbumEntity> ALBUM_ENTITY_PAGE;
    private static Page<AlbumRest> ALBUM_REST_PAGE;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @BeforeEach
    public void setUp() throws SpotifyException {
        SONG_ENTITY_LIST.clear();
        SONG_REST_ALBUMS_LIST.clear();

        ALBUM_ENTITY.setId(1L);
        ALBUM_ENTITY.setTitle("TestingAlbum");
        ALBUM_ENTITY.setDuration(1.1);
        ALBUM_ENTITY.setYearRelease(2021);

        ALBUM_REST.setId(ALBUM_ENTITY.getId());
        ALBUM_REST.setTitle(ALBUM_ENTITY.getTitle());
        ALBUM_REST.setDuration(ALBUM_ENTITY.getDuration());
        ALBUM_REST.setYearRelease(ALBUM_ENTITY.getYearRelease());

        SONG_ENTITY.setId(1);
        SONG_ENTITY.setTitle("TestingSong");
        SONG_ENTITY.setDuration(1.4);
        SONG_ENTITY.setAlbum_ref(ALBUM_ENTITY);

        SONG_REST_ALBUM.setId(SONG_ENTITY.getId());
        SONG_REST_ALBUM.setTitle(SONG_ENTITY.getTitle());
        SONG_REST_ALBUM.setDuration(SONG_ENTITY.getDuration());


        SONG_ENTITY_LIST.add(SONG_ENTITY);
        SONG_REST_ALBUMS_LIST.add(SONG_REST_ALBUM);

        ALBUM_ENTITY.setSongs(SONG_ENTITY_LIST);
        ALBUM_REST.setSongs(SONG_REST_ALBUMS_LIST);

        ALBUM_ENTITY_LIST.add(ALBUM_ENTITY);
        ALBUM_REST_LIST.add(ALBUM_REST);

        ALBUM_ENTITY_PAGE = new PageImpl<>(ALBUM_ENTITY_LIST);
        ALBUM_REST_PAGE = new PageImpl<>(ALBUM_REST_LIST);

    }


    @Test
    public void getAllAlbums() throws SpotifyException {
        when(albumRepository.findAll(Mockito.any(Pageable.class))).thenReturn(ALBUM_ENTITY_PAGE);
        assertEquals(ALBUM_REST_PAGE.getContent(),
                albumService.getAllAlbums(Pageable.unpaged()).getContent());
    }


    @Test
    public void getAlbumById() throws SpotifyException {
        doReturn(ALBUM_ENTITY).when(albumRepository).findById(1L);
        Optional<AlbumEntity> optionalAlbum = Optional.of(ALBUM_ENTITY);
        when(albumRepository.findById(Mockito.anyLong())).thenReturn(optionalAlbum);
        Assertions.assertEquals(albumService.getAlbumById(1L), ALBUM_REST);
    }


    @Test/*(expected = ErrorDto.class)*/
    public void getAlbumByIdNotFoundException() throws SpotifyException {
        Optional<AlbumEntity> optionalAlbum = Optional.of(ALBUM_ENTITY);
        when(albumRepository.findById(Mockito.anyLong())).thenReturn(optionalAlbum);
    }


    @Test
    public void getSongsOfAlbum() throws SpotifyException {
        Optional<AlbumEntity> optionalAlbum = Optional.of(ALBUM_ENTITY);
        when(albumRepository.findById(Mockito.anyLong())).thenReturn(optionalAlbum);
        when(albumMapper.mapToRest(Mockito.any(AlbumEntity.class)).getSongs()).thenReturn(SONG_REST_ALBUMS_LIST);
        Assertions.assertEquals(albumService.getSongsOfAlbum(Pageable.unpaged(), 1L).getContent(), SONG_REST_ALBUMS_LIST);
    }


    @Test
    public void getArtistsOfAlbum() throws SpotifyException {
    }

    @Test
    public void createAlbum() throws SpotifyException {
        Assertions.assertEquals(ALBUM_REST, albumService.createAlbum(ALBUM_ENTITY));
    }

    @Test
    public void updateAlbum() throws SpotifyException {
    }

    @Test
    public void deleteAlbum() throws SpotifyException {
    }

    @Test
    public void addSongOfAlbum() throws SpotifyException {
    }

    @Test
    public void deleteSongOfAlbum() throws SpotifyException {
    }
}