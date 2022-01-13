package Spotify.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import Spotify.persistence.entity.SongEntity;

@Repository
public interface SongRepository extends PagingAndSortingRepository<SongEntity, Long> {


}
