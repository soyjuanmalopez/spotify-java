package Spotify.controller.rest.impl;

import Spotify.controller.rest.ArtistControllerRest;
import Spotify.controller.rest.model.*;
import Spotify.exception.SpotifyException;
import Spotify.mapper.ArtistMapper;
import Spotify.persistence.entity.ArtistEntity;
import Spotify.service.ArtistService;
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

@RestController
@RequiredArgsConstructor
@Tag(name = "Artist", description = "Artist controller")
public class ArtistControllerRestImpl implements ArtistControllerRest {

    @Autowired
    private final ArtistService artistService;

    @Autowired
    private final ArtistMapper artistMapper;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstantsUtils.RESOURCE_ARTISTS, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "getAllArtists", description = "Get all the Artists paginated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public SpotifyResponse<D4iPageRest<ArtistRest>> getAllArtists(
            @RequestParam(defaultValue = CommonConstantsUtils.ZERO) final int page,
            @RequestParam(defaultValue = CommonConstantsUtils.TWENTY) final int size,
            @Parameter(hidden = true) final Pageable pageable) throws SpotifyException {
        final Page<ArtistRest> artistRestList = artistService.getAllArtists(pageable);
        return new SpotifyResponse<>(HttpStatus.OK.toString(),
                String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK,
                new D4iPageRest<>(artistRestList.getContent().toArray(ArtistRest[]::new),
                        new D4iPaginationInfo(artistRestList.getNumber(),
                                pageable.getPageSize(),
                                artistRestList.getTotalPages())));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstantsUtils.RESOURCE_ARTIST + RestConstantsUtils.RESOURCE_ARTIST_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "getArtistById", description = "Get one Artist by given id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)

    })
    public SpotifyResponse<ArtistRest> getArtistById(@PathVariable(value = RestConstantsUtils.ARTIST_ID) final Long id) throws SpotifyException {
        return new SpotifyResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK,artistService.getArtistById(id));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = RestConstantsUtils.RESOURCE_ARTIST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "ArtistClient", description = "Create a new Artist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public SpotifyResponse<ArtistRest> createArtist(@RequestBody final ArtistRest artist) throws SpotifyException {
        final ArtistEntity artistEntity = artistMapper.mapToEntity(artist);
        return new SpotifyResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK,
                artistService.createArtist(artistEntity));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = RestConstantsUtils.RESOURCE_ARTIST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "UpdateArtistStatus", description = "Update Artist status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)

    })
    public SpotifyResponse<ArtistRest> updateArtist(@RequestBody final ArtistRest artist) throws SpotifyException {
        final ArtistEntity artistEntity = artistMapper.mapToEntity(artist);
        return new SpotifyResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK,
                artistService.updateArtist(artistEntity));
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = RestConstantsUtils.RESOURCE_ARTIST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "DeleteArtist", description = "Delete an existing Artist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)

    })
    public void deleteArtist(@PathVariable(value = RestConstantsUtils.ARTIST_ID) Long id) throws SpotifyException {
        artistService.deleteArtist(id);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstantsUtils.RESOURCE_ARTISTS
            + RestConstantsUtils.RESOURCE_ARTIST_ID
            + RestConstantsUtils.RESOURCE_ALBUMS
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "getAlbumsOfAnArtist", description = "Get all the Albums of an Artist paginated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public SpotifyResponse<D4iPageRest<AlbumRest>> getAlbumsOfArtist(int page, int size, Pageable pageable, Long id) throws SpotifyException {
        Page<AlbumRest> albumRestPage = artistService.getAlbumsOfArtist(pageable,id);
        return new SpotifyResponse<>(HttpStatus.OK.toString(),
                String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK,
                new D4iPageRest<>(albumRestPage.getContent().toArray(AlbumRest[]::new),
                        new D4iPaginationInfo(albumRestPage.getNumber(),
                                pageable.getPageSize(),
                                albumRestPage.getTotalPages())));
    }
}
