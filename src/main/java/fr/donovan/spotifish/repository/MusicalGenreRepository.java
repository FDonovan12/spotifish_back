package fr.donovan.spotifish.repository;

import fr.donovan.spotifish.entity.MusicalGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface MusicalGenreRepository extends JpaRepository<MusicalGenre, String>, EntitySlugRepositoryInterface<MusicalGenre> {

    @Query("SELECT e FROM MusicalGenre AS e ORDER BY RAND() LIMIT 1")
    MusicalGenre findRandom();
}