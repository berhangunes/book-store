package com.readingisgood.bookstore.config;


import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;



@Configuration
@EnableCaching
public class RedisConfiguration {

    public static final String BOOKS_CACHE = "Books";
    public static final String ORDER_CACHE = "Order";
    public static final String USER_CACHE = "User";
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        redisClusterConfiguration.clusterNode("http://127.0.0.1",6379);
        return new LettuceConnectionFactory();
    }
    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        return template;
    }
    @Bean
    public RedisCacheManager cacheManager() {
        return RedisCacheManager.builder(redisConnectionFactory())
                .withInitialCacheConfigurations(cacheConfiguration())
                .transactionAware()
                .build();
    }
    private Map<String , RedisCacheConfiguration> cacheConfiguration(){
        Map<String, RedisCacheConfiguration> cacheConfigurationMap = new HashMap<>();
        RedisCacheConfiguration booksMap = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(20))
                .disableCachingNullValues();
        RedisCacheConfiguration orderMap = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(10))
                .disableCachingNullValues();
        RedisCacheConfiguration userMap = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(10))
                .disableCachingNullValues();
        cacheConfigurationMap.put(BOOKS_CACHE, booksMap);
        cacheConfigurationMap.put(ORDER_CACHE, orderMap);
        cacheConfigurationMap.put(USER_CACHE, userMap);
        return cacheConfigurationMap;
    }
}

