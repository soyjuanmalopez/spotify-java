package Spotify.service.impl;

import Spotify.controller.rest.model.AlbumRest;
import Spotify.controller.rest.model.ArtistRest;
import Spotify.controller.rest.model.restArtists.PostArtistRest;
import Spotify.exception.SpotifyException;
import Spotify.exception.SpotifyNotFoundException;
import Spotify.exception.error.ErrorDto;
import Spotify.mapper.ArtistMapper;
import Spotify.mapper.PostArtistMapper;
import Spotify.persistence.entity.ArtistEntity;
import Spotify.persistence.repository.ArtistRepository;
import Spotify.service.ArtistService;
import Spotify.util.constant.ExceptionConstantsUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    private final ArtistMapper artistMapper;

    private final PostArtistMapper postartistMapper;

    @Transactional(readOnly = true)
    @Override
    public Page<ArtistRest> getAllArtists(Pageable pageable) throws SpotifyException {
        return artistRepository.findAll(pageable).map(artist -> artistMapper.mapToRest(artist));
    }

    @Override
    public ArtistRest getArtistById(final Long id) throws SpotifyException {
        ArtistEntity artist = artistRepository.findById(id)
                .orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));

        return artistMapper.mapToRest(artist);
    }

    @Override
    public PostArtistRest createArtist(final ArtistEntity artist) throws SpotifyException {
        artistRepository.save(artist);
        return postartistMapper.mapToRest(artist);
    }

    @Override
    public PostArtistRest updateArtist(ArtistEntity artistEntity) throws SpotifyException {
        ArtistEntity artist = artistRepository.findById(artistEntity.getId())
                .orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        artist.setName(artistEntity.getName());
        artist.setDescription(artistEntity.getDescription());
        artistRepository.save(artist);
        return postartistMapper.mapToRest(artistEntity);
    }

    @Override
    public void deleteArtist(Long id) throws SpotifyException {
        artistRepository.deleteById(id);
    }

    @Override
    public Page<AlbumRest> getAlbumsOfArtist(Pageable pageable, Long id) throws SpotifyException {
        ArtistEntity artist = artistRepository.findById(id)
                .orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        List<AlbumRest> albumsRestList = artistMapper.mapToRest(artist).getAlbums();
        return new PageImpl<>(albumsRestList, pageable, albumsRestList.size());
    }
}
