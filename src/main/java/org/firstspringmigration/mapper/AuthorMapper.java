package org.firstspringmigration.mapper;

import org.firstspringmigration.model.Author;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Author(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("address")
        );
    }
}

