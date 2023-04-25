package spotify.mapper;

import org.mapstruct.Mapper;
import spotify.controller.rest.model.GenereRest;
import spotify.controller.rest.model.restSongs.GenereSongRest;
import spotify.persistence.entity.GenereEntity;

@Mapper(componentModel = "spring")
public interface GenereMapper {

    GenereEntity mapToEntity(GenereRest rest);

    GenereRest mapToRest(GenereEntity entity);

    GenereEntity mapToEntity(GenereSongRest rest);

    GenereSongRest mapToGenereSongRest(GenereEntity entity);
}
