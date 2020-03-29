package com.apple.assignment.flagpicker.repository.mysql;

import com.apple.assignment.flagpicker.model.mysql.Continent;
import org.springframework.data.repository.CrudRepository;

public interface ContinentMySQLRepository extends CrudRepository<Continent, Integer> {

}
