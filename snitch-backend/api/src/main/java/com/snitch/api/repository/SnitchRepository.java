package com.snitch.api.repository;

import com.snitch.entities.model.Snitch;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SnitchRepository extends CrudRepository<Snitch, Long> {
    List<Snitch> findByOrderByDateDesc();
}
