package com.MovieReview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.MovieReview.domain.Movie;
import com.MovieReview.repository.MovieRepository;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieRestController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/recently-released")
    public ResponseEntity<List<Movie>> getRecentlyReleasedMovies() {
        List<Movie> recentlyReleasedMovies = movieRepository.findTop8ByOrderByReleaseDateDesc();
        return new ResponseEntity<>(recentlyReleasedMovies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie != null) {
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
        movieRepository.save(movie);
        return new ResponseEntity<>("Movie added", HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editMovie(@PathVariable Long id, @RequestBody Movie movie) {
        Movie existingMovie = movieRepository.findById(id).orElse(null);
        if (existingMovie != null) {
            // Update existing movie with the new data
            // For brevity, updating logic needs to be added
            return new ResponseEntity<>("Movie updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return new ResponseEntity<>("Movie deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
        }
    }
}
