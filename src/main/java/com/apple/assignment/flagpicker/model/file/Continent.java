package com.apple.assignment.flagpicker.model.file;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Continent {
    @JsonProperty("continent")
    private String continentName;

    private List<Country> countries;

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
