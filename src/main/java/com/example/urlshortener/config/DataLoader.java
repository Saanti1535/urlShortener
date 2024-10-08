package com.example.urlshortener.config;

import com.example.urlshortener.domain.Url;
import com.example.urlshortener.repository.UrlRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

//    @Bean
//    public CommandLineRunner loadData(UrlRepository urlRepository) {
//        return args -> {
//            // Datos de prueba para insertar al iniciar la aplicación
//            urlRepository.save(new Url("https://www.google.com", "goog", true));
//            urlRepository.save(new Url("https://www.facebook.com", "fb", true));
//            urlRepository.save(new Url("https://www.twitter.com", "tw", false));
//            urlRepository.save(new Url("https://www.example.com", "rKKst", false));
//            urlRepository.save(new Url("https://www.fake.com", "1at2w", true));
//            urlRepository.save(new Url("https://www.mock.com", "4Rs2kk", false));
//        };
//    }
}
