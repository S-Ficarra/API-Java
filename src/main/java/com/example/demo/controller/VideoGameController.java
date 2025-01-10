package com.example.demo.controller;
import java.util.Date;
import java.util.List;

import com.example.demo.model.Category;
import com.example.demo.model.VideoGame;
import com.example.demo.service.CategoryService;
import com.example.demo.service.VideoGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/videogames")
public class VideoGameController {


    private final VideoGameService videoGameService;
    private final CategoryService categoryService;

    @Autowired
    public VideoGameController(VideoGameService videoGameService, CategoryService categoryService) {
        this.videoGameService = videoGameService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<Iterable<VideoGame>> getAllVideoGames() {
        Iterable<VideoGame> videoGames = videoGameService.getAllVideoGame();
        return new ResponseEntity<>(videoGames, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getVideoGameById(@PathVariable Long id) {
        VideoGame videogame = videoGameService.getVideoGameById(id);
        if (videogame != null) {
            return new ResponseEntity<>(videogame, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Video game with this id does not exist",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/byname")
    public ResponseEntity<Object> getVideoGameByName(@RequestParam String name) {
        VideoGame videogame = videoGameService.getVideoGameByName(name);
        if (videogame != null) {
            return new ResponseEntity<>(videogame, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Video game with this name does not exist",HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping("/create")
    public ResponseEntity<Object> createVideoGame(
            @RequestParam String name,
            String description,
            @DateTimeFormat(pattern = "MM/yyyy") Date releaseDate,
            String image,
            @RequestParam List<Long> categoryIds) {


        VideoGame videoGameToCreate = new VideoGame();
        videoGameToCreate.setName(name);
        videoGameToCreate.setDescription(description);
        videoGameToCreate.setReleaseDate(releaseDate);
        videoGameToCreate.setImage(image);


        try {

            List<Category> categories = categoryService.getCategoriesByIds(categoryIds);
            videoGameToCreate.setCategories(categories);
            videoGameService.createVideoGame(videoGameToCreate);
            return new ResponseEntity<>(videoGameToCreate, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{id}/edit")
    public ResponseEntity<Object> editVideoGame(@PathVariable Long id, String newName, String newDescription, @DateTimeFormat(pattern = "MM/yyyy") Date newReleaseDate, String newImage, @RequestParam List<Long> categoryIds) {

        List<Category> categories = categoryService.getCategoriesByIds(categoryIds);

        VideoGame videoGameToUpdate = videoGameService.getVideoGameById(id);
        videoGameToUpdate.setName(newName);
        videoGameToUpdate.setDescription(newDescription);
        videoGameToUpdate.setReleaseDate(newReleaseDate);
        videoGameToUpdate.setImage(newImage);
        videoGameToUpdate.setCategories(categories);

        try {
            videoGameService.updateVideoGame(videoGameToUpdate);
            return new ResponseEntity<>(videoGameToUpdate, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/delete")
    public ResponseEntity<Object> deleteVideoGame(@RequestParam Long id) {
        try {
            videoGameService.deleteVideoGame(id);
            return new ResponseEntity<>("Video Game deleted", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }




}
