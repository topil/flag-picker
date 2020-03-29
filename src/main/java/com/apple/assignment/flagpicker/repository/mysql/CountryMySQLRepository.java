package com.apple.assignment.flagpicker.repository.mysql;

import com.apple.assignment.flagpicker.model.mysql.ContinentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryMySQLRepository<CountryEntity, Integer> extends CrudRepository<CountryEntity, Integer> {
    List<CountryEntity> findByName(String name);
    List<CountryEntity> findByContinent(ContinentEntity continent);

}
