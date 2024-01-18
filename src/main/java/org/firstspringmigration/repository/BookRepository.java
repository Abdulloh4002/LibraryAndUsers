package org.firstspringmigration.repository;


import org.firstspringmigration.mapper.BookMapper;
import org.firstspringmigration.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Book> getBooks() {
        return jdbcTemplate.query(
                "select b.id, b.title, b.year, b.language, a.id, a.name, a.surname, a.address, c.id, c.name" +
                        " from book b"+
                        " join author a on b.author_id = a.id"+
                        " join category c on b.category_id = c.id",
                new BookMapper()
        );
    }

    public Book getBookById(Integer id){
        return jdbcTemplate.queryForObject(
                "select b.id, b.title, b.year, b.language, a.id, a.name, a.surname, a.address, c.id, c.name" +
                        " from book b"+
                        " join author a on b.author_id = a.id"+
                        " join category c on b.category_id = c.id"+
                        " where b.id = ?",
                new Object[]{id},
                new BookMapper()
        );
    }

    public void addBook(Book book){

        jdbcTemplate.update(
                "insert into book(title, year, language, author_id, category_id) values(?, ?, ?, ?, ?)",
                book.getTitle(),
                book.getYear(),
                book.getLanguage(),
                book.getAuthor().getId(),
                book.getCategory().getId()
        );
    }

    public void updateBook(Integer id, Book book){

        jdbcTemplate.update(
                "update book set title = ?, year = ?, language = ?, author_id = ?, category_id = ? where id = ?",
                book.getTitle(),
                book.getYear(),
                book.getLanguage(),
                book.getAuthor().getId(),
                book.getCategory().getId(),
                id
        );
    }

    public void deleteBook(Integer id){
        jdbcTemplate.update(
                "delete from book where id = ?",
                id
        );
    }
}

