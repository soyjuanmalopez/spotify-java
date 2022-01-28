package Spotify.controller.rest.impl;

import Spotify.controller.rest.AlbumControllerRest;
import Spotify.controller.rest.model.*;
import Spotify.controller.rest.model.restAlbums.SongRestAlbum;
import Spotify.controller.rest.model.restAlbums.SongRestAlbum;
import Spotify.exception.SpotifyException;
import Spotify.mapper.AlbumMapper;
import Spotify.mapper.ArtistMapper;
import Spotify.mapper.SongMapper;
import Spotify.persistence.entity.AlbumEntity;
import Spotify.service.AlbumService;
import Spotify.util.constant.CommonConstantsUtils;
import Spotify.util.constant.RestConstantsUtils;
import Spotify.exception.SpotifyException;
import Spotify.persistence.entity.AlbumEntity;
import Spotify.util.constant.CommonConstantsUtils;
import io.swagger.annotations.ApiParam;
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
@Tag(name = "Album", description = "Album Controller")
public class AlbumControllerRestImpl implements AlbumControllerRest {

    @Autowired
    private final AlbumService albumService;

    @Autowired
    private final AlbumMapper albumMapper;

    @Autowired
    private final SongMapper songMapper;

    @Autowired
    private final ArtistMapper artistMapper;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstantsUtils.RESOURCE_ALBUMS, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "getAllAlbums", description = "Get all Albums paginated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public SpotifyResponse<D4iPageRest<AlbumRest>> getAllAlbums(
            @RequestParam(defaultValue = CommonConstantsUtils.ZERO) final int page,
            @RequestParam(defaultValue = CommonConstantsUtils.TWENTY) final int size,
            @Parameter(hidden = true) final Pageable pageable)
            throws SpotifyException {
        Page<AlbumRest> albumRestList = albumService.getAllAlbums(pageable);
        return new SpotifyResponse<>(HttpStatus.OK.toString(),
                String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK,
                new D4iPageRest<>(albumRestList.getContent().toArray(AlbumRest[]::new),
                        new D4iPaginationInfo(albumRestList.getNumber(),
                                pageable.getPageSize(),
                                albumRestList.getTotalPages())));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstantsUtils.RESOURCE_ALBUMS + RestConstantsUtils.RESOURCE_ALBUM_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "getAlbumById", description = "Get Albums by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public SpotifyResponse<AlbumRest> getAlbumById(int id) throws SpotifyException {
        AlbumRest albumRest = albumService.getAlbumById(id);
        return new SpotifyResponse<>(HttpStatus.OK.toString(),
                String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK, albumRest);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstantsUtils.RESOURCE_ALBUMS + RestConstantsUtils.RESOURCE_ALBUM_ID + RestConstantsUtils.RESOURCE_SONG, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "getSongsOfAlbum", description = "Get Song of Album")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public SpotifyResponse<D4iPageRest<SongRestAlbum>> getSongsOfAlbum(
            @RequestParam(defaultValue = CommonConstantsUtils.ZERO) final int page,
            @RequestParam(defaultValue = CommonConstantsUtils.TWENTY) final int size,
            @Parameter(hidden = true) final Pageable pageable, int id) throws SpotifyException {
        Page<SongRestAlbum> songRestAlbum = albumService.getSongsOfAlbum(pageable, id);
        return new SpotifyResponse<>(HttpStatus.OK.toString(),
                String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK,
                new D4iPageRest<>(songRestAlbum.getContent().toArray(SongRestAlbum[]::new),
                        new D4iPaginationInfo(songRestAlbum.getNumber(),
                                pageable.getPageSize(),
                                songRestAlbum.getTotalPages())));
    }

    @Override
    public SpotifyResponse<D4iPageRest<ArtistRest>> getArtistsOfAlbum(
            @RequestParam(defaultValue = CommonConstantsUtils.ZERO) final int page,
            @RequestParam(defaultValue = CommonConstantsUtils.TWENTY) final int size,
            @Parameter(hidden = true) final Pageable pageable, int id) throws SpotifyException {
        return null;
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = RestConstantsUtils.RESOURCE_ALBUMS, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "createAlbum", description = "Create new Album")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public SpotifyResponse<AlbumRest> createAlbum(
            @RequestBody AlbumEntity album) throws SpotifyException {
        AlbumRest albumRest = albumService.createAlbum(album);
        return new SpotifyResponse<>(HttpStatus.OK.toString(),
                String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK, albumRest);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = RestConstantsUtils.RESOURCE_ALBUMS + RestConstantsUtils.RESOURCE_ALBUM_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "updateAlbum", description = "Update an existing Album")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public SpotifyResponse<AlbumRest> updateAlbum(@RequestBody AlbumEntity album, int id) throws SpotifyException {
        AlbumRest albumRest = albumService.updateAlbum(album, id);
        return new SpotifyResponse<>(HttpStatus.OK.toString(),
                String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK, albumRest);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = RestConstantsUtils.RESOURCE_ALBUMS + RestConstantsUtils.RESOURCE_ALBUM_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "updateAlbum", description = "Update an existing Album")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public void deleteAlbum(int id) throws SpotifyException {
        albumService.deleteAlbum(id);
    }
}
