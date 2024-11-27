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


    @OneToMany(mappedBy = "videoGame")
    private List<Review> reviews;

    @ManyToMany
    @JoinTable(
            name = "game_categories",
            joinColumns = @JoinColumn(name = "game_name"),
            inverseJoinColumns = @JoinColumn (name = "category_name")
    )
    private Set<Category> categories;


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
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<Category> getCategories() {
        return categories;
    }
    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

}