package org.firstspringmigration.mapper;

import org.firstspringmigration.model.Author;
import org.firstspringmigration.model.Book;
import org.firstspringmigration.model.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new Book(
                rs.getInt(1),
                rs.getString(2),
                rs.getInt(3),
                rs.getString(4),
        new Author(
                rs.getInt(5),
                rs.getString(6),
                rs.getString(7),
                rs.getString(8)
        ),
        new Category(
                rs.getInt(9),
                rs.getString(10)
        )
        );
    }
}

