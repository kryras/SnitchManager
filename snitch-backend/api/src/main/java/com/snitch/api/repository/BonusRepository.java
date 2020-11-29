package com.snitch.api.repository;

import com.snitch.entities.model.Bonus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BonusRepository extends CrudRepository<Bonus, Long> {
}
