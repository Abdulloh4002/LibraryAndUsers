package org.firstspringmigration.repository;

import org.firstspringmigration.mapper.UserMapper;
import org.firstspringmigration.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getUsers() {
        return jdbcTemplate.query(
                "select * from user",
                new UserMapper()
        );
    }

    public User getUserById(Integer id) {
        return jdbcTemplate.queryForObject(
                "select * from user where id = ?",
                new Object[]{id},
                new UserMapper()
        );
    }

    public void addUser(User user) {

        jdbcTemplate.update(
                "insert into user(name, password) values(?, ?)",
                user.getUsername(),
                user.getPassword()
        );
    }

    public void updateUser(Integer id, User user) {

        jdbcTemplate.update(
                "update user set name = ?, password = ? where id = ?",
                user.getUsername(),
                user.getPassword(),
                id
        );
    }

    public void deleteUser(Integer id) {
        jdbcTemplate.update(
                "delete from user" +
                        " where id = ?",
                id
        );
    }

    public boolean presentUser(User user){
        return jdbcTemplate.queryForObject("select username from user where username=?",Integer.class, new Object[]{user.getUsername()}) ==1;
    }
    public boolean checkPassword(User user){
        return jdbcTemplate.queryForObject("select username from user where username=? and password=?",Integer.class, new Object[]{user.getUsername(), user.getPassword()}) ==1;
    }
}


