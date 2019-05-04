package com.xust.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * session会话
 * @author: Luo Daiyang
 * @description:
 * @date Created in 19:37 2019/5/3
 * @modified By:
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400)
public class RedisSessionConfig {
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {

        return new StringRedisTemplate(factory);
    }
}
