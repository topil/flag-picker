package com.apple.assignment.flagpicker.model.mysql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Continent {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @NotNull
    private String name;

    private Set<Country> countries;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[continent: id = ").append(id)
                .append(", name = ").append(name)
                .append(", countries = [");

        for(Country c : countries){
            sb.append(c);
        }
        sb.append("]]");
        return sb.toString();

    }
}
