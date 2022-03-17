package Spotify.service.impl;

import Spotify.controller.rest.model.AlbumRest;
import Spotify.controller.rest.model.ArtistRest;
import Spotify.controller.rest.model.restArtists.PostArtistRest;
import Spotify.exception.SpotifyException;
import Spotify.exception.SpotifyNotFoundException;
import Spotify.mapper.ArtistMapper;
import Spotify.persistence.entity.ArtistEntity;
import Spotify.persistence.repository.ArtistRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ArtistServiceImplTest {

    static final Long ID = 1L;
    static final ArtistEntity ARTIST_ENTITY = new ArtistEntity();
    static final ArtistRest ARTIST_REST = new ArtistRest();
    static final List<AlbumRest> ALBUM_REST_LIST = new ArrayList<>();
    static final PostArtistRest POST_ARTIST_REST = new PostArtistRest();

    @Mock
    private ArtistRepository artistRepository;

    @Mock
    private ArtistMapper artistMapper;

    @InjectMocks
    private ArtistServiceImpl artistServiceImpl;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        ARTIST_ENTITY.setId(ID);
        ARTIST_REST.setId(ID);
        POST_ARTIST_REST.setId(1L);
        POST_ARTIST_REST.setDescription("desc");
        POST_ARTIST_REST.setName("name");

        Mockito.when(artistRepository.findById(anyLong())).thenReturn(Optional.of(ARTIST_ENTITY));
        Mockito.when(artistMapper.mapToEntity(any(ArtistRest.class))).thenReturn(ARTIST_ENTITY);
        Mockito.when(artistMapper.mapToRest(any(ArtistEntity.class))).thenReturn(ARTIST_REST);
        Mockito.when(artistMapper.mapToEntity(any(PostArtistRest.class))).thenReturn(ARTIST_ENTITY);
        Mockito.when(artistMapper.mapToRestPost(any(ArtistEntity.class))).thenReturn(POST_ARTIST_REST);
    }

    @Test
    public void getAllArtistsTest() throws SpotifyException {
        Pageable pageable = PageRequest.of(0, 1);
        Page<ArtistEntity> artistPage = new PageImpl<>(List.of(ARTIST_ENTITY), pageable, 0);

        Mockito.when(artistRepository.findAll(any(Pageable.class))).thenReturn(artistPage);

        Page<ArtistRest> pagedModel = artistServiceImpl.getAllArtists(pageable);

        assertNotNull(pagedModel);
    }

    @Test
    public void getArtistByIdTest() throws SpotifyException {
        Mockito.when(artistRepository.findById(anyLong())).thenReturn(Optional.of(ARTIST_ENTITY));
        Mockito.when(artistMapper.mapToRest(any(ArtistEntity.class))).thenReturn(ARTIST_REST);
        artistServiceImpl.getArtistById(anyLong());
    }

    @Test(expected = SpotifyNotFoundException.class)
    public void getArtistByIdFailTest() throws SpotifyException {
        Mockito.when(artistRepository.findById(anyLong())).thenReturn(Optional.empty());
        artistServiceImpl.getArtistById(anyLong());
    }

    @Test
    public void createArtistTest() throws SpotifyException {
        artistServiceImpl.createArtist(POST_ARTIST_REST);
        Mockito.verify(artistRepository, Mockito.times(1)).save(Mockito.any(ArtistEntity.class));
    }

    @Test
    public void updateArtistTest() throws SpotifyException {
        Mockito.when(artistRepository.findById(anyLong())).thenReturn(Optional.of(ARTIST_ENTITY));
        Mockito.when(artistRepository.save(ARTIST_ENTITY)).thenReturn(ARTIST_ENTITY);

        PostArtistRest artistRestOut = artistServiceImpl.updateArtist(POST_ARTIST_REST);
        assertEquals(POST_ARTIST_REST, artistRestOut);
        Mockito.verify(artistRepository, Mockito.times(1)).save(Mockito.any(ArtistEntity.class));
    }

    @Test(expected = SpotifyNotFoundException.class)
    public void updateArtistFailTest() throws SpotifyException {
        Mockito.when(artistRepository.findById(ID)).thenReturn(Optional.empty());
        artistServiceImpl.updateArtist(POST_ARTIST_REST);
    }

    @Test
    public void deleteArtistTest() throws SpotifyException {
        artistServiceImpl.deleteArtist(ID);
        Mockito.verify(artistRepository, Mockito.times(1)).deleteById(anyLong());
    }

    @Test
    public void getAlbumsOfArtistTest() throws SpotifyException {
        Pageable pageable = PageRequest.of(0, 1);
        ARTIST_REST.setAlbums(ALBUM_REST_LIST);
        Mockito.when(artistRepository.findById(anyLong())).thenReturn(Optional.of(ARTIST_ENTITY));
        Mockito.when(artistMapper.mapToRest(any(ArtistEntity.class))).thenReturn(ARTIST_REST);
        Page<AlbumRest> albumRestPage = artistServiceImpl.getAlbumsOfArtist(pageable, ID);
        assertNotNull(albumRestPage);
    }

    @Test(expected = SpotifyNotFoundException.class)
    public void getAlbumsOfArtistFailTest() throws SpotifyException {
        Pageable pageable = PageRequest.of(0, 1);
        Mockito.when(artistRepository.findById(ID)).thenReturn(Optional.empty());
        artistServiceImpl.getAlbumsOfArtist(pageable, ID);
    }
}
