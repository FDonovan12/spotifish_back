package fr.donovan.spotifish.repository;

import fr.donovan.spotifish.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, String>{
@Query("SELECT e FROM Artist AS e ORDER BY RAND() LIMIT 1")
    Artist findRandom();

    @Query("SELECT a, COUNT(a) FROM Artist a " +
            "LEFT JOIN UserLikeableItem uli ON uli.likeableItem = a " +
            "WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "GROUP BY a " +
            "ORDER BY COUNT(a) DESC")
    ArrayList<Artist> findByNameContaining(String search);
}