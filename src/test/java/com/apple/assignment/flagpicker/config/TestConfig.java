package com.apple.assignment.flagpicker.config;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@ComponentScan({

})
@AutoConfigurationPackage
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class TestConfig {
}
