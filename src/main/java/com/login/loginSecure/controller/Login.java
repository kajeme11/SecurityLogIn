package com.login.loginSecure.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Login {

    @GetMapping("/hello")
    public ResponseEntity<String> hi(){
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/hi")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello again");
    }

    @GetMapping("/contact")
    public ResponseEntity<String> contact(){
        return ResponseEntity.ok("Contact emdpoint");
    }


}
