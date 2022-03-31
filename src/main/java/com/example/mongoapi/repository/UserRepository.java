package com.example.mongoapi.repository;

import com.example.mongoapi.domain.User;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
    
    public Mono<Boolean> existsByEmail(String email);

    @Query("Select max(id) from users")
    public Mono<Long> findMaxId();

}
