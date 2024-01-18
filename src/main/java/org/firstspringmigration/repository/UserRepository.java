//package org.firstspringmigration.repository;
//
//import org.firstspringmigration.mapper.UserMapper;
//import org.firstspringmigration.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class UserRepository {
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    public List<User> getUsers() {
//        return jdbcTemplate.query(
//                "select * from users",
//                new UserMapper()
//        );
//    }
//
//    public User getUserById(Integer id) {
//        return jdbcTemplate.queryForObject(
//                "select * from users where id = ?",
//                new Object[]{id},
//                new UserMapper()
//        );
//    }
//
//    public void addUser(User user) {
//
//        jdbcTemplate.update(
//                "insert into users(username, password) values(?, ?)",
//                user.getUsername(),
//                user.getPassword()
//        );
//    }
//
//    public void updateUser(Integer id, User user) {
//
//        jdbcTemplate.update(
//                "update users set username = ?, password = ? where id = ?",
//                user.getUsername(),
//                user.getPassword(),
//                id
//        );
//    }
//
//    public void deleteUser(Integer id) {
//        jdbcTemplate.update(
//                "delete from users" +
//                        " where id = ?",
//                id
//        );
//    }
//
//    public boolean presentUser(User user){
//        Integer count = jdbcTemplate.queryForObject("select count(*) from users where username=?", Integer.class, user.getUsername());
//        return count != null && count == 1;
//    }
//
//    public boolean checkPassword(User user){
//        Integer count = jdbcTemplate.queryForObject("select count(*) from users where username=? and password=?", Integer.class, user.getUsername(), user.getPassword());
//        return count != null && count == 1;
//    }
//
//}
//
//

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
        return jdbcTemplate.query("SELECT * FROM users", new UserMapper());
    }

    public User getUserById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", new Object[]{id}, new UserMapper());
    }

    public void addUser(User user) {
        jdbcTemplate.update("INSERT INTO users(username, password) VALUES (?, ?)", user.getUsername(), user.getPassword());
    }

    public void updateUser(Integer id, User user) {
        jdbcTemplate.update("UPDATE users SET username = ?, password = ? WHERE id = ?", user.getUsername(), user.getPassword(), id);
    }

    public void deleteUser(Integer id) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
    }

    public boolean isUsernamePresent(String username) {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users WHERE username = ?", Integer.class, username);
        return count != null && count == 1;
    }

    public boolean validateUserCredentials(String username, String password) {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users WHERE username = ? AND password = ?", Integer.class, username, password);
        return count != null && count == 1;
    }
}
