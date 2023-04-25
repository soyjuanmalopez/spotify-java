package spotify.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spotify.controller.rest.model.GenereRest;
import spotify.controller.rest.model.SongRest;
import spotify.controller.rest.model.restSongs.GenereSongRest;
import spotify.exception.SpotifyException;
import spotify.exception.SpotifyNotFoundException;
import spotify.exception.error.ErrorDto;
import spotify.mapper.GenereMapper;
import spotify.mapper.SongMapper;
import spotify.persistence.entity.GenereEntity;
import spotify.persistence.entity.SongEntity;
import spotify.persistence.repository.GenereRepository;
import spotify.persistence.repository.SongRepository;
import spotify.service.GenereService;
import spotify.util.constant.ExceptionConstantsUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenereServiceImpl implements GenereService {

    private final GenereRepository genereRepository;

    private final GenereMapper genereMapper;

    private final SongMapper songMapper;

    private final SongRepository songRepository;


    @Transactional(readOnly = true)
    @Override
    public Page<GenereRest> getAllGeneres(Pageable pageable) throws SpotifyException {
        return genereRepository.findAll(pageable).map(genere -> genereMapper.mapToRest(genere));
    }

    @Transactional(readOnly = true)
    @Override
    public GenereRest getGenereById(Long id) throws SpotifyException {
        GenereEntity genere = genereRepository.findById(id)
                .orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        return genereMapper.mapToRest(genere);
    }

    @Transactional(readOnly = true)
    @Override
    public List<SongRest> getSongsByGenereId(Long genereId) throws SpotifyException {
        GenereEntity genere = genereRepository.findById(genereId)
                .orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        return genere.getSongs().stream().map(songMapper::mapToRest).collect(Collectors.toList());
    }

    @Transactional(readOnly = false)
    @Override
    public GenereRest createGenere(GenereRest genere) throws SpotifyException {
        GenereEntity genereEntity = genereMapper.mapToEntity(genere);
        genereRepository.save(genereEntity);
        return genere;
    }

    @Transactional(readOnly = false)
    @Override
    public GenereRest updateGenere(GenereRest genere) throws SpotifyException {
        GenereEntity genereEntity = genereRepository.findById(genere.getId())
                .orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        genereEntity.setName(genere.getName());
        genereRepository.save(genereEntity);
        return genereMapper.mapToRest(genereEntity);
    }

    @Transactional(readOnly = false)
    @Override
    public GenereSongRest updateSongByGenereId(Long genereId, Long songId) throws SpotifyException {
        GenereEntity genere = genereRepository
                .findById(genereId).orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        SongEntity song = songRepository.findById(songId).orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        song.getGeneres().add(genere);
        genere.getSongs().add(song);
        songRepository.save(song);
        genereRepository.save(genere);
        return genereMapper.mapToGenereSongRest(genere);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteGenere(final Long id) throws SpotifyException {
        genereRepository.deleteById(id);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteSongFromGenereById(final Long genereId, final Long songId) throws SpotifyException {
        GenereEntity genereEntity = genereRepository.findById(genereId).orElseThrow(() -> new SpotifyNotFoundException(new ErrorDto(ExceptionConstantsUtils.NOT_FOUND_GENERIC)));
        List<SongEntity> songToDelete = new ArrayList<>();
        genereEntity.getSongs().forEach(song -> {
            if (song.getId() == songId) {
                songToDelete.add(song);
            }
        });
        genereEntity.getSongs().remove(songToDelete.get(0));
        songToDelete.get(0).getGeneres().remove(genereEntity);
        genereRepository.save(genereEntity);
        songRepository.save(songToDelete.get(0));
    }
}
