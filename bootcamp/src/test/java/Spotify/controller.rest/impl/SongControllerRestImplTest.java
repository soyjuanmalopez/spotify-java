package Spotify.controller.rest.impl;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.*;


import java.util.Collections;

import Spotify.controller.rest.model.D4iPageRest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import Spotify.persistence.entity.SongEntity;
import Spotify.controller.rest.impl.SongControllerRestImpl;
import Spotify.controller.rest.model.SpotifyResponse;
import Spotify.controller.rest.model.SongRest;
import Spotify.exception.SpotifyException;
import Spotify.service.SongService;
import Spotify.util.constant.RestConstantsUtils;

public class SongControllerRestImplTest {

	static final Long ID = 1L;
	static final SongEntity SONG_ENTITY = new SongEntity();
	static final SongRest SONG_REST = new SongRest();


    @Mock
    private SongService songService;

	//@Mock
	//private SongRestMapper songRestMapper;

    @InjectMocks
    private SongControllerRestImpl songControllerRestImpl;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		SONG_ENTITY.setId(ID);
		SONG_REST.setId(ID);
	}
/*
    @Test
    public void getAllSongsTest() throws SpotifyException {
		// given
		int page = 0;
		int size = 2;
		int pageNumber = 1;
		Pageable pageable = PageRequest.of(page, size);

		SongRest songRest2 = new SongRest();
		songRest2.setId(2L);

		Page<SongDto> songsPagedModel = new PageImpl<>(Collections.singletonList(new SongDto()));
		Mockito.when(songService.getAllSongs(any(Pageable.class))).thenReturn(songsPagedModel);
		Mockito.when(songRestMapper.mapToRest(any(SongDto.class))).thenReturn(SONG_REST);

		// when
		SpotifyResponse<D4iPageRest<SongRest>> response = songControllerRestImpl.getAllSongs(page, size, pageable);

		// then
		assertNotNull(response);
		assertEquals(RestConstantsUtils.OK, response.getMessage());
		assertNotNull(response.getData().getContent());
	}

    @Test
    public void getSongByIdTest() throws SpotifyException {
		Mockito.when(songService.getSongById(anyLong())).thenReturn(SONG_DTO);
		Mockito.when(songRestMapper.mapToRest(any(SongDto.class))).thenReturn(SONG_REST);

		SpotifyResponse<SongRest> response = songControllerRestImpl.getSongById(ID);

		assertNotNull(response);
		assertEquals(String.valueOf(HttpStatus.OK), response.getStatus());
		assertEquals(RestConstantsUtils.OK, response.getMessage());
		assertEquals(SONG_REST, response.getData());
	}

    @Test
    public void createSongTest() throws SpotifyException {
		Mockito.when(songService.createSong(any(SongDto.class))).thenReturn(SONG_DTO);
		Mockito.when(songRestMapper.mapToDto(any(SongRest.class))).thenReturn(SONG_DTO);
		Mockito.when(songRestMapper.mapToRest(any(SongDto.class))).thenReturn(SONG_REST);

		SpotifyResponse<SongRest> response = songControllerRestImpl.createSong(SONG_REST);

		assertNotNull(response);
		assertEquals(String.valueOf(HttpStatus.OK), response.getStatus());
		assertEquals("200", response.getCode());
		assertEquals(RestConstantsUtils.OK, response.getMessage());
		assertEquals(SONG_REST, response.getData());
	}

    @Test
    public void deleteSongTest() throws SpotifyException {
		songControllerRestImpl.deleteSong(ID);

		Mockito.verify(songService, Mockito.times(1)).deleteSong(Mockito.anyLong());

	}

    @Test
    public void updateSongTest() throws SpotifyException {
		SongDto songDtoModified = new SongDto();
		songDtoModified.setId(2L);
		Mockito.when(songService.updateSong(any(SongDto.class))).thenReturn(songDtoModified);

		SpotifyResponse<SongRest> response = songControllerRestImpl.updateSong(SONG_REST);

		assertNotNull(response);
		assertEquals(String.valueOf(HttpStatus.OK), response.getStatus());
		assertEquals("200", response.getCode());
		assertEquals(RestConstantsUtils.OK, response.getMessage());
	}*/
}
