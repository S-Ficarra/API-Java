package com.example.demo.model;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<VideoGame> videoGames;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Set<VideoGame> getVideoGames() {
        return videoGames;
    }
    public void setVideoGames(Set<VideoGame> videoGames) {
        this.videoGames = videoGames;
    }

}
