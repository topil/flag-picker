package com.apple.assignment.flagpicker.controller;

import com.apple.assignment.flagpicker.model.response.Country;
import com.apple.assignment.flagpicker.service.FlagPickerService;
import io.micrometer.core.instrument.MeterRegistry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FlagController.class)
@RunWith(SpringRunner.class)
public class FlagControllerTest {
    @MockBean
    private FlagPickerService service;

    @MockBean
    private MeterRegistry meterRegistry;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPickFlagByCountryName() throws Exception {
        List<Country> countries = new ArrayList<>();
        Country c = new Country();
        c.setName("myCountry");
        c.setFlag("myFlag");
        c.setContinentName("myContinent");
        countries.add(c);
        when(service.pickByCountry("myCountry")).thenReturn(countries);
        this.mockMvc.perform(get("/v1.0/flag/pick?country-name=myCountry")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("myFlag")));
    }

    @Test
    public void testPickFlagByContinentName() throws Exception {
        List<Country> countries = new ArrayList<>();
        Country c1 = new Country();
        c1.setName("myCountry1");
        c1.setFlag("myFlag1");
        c1.setContinentName("myContinent");

        Country c2 = new Country();
        c2.setName("myCountry2");
        c2.setFlag("myFlag2");
        c2.setContinentName("myContinent");

        countries.add(c1);
        countries.add(c2);

        when(service.pickByContinent("myContinent")).thenReturn(countries);
        this.mockMvc.perform(get("/v1.0/flag/pick?continent-name=myContinent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("myFlag1")))
                .andExpect(content().string(containsString("myFlag2")));
    }
}
