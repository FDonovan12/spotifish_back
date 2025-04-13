package fr.donovan.spotifish.repository;

import fr.donovan.spotifish.entity.LikeableItem;
import fr.donovan.spotifish.entity.Playlist;
import fr.donovan.spotifish.entity.User;
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
            "AND p.isPrivate = false " +
            "GROUP BY p " +
            "ORDER BY COUNT(p) DESC " +
            "LIMIT 5")
    List<Playlist> findBySearch(String search);

    @Query("SELECT p FROM Playlist p " +
            "LEFT JOIN Contributor c ON c.playlist = p " +
            "WHERE c.user = :user")
    List<Playlist> getByUser(User user);
}