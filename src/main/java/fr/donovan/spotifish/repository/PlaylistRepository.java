package fr.donovan.spotifish.repository;

import fr.donovan.spotifish.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, String>, EntitySlugRepositoryInterface<Playlist> {

    @Query("SELECT e FROM Playlist AS e ORDER BY RAND() LIMIT 1")
    Playlist findRandom();

    @Query("SELECT p, COUNT(p) FROM Playlist p " +
            "LEFT JOIN UserLikeableItem uli ON uli.likeableItem = p " +
            "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "GROUP BY p " +
            "ORDER BY COUNT(p) DESC")
    List<Playlist> findByNameContaining(String search);
}