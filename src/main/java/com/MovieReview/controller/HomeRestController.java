package com.MovieReview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MovieReview.domain.Movie;
import com.MovieReview.repository.MovieRepository;

@RestController
@RequestMapping("/") // Base path for home-related endpoints
public class HomeRestController {

    @Autowired
    private MovieRepository movieRepo;

    @GetMapping("/api/recently-released-movies") // Endpoint for recently released movies
    public ResponseEntity<List<Movie>> getRecentlyReleasedMovies() {
        List<Movie> recentlyReleasedMovies = movieRepo.findTop8ByOrderByReleaseDateDesc();
        return new ResponseEntity<>(recentlyReleasedMovies, HttpStatus.OK);
    }
}
