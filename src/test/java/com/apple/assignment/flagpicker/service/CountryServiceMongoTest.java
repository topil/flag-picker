package com.apple.assignment.flagpicker.service;

import com.apple.assignment.flagpicker.config.TestConfig;
import com.apple.assignment.flagpicker.model.mongo.CountryEntity;
import com.apple.assignment.flagpicker.model.response.Country;
import com.apple.assignment.flagpicker.repository.mongo.CountryMongoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
@ContextConfiguration(classes = {TestConfig.class})
@RunWith(SpringRunner.class)
public class CountryServiceMongoTest {

    @InjectMocks
    CountryServiceMongoImpl svc;

    @Mock
    private CountryMongoRepository repo;

    @Test
    public void testSave(){
        Country c = new Country();

        CountryEntity ce = new CountryEntity();
        ce.setId("12345");
        ce.setCountryName("myCountry");
        ce.setFlag("myFlag");
        ce.setContinentName("myContinent");

        when(repo.save(Mockito.any())).thenReturn(ce);
        String id = svc.save(c);
        Assert.assertTrue(!StringUtils.isEmpty(id));
    }

    @Test
    public void testFindByContinent(){

        CountryEntity ce1 = new CountryEntity();
        ce1.setId("11111");
        ce1.setCountryName("myCountry1");
        ce1.setFlag("myFlag1");
        ce1.setContinentName("myContinent");

        CountryEntity ce2 = new CountryEntity();
        ce2.setId("22222");
        ce2.setCountryName("myCountry2");
        ce2.setFlag("myFlag2");
        ce2.setContinentName("myContinent");

        List<CountryEntity> list = new ArrayList<>();
        list.add(ce1);
        list.add(ce2);

        when(repo.findByContinentName(Mockito.anyString())).thenReturn(list);
        List<Country> respList = svc.getByContinentName("myContinent");
        Assert.assertTrue(respList.size() == 2);

    }


    @Test
    public void testFindByCountry(){

        CountryEntity ce1 = new CountryEntity();
        ce1.setId("11111");
        ce1.setCountryName("myCountry1");
        ce1.setFlag("myFlag1");
        ce1.setContinentName("myContinent");

        List<CountryEntity> list = new ArrayList<>();
        list.add(ce1);

        when(repo.findByCountryName(Mockito.anyString())).thenReturn(list);
        List<Country> respList = svc.getByCountryName("myCountry1");
        Assert.assertTrue(respList.size() == 1);

    }

}
