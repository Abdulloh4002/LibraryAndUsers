package org.firstspringmigration.controller;

import org.firstspringmigration.model.Category;
import org.firstspringmigration.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> getCategories() {
        return categoryRepository.getCategories();
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Integer id){
        return categoryRepository.getCategoryById(id);
    }

    @PostMapping
    public String addCategory(@RequestBody Category category){
        categoryRepository.addCategory(category);
        return "New category was successfully added";
    }

    @PutMapping("/{id}")
    public String updateCategory(@PathVariable Integer id, @RequestBody Category category){
        categoryRepository.updateCategory(id, category);
        return "Existing category was successfully updated";
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable int id){
        categoryRepository.deleteCategory(id);
        return "The chosen category was successfully deleted";
    }

}

