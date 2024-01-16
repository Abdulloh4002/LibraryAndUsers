package org.firstspringmigration.controller;


import org.firstspringmigration.model.Book;
import org.firstspringmigration.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String addBook(@RequestBody Book book){
        bookRepository.addBook(book);
        return "New book was successfully added";
    }

    @PutMapping("/{id}")
    public String updateBook(@PathVariable Integer id, @RequestBody Book book){
        bookRepository.updateBook(id, book);
        return "Existing book was successfully updated";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        bookRepository.deleteBook(id);
        return "The chosen book was successfully deleted";
    }
}
