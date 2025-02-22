package com.example.demo.model;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
public class VideoGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String description;
    private Date releaseDate;
    private String image;



    @OneToMany
    @JoinTable(
            name = "game_reviews",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn (name="review_id")
    )
    private List<Review> reviews;

    @ManyToMany
    @JoinTable(
            name = "game_categories",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn (name = "category_id")
    )
    private List<Category> categories;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public List<Review> getReviews() {
        return reviews;
    }
    public void setReviews(Review reviews) {
        this.reviews.add(reviews);
    }

    public List<Category> getCategories() {
        return categories;
    }
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}