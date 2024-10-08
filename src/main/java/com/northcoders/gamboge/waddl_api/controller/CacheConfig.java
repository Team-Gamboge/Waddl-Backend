package com.northcoders.gamboge.waddl_api.controller;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CaffeineCache taskCache1() {
        return new CaffeineCache("taskCache1",
                Caffeine.newBuilder()
                        .expireAfterAccess(20, TimeUnit.SECONDS)
                        .build());
    }

    @Bean
    public CaffeineCache taskCache2() {
        return new CaffeineCache("taskCache2",
                Caffeine.newBuilder()
                        .expireAfterAccess(5, TimeUnit.SECONDS)
                        .build());
    }

}
