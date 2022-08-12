package com.moviebox.rest;

import com.moviebox.rest.model.MovieModel;
import com.moviebox.service.dto.MovieDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Shayan
 * @since 7/1/2020
 */
@Mapper(componentModel = "spring")
public interface RestModelConverter {
    List<MovieModel> convert(List<MovieDto> movieDtos);

    MovieModel convert(MovieDto movieDto);

    MovieDto convert(MovieModel movieModel);
}
