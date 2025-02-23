package fr.donovan.spotifish.repository;

import fr.donovan.spotifish.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, String>{
@Query("SELECT e FROM Album AS e ORDER BY RAND() LIMIT 1")
    Album findRandom();
}