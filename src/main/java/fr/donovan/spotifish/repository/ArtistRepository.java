package fr.donovan.spotifish.repository;

import fr.donovan.spotifish.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, String>{
@Query("SELECT e FROM Artist AS e ORDER BY RAND() LIMIT 1")
    Artist findRandom();
}