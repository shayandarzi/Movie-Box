package com.moviebox.rest;

import com.moviebox.rest.controller.MovieController;
import com.moviebox.rest.model.MovieModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Shayan
 * @since 7/18/2020
 */
@Component
public class MovieModelAssembler implements RepresentationModelAssembler<MovieModel, EntityModel<MovieModel>> {
    @Override
    public EntityModel<MovieModel> toModel(MovieModel movieModel) {
        return EntityModel.of(movieModel, linkTo(methodOn(MovieController.class).findById(movieModel.getId())).withSelfRel(),
                linkTo(methodOn(MovieController.class).all()).withRel("movies"));
    }
}
