package com.apple.assignment.flagpicker.repository.mongo;

import com.apple.assignment.flagpicker.model.mongo.CountryEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@RunWith(SpringRunner.class)
public class CountryMongoRepositoryTest {

    @Autowired
    private CountryMongoRepository repo;


    @Test
    public void testSaveNQuery(){
        CountryEntity ce = new CountryEntity();
        ce.setCountryName("myCountry");
        ce.setFlag("myFlag");
        ce.setContinentName("myContinent");
        CountryEntity saved = repo.save(ce);

        List<CountryEntity> findByName = repo.findByCountryName("myCountry");
        Assert.assertTrue(findByName.size() == 1);

        List<CountryEntity> findByContinent = repo.findByContinentName("myContinent");
        Assert.assertTrue(findByContinent.size() == 1);
    }
}
