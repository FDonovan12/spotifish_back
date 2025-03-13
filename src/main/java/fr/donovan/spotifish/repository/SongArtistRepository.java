package fr.donovan.spotifish.repository;

import fr.donovan.spotifish.entity.SongArtist;
import fr.donovan.spotifish.entity.embed.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongArtistRepository extends JpaRepository<SongArtist, SongArtistId>, EntitySlugRepositoryInterface<SongArtist> {

    @Query("SELECT e FROM SongArtist AS e ORDER BY RAND() LIMIT 1")
    SongArtist findRandom();
}