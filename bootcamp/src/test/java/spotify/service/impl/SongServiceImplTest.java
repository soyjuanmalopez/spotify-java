package spotify.service.impl;

import org.assertj.core.api.Assertions;
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
import spotify.controller.rest.model.AlbumRest;
import spotify.controller.rest.model.SongRest;
import spotify.controller.rest.model.restSongs.PostSongRest;
import spotify.exception.SpotifyException;
import spotify.exception.SpotifyNotFoundException;
import spotify.mapper.AlbumMapper;
import spotify.mapper.SongMapper;
import spotify.persistence.entity.ArtistEntity;
import spotify.persistence.entity.SongEntity;
import spotify.persistence.repository.ArtistRepository;
import spotify.persistence.repository.SongRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

public class SongServiceImplTest {

    static final Long ID = 1L;
    static final SongEntity SONG_ENTITY = new SongEntity();
    static final Set<SongEntity> SONG_ENTITY_SET = new HashSet<>();
    static final SongRest SONG_REST = new SongRest();
    static final PostSongRest POST_SONG_REST = new PostSongRest();
    static final List<ArtistEntity> ARTIST_ENTITY_LIST = new ArrayList<>();
    static final AlbumRest ALBUM_REST = new AlbumRest();
    static final ArtistEntity ARTIST_ENTITY = new ArtistEntity();

    @Mock
    SongMapper songMapper;


    @Mock
    AlbumMapper albumMapper;

    @Mock
    ArtistRepository artistRepository;

    @Mock
    private SongRepository songRepository;
    ;

    @InjectMocks
    private SongServiceImpl songService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        ARTIST_ENTITY_LIST.add(ARTIST_ENTITY);
        SONG_ENTITY_SET.add(SONG_ENTITY);
        ARTIST_ENTITY.setSongs(SONG_ENTITY_SET);
        SONG_ENTITY.setArtists(ARTIST_ENTITY_LIST);
        ALBUM_REST.setId(ID);
        SONG_ENTITY.setId(ID);
        SONG_REST.setId(ID);
        ARTIST_ENTITY.setId(ID);
        POST_SONG_REST.setId(ID);


        Mockito.when(songRepository.findById(anyLong())).thenReturn(Optional.of(SONG_ENTITY));
        Mockito.when(songMapper.mapToRest(any(SongEntity.class))).thenReturn(SONG_REST);
        Mockito.when(songMapper.mapToEntity(any(SongRest.class))).thenReturn(SONG_ENTITY);
        Mockito.when(songMapper.mapToRestPost(any(SongEntity.class))).thenReturn(POST_SONG_REST);
        Mockito.when(songMapper.mapToEntity(any(PostSongRest.class))).thenReturn(SONG_ENTITY);
        when(artistRepository.findById(anyLong())).thenReturn(Optional.of(ARTIST_ENTITY));


    }

    @Test
    public void getAllSongs() throws SpotifyException {
        Pageable pageable = PageRequest.of(0, 1);
        Page<SongEntity> songPage = new PageImpl<>(List.of(SONG_ENTITY), pageable, 0);
        Page<SongRest> songRestPage = new PageImpl<>(List.of(SONG_REST), pageable, 0);

        Mockito.when(songRepository.findAll(any(Pageable.class))).thenReturn(songPage);
        Page<SongRest> response = songService.getAllSongs(pageable);

        Assertions.assertThat(response).isEqualTo(songRestPage);
    }

    @Test
    public void getSongById() throws SpotifyException {
        SongRest response = songService.getSongById(anyLong());

        Assertions.assertThat(response).isEqualTo(SONG_REST);
    }

    @Test(expected = SpotifyNotFoundException.class)
    public void getSongByIdException() throws SpotifyException {
        when(songRepository.findById(anyLong())).thenReturn(Optional.empty());
        songService.getSongById(ID);
    }

    @Test
    public void getAlbumBySongId() throws SpotifyException {
        when(albumMapper.mapToRest(any())).thenReturn(ALBUM_REST);
        AlbumRest response = songService.getAlbumBySongId(anyLong());

        Assertions.assertThat(response).isEqualTo(ALBUM_REST);
    }

    @Test(expected = SpotifyNotFoundException.class)
    public void getAlbumBySongIdException() throws SpotifyException {
        when(songRepository.findById(anyLong())).thenReturn(Optional.empty());
        songService.getAlbumBySongId(ID);
    }

    @Test
    public void createSong() throws SpotifyException { //PostSongRest
        songService.createSong(any(PostSongRest.class));
        Mockito.verify(songRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void updateSong() throws SpotifyException { //PostSongRest
        when(songRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(SONG_ENTITY));
        when(songMapper.mapToEntity(Mockito.any(PostSongRest.class))).thenReturn(SONG_ENTITY);
        when(songMapper.mapToRestPost(Mockito.any(SongEntity.class))).thenReturn(POST_SONG_REST);
        assertEquals(POST_SONG_REST, songService.updateSong(POST_SONG_REST));
    }

    @Test(expected = SpotifyNotFoundException.class)
    public void updateSongException() throws SpotifyException { //PostSongRest
        when(songRepository.findById(anyLong())).thenReturn(Optional.empty());
        PostSongRest response = songService.updateSong(POST_SONG_REST);

    }

    @Test
    public void updateArtistBySongId() throws SpotifyException {

        SongRest response = songService.updateArtistBySongId(ID, ID);
        verify(songRepository, times(1)).save(any(SongEntity.class));
        verify(artistRepository, times(1)).save(any(ArtistEntity.class));
        Assertions.assertThat(response).isEqualTo(SONG_REST);
    }

    @Test(expected = SpotifyNotFoundException.class)
    public void updateArtistBySongIdSongException() throws SpotifyException {
        when(songRepository.findById(anyLong())).thenReturn(Optional.empty());
        songService.updateArtistBySongId(ID, ID);
    }

    @Test(expected = SpotifyNotFoundException.class)
    public void updateArtistBySongIdArtistException() throws SpotifyException {
        when(artistRepository.findById(anyLong())).thenReturn(Optional.empty());
        songService.updateArtistBySongId(ID, ID);
    }

    @Test
    public void deleteSong() throws SpotifyException {
        songService.deleteSong(1L);
        Mockito.verify(songRepository, Mockito.times(1)).deleteById(ID);

    }

    @Test
    public void deleteArtistFromSongById() throws SpotifyException {
        songService.deleteArtistFromSongById(ID, ID);
        verify(songRepository, times(1)).save(any(SongEntity.class));
        verify(artistRepository, times(1)).save(any(ArtistEntity.class));
    }

    @Test(expected = SpotifyNotFoundException.class)
    public void deleteArtistFromSongByIdException() throws SpotifyException {
        when(songRepository.findById(anyLong())).thenReturn(Optional.empty());
        songService.deleteArtistFromSongById(ID, ID);
    }
}