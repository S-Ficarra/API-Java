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
    private VideoGameService videoGameService;

    @Autowired
    public void Repositories(ReviewRepository reviewRepository, VideoGameRepository videoGameRepository) {
        this.reviewRepository = reviewRepository;
        this.videoGameRepository = videoGameRepository;
    }

    public Review getReviewById(long id) {
        Optional<Review> review = reviewRepository.findById(id);
        return review.orElse(null);
    }

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public void addReviewToAVideoGame (Long videoGameId, Review review) {
        try {

            Optional <VideoGame> videoGame = videoGameRepository.findById(videoGameId);
            if (videoGame.isEmpty()) {
                throw new IllegalArgumentException("Video Game do not exist");
            }

            VideoGame foundVideoGame = videoGame.get();
            foundVideoGame.setReviews(review);
            videoGameRepository.save(foundVideoGame);

        } catch (Exception e) {
            System.err.println("Error adding review to video game: " + e.getMessage());
        }
    }

    public void deleteReviewFromAVideoGame (Long reviewId, Long videoGameId) {

        try {

            Optional <VideoGame> videoGame = videoGameRepository.findById(videoGameId);
            if (videoGame.isEmpty()) {
                throw new IllegalArgumentException("Video Game do not exist");
            }

            Optional <Review> review = reviewRepository.findById(reviewId);
            if (review.isEmpty()) {
                throw new IllegalArgumentException("Review do not exist");
            }

            Review foundReview = review.get();
            VideoGame foundVideoGame = videoGame.get();
            foundVideoGame.getReviews().remove(foundReview);
            videoGameRepository.save(foundVideoGame);

            reviewRepository.deleteById(reviewId);

        } catch (Exception e) {
            System.err.println("Error deleting review from video game: " + e.getMessage());
        }


    }

}




