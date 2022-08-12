package com.moviebox.service.impl;

import com.moviebox.exception.MovieNotFoundException;
import com.moviebox.persistance.repository.MovieRepository;
import com.moviebox.service.ModelConverter;
import com.moviebox.service.dto.MovieDto;
import com.moviebox.service.inf.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Shayan
 * @since 6/26/2020
 */
@Transactional
@Service
@Slf4j
public class MovieServiceImpl implements MovieService {
    private final ModelConverter modelConverter;
    private final MovieRepository movieRepository;

    public MovieServiceImpl(ModelConverter modelConverter, MovieRepository movieRepository) {
        this.modelConverter = modelConverter;
        this.movieRepository = movieRepository;
    }

    @Override
    public List<MovieDto> findAll() {
        return modelConverter.convert(movieRepository.findAll());
    }

    @Override
    public MovieDto findById(Long id) throws MovieNotFoundException {
        return modelConverter.convert(movieRepository.findById(id).orElseThrow(
                () -> new MovieNotFoundException("Movie with id: " + id + " not found")));
    }

    @Override
    public MovieDto addMovie(MovieDto movie) {
        return modelConverter.convert(movieRepository.save(modelConverter.convert(movie)));
    }

    @Override
    public MovieDto updateMovie(MovieDto movie) {
        return modelConverter.convert(movieRepository.save(modelConverter.convert(movie)));
    }

    @Override
    public void deleteById(Long id) {
        movieRepository.deleteById(id);

    }
}
