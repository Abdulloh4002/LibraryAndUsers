package org.sessionproject.sesion;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping
public class MyController {

    @GetMapping
    public void home(HttpServletRequest request, HttpServletResponse response) throws IOException{

    }

    @GetMapping("/login")
    public String login(){
        return "It is the login page!";
    }

    @GetMapping("/dashboard")
    public User dashboard(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if(user != null)
            return user;
        return new User(2, "dash","imron");
    }
    @PostMapping("/login")
    public void postMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute("user",new User(1, "abdulloh", "password"));
        request.getSession().setMaxInactiveInterval(5);
        response.sendRedirect("/dashboard");
    }

}
