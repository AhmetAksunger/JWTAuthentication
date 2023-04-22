package com.ahmetaksunger.springsecuritycourse.controller;

import com.ahmetaksunger.springsecuritycourse.entity.User;
import com.ahmetaksunger.springsecuritycourse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class MyController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/index")
    public String index(){
        return "Index page";
    }

    @GetMapping("/dashboard")
    public String dashboard(){
        return "Dashboard";
    }

    @GetMapping("/admin")
    public String admin(){
        return "Admin";
    }

    @GetMapping("/number/{num}")
    public String number(@PathVariable("num") int num){
        return Integer.toString(num);
    }
    @GetMapping("/getprofile")
    public ResponseEntity<String> getProfile(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // retrieve user information from database or any other source
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user.toString());
    }


}
