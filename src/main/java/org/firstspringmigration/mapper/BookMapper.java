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
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setTitle(rs.getString("title"));
        book.setYear(rs.getInt("year"));


        Author author = new Author();
        author.setId(rs.getInt("author_id")); // Assuming the column name is "author_id"
        book.setAuthor(author);

        Category category = new Category();
        category.setId(rs.getInt("category_id")); // Assuming the column name is "category_id"
        book.setCategory(category);

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

