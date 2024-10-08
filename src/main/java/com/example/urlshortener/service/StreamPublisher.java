package com.example.urlshortener.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StreamPublisher {

    private final StringRedisTemplate redisTemplate;

    public StreamPublisher(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void publishUrlAccessEvent(String shortUrl) {
        Map<String, String> message = new HashMap<>();
        message.put("shortUrl", shortUrl);
        message.put("timestamp", String.valueOf(System.currentTimeMillis()));

        redisTemplate.opsForStream().add("url_access_stream", message);
    }
}
