package com.apple.assignment.flagpicker.controller;

import com.apple.assignment.flagpicker.constant.ApiVersion;
import com.apple.assignment.flagpicker.error.EmptyResultException;
import com.apple.assignment.flagpicker.model.response.Country;
import com.apple.assignment.flagpicker.service.FlagPickerService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(ApiVersion.V1 + "/flag")
public class FlagController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlagController.class);
    private MeterRegistry meterRegistry;
    private FlagPickerService service;
    private Counter countryCounter;
    private Counter continentCounter;

    @Autowired
    public FlagController(FlagPickerService service, MeterRegistry meterRegistry){
        this.service = service;
        this.meterRegistry = meterRegistry;
        initCounters();
    }


    @RequestMapping(value = "/pick", method = GET)
    public List<Country> pickFlag(@RequestParam(name = "continent-name", required = false) String continentName,
                                  @RequestParam(name = "country-name", required = false) String countryName) throws Exception{

        LOGGER.debug("FlagController::pickFlag, continentName="+continentName + "; countryName=" + countryName);

        List<Country> list = null;
        if(continentName == null && countryName == null){
            LOGGER.error("neither continent-name nor country-name is provided. ");
            MissingServletRequestParameterException ex =
                    new MissingServletRequestParameterException("either continent-name or country-name", "String");

            throw ex;
        }else if(continentName != null && countryName != null){
            //TODO: requirement is not very clear about this. return the country that has continentName as input
            LOGGER.debug("pickFlag by continent-name & country-name ");
            list = service.pickByCountry(countryName).stream().filter(country -> continentName.equals(country.getContinentName())).collect(Collectors.toList());
            increaseCounter(countryCounter);
            increaseCounter(continentCounter);

        }else if(continentName != null){
            LOGGER.debug("pickFlag by continent-name only.");
            list = service.pickByContinent(continentName);
            increaseCounter(continentCounter);
        }else{
            LOGGER.debug("pickFlag by country-name only.");
            list = service.pickByCountry(countryName);
            increaseCounter(countryCounter);
        }

        if(CollectionUtils.isEmpty(list)){
            processEmpty(continentName, countryName);
        }
        return list;
    }

    private void processEmpty(String continentName, String countryName) throws EmptyResultException {
        StringBuilder sb = new StringBuilder();

        if(!StringUtils.isEmpty(continentName)){
            sb.append("continent-name: ").append(continentName).append(" ");
        }
        if(!StringUtils.isEmpty(countryName)){
            sb.append("country-name: ").append(countryName).append(" ");
        }

        throw new EmptyResultException(sb.toString());
    }

    private void increaseCounter(Counter counter){
        if(counter != null){
            counter.increment();
        }
    }

    private void initCounters() {
        countryCounter = Counter.builder("flag.picker")
                .tag("type", "country")
                .description("The number of queries by country-name")
                .register(meterRegistry);

        continentCounter = Counter.builder("flag.picker")
                .tag("type", "continent")
                .description("The number of queries by continent-name")
                .register(meterRegistry);
    }

}
