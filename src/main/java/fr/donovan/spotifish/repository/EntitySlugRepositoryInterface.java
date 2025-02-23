package fr.donovan.spotifish.repository;

import java.util.Optional;

public interface EntitySlugRepositoryInterface<T> {

    Optional<T> findBySlug(String slug);

}