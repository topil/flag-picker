package com.apple.assignment.flagpicker.service;

import com.apple.assignment.flagpicker.model.response.Country;

import java.util.List;

public interface FlagPickerService {

    public List<Country> pickByContinent(String continentName);
    public List<Country> pickByCountry(String countryName);
}
