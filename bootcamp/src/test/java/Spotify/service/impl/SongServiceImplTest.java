/* package Spotify.service.impl;

import Spotify.controller.rest.model.SongRest;
import Spotify.exception.SpotifyException;
import Spotify.exception.SpotifyNotFoundException;
import Spotify.persistence.entity.SongEntity;
import Spotify.persistence.mapper.SongEntityMapper2;
import Spotify.persistence.repository.SongRepository;
import Spotify.service.model.SongDto;
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

import java.util.List;
import java.util.Optional;


import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;

public class SongServiceImplTest {

	static final Long ID = 1L;
	static final SongEntity SONG_ENTITY = new SongEntity();
	static final SongRest SONG_REST = new SongRest();
	static final SongDto SONG_DTO = new SongDto();

    @Mock
    private SongRepository songRepository;

	@Mock
	private SongEntityMapper2 songEntityMapper;

    @InjectMocks
    private SongServiceImpl songService;

    @Before
    public void init() {
	 MockitoAnnotations.initMocks(this);
		SONG_ENTITY.setId(ID);
		SONG_REST.setId(ID);


		Mockito.when(songRepository.findById(anyLong())).thenReturn(Optional.of(SONG_ENTITY));
		Mockito.when(songEntityMapper.mapToEntity(any(SongDto.class))).thenReturn(SONG_ENTITY);
		Mockito.when(songEntityMapper.mapToDto(any(SongEntity.class))).thenReturn(SONG_DTO);

	}

    @Test
    public void getAllSongsTest() throws SpotifyException {
		Pageable pageable = PageRequest.of(0, 1);

		Page<SongEntity> songPage = new PageImpl<>(List.of(SONG_ENTITY), pageable, 0);
		Mockito.when(songRepository.findAll(any(Pageable.class))).thenReturn(songPage);

		Page<SongRest> pagedModel = songService.getAllSongs(pageable);

		assertNotNull(pagedModel);
    }

    @Test
    public void getSongByIdTest() throws SpotifyException {
		SongDto response = songService.getSongById(ID);

		assertEquals(ID, response.getId());
    }

    @Test
    public void createSongTest() throws SpotifyException {
		Mockito.when(songRepository.save(SONG_ENTITY)).thenReturn(SONG_ENTITY);

		SongDto songDtoOut = songService.createSong(SONG_DTO);

		assertEquals(SONG_DTO,songDtoOut);
		Mockito.verify(songRepository, Mockito.times(1)).save(Mockito.any(SongEntity.class));
    }

    @Test
    public void updateSongTest() throws SpotifyException {
		Mockito.when(songRepository.save(any())).thenReturn(SONG_ENTITY);

		SongDto songDtoOut = songService.updateSong(SONG_DTO);
		assertEquals(SONG_DTO,songDtoOut);
		Mockito.verify(songRepository, Mockito.times(1)).save(Mockito.any(SongEntity.class));
    }

    @Test(expected = SpotifyNotFoundException.class)
    public void updateSongButNotExists() throws SpotifyException {
		Mockito.when(songRepository.findById(ID)).thenReturn(Optional.empty());

		songService.updateSong(SONG_DTO);
	}

    @Test
    public void deleteSong() throws SpotifyException {

		songService.deleteSong(1L);

		Mockito.verify(songRepository, Mockito.times(1)).deleteById(anyLong());
	}
} */