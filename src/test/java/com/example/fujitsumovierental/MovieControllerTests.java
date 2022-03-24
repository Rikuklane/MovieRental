package com.example.fujitsumovierental;

import com.example.fujitsumovierental.controllers.MovieController;
import com.example.fujitsumovierental.dao.MovieDAO;
import com.example.fujitsumovierental.model.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerTests {

    @Autowired
    private MovieController controller;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MovieDAO movieDAO;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void testGetMovie() throws Exception {
        Movie mockedMovie = new Movie(100, "John Wick", 120, List.of("action"), "2015-05-13", List.of("director"),
                List.of("writer"), List.of("actor"), "Dog gets killed, Man is angry");
        Mockito.doReturn(mockedMovie).when(movieDAO).getMovieForId(100);
        final String expectedResponseContent = objectMapper.writeValueAsString(mockedMovie);
        this.mockMvc.perform(get("/movie/100"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponseContent));
    }

    @Test
    void testGetMovieList() throws Exception {
        this.mockMvc.perform(get("/movie/list"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetMovieStatistics() throws Exception {
        this.mockMvc.perform(get("/movie/statistics"))
                .andExpect(status().isOk());
    }
}
