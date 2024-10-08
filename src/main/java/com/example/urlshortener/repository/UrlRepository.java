package com.example.urlshortener.repository;

import com.example.urlshortener.domain.Url;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlRepository extends CrudRepository<Url, Long> {
	Url findByOriginalUrl(String originalUrl);
    List<Url> findAll();
}
