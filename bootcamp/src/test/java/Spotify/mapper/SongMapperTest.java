package Spotify.mapper;

import Spotify.controller.rest.model.ArtistRest;
import Spotify.controller.rest.model.GenreRest;
import Spotify.controller.rest.model.SongRest;
import Spotify.controller.rest.model.restSongs.AlbumRestSong;
import Spotify.persistence.entity.AlbumEntity;
import Spotify.persistence.entity.ArtistEntity;
import Spotify.persistence.entity.GenreEntity;
import Spotify.persistence.entity.SongEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

public class SongMapperTest {

    Long id = 1L;
    String title= "titulo";
    Integer reproductions= 300;
    Double duration= 3.90;
    AlbumRestSong albumRest = new AlbumRestSong();
    AlbumEntity albumEntity = new AlbumEntity();
    Set<ArtistRest> artistRestSet = new HashSet<>();
    ArtistRest artistRest = new ArtistRest();
    Set<GenreRest> genreRestSet = new HashSet<>();
    GenreRest genreRest = new GenreRest();
    List<ArtistEntity> artistEntityList = new ArrayList<>();
    ArtistEntity artistEntity = new ArtistEntity();
    Set<GenreEntity> genreEntitySet = new HashSet<>();
    GenreEntity genreEntity = new GenreEntity();

    @InjectMocks
    SongMapperImpl songMapperImpl;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        artistRestSet.add(null);
        artistRestSet.add(artistRest);
        artistEntityList.add(null);
        artistEntityList.add(artistEntity);
        genreEntitySet.add(null);
        genreEntitySet.add(genreEntity);
        genreRestSet.add(null);
        genreRestSet.add(genreRest);
    }

    @Test
    public void mapToEntity() {
        SongRest song = new SongRest();
        song.setId(id);
        song.setTitle(title);
        song.setReproductions(reproductions);
        song.setDuration(duration);
        song.setAlbum(albumRest);
        song.setGenres(genreRestSet);
        song.setArtists(artistRestSet);

        SongEntity response = songMapperImpl.mapToEntity(song);

        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getTitle()).isEqualTo(title);
        assertThat(response.getDuration()).isEqualTo(duration);
        assertThat(response.getReproductions()).isEqualTo(reproductions);
        assertThat(response.getAlbum()).usingRecursiveComparison().isEqualTo(albumEntity);
        assertThat(response.getGenres()).usingRecursiveComparison().isEqualTo(genreEntitySet);
        assertThat(response.getArtists()).usingRecursiveComparison().isEqualTo(artistEntityList);


    }

    @Test
    public void mapToEntityListSetAndAlbumNull() {
        SongRest song = new SongRest();
        song.setAlbum(null);
        song.setGenres(null);
        song.setArtists(null);

        SongEntity response = songMapperImpl.mapToEntity(song);

        assertThat(response.getAlbum()).isEqualTo(null);
        assertThat(response.getGenres()).isEqualTo(null);
        assertThat(response.getArtists()).isEqualTo(null);


    }
    @Test
    public void mapToEntityNull() {
        SongEntity response = songMapperImpl.mapToEntity((SongRest) null);
        assertThat(response).isEqualTo(null);
    }

    @Test
    public void mapToRest() {
        SongEntity song = new SongEntity();
        song.setId(id);
        song.setTitle(title);
        song.setReproductions(reproductions);
        song.setDuration(duration);
        song.setAlbum(albumEntity);
        song.setGenres(genreEntitySet);
        song.setArtists(artistEntityList);

        SongRest response = songMapperImpl.mapToRest(song);

        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getTitle()).isEqualTo(title);
        assertThat(response.getDuration()).isEqualTo(duration);
        assertThat(response.getReproductions()).isEqualTo(reproductions);
        assertThat(response.getAlbum()).usingRecursiveComparison().isEqualTo(albumRest);
        assertThat(response.getGenres()).usingRecursiveComparison().isEqualTo(genreRestSet);
        assertThat(response.getArtists()).usingRecursiveComparison().isEqualTo(artistRestSet);
    }
    @Test
    public void mapToRestSetsAndAlbumNull() {
        SongEntity song = new SongEntity();
        song.setAlbum(null);
        song.setGenres(null);
        song.setArtists(null);

        SongRest response = songMapperImpl.mapToRest(song);

        assertThat(response.getAlbum()).isEqualTo(null);
        assertThat(response.getGenres()).isEqualTo(null);
        assertThat(response.getArtists()).isEqualTo(null);

    }
    @Test
    public void mapToRestNull() {
        SongRest response = songMapperImpl.mapToRest(null);
        assertThat(response).isEqualTo(null);
    }

}