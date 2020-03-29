package com.apple.assignment.flagpicker.repository.mysql;

import com.apple.assignment.flagpicker.model.mysql.ContinentEntity;
import org.springframework.data.repository.CrudRepository;

public interface ContinentMySQLRepository extends CrudRepository<ContinentEntity, Integer> {
    public ContinentEntity findByName(String name);

}
