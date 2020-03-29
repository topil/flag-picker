package com.apple.assignment.flagpicker.repository.mongo;


import com.apple.assignment.flagpicker.model.mongo.CountryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface CountryMongoRepository extends MongoRepository<CountryEntity, String> {
    public List<CountryEntity> findByCountryName(String countryName);
    public List<CountryEntity> findByContinentName(String continentName);



}
