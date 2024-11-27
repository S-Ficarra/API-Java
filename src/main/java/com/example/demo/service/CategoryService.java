package com.example.demo.service;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class CategoryService {


    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }

    public Category createCategory(Category category) {

        Optional<Category> alreadyExisting = categoryRepository.findByName(category.getName());
        if (alreadyExisting.isPresent()) {
            throw new IllegalArgumentException("Category already exists");
        }

        return categoryRepository.save(category);

    }

    public Category updateCategory(Category category) {

        Optional <Category> alreadyExisting = categoryRepository.findByName(category.getName());
        if (alreadyExisting.isPresent()) {
            throw new IllegalArgumentException("Category already exists");
        }
        return categoryRepository.save(category);
    }

    public void deleteCategory(long id) {
        categoryRepository.deleteById(id);
    }


}
