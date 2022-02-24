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
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class AlbumServiceImplTest {

    @Mock
    public AlbumRepository albumRepository;

    @Mock
    public SongRepository songRepository;

    @Mock
    public AlbumMapper albumMapper;

    @Mock
    public SongMapper songMapper;

    @InjectMocks
    public AlbumServiceImpl albumService = new AlbumServiceImpl();


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


    @Before
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
        when(albumMapper.mapToRest(Mockito.any(AlbumEntity.class))).thenReturn(ALBUM_REST);
        Page<AlbumRest> albumRests = albumService.getAllAlbums(Pageable.unpaged());
        assertEquals(ALBUM_REST_PAGE.getContent(), albumRests.getContent());
    }


    @Test
    public void getAlbumById() throws SpotifyException {
        Optional<AlbumEntity> optionalAlbum = Optional.of(ALBUM_ENTITY);
        when(albumRepository.findById(Mockito.anyLong())).thenReturn(optionalAlbum);
        when(albumMapper.mapToRest(Mockito.any(AlbumEntity.class))).thenReturn(ALBUM_REST);
        assertEquals(albumService.getAlbumById(1L), ALBUM_REST);
    }


    @Test(expected = SpotifyException.class)
    public void getAlbumByIdNullPointerException() throws SpotifyException {
        when(albumRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        albumService.getAlbumById(-1L);
    }


    @Test
    public void getSongsOfAlbum() throws SpotifyException {
        Optional<AlbumEntity> optionalAlbum = Optional.of(ALBUM_ENTITY);
        when(albumRepository.findById(Mockito.anyLong())).thenReturn(optionalAlbum);
        when(albumMapper.mapToRest(Mockito.any(AlbumEntity.class))).thenReturn(ALBUM_REST);
        assertEquals(albumService.getSongsOfAlbum(Pageable.unpaged(), 1L).getContent(), SONG_REST_ALBUMS_LIST);
    }

    @Test(expected = SpotifyException.class)
    public void getSongsOfAlbumNullPointerException() throws SpotifyException {
        when(albumRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        albumService.getSongsOfAlbum(Pageable.unpaged(), -1L);
    }


    @Test
    public void getArtistsOfAlbum() throws SpotifyException {
    }

    @Test
    public void createAlbum() throws SpotifyException {
        when(albumMapper.mapToRest(Mockito.any(AlbumEntity.class))).thenReturn(ALBUM_REST);
        assertEquals(ALBUM_REST, albumService.createAlbum(ALBUM_ENTITY));
    }

    @Test
    public void updateAlbum() throws SpotifyException {
        when(albumRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(ALBUM_ENTITY));
        when(albumMapper.mapToRest(Mockito.any(AlbumEntity.class))).thenReturn(ALBUM_REST);
        assertEquals(ALBUM_REST, albumService.updateAlbum(ALBUM_ENTITY, 1L));
    }

    @Test(expected = SpotifyException.class)
    public void updateAlbumNotFound() throws SpotifyException {
        when(albumRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        albumService.updateAlbum(ALBUM_ENTITY, -1L);
    }

    @Test
    public void deleteAlbum() throws SpotifyException {
        when(albumRepository.existsById(Mockito.anyLong())).thenReturn(true);
        albumService.deleteAlbum(1L);
    }

    @Test(expected = SpotifyException.class)
    public void deleteAlbumNotFound() throws SpotifyException {
        when(albumRepository.existsById(Mockito.anyLong())).thenReturn(false);
        albumService.deleteAlbum(-1L);
    }

    @Test
    public void addSongOfAlbum() throws SpotifyException {
        when(albumRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(ALBUM_ENTITY));
        when(songRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(SONG_ENTITY));
        when(albumMapper.mapToRest(Mockito.any(AlbumEntity.class))).thenReturn(ALBUM_REST);
        assertEquals(ALBUM_REST, albumService.addSongOfAlbum(1L, 1));
    }

    @Test(expected = SpotifyException.class)
    public void addSongOfAlbumNotFoundAlbum() throws SpotifyException {
        when(albumRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        when(songRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(SONG_ENTITY));
        albumService.addSongOfAlbum(1L, 1);
    }

    @Test(expected = SpotifyException.class)
    public void addSongOfAlbumNotFoundAlbumSong() throws SpotifyException {
        when(albumRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(ALBUM_ENTITY));
        when(songRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        albumService.addSongOfAlbum(1L, 1);
    }

    @Test(expected = SpotifyException.class)
    public void deleteSongOfAlbumNotFound() throws SpotifyException {
        when(albumRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        when(albumMapper.mapToRest(Mockito.any(AlbumEntity.class))).thenReturn(ALBUM_REST);
        assertEquals(ALBUM_REST, albumService.deleteSongOfAlbum(1L, 1));
    }


    @Test
    public void deleteSongOfAlbum() throws SpotifyException {
        when(albumRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(ALBUM_ENTITY));
        when(albumMapper.mapToRest(Mockito.any(AlbumEntity.class))).thenReturn(ALBUM_REST);
        assertEquals(ALBUM_REST, albumService.deleteSongOfAlbum(1L, 1));
    }
}