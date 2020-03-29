package com.apple.assignment.flagpicker.model.mysql;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "countries")
public class CountryEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String flag;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private ContinentEntity continent;


    public CountryEntity(String name, String flag, ContinentEntity ce){
        this.name = name;
        this.flag = flag;
        this.continent = ce;
    }
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

    public ContinentEntity getContinent() {
        return continent;
    }

    public void setContinent(ContinentEntity continent) {
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
