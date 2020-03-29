package com.apple.assignment.flagpicker.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "mongo")
@ComponentScan({
        "com.apple.assignment.flagpicker"
})
@EnableMongoRepositories(basePackages = "com.apple.assignment.flagpicker.repository.mongo")
@AutoConfigurationPackage
@SpringBootConfiguration
public class MongoConfig {
}
