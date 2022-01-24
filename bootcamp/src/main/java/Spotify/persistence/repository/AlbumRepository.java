package Spotify.persistence.repository;

import Spotify.persistence.entity.AlbumEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends PagingAndSortingRepository<AlbumEntity, Integer> {


}
