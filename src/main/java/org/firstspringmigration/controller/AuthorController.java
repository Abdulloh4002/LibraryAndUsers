package org.firstspringmigration.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.firstspringmigration.model.Author;
import org.firstspringmigration.model.Role;
import org.firstspringmigration.model.User;
import org.firstspringmigration.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public List<Author> getAuthors() {
        return authorRepository.getAuthors();
    }


    @GetMapping("/{id}")
    public Author getAuthor(@PathVariable Integer id){
        return authorRepository.getAuthorById(id);
    }

    @PostMapping
    public String addAuthor(@RequestBody Author author, HttpServletRequest request) throws AccessDeniedException {

        HttpSession session = request.getSession();
        Role.hasRole((User)session.getAttribute("user"));
        authorRepository.addAuthor(author);
        return "New author was successfully added";
    }

    @PutMapping("/{id}")
    public String updateAuthor(@PathVariable Integer id, @RequestBody Author author, HttpServletRequest request) throws AccessDeniedException {
        HttpSession session = request.getSession();
        Role.hasRole((User)session.getAttribute("user"));
        authorRepository.updateAuthor(id, author);
        return "Existing author was successfully updated";
    }

    @DeleteMapping("/{id}")
    public String deleteAuthor(@PathVariable int id, HttpServletRequest request) throws AccessDeniedException {
        HttpSession session = request.getSession();
        Role.hasRole((User)session.getAttribute("user"));
        authorRepository.deleteAuthor(id);
        return "The chosen author was successfully deleted";
    }

}

