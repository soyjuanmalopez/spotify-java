package Spotify.service.impl;

import Spotify.mapper.AlbumMapper;
import Spotify.mapper.SongMapper;
import Spotify.persistence.repository.AlbumRepository;
import Spotify.persistence.repository.SongRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class AlbumServiceImplTest {

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private SongRepository songRepository;

    @Mock
    private AlbumMapper albumMapper;

    @Mock
    private SongMapper songMapper;

    @InjectMocks
    private AlbumServiceImpl albumService;

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllAlbums() {
    }

    @Test
    public void getAlbumById() {
    }

    @Test
    public void getSongsOfAlbum() {
    }

    @Test
    public void getArtistsOfAlbum() {
    }

    @Test
    public void createAlbum() {
    }

    @Test
    public void updateAlbum() {
    }

    @Test
    public void deleteAlbum() {
    }

    @Test
    public void addSongOfAlbum() {
    }

    @Test
    public void addArtistToAlbum() {
    }

    @Test
    public void deleteSongOfAlbum() {
    }

    @Test
    public void deleteArtistOfAlbum() {
    }
}