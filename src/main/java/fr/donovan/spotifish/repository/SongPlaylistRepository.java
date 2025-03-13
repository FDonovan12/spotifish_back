package fr.donovan.spotifish.repository;

import fr.donovan.spotifish.entity.SongPlaylist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongPlaylistRepository extends JpaRepository<SongPlaylist, Long>, EntitySlugRepositoryInterface<SongPlaylist> {

    @Query("SELECT e FROM SongPlaylist AS e ORDER BY RAND() LIMIT 1")
    SongPlaylist findRandom();
}