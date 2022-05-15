package com.sivaguru.urlShortner.dao;

import com.sivaguru.urlShortner.model.Url;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface UrlRepository extends MongoRepository<Url, String> {

    public Url findByUrl(String url);
    public Url findByCustomShortUrl(String customShortUrl);
}
