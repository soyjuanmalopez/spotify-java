package spotify.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import spotify.persistence.entity.GenreEntity;

@Repository
public interface GenreRepository extends PagingAndSortingRepository<GenreEntity, Long> {


}
