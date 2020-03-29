package com.apple.assignment.flagpicker.service;

import com.apple.assignment.flagpicker.model.response.Country;

import java.util.List;

public class CountryServiceMySqlImpl implements CountryService {

    @Override
    public List<Country> getByCountryName(String countryName) {
        return null;
    }

    @Override
    public List<Country> getByContinentName(String continentName) {
        return null;
    }

    @Override
    public String save(Country country) {
        return null;
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
        return 0;
    }
}
