package com.snitch.api.repository;

import com.snitch.entities.model.Snitch;
import org.springframework.data.repository.CrudRepository;

public interface SnitchRepository extends CrudRepository<Snitch, Long> {
}
