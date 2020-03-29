package com.apple.assignment.flagpicker.service;

import com.apple.assignment.flagpicker.GuavaConfig;
import com.apple.assignment.flagpicker.model.mongo.CountryEntity;
import com.apple.assignment.flagpicker.repository.mongo.CountryMongoRepository;
import com.apple.assignment.flagpicker.model.response.Country;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryServiceMongoImpl implements CountryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryServiceMongoImpl.class);

    @Autowired
    private CountryMongoRepository countryRepository;

    @Override

    public List<Country> getByCountryName(String countryName) {
        List<CountryEntity> list = countryRepository.findByCountryName(countryName);
        return convert(list);
    }

    @Override

    public List<Country> getByContinentName(String continentName) {
        List<CountryEntity> list = countryRepository.findByContinentName(continentName);
        return convert(list);

    }

    @Override
    public String save(Country country) {
        CountryEntity entity = new CountryEntity();
        entity.setContinentName(country.getContinentName());
        entity.setCountryName(country.getName());
        entity.setFlag(country.getFlag());
        return countryRepository.save(entity).getId();
    }

    @Override
    public boolean deleteById(String countryId) {
        return false;
    }

    @Override
    public boolean deleteByName(String countryName) {
        return false;
    }

    @Override
    public List<Country> getAll() {
        List<CountryEntity> list = countryRepository.findAll();
        return convert(list);
    }

    @Override
    public long count() {
        return countryRepository.count();
    }

    private List<Country> convert(List<CountryEntity> list){
        return list.stream().map(e->{
            Country c = new Country();
            c.setName(e.getCountryName());
            c.setFlag(e.getFlag());
            c.setContinentName(e.getContinentName());
            return c;
        }).collect(Collectors.toList());
    }
}
