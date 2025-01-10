package com.example.demo.controller;
import com.example.demo.model.Review;
import com.example.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{videoGameId}/addreview")
    public ResponseEntity<Object> addReviewToVideoGame (@RequestParam String comment, @PathVariable Long videoGameId, String authorName, String rate) {

        try {

            Review reviewToCreate = new Review();
            reviewToCreate.setAuthorName(authorName);
            reviewToCreate.setComment(comment);
            reviewToCreate.setRating(Double.parseDouble(rate));

            Review reviewCreated = reviewService.createReview(reviewToCreate);

            reviewService.addReviewToAVideoGame(videoGameId, reviewCreated);

            return new ResponseEntity<>(reviewCreated, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/{videoGameId}/deletereview")
    public ResponseEntity<Object> deleteReview (@PathVariable Long videoGameId, Long reviewId) {

        try {
            reviewService.deleteReviewFromAVideoGame(reviewId, videoGameId);
            return new ResponseEntity<>("Review deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }






}
