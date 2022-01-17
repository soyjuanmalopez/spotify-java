package Spotify.persistence.mapper;

import Spotify.persistence.entity.SongEntity;
import Spotify.service.model.SongDto;
import org.springframework.stereotype.Component;

@Component
public class SongEntityMapper2 implements EntityMapper<SongEntity, SongDto>{
    @Override
    public SongEntity mapToEntity(final SongDto dto) {
        return new SongEntity(dto.getId(), dto.getTitle(),dto.getAlbum_ref(),dto.getReproductions(),dto.getDuration());
    }

    @Override
    public SongDto mapToDto(final SongEntity entity) {
        return new SongDto(entity.getId(), entity.getTitle(),entity.getReproductions(),entity.getAlbum_ref(),entity.getDuration());
    }
}