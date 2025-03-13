package fr.donovan.spotifish.repository;

import fr.donovan.spotifish.entity.SongAlbum;
import fr.donovan.spotifish.entity.embed.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongAlbumRepository extends JpaRepository<SongAlbum, SongAlbumId>, EntitySlugRepositoryInterface<SongAlbum> {

    @Query("SELECT e FROM SongAlbum AS e ORDER BY RAND() LIMIT 1")
    SongAlbum findRandom();
}