package Spotify.controller.rest.impl;

import Spotify.controller.rest.ArtistControllerRest;
import Spotify.controller.rest.model.*;
import Spotify.controller.rest.model.restArtists.PostArtistRest;
import Spotify.exception.SpotifyException;
import Spotify.mapper.PostArtistMapper;
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

    private final ArtistService artistService;

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
    public SpotifyResponse<PostArtistRest> createArtist(@RequestBody final PostArtistRest artist) throws SpotifyException {
        final PostArtistRest postArtistRest = artistService.createArtist(artist);
        return new SpotifyResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK, postArtistRest);
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
    public SpotifyResponse<PostArtistRest> updateArtist(@RequestBody final PostArtistRest artist) throws SpotifyException {
        return new SpotifyResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK,artistService.updateArtist(artist));
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
    public void deleteArtist(@PathVariable(value = RestConstantsUtils.ARTIST_ID) final Long id) throws SpotifyException {
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
    public SpotifyResponse<D4iPageRest<AlbumRest>> getAlbumsOfArtist(
            @RequestParam(defaultValue = CommonConstantsUtils.ZERO) final int page,
            @RequestParam(defaultValue = CommonConstantsUtils.TWENTY) final int size,
            @Parameter(hidden = true) final Pageable pageable, final Long id) throws SpotifyException {
        final Page<AlbumRest> albumRestPage = artistService.getAlbumsOfArtist(pageable,id);
        return new SpotifyResponse<>(HttpStatus.OK.toString(),
                String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK,
                new D4iPageRest<>(albumRestPage.getContent().toArray(AlbumRest[]::new),
                        new D4iPaginationInfo(albumRestPage.getNumber(),
                                pageable.getPageSize(),
                                albumRestPage.getTotalPages())));
    }
}
