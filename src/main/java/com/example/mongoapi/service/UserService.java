package com.example.mongoapi.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.example.mongoapi.domain.User;
import com.example.mongoapi.domain.UserDTO;
import com.example.mongoapi.domain.UserExistsException;
import com.example.mongoapi.domain.UserNotFoundException;
import com.example.mongoapi.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    private Environment environment;

    @Autowired
    private UserRepository userRepository;

    public Mono<User> getUserById(String id) {
        return userRepository.findById(id)
            .switchIfEmpty(Mono.error(new UserNotFoundException()));
    }

    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Mono<String> deleteUserById(String id) {
        return userRepository.deleteById(id)
            .thenReturn(id)
            .switchIfEmpty(Mono.error(new UserNotFoundException()));
    }

    public Mono<User> createUser(UserDTO userDTO) {
        return userRepository.existsByEmail(userDTO.getEmail())
            .flatMap(exists -> {
                if(exists) {
                    throw new UserExistsException();
                }
                User newUser = userDTO.getUser();
                return userRepository.save(newUser);
            });
    }

    public Mono<User> updateUser(String id, UserDTO userDTO) {
        return userRepository.findById(id)
            .flatMap(existingUser -> {
                User updatedUser = userDTO.updateUser(existingUser);
                return userRepository.save(updatedUser);
            }).switchIfEmpty(Mono.error(UserNotFoundException::new));
    }

    private boolean isDev() {
        return Arrays.asList(environment.getActiveProfiles()).contains("dev");
    }

    @PostConstruct
    public void initData() throws IOException {
        if(isDev()) {
            File file = new File("data/users.json");
            List<User> users = new ObjectMapper().readValue(file, new TypeReference<List<User>>() {});
            userRepository.saveAll(users).blockLast();
        }
    }

    @PreDestroy
    public void destroyData() {
        if(isDev()) {
            userRepository.deleteAll().block();
        }
    }
    
}
