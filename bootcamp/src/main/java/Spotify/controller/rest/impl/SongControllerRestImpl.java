package Spotify.controller.rest.impl;

import Spotify.controller.rest.SongControllerRest;
import Spotify.controller.rest.mapper.SongRestMapper;
import Spotify.controller.rest.model.SongRest;
import Spotify.controller.rest.model.D4iPageRest;
import Spotify.controller.rest.model.D4iPaginationInfo;
import Spotify.controller.rest.model.SpotifyResponse;
import Spotify.exception.SpotifyException;
import Spotify.service.SongService;
import Spotify.util.constant.CommonConstantsUtils;
import Spotify.util.constant.RestConstantsUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Song", description = "Song rest")
public class SongControllerRestImpl implements SongControllerRest {

    private final SongService songService;
    private final SongRestMapper songRestMapper;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstantsUtils.RESOURCE_SONG, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "getAllSongs", description = "Get all Songs paginated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public SpotifyResponse<D4iPageRest<SongRest>> getAllSongs(
      @RequestParam(defaultValue = CommonConstantsUtils.ZERO) final int page,
	    @RequestParam(defaultValue = CommonConstantsUtils.TWENTY) final int size, 
      @Parameter(hidden = true) final Pageable pageable) throws SpotifyException {
        final Page<SongRest> songRestList = songService.getAllSongs(pageable).map(song -> songRestMapper.mapToRest(song));
        return new SpotifyResponse<>(HttpStatus.OK.toString(),
                                    String.valueOf(HttpStatus.OK.value()),
                                    CommonConstantsUtils.OK,
                                    new D4iPageRest<>(songRestList.getContent().toArray(SongRest[]::new),
                                        new D4iPaginationInfo(songRestList.getNumber(),
                                                                pageable.getPageSize(),
                                                                songRestList.getTotalPages())));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "getSongById", description = "Get one Song by given id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @GetMapping(value = RestConstantsUtils.RESOURCE_SONG + RestConstantsUtils.RESOURCE_SONGID)
    public SpotifyResponse<SongRest> getSongById(@PathVariable(value = RestConstantsUtils.SONGID) final Long id)
	    throws SpotifyException {
	return new SpotifyResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
		CommonConstantsUtils.OK,songRestMapper.mapToRest(songService.getSongById(id)));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = RestConstantsUtils.RESOURCE_SONG)
    @Operation(summary = "SongClient", description = "Create a new Song")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public SpotifyResponse<SongRest> createSong(@RequestBody final SongRest song) throws SpotifyException {
        final SongRest songRest = songRestMapper.mapToRest(
                songService.createSong(songRestMapper.mapToDto(song)));
        return new SpotifyResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                                    CommonConstantsUtils.OK, songRest);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "updateSongStatus", description = "Update Song status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @PutMapping(value = RestConstantsUtils.RESOURCE_SONG)
    public SpotifyResponse<SongRest> updateSong(@RequestBody final SongRest songRest) throws SpotifyException {
	return new SpotifyResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
		CommonConstantsUtils.OK,songRestMapper.mapToRest(songService.updateSong(songRestMapper.mapToDto(songRest))));
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "deleteSong", description = "Delete Song by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @DeleteMapping(value = RestConstantsUtils.RESOURCE_SONG + RestConstantsUtils.RESOURCE_SONGID)
    public void deleteSong(@PathVariable(value = RestConstantsUtils.SONGID) final Long id)
	    throws SpotifyException {
	      songService.deleteSong(id);
    }
}
