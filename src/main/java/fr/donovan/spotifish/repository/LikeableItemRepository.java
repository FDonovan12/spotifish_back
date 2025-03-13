package fr.donovan.spotifish.repository;

import fr.donovan.spotifish.entity.LikeableItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeableItemRepository extends JpaRepository<LikeableItem, String>, EntitySlugRepositoryInterface<LikeableItem> {

    @Query("SELECT e FROM LikeableItem AS e ORDER BY RAND() LIMIT 1")
    LikeableItem findRandom();
}