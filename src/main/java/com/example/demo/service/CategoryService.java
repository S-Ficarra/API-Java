package com.example.demo.service;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
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

    public List<Category> getCategoriesByIds(List<Long> ids) {
        Iterable<Category> iterable = categoryRepository.findAllById(ids);
        List<Category> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
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
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            categoryRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("A Category with this id does not exist");
        }
    }


}
