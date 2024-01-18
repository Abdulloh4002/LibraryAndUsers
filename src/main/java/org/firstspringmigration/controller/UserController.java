//package org.firstspringmigration.controller;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import org.firstspringmigration.model.Role;
//import org.firstspringmigration.model.User;
//import org.firstspringmigration.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.nio.file.AccessDeniedException;
//import java.util.List;
//
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping
//    public List<User> getUsers(HttpServletRequest request) throws AccessDeniedException {
//        HttpSession session = request.getSession();
//        Role.hasRole((User)session.getAttribute("user"));
//        return userRepository.getUsers();
//    }
//
//    @GetMapping("/{id}")
//    public User getUser(@PathVariable Integer id, HttpServletRequest request) throws AccessDeniedException {
//        HttpSession session = request.getSession();
//        Role.userAccount((User)session.getAttribute("user"),id);
//
//        return userRepository.getUserById(id);
//    }
//
//    @PostMapping
//    public String addUser(@RequestBody User user, HttpServletResponse response) throws IOException {
//        if (userRepository.presentUser(user)){
//            return "There is already a user under this username";
//        }
//        userRepository.addUser(user);
//        response.sendRedirect("/dashboard");
//        return "New user was successfully added";
//    }
//
//    @PutMapping("/{id}")
//    public String updateUser(@PathVariable Integer id, @RequestBody User user) {
//        if (userRepository.presentUser(user) && userRepository.checkPassword(user)) {
//            userRepository.updateUser(id, user);
//            return "Existing user was successfully updated";
//        }
//        else return "User was not updated! Either user under "+user.getUsername()+" does not exist or the password is wrong";
//    }
//    @DeleteMapping("/{id}")
//    public String deleteUser(@PathVariable int id, HttpServletRequest request) throws AccessDeniedException {
//        HttpSession session = request.getSession();
//        Role.userAccount((User)session.getAttribute("user"),id);
//        userRepository.deleteUser(id);
//        return "The chosen user was successfully deleted";
//    }
//
//}
//

package org.firstspringmigration.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.firstspringmigration.model.Role;
import org.firstspringmigration.model.User;
import org.firstspringmigration.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> getUsers(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            Role.hasRole((User) session.getAttribute("user"));
            List<User> users = userRepository.getUsers();
            return ResponseEntity.ok(users);
        } catch (AccessDeniedException e) {
            logger.error("Access denied when fetching users.", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            Role.userAccount((User) session.getAttribute("user"), id);
            User user = userRepository.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (AccessDeniedException e) {
            logger.error("Access denied when fetching user with id {}.", id, e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) throws IOException {
        if (userRepository.isUsernamePresent(user.getUsername())) {
            return ResponseEntity.badRequest().body("There is already a user under this username");
        }
        userRepository.addUser(user);
        logger.info("New user {} was successfully added.", user.getUsername());
        // Redirect to the dashboard using ResponseEntity with HttpStatus.FOUND
        return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/dashboard")
                .body("New user was successfully added");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Integer id, @RequestBody User user) {
        try {
            if (userRepository.isUsernamePresent(user.getUsername()) && userRepository.validateUserCredentials(user.getUsername(),user.getPassword())) {
                userRepository.updateUser(id, user);
                return ResponseEntity.ok("Existing user was successfully updated");
            } else {
                return ResponseEntity.badRequest()
                        .body("User was not updated! Either user under " + user.getUsername()
                                + " does not exist or the password is wrong");
            }
        } catch (Exception e) {
            logger.error("Error occurred while updating user with id {}.", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            Role.userAccount((User) session.getAttribute("user"), id);
            userRepository.deleteUser(id);
            return ResponseEntity.ok("The chosen user was successfully deleted");
        } catch (AccessDeniedException e) {
            logger.error("Access denied when deleting user with id {}.", id, e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
