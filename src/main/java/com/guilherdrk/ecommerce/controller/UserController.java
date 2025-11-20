package com.guilherdrk.ecommerce.controller;

import com.guilherdrk.ecommerce.dto.CreateUserDTO;
import com.guilherdrk.ecommerce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDTO createUserDTO){

        var user = userService.createUser(createUserDTO);

        return ResponseEntity.created(URI.create("/users/" + user.getUserId())).build();
    }
}
