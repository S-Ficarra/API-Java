package com.example.demo.controller;
import java.util.Date;
import com.example.demo.model.VideoGame;
import com.example.demo.service.VideoGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/videogames")
public class VideoGameController {


    private VideoGameService videoGameService;

    @Autowired
    public VideoGameController(VideoGameService videoGameService) {
        this.videoGameService = videoGameService;
    }

    @GetMapping
    public ResponseEntity<Iterable<VideoGame>> getAllVideoGames() {
        Iterable<VideoGame> videoGames = videoGameService.getAllVideoGame();
        return new ResponseEntity<>(videoGames, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createVideoGame(@RequestParam String name, String description, Date releaseDate, String image) {

        VideoGame videoGameToCreate = new VideoGame();
        videoGameToCreate.setName(name);
        videoGameToCreate.setDescription(description);
        videoGameToCreate.setReleaseDate(releaseDate);
        videoGameToCreate.setImage(image);

        try {
            videoGameService.createVideoGame(videoGameToCreate);
            return new ResponseEntity<>(videoGameToCreate, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }




}
