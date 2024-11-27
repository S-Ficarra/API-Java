package com.example.demo.service;
import com.example.demo.model.VideoGame;
import com.example.demo.repository.VideoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public VideoGame getVideoGameByName(String videoGameName) {

        Optional<VideoGame> videoGame = videoGameRepository.findByName(videoGameName);
        return videoGame.orElse(null);

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
        if (existingGame.isPresent()) {
            return videoGameRepository.save(videoGame);
        } else {
            throw new IllegalArgumentException("A Video Game with this name does not exist : " + videoGame);
        }

    }

    public void deleteVideoGame(VideoGame videoGame) {
        videoGameRepository.delete(videoGame);
    }


}
