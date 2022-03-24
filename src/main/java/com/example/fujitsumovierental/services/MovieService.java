package com.example.fujitsumovierental.services;

import java.io.IOException;
import java.util.List;

import com.example.fujitsumovierental.dao.MovieDAO;
import com.example.fujitsumovierental.model.Movie;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieDAO movieDao;

    public MovieService(MovieDAO movieDao) {
        this.movieDao = movieDao;
    }

    public List<Movie> getAllMovies() {
        return movieDao.getAllMovies();
    }

    public List<Movie> getMoviesFromCategory(String category) {
        return movieDao.getAllMoviesFromCategory(category);
    }

    public Movie getMovieForId(long id) {
        return movieDao.getMovieForId(id);
    }

    public Movie createMovie(Movie user) throws IOException {
        return movieDao.createMovie(user);
    }

    public Movie updateMovie(long id, Movie user) throws IOException {
        return movieDao.updateMovie(id, user);
    }

    public void deleteMovie(long id) throws IOException {
        movieDao.deleteMovie(id);
    }

}