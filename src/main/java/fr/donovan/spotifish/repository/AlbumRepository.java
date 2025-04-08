package fr.donovan.spotifish.repository;

import fr.donovan.spotifish.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, String>, EntitySlugRepositoryInterface<Album> {

    @Query("SELECT e FROM Album AS e ORDER BY RAND() LIMIT 1")
    Album findRandom();

    @Query("SELECT a, COUNT(a) FROM Album a " +
            "LEFT JOIN UserLikeableItem uli ON uli.likeableItem = a " +
            "WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "GROUP BY a " +
            "ORDER BY COUNT(a) DESC " +
            "LIMIT 5")
    List<Album> findBySearch(String search);
}