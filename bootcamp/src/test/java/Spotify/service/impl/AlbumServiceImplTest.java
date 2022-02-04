package Spotify.service.impl;

import Spotify.controller.rest.model.AlbumRest;
import Spotify.controller.rest.model.restAlbums.SongRestAlbum;
import Spotify.exception.SpotifyException;
import Spotify.exception.error.ErrorDto;
import Spotify.mapper.AlbumMapper;
import Spotify.mapper.SongMapper;
import Spotify.persistence.entity.AlbumEntity;
import Spotify.persistence.entity.SongEntity;
import Spotify.persistence.repository.AlbumRepository;
import Spotify.persistence.repository.ArtistRepository;
import Spotify.persistence.repository.SongRepository;
import Spotify.util.constant.ExceptionConstantsUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class AlbumServiceImplTest {

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private SongRepository songRepository;

    @Mock
    private AlbumMapper albumMapper;

    @Mock
    private SongMapper songMapper;

    @InjectMocks
    private AlbumServiceImpl albumService = new AlbumServiceImpl(albumRepository, songRepository, albumMapper, songMapper);

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

    @BeforeEach
    void setUp() {
        SONG_ENTITY_LIST.clear();
        SONG_REST_ALBUMS_LIST.clear();

        MockitoAnnotations.initMocks(this);
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
    void getAllAlbums() throws SpotifyException {
        when(albumRepository.findAll(Mockito.any(Pageable.class))).thenReturn(ALBUM_ENTITY_PAGE);
        assertEquals(ALBUM_REST_PAGE.getContent(),
                albumService.getAllAlbums(Pageable.unpaged()).getContent());
    }

    @Test
    void getAlbumById() throws SpotifyException{
        Optional<AlbumEntity> optionalAlbum = Optional.of(ALBUM_ENTITY);
        when(albumRepository.findById(Mockito.anyLong())).thenReturn(optionalAlbum);

    }

    @Test/*(expected = ErrorDto.class)*/
    void getAlbumByIdNotFoundException() throws SpotifyException{
        Optional<AlbumEntity> optionalAlbum = Optional.of(ALBUM_ENTITY);
        when(albumRepository.findById(Mockito.anyLong())).thenReturn(optionalAlbum);

    }

    @Test
    void getSongsOfAlbum() throws SpotifyException{
    }

    @Test
    void getArtistsOfAlbum() throws SpotifyException{
    }

    @Test
    void createAlbum() throws SpotifyException{
    }

    @Test
    void updateAlbum() throws SpotifyException{
    }

    @Test
    void deleteAlbum() throws SpotifyException{
    }

    @Test
    void addSongOfAlbum() throws SpotifyException{
    }

    @Test
    void deleteSongOfAlbum() throws SpotifyException{
    }
}