package spotify.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spotify.controller.rest.model.AlbumRest;
import spotify.controller.rest.model.ArtistRest;
import spotify.controller.rest.model.restArtists.PostArtistRest;
import spotify.exception.SpotifyException;
import spotify.exception.SpotifyNotFoundException;
import spotify.exception.error.ErrorDto;
import spotify.mapper.ArtistMapper;
import spotify.persistence.entity.ArtistEntity;
import spotify.persistence.repository.ArtistRepository;
import spotify.service.ArtistService;
import spotify.util.constant.ExceptionConstantsUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    private final ArtistMapper artistMapper;


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
        ArtistEntity artistEntity = artistMapper.mapToEntity(artist);
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
        return artistMapper.mapToRestPost(artist);
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
