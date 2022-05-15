package com.sivaguru.urlShortner.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sivaguru.urlShortner.dao.UrlRepository;
import com.sivaguru.urlShortner.model.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.LocalDateTime;

@EnableMongoRepositories(basePackageClasses = UrlRepository.class)
@Configuration
public class MongoDbConfig {

    @Bean
    CommandLineRunner commandLineRunner(UrlRepository urlRepository) {
        Url url = Url.builder().id("0").url("https://www.testTiny.com/").createdOn(LocalDateTime.now()).customShortUrl("test").build();
        return strings -> {
            urlRepository.save(url);
        };
    }

}
