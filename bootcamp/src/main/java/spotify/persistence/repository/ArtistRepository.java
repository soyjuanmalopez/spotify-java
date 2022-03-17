package spotify.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import spotify.persistence.entity.ArtistEntity;

@Repository
public interface ArtistRepository extends PagingAndSortingRepository<ArtistEntity, Long> {

}
