package spotify.mapper;

import org.mapstruct.Mapper;
import spotify.controller.rest.model.GenreRest;
import spotify.controller.rest.model.restSongs.GenreSongRest;
import spotify.persistence.entity.GenreEntity;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreEntity mapToEntity(GenreRest rest);

    GenreRest mapToRest(GenreEntity entity);

    GenreEntity mapToEntity(GenreSongRest rest);

    GenreSongRest mapToGenreSongRest(GenreEntity entity);
}
