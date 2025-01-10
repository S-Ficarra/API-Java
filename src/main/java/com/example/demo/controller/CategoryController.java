package com.example.demo.controller;
import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {


    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Category>> getAllCategories() {
        Iterable<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    
    @PostMapping("/create")
    public ResponseEntity <Object> createCategory(@RequestParam String name) {

        Category newCategory = new Category();
        newCategory.setName(name);

        try {
            Category categoryCreated = categoryService.createCategory(newCategory);
            return new ResponseEntity<>(categoryCreated, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/edit")
    public ResponseEntity <Object> editCategory(@RequestParam String newName, @RequestParam String id) {

        Category currentCategory = categoryService.getCategoryById(Long.parseLong(id));
        currentCategory.setName(newName);

        try {
            Category categoryUpdated = categoryService.updateCategory(currentCategory);
            return new ResponseEntity<>(categoryUpdated, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }


    @PostMapping("/delete")
    public ResponseEntity<Object> deleteCategory(@RequestParam Long id) {
        try {
            categoryService.deleteCategory(id);
            return new ResponseEntity<>("Category deleted" ,HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
