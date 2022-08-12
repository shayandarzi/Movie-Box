package com.moviebox.rest.controller;

import com.moviebox.rest.MovieModelAssembler;
import com.moviebox.rest.RestModelConverter;
import com.moviebox.rest.model.MovieModel;
import com.moviebox.service.inf.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Shayan
 * @since 7/1/2020
 */

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final RestModelConverter modelConverter;
    private final MovieModelAssembler movieModelAssembler;
    private final MovieService movieService;

    @Autowired
    public MovieController(RestModelConverter modelConverter, MovieModelAssembler movieModelAssembler, MovieService movieService) {
        this.modelConverter = modelConverter;
        this.movieModelAssembler = movieModelAssembler;
        this.movieService = movieService;
    }

    @GetMapping("")
    public CollectionModel<EntityModel<MovieModel>> all() {
        List<EntityModel<MovieModel>> movies = modelConverter.convert(movieService.findAll()).stream().map(movieModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(movies, linkTo(methodOn(MovieController.class).all()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<MovieModel> findById(@PathVariable Long id) {
        return movieModelAssembler.toModel(modelConverter.convert(movieService.findById(id)));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EntityModel<MovieModel>> addMovie(@Valid @RequestBody MovieModel newMovie) {
        EntityModel<MovieModel> entityModel = movieModelAssembler.toModel(modelConverter.convert(movieService.addMovie(modelConverter.convert(newMovie))));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);

    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EntityModel<MovieModel>> updateMovie(@Valid @RequestBody MovieModel movie) {
        EntityModel<MovieModel> entityModel = movieModelAssembler.toModel(modelConverter.convert(movieService.updateMovie(modelConverter.convert(movie))));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
