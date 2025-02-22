package com.example.demo.repository;
import com.example.demo.model.Category;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;


public interface CategoryRepository extends CrudRepository <Category, Long> {

    Optional<Category> findByName(String name);

}
