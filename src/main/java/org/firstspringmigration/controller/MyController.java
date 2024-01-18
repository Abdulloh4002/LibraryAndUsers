package org.firstspringmigration.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.firstspringmigration.model.User;
import org.firstspringmigration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping
public class MyController {

    @GetMapping("/home")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Welcome to the home page!");
    }

    @GetMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("It is the login page!");
    }

    @GetMapping("/dashboard")
    public ResponseEntity<String> dashboard(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(1800);

        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("/login");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not logged in. Redirecting to login page.");
        }

        return ResponseEntity.ok("You are now logged in " + user.getUsername());
    }

    @PostMapping("/login")
    public ResponseEntity<String> postMethod(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        if (!new UserRepository().isUsernamePresent(user.getUsername())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("There is no user under this username... ");
        }
        response.sendRedirect("/dashboard");
        return ResponseEntity.status(HttpStatus.FOUND).body("Redirecting to dashboard...");
    }
}
