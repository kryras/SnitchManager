package com.snitch.api.repository;

import java.util.Optional;

import com.snitch.entities.model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> getByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
