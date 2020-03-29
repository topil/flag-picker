package com.apple.assignment.flagpicker;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableCaching
public class FlagPickerApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlagPickerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(FlagPickerApplication.class, args);
    }




}
