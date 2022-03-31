package com.example.mongoapi.controller;

import com.example.mongoapi.domain.User;
import com.example.mongoapi.domain.UserDTO;
import com.example.mongoapi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/users/{id}")
    public Mono<User> getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/users")
    public Flux<User> getUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/users/{id}")
    public Mono<Long> deleteUser(@PathVariable Long id) {
        return userService.deleteUserById(id);
    }

    @PostMapping("/users")
    public Mono<User> createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);   
    }

    @PutMapping("/users/{id}")
    public Mono<User> updateUser(
        @PathVariable Long id,
        @RequestBody UserDTO userDTO
    ) {
        return userService.updateUser(id, userDTO);
    }

}
