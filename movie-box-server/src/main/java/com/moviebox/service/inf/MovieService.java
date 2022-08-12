package com.moviebox.service.inf;

import com.moviebox.exception.MovieNotFoundException;
import com.moviebox.service.dto.MovieDto;

import java.util.List;

/**
 * @author Shayan
 * @since 6/26/2020
 */
public interface MovieService {
    List<MovieDto> findAll();

    MovieDto findById(Long id) throws MovieNotFoundException;

    MovieDto addMovie(MovieDto movie);

    MovieDto updateMovie(MovieDto movie);

    void deleteById(Long id);

}
