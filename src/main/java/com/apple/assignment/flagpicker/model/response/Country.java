package com.apple.assignment.flagpicker.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Country {
    private String name;
    private String flag;
    @JsonIgnore
    private String continentName;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }
}
