package org.firstspringmigration.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.firstspringmigration.model.Category;
import org.firstspringmigration.model.Role;
import org.firstspringmigration.model.User;
import org.firstspringmigration.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
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
    public String addCategory(@RequestBody Category category, HttpServletRequest request) throws AccessDeniedException {
        HttpSession session = request.getSession();
        Role.hasRole((User)session.getAttribute("user"));
        categoryRepository.addCategory(category);
        return "New category was successfully added";
    }

    @PutMapping("/{id}")
    public String updateCategory(@PathVariable Integer id, @RequestBody Category category, HttpServletRequest request) throws AccessDeniedException {
        HttpSession session = request.getSession();
        Role.hasRole((User)session.getAttribute("user"));
        categoryRepository.updateCategory(id, category);
        return "Existing category was successfully updated";
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Integer id, HttpServletRequest request) throws AccessDeniedException {
        HttpSession session = request.getSession();
        Role.hasRole((User)session.getAttribute("user"));
        categoryRepository.deleteCategory(id);
        return "The chosen category was successfully deleted";
    }

}

