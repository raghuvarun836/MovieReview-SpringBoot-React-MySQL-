package com.MovieReview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MovieReview.domain.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{

	Review findByMovieIdAndUserId(Long movieId, Long userId);

	List<Review> findByUserId(Long userId);

	List<Review> findByMovieId(Long movieId);
	
}
