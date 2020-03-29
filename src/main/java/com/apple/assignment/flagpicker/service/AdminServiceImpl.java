package com.apple.assignment.flagpicker.service;

import com.apple.assignment.flagpicker.model.file.Continent;
import com.apple.assignment.flagpicker.model.response.Country;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);
    private static ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private CountryServiceFactory factory;

    @Override
    public String loadData() {
        CountryService svc = factory.getCountryService();
        if(svc.count() > 0)
            return "already has data.";


        try {
            File f =  new ClassPathResource("continents.txt").getFile();
            List<Continent> list = MAPPER.readValue(f, new TypeReference<List<Continent>>() {
            });
            for(Continent continent : list){
                for(com.apple.assignment.flagpicker.model.file.Country country: continent.getCountries()){
                    Country c = new Country();
                    c.setContinentName(continent.getContinentName());
                    c.setName(country.getName());
                    c.setFlag(country.getFlag());
                    String id = svc.save(c);
                    LOGGER.debug("load country into db. id: " + id);
                }

            }

            long cnt = svc.count();
            LOGGER.debug("load total: " + cnt + " countries into db." );
            return "load total: " + cnt + " countries into db.";
        }catch(Exception ex){
            LOGGER.error("failed to load data into db. ", ex);
            return "got exception while loading data into db. " + ex.getLocalizedMessage();
        }
    }


}
