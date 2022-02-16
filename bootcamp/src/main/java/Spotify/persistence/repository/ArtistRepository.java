package Spotify.persistence.repository;

import Spotify.persistence.entity.ArtistEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends PagingAndSortingRepository<ArtistEntity,Long> {

}
