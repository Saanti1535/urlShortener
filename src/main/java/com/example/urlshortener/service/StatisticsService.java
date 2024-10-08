package com.example.urlshortener.service;

import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@Service
public class StatisticsService implements StreamListener<String, MapRecord<String, String, String>> {
    private final Map<String, Integer> accessCounts = new ConcurrentHashMap<>();

    @Override
    public void onMessage(MapRecord<String, String, String> record) {
        // Recupera la URL corta del evento y actualiza el contador de accesos
        String shortUrl = record.getValue().get("shortUrl");
        accessCounts.put(shortUrl, accessCounts.getOrDefault(shortUrl, 0) + 1);
    }

    public int getAccessCount(String shortUrl) {
        // Devuelve el número de accesos para una URL en particular
        return accessCounts.getOrDefault(shortUrl, 0);
    }
}
