package com.apple.assignment.flagpicker.model.mysql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String flag;

    @NotNull
    private Continent continent;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[country:  id = ").append(id)
                .append(", name = ").append(name)
                .append(", flag = ").append(flag)
                .append(", continent = ").append(continent)
                .append("]");

        return sb.toString();
    }
}
