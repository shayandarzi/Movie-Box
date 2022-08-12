package com.moviebox.service.dto;

import lombok.Data;

import java.util.Set;

/**
 * @author Shayan
 * @since 6/26/2020
 */
@Data
public class MovieDto {
    private Long id;
    private String name;
    private String description;
    private String director;
    private String writer;
    private String actors;
    private byte[] poster;
    private String contentLocation;
}
