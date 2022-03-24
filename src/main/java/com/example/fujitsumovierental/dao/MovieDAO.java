package com.example.fujitsumovierental.dao;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.example.fujitsumovierental.model.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

@Repository
public class MovieDAO {
    static HashMap<Long, Movie> movieMap;
    static ObjectMapper mapper = new ObjectMapper();

    public MovieDAO() throws IOException {
        mapper.findAndRegisterModules();
        readFromJson();
        // TODO add method for reading / writing YAML
    }

    public List<Movie> getAllMovies() {
        return new ArrayList<>(movieMap.values());
    }

    public List<Movie> getAllMoviesFromCategory(String category) {
        return this.getAllMovies()
                .stream()
                .filter(movie -> movie.getCategories().contains(category.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

    public Movie getMovieForId(long id) {
        return movieMap.get(id);
    }

    public Movie createMovie(Movie movie) throws IOException {
        movieMap.put(movie.getId(), movie);
        writeToJson();
        return movieMap.get(movie.getId());
    }

    public Movie updateMovie(long id, Movie movie) throws IOException {
        if (movieMap.get(id) != null) {
            // TODO write update
            System.out.println("update");
        } else {
            movieMap.put(id, movie);
        }
        writeToJson();
        return movieMap.get(id);
    }

    public void deleteMovie(long id) throws IOException {
        movieMap.remove(id);
        writeToJson();
    }

    private static void readFromJson() throws IOException {
        movieMap = new HashMap<>();
        File jsonFile = ResourceUtils.getFile("classpath:data/movies.json");

        // convert JSON array to list of movies and put into hashmap
        List<Movie> movies = Arrays.asList(mapper.readValue(jsonFile, Movie[].class));
        movies.forEach(movie -> movieMap.put(movie.getId(), movie));
    }

    private static void writeToJson() throws IOException {
        File jsonFile = ResourceUtils.getFile("classpath:data/movies1.json");
        System.out.println(jsonFile.getPath());

        // write to JSON
        mapper.writeValue(jsonFile, movieMap);
    }
}
