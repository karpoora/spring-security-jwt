package com.plantwoo.security.controller;

import com.plantwoo.security.model.User;
import com.plantwoo.security.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("/secure/test-login")
    public ResponseEntity<String> testLoginAPI(){

        return new ResponseEntity<String>("test Completed", HttpStatus.ACCEPTED);
    }


    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody User user){
        return new ResponseEntity<String>(loginService.validateCredential(user), HttpStatus.ACCEPTED);
    }
}
