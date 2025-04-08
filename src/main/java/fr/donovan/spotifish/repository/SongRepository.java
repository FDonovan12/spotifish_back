package fr.donovan.spotifish.repository;

import fr.donovan.spotifish.entity.Artist;
import fr.donovan.spotifish.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, String>, EntitySlugRepositoryInterface<Song> {

    @Query("SELECT e FROM Song AS e ORDER BY RAND() LIMIT 1")
    public List<Song> searchSongByEverything();

    @Query("SELECT e FROM Song AS e ORDER BY RAND() LIMIT 1")
    Song findRandom();

    @Query("SELECT s, COUNT(s) FROM Song s " +
            "LEFT JOIN UserLikeableItem uli ON uli.likeableItem = s " +
            "WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "GROUP BY s " +
            "ORDER BY COUNT(s) DESC " +
            "LIMIT 5")
    List<Song> findBySearch(String search);

    @Query("SELECT s FROM Song s LEFT JOIN SongArtist sa ON sa.song = s WHERE sa.artist = :artist")
    List<Song> findByUser(Artist artist);
}