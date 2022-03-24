package com.example.fujitsumovierental.controllers;

import com.example.fujitsumovierental.model.Movie;
import com.example.fujitsumovierental.services.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> read(@PathVariable Long id) {
        Movie foundMovie = service.getMovieForId(id);
        if (foundMovie == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundMovie);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Movie> create(@RequestBody Movie movie) throws IOException {
        Movie createdMovie = service.createMovie(movie);
        if (createdMovie == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdMovie.getId())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(createdMovie);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> update(@RequestBody Movie movie, @PathVariable Long id) throws IOException {
        Movie updatedMovie = service.updateMovie(id, movie);
        if (updatedMovie == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedMovie);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMovie(@PathVariable Long id) throws IOException {
        service.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Movie>> listMovies(@RequestParam(value = "category", defaultValue = "all") String category) {
        List<Movie> movies = category.equals("all") ? service.getAllMovies() : service.getMoviesFromCategory(category);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/statistics")
    public String getStatistics() {
        return "Most popular movie is 'IT'";
    }
}
