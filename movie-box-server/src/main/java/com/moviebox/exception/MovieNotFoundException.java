package com.moviebox.exception;

import javax.persistence.EntityNotFoundException;

/**
 * @author Shayan
 * @since 6/26/2020
 */
public class MovieNotFoundException extends EntityNotFoundException {
    public MovieNotFoundException(String msg) {
        super(msg);
    }
}
