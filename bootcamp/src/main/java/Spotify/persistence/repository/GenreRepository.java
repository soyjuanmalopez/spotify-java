package Spotify.persistence.repository;

import Spotify.persistence.entity.GenreEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends PagingAndSortingRepository<GenreEntity,Integer> {


}
