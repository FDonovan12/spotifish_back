package fr.donovan.spotifish.repository;

import fr.donovan.spotifish.entity.Playlist;
import fr.donovan.spotifish.entity.Shared;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface SharedRepository extends JpaRepository<Shared, String>, EntitySlugRepositoryInterface<Shared> {

    @Query("SELECT e FROM Shared AS e ORDER BY RAND() LIMIT 1")
    Shared findRandom();

    Optional<Shared> findByPlaylist(Playlist playlist);
}