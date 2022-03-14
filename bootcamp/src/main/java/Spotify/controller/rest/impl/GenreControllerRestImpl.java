package Spotify.controller.rest.impl;

import Spotify.controller.rest.GenreControllerRest;
import Spotify.controller.rest.model.*;
import Spotify.controller.rest.model.restSongs.GenreSongRest;
import Spotify.exception.SpotifyException;
import Spotify.mapper.GenreMapper;
import Spotify.service.GenreService;
import Spotify.util.constant.CommonConstantsUtils;
import Spotify.util.constant.RestConstantsUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Tag(name = "Genre", description = "Genre controller")
public class GenreControllerRestImpl implements GenreControllerRest {

    private final GenreService genreService;

    private final GenreMapper genreMapper;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstantsUtils.RESOURCE_GENRE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "getAllGenres", description = "Get all Genres paginated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public SpotifyResponse<D4iPageRest<GenreRest>> getAllGenres(
    @RequestParam(defaultValue = CommonConstantsUtils.ZERO) final Long page,
    @RequestParam(defaultValue = CommonConstantsUtils.TWENTY) final Long size,
    @Parameter(hidden = true) final Pageable pageable) throws SpotifyException {

        final Page<GenreRest> genreRestList = genreService.getAllGenres(pageable);
        return new SpotifyResponse<>(HttpStatus.OK.toString(),
                String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK,
                new D4iPageRest<>(genreRestList.getContent().toArray(GenreRest[]::new),
                        new D4iPaginationInfo(genreRestList.getNumber(),
                                pageable.getPageSize(),
                                genreRestList.getTotalPages())));
    }


    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstantsUtils.RESOURCE_GENRE+RestConstantsUtils.RESOURCE_GENREID, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "getGenreById", description = "Get a genre by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public SpotifyResponse<GenreRest> getGenreById(@RequestParam final Long id) throws SpotifyException {
        return new SpotifyResponse<>(HttpStatus.OK.toString()
                , String.valueOf(HttpStatus.OK.value())
                ,CommonConstantsUtils.OK
                ,(genreService.getGenreById(id)));

    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "getSongByGenreId", description = "Get an album from a song")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @GetMapping(value = RestConstantsUtils.RESOURCE_GENRE + RestConstantsUtils.RESOURCE_GENREID + RestConstantsUtils.RESOURCE_SONG)
    public SpotifyResponse<List<SongRest>> getSongByGenreId(final Long genreId) throws SpotifyException {
        return new SpotifyResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK,(genreService.getSongsByGenreId(genreId)));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = RestConstantsUtils.RESOURCE_GENRE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "createGenre", description = "createGenre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public SpotifyResponse<GenreRest> createGenre(@RequestBody final GenreRest genre) throws SpotifyException {
        final GenreRest genreRest =
                genreService.createGenre(genre);
        return new SpotifyResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK, genreRest);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = RestConstantsUtils.RESOURCE_GENRE)
    @Operation(summary = "updateGenre", description = "updateGenre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public SpotifyResponse<GenreRest> updateGenre(@RequestBody final GenreRest genre) throws SpotifyException {
        return new SpotifyResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK,genreService.updateGenre(genre));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "updateSongByGenreId", description = "updateSongByGenreId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @PutMapping(value = RestConstantsUtils.RESOURCE_GENRE+RestConstantsUtils.RESOURCE_GENREID+RestConstantsUtils.RESOURCE_SONG + RestConstantsUtils.RESOURCE_SONGID)
    public SpotifyResponse<GenreSongRest> updateSongByGenreId(@RequestParam final Long genreId, @RequestParam final Long songId) throws SpotifyException {
        return new SpotifyResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK,genreService.updateSongByGenreId(genreId,songId));
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = RestConstantsUtils.RESOURCE_GENRE+RestConstantsUtils.RESOURCE_GENREID)
    @Operation(summary = "deleteGenres", description = "Delte a certain genre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public void deleteGenre(@RequestParam final Long id) throws SpotifyException {
    genreService.deleteGenre(id);
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = RestConstantsUtils.RESOURCE_GENRE+RestConstantsUtils.RESOURCE_GENREID+RestConstantsUtils.RESOURCE_SONG+RestConstantsUtils.SONGID)
    @Operation(summary = "deleteSongFromGenreById", description = "Delte a certain song from its genre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public void deleteSongFromGenreById(@RequestParam final Long genreId,@RequestParam final Long songId)
            throws SpotifyException {
    genreService.deleteSongFromGenreById(genreId,songId);
    }
}
