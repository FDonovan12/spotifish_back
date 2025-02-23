package fr.donovan.spotifish.repository;

import fr.donovan.spotifish.entity.Historical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoricalRepository extends JpaRepository<Historical, String>, EntitySlugRepositoryInterface<Historical> {
@Query("SELECT e FROM Historical AS e ORDER BY RAND() LIMIT 1")
    Historical findRandom();
}