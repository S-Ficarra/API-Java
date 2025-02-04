package com.example.demo.service;

import com.example.demo.model.VideoGame;
import com.example.demo.repository.VideoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoGameService {

    private VideoGameRepository videoGameRepository;

    @Autowired
    public void setVideoGameRepository(VideoGameRepository videoGameRepository) {
        this.videoGameRepository = videoGameRepository;
    }

    public Iterable<VideoGame> getAllVideoGame () {
        return videoGameRepository.findAll();
    }

    public VideoGame getVideoGameById(Long id) {
        Optional<VideoGame> videoGame = videoGameRepository.findById(id);
        return videoGame.orElse(null);
    }

    public VideoGame getVideoGameByName(String videoGameName) {

        Optional<VideoGame> videoGame = videoGameRepository.findByName(videoGameName);
        return videoGame.orElse(null);

    }

    public List<VideoGame> getVideoGameBySearch(String videoGameName) {

        return videoGameRepository.findByNameContaining(videoGameName);

    }

    public VideoGame createVideoGame(VideoGame videoGame) {

        Optional<VideoGame> existingGame = videoGameRepository.findByName(videoGame.getName());
        if (existingGame.isPresent()) {
            throw new IllegalArgumentException("A Video Game with this name already exist");
        }
        return videoGameRepository.save(videoGame);
    }

    public VideoGame updateVideoGame(VideoGame videoGame) {

        Optional<VideoGame> existingGame = videoGameRepository.findByName(videoGame.getName());

        if (existingGame.isPresent() && existingGame.get().getId() != videoGame.getId()) {
            throw new IllegalArgumentException("A Video Game with this name already exist");
        } else {
            return videoGameRepository.save(videoGame);
        }

    }

    public void deleteVideoGame(Long id) {
        Optional<VideoGame> existingGame = videoGameRepository.findById(id);
        if (existingGame.isPresent()) {
            videoGameRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("A Video Game with this id does not exist");
        }
    }


}
