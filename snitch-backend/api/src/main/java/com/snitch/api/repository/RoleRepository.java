package com.snitch.api.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.snitch.entities.model.Role;

import enums.ERole;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> getByName(ERole name);
    boolean existsByName(ERole name);
}
