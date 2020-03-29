package com.apple.assignment.flagpicker.service;


import com.apple.assignment.flagpicker.model.response.Country;

import java.util.List;

public interface CountryService {
    public List<Country> getByCountryName(String countryName);
    public List<Country> getByContinentName(String continentName);
    public String save(Country country);
    public boolean deleteById(String countryId);
    public boolean deleteByName(String countryName);
    public List<Country> getAll();
    public long count();

}
