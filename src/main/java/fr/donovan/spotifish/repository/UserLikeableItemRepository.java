package fr.donovan.spotifish.repository;

import fr.donovan.spotifish.entity.UserLikeableItem;
import fr.donovan.spotifish.entity.embed.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserLikeableItemRepository extends JpaRepository<UserLikeableItem, UserLikeableItemId>, EntitySlugRepositoryInterface<UserLikeableItem> {
@Query("SELECT e FROM UserLikeableItem AS e ORDER BY RAND() LIMIT 1")
    UserLikeableItem findRandom();
}