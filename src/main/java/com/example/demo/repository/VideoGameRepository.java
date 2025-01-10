package com.example.demo.repository;
import com.example.demo.model.VideoGame;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;


public interface VideoGameRepository extends CrudRepository<VideoGame, Long> {

    Optional<VideoGame> findByName(String name);

}
