package com.example.fujitsumovierental;

import com.example.fujitsumovierental.dao.MovieDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MovieDAOTests {

    @Autowired
    private MovieDAO dao;

    @Test
    void contextLoads() {
        assertThat(dao).isNotNull();
    }
}
