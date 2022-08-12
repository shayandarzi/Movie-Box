package com.moviebox;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviebox.rest.model.MovieModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MovieboxWebTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void listMovies() throws Exception {
        this.mockMvc.perform(get("/api/movies")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void addMovie() throws Exception {
        MovieModel movieModel = new MovieModel();
        movieModel.setName("movie1");
        movieModel.setDescription("movie1 description");
        movieModel.setDirector("test director");
        movieModel.setWriter("test writer");
        movieModel.setActors("test actor1- test actor2");
        movieModel.setContentLocation("ftp://c:test");
        movieModel.setPoster("test".getBytes());
        this.mockMvc.perform(post("/api/movies").content(objectMapper.writeValueAsString(movieModel))
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).
                andExpect(status().isCreated()).
                andExpect(content().string(containsString("movie1")));
    }

    @Test
    public void updateMovie() throws Exception {
        MovieModel movieModel = new MovieModel();
        movieModel.setId(1L);
        movieModel.setName("movie updated 1");
        movieModel.setDescription("movie1 description");
        movieModel.setDirector("test director");
        movieModel.setWriter("test writer");
        movieModel.setActors("test actor1- test actor2");
        movieModel.setContentLocation("ftp://c:test");
        movieModel.setPoster("test".getBytes());
        this.mockMvc.perform(put("/api/movies").content(objectMapper.writeValueAsString(movieModel))
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).
                andExpect(status().isOk()).
                andExpect(content().string(containsString("movie updated ")));
    }

    @Test
    public void deleteMovie() throws Exception {
        this.mockMvc.perform(delete("/api/movies/1"))
                .andExpect(status().isOk());
    }
}
