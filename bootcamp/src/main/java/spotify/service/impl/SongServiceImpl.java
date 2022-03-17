package spotify.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spotify.controller.rest.model.AlbumRest;
import spotify.controller.rest.model.SongRest;
import spotify.controller.rest.model.restSongs.PostSongRest;
import spotify.exception.SpotifyException;
import spotify.exception.SpotifyNotFoundException;
import spotify.exception.error.ErrorDto;
import spotify.mapper.AlbumMapper;
import spotify.mapper.SongMapper;
import spotify.persistence.entity.ArtistEntity;
import spotify.persistence.entity.SongEntity;
import spotify.persistence.repository.ArtistRepository;
import spotify.persistence.repository.SongRepository;
import spotify.service.SongService;
import spotify.util.constant.ExceptionConstantsUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    private final ArtistRepository artistRepository;

    private final SongMapper songMapper;

    private final AlbumMapper albumMapper;


    @Transactional(readOnly = true)
    @Override
    public Page<SongRest> getAllSongs(final Pageable pageable) throws SpotifyException {
        return songRepository.findAll(pageable).map(songMapper::mapToRest);
    }

    @Transactional(readOnly = false)
    @Override
    public PostSongRest createSong(final PostSongRest song) throws SpotifyException {
        SongEntity songEntity = songMapper.mapToEntity(song);
        songRepository.save(songEntity);
        return song;

    }

    @Transactional(readOnly = true)
    @Override
    public SongRest getSongById(final Long id) throws SpotifyException {
        SongEntity song = songRepository.findById(id)
                .orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        return songMapper.mapToRest(song);
    }

    @Transactional(readOnly = true)
    @Override
    public AlbumRest getAlbumBySongId(Long songId) throws SpotifyException {
        SongEntity song = songRepository.findById(songId)
                .orElseThrow(() ->
                        new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        return albumMapper.mapToRest(song.getAlbum());
    }

    @Transactional(readOnly = false)
    @Override
    public PostSongRest updateSong(final PostSongRest postSongRest) throws SpotifyException {

        SongEntity song = songRepository.findById(postSongRest.getId())
                .orElseThrow(() ->
                        new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        SongEntity songEntity = songMapper.mapToEntity(postSongRest);
        song.setTitle(songEntity.getTitle());
        song.setDuration(songEntity.getDuration());
        song.setReproductions(songEntity.getReproductions());
        song.setAlbum(songEntity.getAlbum());

        songRepository.save(song);
        return songMapper.mapToRestPost(song);
    }

    @Transactional(readOnly = false)
    @Override
    public SongRest updateArtistBySongId(Long songId, Long artistId) throws SpotifyException {
        SongEntity song = songRepository.findById(songId).orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        ArtistEntity artist = artistRepository.findById(artistId).orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        song.getArtists().add(artist);
        artist.getSongs().add(song);
        songRepository.save(song);
        artistRepository.save(artist);
        return songMapper.mapToRest(song);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteSong(final Long id) throws SpotifyException {
        songRepository.deleteById(id);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteArtistFromSongById(Long songId, Long artistId) throws SpotifyException {
        SongEntity song = songRepository.findById(songId).orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        List<ArtistEntity> artistToDelete = new ArrayList<>();
        song.getArtists().forEach(artist -> {
            if (Objects.equals(artist.getId(), artistId)) {
                artistToDelete.add(artist);
            }
        });
        song.getArtists().remove(artistToDelete.get(0));
        artistToDelete.get(0).getSongs().remove(song);
        artistRepository.save(artistToDelete.get(0));
        songRepository.save(song);

    }


}
