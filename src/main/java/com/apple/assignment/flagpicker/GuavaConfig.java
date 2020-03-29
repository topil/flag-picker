package com.apple.assignment.flagpicker;

import com.apple.assignment.flagpicker.config.FlagPickerCacheConf;
import com.apple.assignment.flagpicker.config.StoreStrategyConf;
import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class GuavaConfig implements CachingConfigurer {
    public static final String COUNTRY_CACHE = "CountryCache";
    @Autowired
    private FlagPickerCacheConf conf;

    private static final Logger LOGGER = LoggerFactory.getLogger(GuavaConfig.class);
    @Bean
    @Override
    public CacheManager cacheManager() {
        LOGGER.info("Initializing Guava Cache manager.");
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        GuavaCache cacheOneHour = new GuavaCache(COUNTRY_CACHE, CacheBuilder.newBuilder()
                .expireAfterWrite(conf.getTtl(), TimeUnit.MINUTES)
                .build());



        cacheManager.setCaches(Arrays.asList(cacheOneHour));

        return cacheManager;
    }

    @Override
    public CacheResolver cacheResolver() {
        return null;
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return null;
    }
}

