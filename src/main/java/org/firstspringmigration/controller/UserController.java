package org.firstspringmigration.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.firstspringmigration.model.Role;
import org.firstspringmigration.model.User;
import org.firstspringmigration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getUsers(HttpServletRequest request) throws AccessDeniedException {
        HttpSession session = request.getSession();
        Role.hasRole((User)session.getAttribute("user"));
        return userRepository.getUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id, HttpServletRequest request) throws AccessDeniedException {
        HttpSession session = request.getSession();
        Role.userAccount((User)session.getAttribute("user"),id);

        return userRepository.getUserById(id);
    }

    @PostMapping
    public String addUser(@RequestBody User user){
        userRepository.addUser(user);
        return "New user was successfully added";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Integer id, @RequestBody User user) {
        if (userRepository.presentUser(user) && userRepository.checkPassword(user)) {
            userRepository.updateUser(id, user);
            return "Existing user was successfully updated";
        }
        else return "User was not updated! Either user under "+user.getUsername()+" does not exist or the password is wrong";
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id, HttpServletRequest request) throws AccessDeniedException {
        HttpSession session = request.getSession();
        Role.userAccount((User)session.getAttribute("user"),id);
        userRepository.deleteUser(id);
        return "The chosen user was successfully deleted";
    }

}

