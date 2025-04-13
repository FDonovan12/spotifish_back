package fr.donovan.spotifish.repository;

import fr.donovan.spotifish.entity.Contributor;
import fr.donovan.spotifish.entity.embed.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContributorRepository extends JpaRepository<Contributor, ContributorId>, EntitySlugRepositoryInterface<Contributor> {

    @Query("SELECT e FROM Contributor AS e ORDER BY RAND() LIMIT 1")
    Contributor findRandom();

    Optional<Contributor> findByUuid(String id);
}