package com.apple.assignment.flagpicker.repository.mysql;

import com.apple.assignment.flagpicker.model.mysql.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryMySQLRepository extends CrudRepository<Country, Integer> {

}
