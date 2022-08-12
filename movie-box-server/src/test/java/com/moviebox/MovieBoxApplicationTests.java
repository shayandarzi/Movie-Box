package com.moviebox;

import com.moviebox.rest.controller.MovieController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext
class MovieBoxApplicationTests {
    @Autowired
    private MovieController movieController;

    @Test
    void contextLoads() {
        assertThat(movieController).isNotNull();
    }

}
