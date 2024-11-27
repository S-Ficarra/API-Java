package com.example.demo.service;
import com.example.demo.model.Review;
import com.example.demo.model.VideoGame;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.VideoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ReviewService {

    private ReviewRepository reviewRepository;
    private VideoGameRepository videoGameRepository;

    @Autowired
    public void setReviewRepository(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
    @Autowired
    public void setVideoGameRepository(VideoGameRepository videoGameRepository) {
        this.videoGameRepository = videoGameRepository;
    }

    public Iterable<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Review createReview(Review review, String videoGameName) {
        Optional<VideoGame> existingGame = videoGameRepository.findByName(videoGameName);
        if (existingGame.isPresent()) {
            review.setVideoGame(existingGame.get());
            return reviewRepository.save(review);
        } else {
            throw new IllegalArgumentException("Invalid video game");
        }
    }

    public Review updateReview(Review review) {
        Optional<Review> existingReview = reviewRepository.findById(review.getId());
        if (existingReview.isPresent()) {
            return reviewRepository.save(review);
        } else {
            throw new IllegalArgumentException("Invalid review");
        }
    }

    public void deleteReview(Review review) {
        Optional<Review> existingReview = reviewRepository.findById(review.getId());
        if (existingReview.isPresent()) {
            reviewRepository.delete(review);
        } else {
            throw new IllegalArgumentException("Invalid review");
        }
    }



}
