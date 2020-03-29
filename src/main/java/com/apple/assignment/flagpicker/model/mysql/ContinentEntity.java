package com.apple.assignment.flagpicker.model.mysql;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "continents")
public class ContinentEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "continent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CountryEntity> countries;

    public ContinentEntity(String name){
        this.name = name;
    }

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

    public List<CountryEntity> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryEntity> countries) {
        this.countries = countries;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[continent: id = ").append(id)
                .append(", name = ").append(name)
                .append(", countries = [");

        for(CountryEntity c : countries){
            sb.append(c);
        }
        sb.append("]]");
        return sb.toString();

    }
}
