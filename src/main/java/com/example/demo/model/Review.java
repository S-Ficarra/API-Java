package com.example.demo.model;
import jakarta.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String authorName;
    private int rating;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "categories")
    private VideoGame videoGame;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public VideoGame getVideoGame() {
        return videoGame;
    }
    public void setVideoGame(VideoGame videoGame) {
        this.videoGame = videoGame;
    }


}
