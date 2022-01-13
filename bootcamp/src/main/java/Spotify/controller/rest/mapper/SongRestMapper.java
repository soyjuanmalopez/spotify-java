package Spotify.controller.rest.mapper;

import Spotify.controller.rest.model.SongRest;
import Spotify.service.model.SongDto;
import org.springframework.stereotype.Component;

@Component
public class SongRestMapper implements RestMapper<SongRest, SongDto>{

    @Override
    public SongRest mapToRest(final SongDto dto) {
        return new SongRest(dto.getId(), dto.getTitle(), dto.getAlbum_ref(),dto.getReproductions(),dto.getDuration());

    }

    @Override
    public SongDto mapToDto(final SongRest rest) {
        return new SongDto(rest.getId(), rest.getTitle(),rest.getReproductions(),rest.getAlbum_ref(),rest.getDuration());
    }

}