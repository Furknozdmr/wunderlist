package com.furkanozdemir.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.furkanozdemir.common.CacheTTL;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Setter
@Configuration
@EnableCaching
public class RedisConfiguration {

    @Value(value = "${spring.data.redis.host:redis}")
    private String host;

    @Value(value = "${spring.data.redis.port:6379}")
    private Integer port;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory(new RedisStandaloneConfiguration(host,port));

    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());

        var objectMapper = createObjectMapper();
        var jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer(objectMapper);

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;

    }

    @Bean("cache-manager")
    public RedisCacheManager redisCacheManager(RedisTemplate<String, Object> redisTemplate) {
        var redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(Objects.requireNonNull(redisTemplate.getConnectionFactory()));
        var redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                                                             .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                                                                     redisTemplate.getValueSerializer()));

        //@formatter:off
        var cacheConfigurationMap =
                Arrays.stream(CacheTTL.values())
                      .collect(Collectors.toMap(CacheTTL::getCacheName, cacheTTL ->
                              RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofDays(cacheTTL.getDuration()))
                                                     .serializeValuesWith(RedisSerializationContext
                                                                                  .SerializationPair
                                                                                  .fromSerializer(redisTemplate.getValueSerializer()))));
        //@formatter:on

        return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration, cacheConfigurationMap);

    }

    private ObjectMapper createObjectMapper() {
        var objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL,
                                           JsonTypeInfo.As.WRAPPER_ARRAY);
        return objectMapper;

    }

}
