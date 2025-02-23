package fr.donovan.spotifish.repository;

import fr.donovan.spotifish.entity.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModeratorRepository extends JpaRepository<Moderator, String>{
@Query("SELECT e FROM Moderator AS e ORDER BY RAND() LIMIT 1")
    Moderator findRandom();
}