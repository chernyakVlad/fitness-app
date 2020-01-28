package com.training.fitnessappserver.repository;

import com.training.fitnessappserver.entity.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByLogin(String login);
    Optional<User> findById(String id);
}
