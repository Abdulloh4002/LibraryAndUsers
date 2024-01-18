package org.firstspringmigration.repository;



import org.firstspringmigration.mapper.AuthorMapper;
import org.firstspringmigration.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Author> getAuthors() {
        return jdbcTemplate.query(
                "select * from author",
                new AuthorMapper()
        );
    }

    public Author getAuthorById(Integer id){
        return jdbcTemplate.queryForObject(
                "select * from author where id = ?",
                new Object[]{id},
                new AuthorMapper()
        );
    }

    public void addAuthor(Author author){

        jdbcTemplate.update(
                "insert into author(name, surname, address) values(?, ?, ?)",
                author.getName(),
                author.getSurname(),
                author.getAddress()
        );
    }

    public void updateAuthor(Integer id, Author author){

        jdbcTemplate.update(
                "update author set name = ?, surname = ?, address = ? where id = ?",
                author.getName(),
                author.getSurname(),
                author.getAddress(),
                id
        );
    }

    public void deleteAuthor(Integer id){
        jdbcTemplate.update(
                "delete from author where id = ?",
                id
        );
    }


}
