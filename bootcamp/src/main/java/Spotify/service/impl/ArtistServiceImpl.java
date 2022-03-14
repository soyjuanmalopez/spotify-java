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

    @Autowired
    private final ArtistRepository artistRepository;

    @Autowired
    private final ArtistMapper artistMapper;
    @Autowired
    private final PostArtistMapper postartistMapper;

    @Transactional(readOnly = true)
    @Override
    public Page<ArtistRest> getAllArtists(Pageable pageable) throws SpotifyException {
        return artistRepository.findAll(pageable).map(artistMapper::mapToRest);
    }

    @Override
    public ArtistRest getArtistById(final Long id) throws SpotifyException {
        ArtistEntity artist = artistRepository.findById(id)
                .orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));

        return artistMapper.mapToRest(artist);
    }

    @Override
    public PostArtistRest createArtist(final PostArtistRest artist) throws SpotifyException {
        ArtistEntity artistEntity =  postartistMapper.mapToEntity(artist);
        artistRepository.save(artistEntity);
        return artist;
    }

    @Override
    public PostArtistRest updateArtist(PostArtistRest postArtistRest) throws SpotifyException {
        ArtistEntity artist = artistRepository.findById(postArtistRest.getId())
                .orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        artist.setName(postArtistRest.getName());
        artist.setDescription(postArtistRest.getDescription());
        artistRepository.save(artist);
        return postartistMapper.mapToRest(artist);
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
