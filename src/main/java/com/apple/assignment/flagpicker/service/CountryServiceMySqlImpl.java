package com.apple.assignment.flagpicker.service;

import com.apple.assignment.flagpicker.model.mysql.ContinentEntity;
import com.apple.assignment.flagpicker.model.mysql.CountryEntity;
import com.apple.assignment.flagpicker.model.response.Country;
import com.apple.assignment.flagpicker.repository.mysql.ContinentMySQLRepository;
import com.apple.assignment.flagpicker.repository.mysql.CountryMySQLRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class CountryServiceMySqlImpl implements CountryService {

    @Autowired
    private CountryMySQLRepository countryRepo;

    @Autowired
    private ContinentMySQLRepository continentRepo;


    @Override
    public List<Country> getByCountryName(String countryName) {
        List<CountryEntity> list = countryRepo.findByName(countryName);
        return convert(list);
    }

    @Override
    public List<Country> getByContinentName(String continentName) {
        ContinentEntity continent = continentRepo.findByName(continentName);
        List<CountryEntity> list = countryRepo.findByContinent(continent);
        return convert(list);

    }

    @Override
    public String save(Country country) {
        ContinentEntity countinentEntity = continentRepo.findByName(country.getContinentName());
        CountryEntity ce = new CountryEntity(country.getName(), country.getFlag(),countinentEntity);
        CountryEntity saved = (CountryEntity) countryRepo.save(ce);
        int id = saved.getId();
        return String.valueOf(id);
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
        return null;
    }

    @Override
    public long count() {
        return countryRepo.count();
    }


    private List<Country> convert(List<com.apple.assignment.flagpicker.model.mysql.CountryEntity> list){
        return list.stream().map(e->{
            Country c = new Country();
            c.setName(e.getName());
            c.setFlag(e.getFlag());
            c.setContinentName(e.getContinent().getName());
            return c;
        }).collect(Collectors.toList());
    }
}
