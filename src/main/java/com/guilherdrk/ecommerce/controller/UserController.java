package com.guilherdrk.ecommerce.controller;

import com.guilherdrk.ecommerce.dto.CreateUserDTO;
import com.guilherdrk.ecommerce.entities.UserEntity;
import com.guilherdrk.ecommerce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

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

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserEntity> findById(@PathVariable("userId") UUID userId){
        var user =userService.findById(userId);
        return user.isPresent() ?
                ResponseEntity.ok(user.get()) :
                ResponseEntity.notFound().build();

    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<UserEntity> deleteById(@PathVariable("userId") UUID userId){
        var deleted =userService.deleteById(userId);
        return deleted ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();

    }

}
