package com.example.cache;


import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
@EnableCaching
public class RedisConfig{
	
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
    	RedisTemplate<String, Object> template = new RedisTemplate<>();  
    	// key的序列化采用StringRedisSerializer  解决redis中key乱码问题
    	template.setKeySerializer(new StringRedisSerializer());  
    	template.setHashKeySerializer(new StringRedisSerializer());  
  
        template.setConnectionFactory(redisConnectionFactory);  
        return template;  
    }

      
}
