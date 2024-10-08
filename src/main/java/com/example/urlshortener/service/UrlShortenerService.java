package com.example.urlshortener.service;

import com.example.urlshortener.dto.UrlRequest;
import com.example.urlshortener.dto.UrlResponse;
import com.example.urlshortener.exception.ExistingUrlException;
import com.example.urlshortener.exception.UrlNotFoundException;
import com.example.urlshortener.repository.UrlRepository;
import com.example.urlshortener.util.Base10To62Converter;
import com.example.urlshortener.domain.Url;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerService {

	@Autowired
    private UrlRepository urlRepository;
	
    @Value("${url.shortener.domain}")
    private String domain;
	
    public String shortenUrl(UrlRequest urlRequest) {
    	if(urlRepository.findByOriginalUrl(urlRequest.getOriginalUrl()) != null) {
    		 throw new ExistingUrlException("Esta URL ya está registrada.");
    	}
    	
    	Url url = new Url();
    	url.setOriginalUrl(urlRequest.getOriginalUrl());
    	url.setEnabled(true);
    	
    	Url persistedUrl = urlRepository.save(url);

        String shortUrl = Base10To62Converter.encode(persistedUrl.getId());
        return domain + shortUrl;
    }

    // Primero revisa si existe la shortUrl buscada en cache. Sino, busca en la base de datos
    // Los resultados obtenidos de la BD se almacenan en la cache, a menos que el resutlado sea null
    @Cacheable(value = "shortURLs", key = "#shortUrl", unless = "#result == null")
    public String resolveUrl(String shortUrl) {
    	Url url = getUrl(shortUrl);
        
        if (!url.isEnabled()) {
            throw new IllegalStateException("Esta URL está deshabilitada.");
        }

        return url.getOriginalUrl();
    }

    public UrlResponse updateUrl(String shortUrl, UrlRequest urlRequest) {
        Url url = getUrl(shortUrl);

        url.setOriginalUrl(urlRequest.getOriginalUrl());
        urlRepository.save(url);

        return new UrlResponse(shortUrl, url.getOriginalUrl());
    }

    public void toggleUrlStatus(String shortUrl, boolean enabled) {
    	Url url = getUrl(shortUrl);

        url.setEnabled(enabled);
        urlRepository.save(url);
    }
    
    
    private Url getUrl(String shortUrl) {
    	Long id = Base10To62Converter.decode(shortUrl);
    	
        return urlRepository.findById(id)
                .orElseThrow(() -> new UrlNotFoundException("URL no encontrada"));
    }
    
    public List<Url> findAll() {
    	return urlRepository.findAll();
    }
}
