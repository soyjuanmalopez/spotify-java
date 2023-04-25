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
import spotify.controller.rest.AlbumControllerRest;
import spotify.controller.rest.model.AlbumRest;
import spotify.controller.rest.model.D4iPageRest;
import spotify.controller.rest.model.D4iPaginationInfo;
import spotify.controller.rest.model.SpotifyResponse;
import spotify.controller.rest.model.restAlbums.AlbumRestPost;
import spotify.controller.rest.model.restAlbums.ArtistRestAlbum;
import spotify.controller.rest.model.restAlbums.SongRestAlbum;
import spotify.exception.SpotifyException;
import spotify.service.AlbumService;
import spotify.util.constant.CommonConstantsUtils;
import spotify.util.constant.RestConstantsUtils;

@RestController
@Tag(name = "Album", description = "Album Controller")
@RequiredArgsConstructor
public class AlbumControllerRestImpl implements AlbumControllerRest {

    private final AlbumService albumService;

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
        final Page<AlbumRest> albumRestList = albumService.getAllAlbums(pageable);
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
    public SpotifyResponse<AlbumRest> getAlbumById(final Long id) throws SpotifyException {
        final AlbumRest albumRest = albumService.getAlbumById(id);
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
            @Parameter(hidden = true) final Pageable pageable, final Long id) throws SpotifyException {
        final Page<SongRestAlbum> songRestAlbum = albumService.getSongsOfAlbum(pageable, id);
        return new SpotifyResponse<>(HttpStatus.OK.toString(),
                String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK,
                new D4iPageRest<>(songRestAlbum.getContent().toArray(SongRestAlbum[]::new),
                        new D4iPaginationInfo(songRestAlbum.getNumber(),
                                pageable.getPageSize(),
                                songRestAlbum.getTotalPages())));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstantsUtils.RESOURCE_ALBUMS + RestConstantsUtils.RESOURCE_ALBUM_ID + RestConstantsUtils.RESOURCE_ARTIST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "getArtistOfAlbum", description = "Get Artist of Album")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public SpotifyResponse<D4iPageRest<ArtistRestAlbum>> getArtistsOfAlbum(
            @RequestParam(defaultValue = CommonConstantsUtils.ZERO) final int page,
            @RequestParam(defaultValue = CommonConstantsUtils.TWENTY) final int size,
            @Parameter(hidden = true) final Pageable pageable, final Long id) throws SpotifyException {
        final Page<ArtistRestAlbum> artistRestAlbum = albumService.getArtistsOfAlbum(pageable, id);
        return new SpotifyResponse<>(HttpStatus.OK.toString(),
                String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK,
                new D4iPageRest<>(artistRestAlbum.getContent().toArray(ArtistRestAlbum[]::new),
                        new D4iPaginationInfo(artistRestAlbum.getNumber(),
                                pageable.getPageSize(),
                                artistRestAlbum.getTotalPages())));
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
    public SpotifyResponse<AlbumRestPost> createAlbum(
            @RequestBody final AlbumRestPost album) throws SpotifyException {
        final AlbumRestPost albumRest = albumService.createAlbum(album);
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
    public SpotifyResponse<AlbumRestPost> updateAlbum(@RequestBody final AlbumRestPost album) throws SpotifyException {
        final AlbumRestPost albumRest = albumService.updateAlbum(album, album.getId());
        return new SpotifyResponse<>(HttpStatus.OK.toString(),
                String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK, albumRest);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = RestConstantsUtils.RESOURCE_ALBUMS + RestConstantsUtils.RESOURCE_ALBUM_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "deleteAlbum", description = "Delete an existing Album")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public SpotifyResponse<Object> deleteAlbum(@RequestParam final Long id) throws SpotifyException {
        albumService.deleteAlbum(id);
        return new SpotifyResponse<>(HttpStatus.OK.toString(),
                String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK);
    }


    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = RestConstantsUtils.RESOURCE_ALBUMS + RestConstantsUtils.RESOURCE_ALBUM_ID
            + RestConstantsUtils.RESOURCE_SONGS + RestConstantsUtils.RESOURCE_SONGID
            , produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "deleteSongOfAlbum", description = "Delete Song of Album")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public SpotifyResponse<AlbumRest> deleteSongOfAlbum(@RequestParam final Long albumId, @RequestParam final Long songId) throws SpotifyException {
        final AlbumRest albumRest = albumService.deleteSongOfAlbum(albumId, songId);
        return new SpotifyResponse<>(HttpStatus.OK.toString(),
                String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK, albumRest);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = RestConstantsUtils.RESOURCE_ALBUMS + RestConstantsUtils.RESOURCE_ALBUM_ID
            + RestConstantsUtils.RESOURCE_ARTIST + RestConstantsUtils.RESOURCE_ARTIST_ID
            , produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "deleteArtistToAlbum", description = "Delete an Artist to Album")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public SpotifyResponse<AlbumRest> deleteArtistOfAlbum(final Long albumId, final Long artistId) throws SpotifyException {
        final AlbumRest albumRest = albumService.deleteArtistOfAlbum(albumId, artistId);
        return new SpotifyResponse<>(HttpStatus.OK.toString(),
                String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK, albumRest);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = RestConstantsUtils.RESOURCE_ALBUMS + RestConstantsUtils.RESOURCE_ALBUM_ID
            + RestConstantsUtils.RESOURCE_SONGS + RestConstantsUtils.RESOURCE_SONGID
            , produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "addSongToAlbum", description = "Add a Song to Album")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public SpotifyResponse<AlbumRest> addSongOfAlbum(@RequestParam final Long albumId, @RequestParam final Long songId) throws SpotifyException {
        final AlbumRest albumRest = albumService.addSongOfAlbum(albumId, songId);
        return new SpotifyResponse<>(HttpStatus.OK.toString(),
                String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK, albumRest);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = RestConstantsUtils.RESOURCE_ALBUMS + RestConstantsUtils.RESOURCE_ALBUM_ID
            + RestConstantsUtils.RESOURCE_ARTIST + RestConstantsUtils.RESOURCE_ARTIST_ID
            , produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "addArtistToAlbum", description = "Add an Artist to Album")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public SpotifyResponse<AlbumRest> addArtistToAlbum(final Long albumId, final Long artistId) throws SpotifyException {
        final AlbumRest albumRest = albumService.addArtistToAlbum(albumId, artistId);
        return new SpotifyResponse<>(HttpStatus.OK.toString(),
                String.valueOf(HttpStatus.OK.value()),
                CommonConstantsUtils.OK, albumRest);
    }
}
