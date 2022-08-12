package com.moviebox.persistance.repository;

import com.moviebox.persistance.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Shayan
 * @since 6/26/2020
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
