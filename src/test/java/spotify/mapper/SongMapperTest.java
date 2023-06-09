package spotify.mapper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import spotify.controller.rest.model.ArtistRest;
import spotify.controller.rest.model.GenereRest;
import spotify.controller.rest.model.SongRest;
import spotify.controller.rest.model.restSongs.AlbumRestSong;
import spotify.persistence.entity.AlbumEntity;
import spotify.persistence.entity.ArtistEntity;
import spotify.persistence.entity.GenereEntity;
import spotify.persistence.entity.SongEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SongMapperTest {

    Long id = 1L;
    String title = "titulo";
    Integer reproductions = 300;
    Double duration = 3.90;
    AlbumRestSong albumRest = new AlbumRestSong();
    AlbumEntity albumEntity = new AlbumEntity();
    Set<ArtistRest> artistRestSet = new HashSet<>();
    ArtistRest artistRest = new ArtistRest();
    Set<GenereRest> genereRestSet = new HashSet<>();
    GenereRest genereRest = new GenereRest();
    List<ArtistEntity> artistEntityList = new ArrayList<>();
    ArtistEntity artistEntity = new ArtistEntity();
    Set<GenereEntity> genereEntitySet = new HashSet<>();
    GenereEntity genereEntity = new GenereEntity();

    @InjectMocks
    SongMapperImpl songMapperImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        artistRestSet.add(null);
        artistRestSet.add(artistRest);
        artistEntityList.add(null);
        artistEntityList.add(artistEntity);
        genereEntitySet.add(null);
        genereEntitySet.add(genereEntity);
        genereRestSet.add(null);
        genereRestSet.add(genereRest);
    }

    @Test
    public void mapToEntity() {
        SongRest song = new SongRest();
        song.setId(id);
        song.setTitle(title);
        song.setReproductions(reproductions);
        song.setDuration(duration);
        song.setAlbum(albumRest);
        song.setGeneres(genereRestSet);
        song.setArtists(artistRestSet);

        SongEntity response = songMapperImpl.mapToEntity(song);

        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getTitle()).isEqualTo(title);
        assertThat(response.getDuration()).isEqualTo(duration);
        assertThat(response.getReproductions()).isEqualTo(reproductions);
        assertThat(response.getAlbum()).usingRecursiveComparison().isEqualTo(albumEntity);
        assertThat(response.getGeneres()).usingRecursiveComparison().isEqualTo(genereEntitySet);
        assertThat(response.getArtists()).usingRecursiveComparison().isEqualTo(artistEntityList);


    }

    @Test
    public void mapToEntityListSetAndAlbumNull() {
        SongRest song = new SongRest();
        song.setAlbum(null);
        song.setGeneres(null);
        song.setArtists(null);

        SongEntity response = songMapperImpl.mapToEntity(song);

        assertThat(response.getAlbum()).isNull();
        assertThat(response.getGeneres()).isNull();
        assertThat(response.getArtists()).isNull();


    }

    @Test
    public void mapToEntityNull() {
        SongEntity response = songMapperImpl.mapToEntity((SongRest) null);
        assertThat(response).isNull();
    }

    @Test
    public void mapToRest() {
        SongEntity song = new SongEntity();
        song.setId(id);
        song.setTitle(title);
        song.setReproductions(reproductions);
        song.setDuration(duration);
        song.setAlbum(albumEntity);
        song.setGeneres(genereEntitySet);
        song.setArtists(artistEntityList);

        SongRest response = songMapperImpl.mapToRest(song);

        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getTitle()).isEqualTo(title);
        assertThat(response.getDuration()).isEqualTo(duration);
        assertThat(response.getReproductions()).isEqualTo(reproductions);
        assertThat(response.getAlbum()).usingRecursiveComparison().isEqualTo(albumRest);
        assertThat(response.getGeneres()).usingRecursiveComparison().isEqualTo(genereRestSet);
        assertThat(response.getArtists()).usingRecursiveComparison().isEqualTo(artistRestSet);
    }

    @Test
    public void mapToRestSetsAndAlbumNull() {
        SongEntity song = new SongEntity();
        song.setAlbum(null);
        song.setGeneres(null);
        song.setArtists(null);

        SongRest response = songMapperImpl.mapToRest(song);

        assertThat(response.getAlbum()).isNull();
        assertThat(response.getGeneres()).isNull();
        assertThat(response.getArtists()).isNull();

    }

    @Test
    public void mapToRestNull() {
        SongRest response = songMapperImpl.mapToRest(null);
        assertThat(response).isNull();
    }

}