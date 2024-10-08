package com.example.urlshortener.controller;

import com.example.urlshortener.domain.Url;
import com.example.urlshortener.dto.UrlRequest;
import com.example.urlshortener.dto.UrlResponse;
import com.example.urlshortener.service.StatisticsService;
import com.example.urlshortener.service.StreamPublisher;
import com.example.urlshortener.service.UrlShortenerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/url")
public class URLShortenerController {

	@Autowired
    private UrlShortenerService urlShorteningService;
	
	@Autowired
	private StatisticsService statisticsService;
	
	@Autowired
	private StreamPublisher streamPublisher;

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody UrlRequest urlRequest) {
    	String response = urlShorteningService.shortenUrl(urlRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/resolve/{shortUrl}")
    public ResponseEntity<String> resolveUrl(@PathVariable String shortUrl) {
    	streamPublisher.publishUrlAccessEvent(shortUrl);
        String originalUrl = urlShorteningService.resolveUrl(shortUrl);
//        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(originalUrl)).build(); // Por problemas de redirección (en postman) desactivo esta respuesta
        return ResponseEntity.ok(originalUrl);
    }

    @PutMapping("/update/{shortUrl}")
    public ResponseEntity<UrlResponse> updateUrl(@PathVariable String shortUrl, @RequestBody UrlRequest urlRequest) {
        UrlResponse response = urlShorteningService.updateUrl(shortUrl, urlRequest);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/status/{shortUrl}")
    public ResponseEntity<Void> toggleUrlStatus(@PathVariable String shortUrl, @RequestParam boolean enabled) {
        urlShorteningService.toggleUrlStatus(shortUrl, enabled);
        return ResponseEntity.noContent().build();
    }
    
    
    @GetMapping("/statistics/{shortUrl}")
    public ResponseEntity<Integer> getUrlStatistics(@PathVariable String shortUrl) {
        int accessCount = statisticsService.getAccessCount(shortUrl);
        return ResponseEntity.ok(accessCount);
    }
    
    // Por propósitos de testing
    @GetMapping("/test/getAllUrls")
    public ResponseEntity<List<Url>> findAll() {
        List<Url> res = urlShorteningService.findAll();
        return ResponseEntity.ok(res);
    }
    
}

