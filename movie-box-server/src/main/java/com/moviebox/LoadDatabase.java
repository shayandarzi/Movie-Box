package com.moviebox;

import com.moviebox.persistance.entity.Movie;
import com.moviebox.persistance.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Shayan
 * @since 7/2/2020
 */
@Slf4j
@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(MovieRepository movieRepository) {
        Movie movie = new Movie(1L, "test", "test description", "director", "writer", "actors", "lll".getBytes(), "kkd");
        Movie movie2 = new Movie(1L, "test2", "test description", "director", "writer", "actors", "lll".getBytes(), "kkd");
        return args -> {
            log.info("preloading movie " + movieRepository.save(movie));
            log.info("movies before update: " + movieRepository.findAll());
            log.info("updating movie" + movieRepository.save(movie2));
            log.info("movies after update: " + movieRepository.findAll());
        };
    }
}
