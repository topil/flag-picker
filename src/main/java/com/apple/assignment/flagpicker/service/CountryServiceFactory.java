package com.apple.assignment.flagpicker.service;

import com.apple.assignment.flagpicker.config.StoreStrategyConf;
import com.apple.assignment.flagpicker.repository.mongo.CountryMongoRepository;
import com.apple.assignment.flagpicker.repository.mysql.CountryMySQLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountryServiceFactory {


    @Autowired
    private StoreStrategyConf conf;

    @Autowired
    private CountryServiceMongoImpl mongoSvc;

//    @Autowired
//    private CountryServiceMySqlImpl mysqlSvc;



    public CountryService getCountryService(){
        if(conf.getType() == null){
            throw new IllegalArgumentException("storage type is missing! " );
        }

        if("mongo".equals(conf.getType())){
            return mongoSvc;
        }
//        else if("mysql".equals(conf.getType())){
//            return mysqlSvc;
//        }
        else{
            throw new IllegalArgumentException("Invalid storage type: " + conf.getType());
        }
    }

}
