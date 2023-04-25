package spotify.mapper;

import org.mapstruct.Mapper;
import spotify.controller.rest.model.SongRest;
import spotify.controller.rest.model.restSongs.PostSongRest;
import spotify.persistence.entity.SongEntity;

@Mapper(componentModel = "spring")
public interface SongMapper {

    SongEntity mapToEntity(SongRest rest);

    SongRest mapToRest(SongEntity entity);

    SongEntity mapToEntity(PostSongRest rest);

    PostSongRest mapToRestPost(SongEntity entity);
}
