package fr.donovan.spotifish.repository;

import fr.donovan.spotifish.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, String>{
@Query("SELECT e FROM Playlist AS e ORDER BY RAND() LIMIT 1")
    Playlist findRandom();
}