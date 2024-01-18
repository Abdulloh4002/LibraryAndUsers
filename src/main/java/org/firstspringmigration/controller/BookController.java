package org.firstspringmigration.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.firstspringmigration.model.Book;
import org.firstspringmigration.model.Role;
import org.firstspringmigration.model.User;
import org.firstspringmigration.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<Book> getBooks() {
        return bookRepository.getBooks();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Integer id){
        return bookRepository.getBookById(id);
    }

    @PostMapping
    public String addBook(@RequestBody Book book, HttpServletRequest request) throws AccessDeniedException {
        HttpSession session = request.getSession();
        Role.hasRole((User)session.getAttribute("user"));
        bookRepository.addBook(book);
        return "New book was successfully added";
    }

    @PutMapping("/{id}")
    public String updateBook(@PathVariable Integer id, @RequestBody Book book, HttpServletRequest request) throws AccessDeniedException {
        HttpSession session = request.getSession();
        Role.hasRole((User)session.getAttribute("user"));
        bookRepository.updateBook(id, book);
        return "Existing book was successfully updated";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id, HttpServletRequest request) throws AccessDeniedException {
        HttpSession session = request.getSession();
        Role.hasRole((User)session.getAttribute("user"));
        bookRepository.deleteBook(id);
        return "The chosen book was successfully deleted";
    }
}
