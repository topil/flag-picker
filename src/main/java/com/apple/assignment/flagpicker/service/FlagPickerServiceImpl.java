package com.apple.assignment.flagpicker.service;

import com.apple.assignment.flagpicker.GuavaConfig;
import com.apple.assignment.flagpicker.model.response.Country;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlagPickerServiceImpl implements FlagPickerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlagPickerServiceImpl.class);
    private CountryServiceFactory factory;

    @Autowired
    public FlagPickerServiceImpl(CountryServiceFactory factory){
        this.factory = factory;
    }


    @Override
    @Cacheable(GuavaConfig.COUNTRY_CACHE)
    public List<Country> pickByContinent(String continentName) {
        LOGGER.debug("missed cache, going to call getByContinentName...");
        CountryService svc = factory.getCountryService();
        return svc.getByContinentName(continentName);
    }

    @Override
    @Cacheable(GuavaConfig.COUNTRY_CACHE)
    public List<Country> pickByCountry(String countryName) {
        LOGGER.debug("missed cache, going to call getByCountryName...");
        CountryService svc = factory.getCountryService();
        return svc.getByCountryName(countryName);
    }



}
