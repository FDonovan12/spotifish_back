package fr.donovan.spotifish.repository;

import fr.donovan.spotifish.entity.LikeableItem;
import fr.donovan.spotifish.entity.Song;
import fr.donovan.spotifish.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeableItemRepository extends JpaRepository<LikeableItem, String>, EntitySlugRepositoryInterface<LikeableItem> {

    @Query("SELECT e FROM LikeableItem AS e ORDER BY RAND() LIMIT 1")
    LikeableItem findRandom();

    @Query("SELECT l FROM LikeableItem l " +
            "JOIN UserLikeableItem uli ON uli.likeableItem = l " +
            "WHERE TYPE(l) <> Song AND uli.user = :user")
    List<LikeableItem> findWithoutSong(User user);

    @Query("SELECT COUNT(l) FROM LikeableItem l " +
            "JOIN UserLikeableItem uli ON uli.likeableItem = l " +
            "WHERE TYPE(l) = Song AND uli.user = :user")
    long countSongsLiked(User user);

    @Query("SELECT s FROM Song s JOIN UserLikeableItem uli ON uli.likeableItem = s AND uli.user = :user")
    List<Song> getSongsLiked(User user);
}