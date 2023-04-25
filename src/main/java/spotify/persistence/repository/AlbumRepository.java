package spotify.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import spotify.persistence.entity.AlbumEntity;

@Repository
public interface AlbumRepository extends PagingAndSortingRepository<AlbumEntity, Long> {


}
