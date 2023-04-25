package spotify.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import spotify.persistence.entity.GenereEntity;

@Repository
public interface GenereRepository extends PagingAndSortingRepository<GenereEntity, Long> {


}
