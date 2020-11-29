package com.snitch.api.repository;

import com.snitch.entities.model.SnitchType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SnitchTypeRepository extends CrudRepository<SnitchType, Long> {
}
