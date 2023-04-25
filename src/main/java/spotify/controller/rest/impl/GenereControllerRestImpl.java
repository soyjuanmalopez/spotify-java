package spotify.controller.rest.impl;

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
import spotify.controller.rest.GenereControllerRest;
import spotify.controller.rest.model.*;
import spotify.controller.rest.model.restSongs.GenereSongRest;
import spotify.exception.SpotifyException;
import spotify.mapper.GenereMapper;
import spotify.service.GenereService;
import spotify.util.constant.CommonConstantsUtils;
import spotify.util.constant.RestConstantsUtils;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Tag(name = "Genere", description = "Genere controller")
public class GenereControllerRestImpl implements GenereControllerRest {

    private final GenereService genereService;

    private final GenereMapper genereMapper;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstantsUtils.RESOURCE_GENRE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "getAllGeneres", description = "Get all Generes paginated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public SpotifyResponse<D4iPageRest<GenereRest>> getAllGeneres(
            @RequestParam(defaultValue = CommonConstantsUtils.ZERO) final Long page,
            @RequestParam(defaultValue = CommonConstantsUtils.TWENTY) final Long size,
            @Parameter(hidden = true) final Pageable pageable) throws SpotifyException {

        final Page<GenereRest> genereRestList = genereService.getAllGeneres(pageable);
        return new SpotifyResponse<>(HttpStatus.OK.toString(),
                String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK,
                new D4iPageRest<>(genereRestList.getContent().toArray(GenereRest[]::new),
                        new D4iPaginationInfo(genereRestList.getNumber(),
                                pageable.getPageSize(),
                                genereRestList.getTotalPages())));
    }


    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstantsUtils.RESOURCE_GENRE + RestConstantsUtils.RESOURCE_GENREID, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "getGenereById", description = "Get a genere by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public SpotifyResponse<GenereRest> getGenereById(@RequestParam final Long id) throws SpotifyException {
        return new SpotifyResponse<>(HttpStatus.OK.toString()
                , String.valueOf(HttpStatus.OK.value())
                , CommonConstantsUtils.OK
                , (genereService.getGenereById(id)));

    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "getSongByGenereId", description = "Get an album from a song")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @GetMapping(value = RestConstantsUtils.RESOURCE_GENRE + RestConstantsUtils.RESOURCE_GENREID + RestConstantsUtils.RESOURCE_SONG)
    public SpotifyResponse<List<SongRest>> getSongByGenereId(final Long genereId) throws SpotifyException {
        return new SpotifyResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK, (genereService.getSongsByGenereId(genereId)));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = RestConstantsUtils.RESOURCE_GENRE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "createGenere", description = "createGenere")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public SpotifyResponse<GenereRest> createGenere(@RequestBody final GenereRest genere) throws SpotifyException {
        final GenereRest genereRest =
                genereService.createGenere(genere);
        return new SpotifyResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK, genereRest);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = RestConstantsUtils.RESOURCE_GENRE)
    @Operation(summary = "updateGenere", description = "updateGenere")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public SpotifyResponse<GenereRest> updateGenere(@RequestBody final GenereRest genere) throws SpotifyException {
        return new SpotifyResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK, genereService.updateGenere(genere));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "updateSongByGenereId", description = "updateSongByGenereId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @PutMapping(value = RestConstantsUtils.RESOURCE_GENRE + RestConstantsUtils.RESOURCE_GENREID + RestConstantsUtils.RESOURCE_SONG + RestConstantsUtils.RESOURCE_SONGID)
    public SpotifyResponse<GenereSongRest> updateSongByGenereId(@RequestParam final Long genereId, @RequestParam final Long songId) throws SpotifyException {
        return new SpotifyResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK, genereService.updateSongByGenereId(genereId, songId));
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = RestConstantsUtils.RESOURCE_GENRE + RestConstantsUtils.RESOURCE_GENREID)
    @Operation(summary = "deleteGeneres", description = "Delte a certain genere")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public void deleteGenere(@RequestParam final Long id) throws SpotifyException {
        genereService.deleteGenere(id);
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = RestConstantsUtils.RESOURCE_GENRE + RestConstantsUtils.RESOURCE_GENREID + RestConstantsUtils.RESOURCE_SONG + RestConstantsUtils.SONGID)
    @Operation(summary = "deleteSongFromGenereById", description = "Delte a certain song from its genere")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public void deleteSongFromGenereById(@RequestParam final Long genereId, @RequestParam final Long songId)
            throws SpotifyException {
        genereService.deleteSongFromGenereById(genereId, songId);
    }
}
