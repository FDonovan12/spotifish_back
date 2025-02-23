package fr.donovan.spotifish.repository;

import fr.donovan.spotifish.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, String>{

    @Query("SELECT e FROM Song AS e ORDER BY RAND() LIMIT 1")
    public List<Song> searchSongByEverything();

@Query("SELECT e FROM Song AS e ORDER BY RAND() LIMIT 1")
    Song findRandom();
}