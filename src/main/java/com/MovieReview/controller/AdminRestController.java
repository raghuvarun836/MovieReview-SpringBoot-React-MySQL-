package com.MovieReview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.MovieReview.domain.Admin;
import com.MovieReview.domain.Movie;
import com.MovieReview.repository.AdminRepository;
import com.MovieReview.repository.MovieRepository;
@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private MovieRepository movieRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("email") String email, @RequestParam("password") String password) {
        Admin admin = adminRepository.findByEmail(email);

        if (admin != null && admin.getPassword().equals(password)) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Login failed", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/addMovie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
        movieRepository.save(movie);
        return new ResponseEntity<>("Movie added", HttpStatus.CREATED);
    }
}
