package com.apple.assignment.flagpicker.model.mongo;

import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Document(collection = "country")
public class CountryEntity {
    @Id
    private String id;

    @NotEmpty
    private String countryName;

    @NotEmpty
    private String continentName;

    @NotEmpty
    private String flag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString(){
        return String.format("country[countryName='%s', flag='%s', continentName='%s']", countryName, flag, continentName);
    }
}
