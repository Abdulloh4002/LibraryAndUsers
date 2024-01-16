package org.firstspringmigration.repository;
import org.firstspringmigration.mapper.CategoryMapper;
import org.firstspringmigration.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Category> getCategories() {
        return jdbcTemplate.query(
                "select * from category",
                new CategoryMapper()
        );
    }

    public Category getCategoryById(Integer id){
        return jdbcTemplate.queryForObject(
                "select * from category where id = ?",
                new Object[]{id},
                new CategoryMapper()
        );
    }

    public void addCategory(Category category){

        jdbcTemplate.update(
                "insert into category(name) values(?)",
                category.getName()
        );
    }

    public void updateCategory(Integer id, Category category){

        jdbcTemplate.update(
                "update category set name = ? where id = ?)",
                category.getName(),
                id
        );
    }

    public void deleteCategory(Integer id){
        jdbcTemplate.update(
                "delete from table category where id = ?",
                id
        );
    }



}

